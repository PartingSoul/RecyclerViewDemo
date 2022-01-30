package com.parting_soul.recyclerviewdemo.pre_load;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.parting_soul.recyclerviewdemo.CommonAdapter;
import com.parting_soul.recyclerviewdemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author parting_soul
 * @date 1/30/22
 */
public class PreLoad2Activity extends AppCompatActivity {
    private RecyclerView mRv;
    private Adapter mAdapter;
    private boolean isSchedulePreLoad;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_pre_load2);
        initRecyclerView();
    }

    private void initRecyclerView() {
        mRv = findViewById(R.id.rv);
        List<String> lists = getDataFromLocal();
        mAdapter = new Adapter(this, lists);
        mAdapter.bindRecyclerView(mRv);
        mAdapter.mOnPreLoadCallback = new Adapter.OnPreLoadCallback() {
            @Override
            public void onPreload() {
                if (!isSchedulePreLoad) {
                    isSchedulePreLoad = true;
//                    mRv.postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
                            List<String> list = getDataFromLocal();
                            mAdapter.addDataList(list);
                            isSchedulePreLoad = false;
                            Log.e("onPreLoad", "开始预加载");
                        }
//                    }, 1000);
//                }
            }
        };
        mRv.setLayoutManager(new LinearLayoutManager(this));
        mRv.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    private List<String> getDataFromLocal() {
        List<String> lists = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            lists.add("android");
        }
        return lists;
    }

    static class Adapter extends CommonAdapter {
        private int preloadCount = 2;
        private OnPreLoadCallback mOnPreLoadCallback;
        private int scrollState;

        public Adapter(Context context, List<String> list) {
            super(context, list);
        }

        @Override
        public void onBindViewHolder(@NonNull CommonAdapter.ViewHolder holder, int position) {
            super.onBindViewHolder(holder, position);
            checkPreload(position);
        }

        private void checkPreload(int position) {
            if (scrollState != RecyclerView.SCROLL_STATE_IDLE &&
                    position == getItemCount() - 1 - preloadCount) {
                onPreload();
            }
        }

        private void onPreload() {
            if (mOnPreLoadCallback != null) {
                mOnPreLoadCallback.onPreload();
            }
        }

        public void bindRecyclerView(RecyclerView recyclerView) {
            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                    super.onScrollStateChanged(recyclerView, newState);
                    Adapter.this.scrollState = newState;
                }
            });
        }

        public interface OnPreLoadCallback {
            void onPreload();
        }

    }

}
