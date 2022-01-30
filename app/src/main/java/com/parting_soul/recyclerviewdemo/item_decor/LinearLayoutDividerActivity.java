package com.parting_soul.recyclerviewdemo.item_decor;

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
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.parting_soul.recyclerviewdemo.R;
import com.scwang.smartrefresh.layout.util.DensityUtil;

/**
 * 列表分割线
 *
 * @author parting_soul
 * @date 2022/1/23
 */
public class LinearLayoutDividerActivity extends AppCompatActivity {
    private static final String TAG = "TAG";
    private RecyclerView mRv1;
    private RecyclerView mRv2;
    private DataAdapter mAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_linear_divider);
        mAdapter = new DataAdapter(this);
        initRecyclerView1();
        initRecyclerView2();
    }

    private void initRecyclerView1() {
        DividerItemDecoration itemDecoration = new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL);
        itemDecoration.setDrawable(ContextCompat.getDrawable(this, R.drawable.shape_divider));

        mRv1 = findViewById(R.id.rv1);
        mRv1.setLayoutManager(new LinearLayoutManager(this));
        mRv1.addItemDecoration(itemDecoration);
        mRv1.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    private void initRecyclerView2() {
        CustomDividerItemDecoration itemDecoration = new CustomDividerItemDecoration(this,
                OrientationHelper.VERTICAL);
        itemDecoration.setDrawable(ContextCompat.getDrawable(this, R.drawable.shape_divider));

        mRv2 = findViewById(R.id.rv2);
        mRv2.setLayoutManager(new LinearLayoutManager(this));
        mRv2.addItemDecoration(itemDecoration);
        mRv2.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    private static class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
        private Context context;

        public DataAdapter(Context context) {
            this.context = context;
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
            viewHolder.tv.setText(String.format("android %d", i));
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
            return 30;
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
