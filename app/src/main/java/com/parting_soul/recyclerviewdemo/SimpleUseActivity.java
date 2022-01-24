package com.parting_soul.recyclerviewdemo;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smartrefresh.layout.util.DensityUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author parting_soul
 * @date 2022/1/24
 */
public class SimpleUseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_simple_use);
        initRecyclerView();
    }

    private List<String> lists = new ArrayList<>();

    {
        for (int i = 0; i < 10; i++) {
            lists.add("android" + i);
        }
    }

    private void initRecyclerView() {
        RecyclerView rv = findViewById(R.id.rv);
        rv.setHasFixedSize(true);
        final DataAdapter adapter = new DataAdapter(this, lists);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);

        rv.postDelayed(new Runnable() {
            @Override
            public void run() {
                lists.removeAll(lists);
                lists.add("aaaaaa");
                adapter.notifyDataSetChanged();
            }
        }, 3 * 1000);
    }

    private static class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
        private static final String TAG = "DataAdapter===>>";
        private Context context;
        private List<String> dataList;

        public DataAdapter(Context context, List<String> list) {
            this.context = context;
            this.dataList = list;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new ViewHolder(
                    LayoutInflater.from(context).inflate(R.layout.adapter_item,
                            viewGroup, false)
            );
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
            viewHolder.tv.setText(dataList.get(i));
            viewHolder.itemView.setBackgroundColor(Color.RED);

            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams)
                    viewHolder.itemView.getLayoutParams();
            int margin = DensityUtil.dp2px(10);
            params.setMargins(margin, margin, margin, margin);

            Log.e(TAG, "height = " + viewHolder.itemView.getHeight()
                    + " width = " + viewHolder.itemView.getWidth());
        }

        @Override
        public int getItemCount() {
            return dataList.size();
        }

        static class ViewHolder extends RecyclerView.ViewHolder {
            TextView tv;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                tv = itemView.findViewById(R.id.tv_content);
            }
        }
    }
}
