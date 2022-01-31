package com.parting_soul.recyclerviewdemo.item_drag;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.parting_soul.recyclerviewdemo.CommonAdapter;
import com.parting_soul.recyclerviewdemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author parting_soul
 * @date 1/31/22
 */
public class ItemDragAndRemoveActivity extends AppCompatActivity {
    private RecyclerView mRv;
    private CommonAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_item_drag_and_remove);
        initRecyclerView();
    }

    private void initRecyclerView() {
        mRv = findViewById(R.id.rv);
        List<String> lists = getDataFromLocal();
        mAdapter = new CommonAdapter(this, lists);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchCallbackImpl(mAdapter));
        itemTouchHelper.attachToRecyclerView(mRv);

        mRv.setLayoutManager(new LinearLayoutManager(this));
        mRv.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    private List<String> getDataFromLocal() {
        List<String> lists = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            lists.add("android 长按拖拽,左滑删除");
        }
        return lists;
    }

}
