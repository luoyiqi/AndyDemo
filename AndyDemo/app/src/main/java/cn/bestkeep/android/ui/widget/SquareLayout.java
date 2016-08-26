package cn.bestkeep.android.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;


public class SquareLayout extends RelativeLayout
{
    private boolean hasBeenCalculated = false;
    private int widthMeasureSpec;
    private int heightMeasureSpec;

    public SquareLayout(Context context)
    {
        super(context);
    }

    public SquareLayout(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public SquareLayout(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        calculate(widthMeasureSpec, heightMeasureSpec);
        super.onMeasure(this.widthMeasureSpec, this.heightMeasureSpec);
    }

    private void calculate(int widthMeasureSpec, int heightMeasureSpec)
    {
        if (hasBeenCalculated)
        {
            return;
        }
        // For simple implementation, or internal size is always 0.
        // We depend on the container to specify the layout size of
        // our view. We can't really know what it is since we will be
        // adding and removing different arbitrary views and do not
        // want the layout to change as this happens.
        setMeasuredDimension(getDefaultSize(0, widthMeasureSpec), getDefaultSize(0, heightMeasureSpec));


        // Children are just made to fill our space.
        int childWidthSize = getMeasuredWidth();
        int childHeightSize = getMeasuredHeight();

        //高度和宽度一样
        this.heightMeasureSpec = this.widthMeasureSpec = MeasureSpec.makeMeasureSpec(childWidthSize, MeasureSpec.EXACTLY);
        hasBeenCalculated = true;
    }
}
