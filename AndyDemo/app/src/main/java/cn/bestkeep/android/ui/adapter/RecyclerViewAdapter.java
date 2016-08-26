package cn.bestkeep.android.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public abstract class RecyclerViewAdapter<E> extends RecyclerView.Adapter<RecyclerViewHolder<E>>
{
    private List<E> dataList = new ArrayList<>();

    public RecyclerViewAdapter()
    {
    }

    public RecyclerViewAdapter(List<E> dataList)
    {
        changeData(dataList);
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = getItemView(parent, viewType);
        return buildViewHolder(view, viewType);
    }

    protected abstract RecyclerViewHolder buildViewHolder(View view, int viewType);

    /*
    *
    * 以下两个方法二选一重写
    * getItemViewLayout - 返回布局id
    * getItemView - 返回View
    */
    protected int getItemViewLayout(int viewType)
    {
        return -1;
    }

    protected View getItemView(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(getItemViewLayout(viewType), parent, false);
        return view;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position)
    {
        if (get(position) == null)
        {
            return;
        }
        holder.display(get(position));
    }

    public E get(int position)
    {
        if (position < 0 || position >= dataList.size())
        {
            return null;
        }
        return dataList.get(position);
    }

    public void setData(List<E> dataList)
    {
        for (E data : dataList)
        {
            setData(data);
        }
    }

    public void setData(E data)
    {
        int index = dataList.indexOf(data);
        if (index >= 0)
        {
            dataList.set(index, data);
            notifyItemChanged(index);
            return;
        }
        dataList.add(data);
        notifyItemInserted(dataList.size() - 1);
    }

    public void add(E data)
    {
        add(dataList.size(), data);
    }

    public void add(int position, E data)
    {
        dataList.add(position, data);
        notifyItemInserted(position);
    }

    public void remove(E data)
    {
        int index = dataList.indexOf(data);
        if (index < 0)
        {
            return;
        }
        remove(index);
    }

    public void remove(int position)
    {
        dataList.remove(position);
        notifyItemRemoved(position);
    }

    public void changeData(List<E> dataList)
    {
        this.dataList.clear();
        if (dataList != null && dataList.size() > 0)
        {
            this.dataList.addAll(dataList);
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount()
    {
        return getListSize();
    }

    public int getListSize()
    {
        return dataList.size();
    }
}
