package com.parting_soul.recyclerviewdemo.item_decor;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author parting_soul
 * @date 2022/1/23
 */
public class CustomGridDividerItemDecoration extends RecyclerView.ItemDecoration {
    private int mOrientation;
    private Context context;
    private Drawable mDrawable;

    public CustomGridDividerItemDecoration(Context context, int orientation) {
        this.mOrientation = orientation;
        this.context = context;
    }

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);
        drawHorizontal(c, parent);
        drawVertical(c, parent);
    }

    private void drawHorizontal(Canvas c, RecyclerView parent) {
        int childCount = parent.getChildCount();
        int left = 0;
        int top = 0;
        View child;
        RecyclerView.LayoutParams params;
        for (int i = 0; i < childCount; i++) {
            child = parent.getChildAt(i);
            params = (RecyclerView.LayoutParams) child.getLayoutParams();
            if (!isLastRow(parent, i)) {
                left = child.getLeft();
                top = child.getBottom() + params.bottomMargin;
            }
            mDrawable.setBounds(left, top, left + child.getWidth() + mDrawable.getIntrinsicWidth(),
                    top + mDrawable.getIntrinsicHeight());
            mDrawable.draw(c);
        }
    }

    private void drawVertical(Canvas c, RecyclerView parent) {
        int childCount = parent.getChildCount();
        int left = 0;
        int top = 0;
        View child;
        RecyclerView.LayoutParams params;
        for (int i = 0; i < childCount; i++) {
            child = parent.getChildAt(i);
            params = (RecyclerView.LayoutParams) child.getLayoutParams();
            if (!isLastColumn(parent, i)) {
                left = child.getRight() + params.rightMargin;
                top = child.getTop();
            }
            mDrawable.setBounds(left, top, left + mDrawable.getIntrinsicWidth(),
                    top + child.getHeight());
            mDrawable.draw(c);
        }
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int position = ((RecyclerView.LayoutParams) view.getLayoutParams()).getViewLayoutPosition();
        if (isLastColumn(parent, position)) {
            // 第spanCount - 1列
            outRect.set(0, 0, 0, mDrawable.getIntrinsicHeight());
        } else if (isLastRow(parent, position)) {
            // 最后一行
            outRect.set(0, 0, mDrawable.getIntrinsicWidth(), 0);
        } else {
            outRect.set(0, 0, mDrawable.getIntrinsicWidth(), mDrawable.getIntrinsicHeight());
        }
    }


    private int getSpanCount(RecyclerView recyclerView) {
        if (recyclerView.getLayoutManager() instanceof GridLayoutManager) {
            GridLayoutManager layoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
            return layoutManager.getSpanCount();
        }
        return 1;
    }

    private boolean isLastRow(RecyclerView recyclerView, int position) {
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        if (adapter == null) {
            return false;
        }
        int spanCount = getSpanCount(recyclerView);
        int lastRow = adapter.getItemCount() / spanCount;
        if (mOrientation == OrientationHelper.VERTICAL) {
            return position / spanCount == lastRow;
        }
        // 水平方向上的最后一行相当于最后一列
        return (position + 1) % spanCount == 0;
    }

    private boolean isLastColumn(RecyclerView recyclerView, int position) {
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        if (adapter == null) {
            return false;
        }
        int spanCount = getSpanCount(recyclerView);
        if (mOrientation == OrientationHelper.VERTICAL) {
            return (position + 1) % spanCount == 0;
        }
        // 水平方向的最后一列相当于最后一行
        int lastRow = adapter.getItemCount() / spanCount;
        return position / spanCount == lastRow;
    }

    public void setDrawable(Drawable drawable) {
        this.mDrawable = drawable;
    }

}
