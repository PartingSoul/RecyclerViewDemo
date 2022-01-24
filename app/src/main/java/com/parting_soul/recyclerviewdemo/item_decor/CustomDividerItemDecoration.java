package com.parting_soul.recyclerviewdemo.item_decor;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author parting_soul
 * @date 2022/1/23
 */
public class CustomDividerItemDecoration extends RecyclerView.ItemDecoration {
    private Context context;
    private Drawable mDrawable;
    private int mOrientation = OrientationHelper.VERTICAL;

    public CustomDividerItemDecoration(Context context, int orientation) {
        this.context = context;
        this.mOrientation = orientation;
    }

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);

        if (mOrientation == OrientationHelper.VERTICAL) {
            drawVertical(c, parent);
        } else {
            drawHorizontal(c, parent);
        }
    }

    private void drawHorizontal(Canvas c, RecyclerView parent) {
        int childCount = parent.getChildCount();
        View child = null;
        RecyclerView.LayoutParams params = null;
        RecyclerView.Adapter adapter = parent.getAdapter();
        if (adapter == null) {
            return;
        }
        int itemCount = adapter.getItemCount();

        for (int i = 0; i < childCount; i++) {
            if (i + 1 == itemCount) {
                continue;
            }
            child = parent.getChildAt(i);
            params = (RecyclerView.LayoutParams) child.getLayoutParams();

            int left = child.getRight() + params.rightMargin;
            int top = child.getTop();
            int bottom = child.getBottom();
            mDrawable.setBounds(left, top, left + mDrawable.getIntrinsicWidth(), bottom);
            mDrawable.draw(c);
        }
    }

    private void drawVertical(Canvas c, RecyclerView parent) {
        int childCount = parent.getChildCount();
        View child = null;
        RecyclerView.LayoutParams params = null;
        RecyclerView.Adapter adapter = parent.getAdapter();
        if (adapter == null) {
            return;
        }
        int itemCount = adapter.getItemCount();

        // 最后一个item后面不进行绘制
        for (int i = 0; i < childCount; i++) {
            if (i + 1 == itemCount) {
                continue;
            }
            child = parent.getChildAt(i);
            params = (RecyclerView.LayoutParams) child.getLayoutParams();

            int left = child.getLeft();
            int right = child.getRight();
            int top = child.getBottom() + params.bottomMargin;
            mDrawable.setBounds(left, top, right, top + mDrawable.getIntrinsicHeight());
            mDrawable.draw(c);
        }
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        // 最后一行不添加分割线
        RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) view.getLayoutParams();
        int position = params.getViewLayoutPosition();

        RecyclerView.Adapter adapter = parent.getAdapter();
        if (adapter == null) {
            return;
        }

        if (position + 1 != adapter.getItemCount()) {
            if (mOrientation == OrientationHelper.VERTICAL) {
                outRect.set(0, 0, 0, mDrawable.getIntrinsicHeight());
            } else {
                outRect.set(0, 0, mDrawable.getIntrinsicWidth(), 0);
            }
        }
        Log.e("getItemOffsets", " pos " + position + " " + adapter.getItemCount());
    }

    public void setDrawable(Drawable drawable) {
        this.mDrawable = drawable;
    }
}
