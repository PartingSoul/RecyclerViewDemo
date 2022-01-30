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
import androidx.recyclerview.widget.OrientationHelper;
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
        for (int i = 0; i < 35; i++) {
            lists.add("android" + i);
        }
    }

    private void initRecyclerView() {
        final RecyclerView rv = findViewById(R.id.rv);
        rv.setHasFixedSize(true);
        final DataAdapter adapter = new DataAdapter(this, lists);
        rv.setLayoutManager(new LinearLayoutManager(this, OrientationHelper.VERTICAL, false));
        rv.setAdapter(adapter);

        rv.postDelayed(new Runnable() {
            @Override
            public void run() {
//                lists.removeAll(lists);
//                lists.add("aaaaaa");
//                adapter.notifyDataSetChanged();
//                rv.scrollToPosition(1);
            }
        }, 3 * 1000);


        adapter.setOnItemClickListener(new DataAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View target) {
//                rv.scrollToPosition(position - 20);
            }
        });
    }

    private static class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
        private static final String TAG = "DataAdapter===>>";
        private Context context;
        private List<String> dataList;
        private OnItemClickListener mOnItemClickListener;

        public DataAdapter(Context context, List<String> list) {
            this.context = context;
            this.dataList = list;
            count = 0;
        }

        static int count;

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            count++;
            Log.e("onCreateViewHolder", "count = " + count);
            return new ViewHolder(
                    LayoutInflater.from(context).inflate(R.layout.adapter_item,
                            viewGroup, false)
            );
        }

        @Override
        public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
            viewHolder.tv.setText(dataList.get(i));
            viewHolder.itemView.setBackgroundColor(Color.RED);

            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams)
                    viewHolder.itemView.getLayoutParams();
            int margin = DensityUtil.dp2px(10);
            params.setMargins(margin, margin, margin, margin);

            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onItemClick(viewHolder.getAdapterPosition(), viewHolder.itemView);
                    }
                }
            });

            Log.e(TAG, "height = " + viewHolder.itemView.getHeight()
                    + " width = " + viewHolder.itemView.getWidth());
        }

        @Override
        public int getItemCount() {
            return dataList.size();
        }

        public void setOnItemClickListener(OnItemClickListener listener) {
            this.mOnItemClickListener = listener;
        }

        static class ViewHolder extends RecyclerView.ViewHolder {
            TextView tv;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                tv = itemView.findViewById(R.id.tv_content);
            }
        }

        interface OnItemClickListener {
            void onItemClick(int position, View target);
        }
    }


}
