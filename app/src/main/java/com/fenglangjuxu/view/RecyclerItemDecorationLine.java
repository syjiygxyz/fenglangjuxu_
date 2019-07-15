package com.fenglangjuxu.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.fenglangjuxu.R;

/**
 * @author syj
 * @date 2019/7/3
 */
public class RecyclerItemDecorationLine extends RecyclerView.ItemDecoration {
    private Context mContext;
    private Paint linePaint;
    private int mLineHeight;
    public RecyclerItemDecorationLine(Context context) {
        this.mContext = context;
        this.linePaint = new Paint();
        linePaint.setColor(mContext.getResources().getColor(R.color.commonBg));
        mLineHeight = mContext.getResources().getDimensionPixelSize(R.dimen.item_divider_line_width);
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.set(0, 0, 0, mLineHeight);
    }

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);
        final int left = parent.getPaddingLeft();
        final int right = parent.getRight() - parent.getPaddingRight();
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount-1; i++){
            final  View child = parent.getChildAt(i);
            final  int top = child.getBottom();
            final int bottom = top + mLineHeight;
            c.drawRect(left, top, right, bottom, linePaint);
        }
    }
}
