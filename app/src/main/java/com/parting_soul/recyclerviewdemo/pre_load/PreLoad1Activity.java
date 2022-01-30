package com.parting_soul.recyclerviewdemo.pre_load;

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
 * 通过监听滚动实现，但在快速滑动时会出现问题
 *
 * @author parting_soul
 * @date 1/30/22
 */
public class PreLoad1Activity extends AppCompatActivity {
    private RecyclerView mRv;
    private CommonAdapter mAdapter;
    private int preloadCount = 2;
    private int mLastPreLoadPos;
    private boolean isSchedulePreLoad;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_pre_load1);
        initRecyclerView();
    }

    private void initRecyclerView() {
        mRv = findViewById(R.id.rv);
        List<String> lists = getDataFromLocal();
        mAdapter = new CommonAdapter(this, lists);
        mRv.setLayoutManager(new LinearLayoutManager(this));
        mRv.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

        mRv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {
                    RecyclerView.Adapter adapter = recyclerView.getAdapter();
                    LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
                    int lastVisiblePos = manager.findLastVisibleItemPosition();
                    if (dy > 0 && lastVisiblePos == adapter.getItemCount() - preloadCount - 1
                            && lastVisiblePos != mLastPreLoadPos && !isSchedulePreLoad) {
                        // 手指向上滑动且滑动到倒数第2个item时进行预加载
                        mLastPreLoadPos = lastVisiblePos;
                        isSchedulePreLoad = true;
                        Log.e("onPreLoad", "开始预加载");
                        onPreLoad();
                    }
                }
            }

            private void onPreLoad() {
                List<String> lists = getDataFromLocal();
                mAdapter.addDataList(lists);
                isSchedulePreLoad = false;
            }
        });
    }

    private List<String> getDataFromLocal() {
        List<String> lists = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            lists.add("android");
        }
        return lists;
    }


}
