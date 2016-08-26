package cn.bestkeep.android.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.jakewharton.rxbinding.view.RxView;

import org.xutils.x;

import java.util.concurrent.TimeUnit;


public abstract class RecyclerViewHolder<E> extends RecyclerView.ViewHolder {
    protected E data;

    public RecyclerViewHolder(final View itemView) {
        super(itemView);
        x.view().inject(this, itemView);

        RxView.clicks(itemView)
                .throttleFirst(2000, TimeUnit.MILLISECONDS)
                .subscribe(aVoid -> onClick(itemView));
    }

    public E data() {
        return data;
    }

    public final void display(E data) {
        this.data = data;
        display();
    }

    public abstract void display();

    public void onClick(View view) {
    }

}
