package com.parting_soul.recyclerviewdemo.item_drag;

import android.graphics.Color;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.parting_soul.recyclerviewdemo.CommonAdapter;

import java.util.Collections;

/**
 * @author parting_soul
 * @date 1/31/22
 */
public class ItemTouchCallbackImpl extends ItemTouchHelper.Callback {

    private String TAG = "ItemTouchHelperCallback";
    private CommonAdapter adapter;

    public ItemTouchCallbackImpl(CommonAdapter adapter) {
        this.adapter = adapter;
    }

    /**
     * 设置拖拽和滑动支持的方向
     *
     * @param recyclerView
     * @param viewHolder
     * @return
     */
    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        // 拖拽方向 设置可以上下拖动
        int dragFlag = ItemTouchHelper.DOWN | ItemTouchHelper.UP;
        // 滑动方向 设置可以左滑动
        int swipeFlag = ItemTouchHelper.LEFT;

        return makeMovementFlags(dragFlag, swipeFlag);
    }

    /**
     * 拖拽时发生回调
     *
     * @param recyclerView
     * @param viewHolder
     * @param target
     * @return
     */
    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        int fromPos = viewHolder.getAdapterPosition();
        int toPos = target.getAdapterPosition();

        // 拖动时交换位置
        if (fromPos < toPos) {
            for (int i = fromPos; i < toPos; i++) {
                Collections.swap(adapter.getDataList(), i, i + 1);
            }
        } else {
            for (int i = fromPos; i > toPos; i--) {
                Collections.swap(adapter.getDataList(), i, i - 1);
            }
        }

        adapter.notifyItemMoved(fromPos, toPos);
        Log.e(TAG, "onMove fromPos = " + fromPos + " toPos = " + toPos);
        return true;
    }

    /**
     * 滑动时发生回调
     *
     * @param viewHolder
     * @param direction
     */
    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        // 左划删除
        if (direction == ItemTouchHelper.LEFT) {
            int position = viewHolder.getAdapterPosition();
            adapter.getDataList().remove(position);
            adapter.notifyItemRemoved(position);
        }
        Log.e(TAG, "onSwipe");
    }

    /**
     * 状态发生变化时回调
     *
     * @param viewHolder
     * @param actionState
     */
    @Override
    public void onSelectedChanged(@Nullable RecyclerView.ViewHolder viewHolder, int actionState) {
        super.onSelectedChanged(viewHolder, actionState);
        Log.e(TAG, "onSelectedChanged " + actionState);
        if (actionState == ItemTouchHelper.ACTION_STATE_DRAG) {
            // 拖动状态
            viewHolder.itemView.setBackgroundColor(Color.RED);
        }
    }

    /**
     * 交互结束时发生回调(手指松开时)
     *
     * @param recyclerView
     * @param viewHolder
     */
    @Override
    public void clearView(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
        Log.e(TAG, "clearView");
        // 用于恢复状态
        viewHolder.itemView.setBackgroundColor(Color.TRANSPARENT);
    }

    /**
     * 是否启用长按拖拽
     *
     * @return
     */
    @Override
    public boolean isLongPressDragEnabled() {
        return super.isLongPressDragEnabled();
    }
}
