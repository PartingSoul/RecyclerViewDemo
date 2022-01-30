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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.parting_soul.recyclerviewdemo.R;

/**
 * 网格分割线
 *
 * @author parting_soul
 * @date 2022/1/23
 */
public class GridLayoutDividerActivity extends AppCompatActivity {
    private static final String TAG = "TAG";
    private RecyclerView mRv1;
    private RecyclerView mRv2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_grid_divider);
        initRecyclerView1();
        initRecyclerView2();
    }

    private void initRecyclerView1() {
        CustomGridDividerItemDecoration itemDecoration = new CustomGridDividerItemDecoration(this,
                OrientationHelper.VERTICAL);
        itemDecoration.setDrawable(ContextCompat.getDrawable(this, R.drawable.shape_divider));

        DataAdapter adapter = new DataAdapter(this, OrientationHelper.VERTICAL);
        mRv1 = findViewById(R.id.rv1);
        mRv1.setLayoutManager(new GridLayoutManager(this, 4));
        mRv1.addItemDecoration(itemDecoration);
        mRv1.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void initRecyclerView2() {
        CustomGridDividerItemDecoration itemDecoration = new CustomGridDividerItemDecoration(this,
                OrientationHelper.HORIZONTAL);
        itemDecoration.setDrawable(ContextCompat.getDrawable(this, R.drawable.shape_divider));

        DataAdapter adapter = new DataAdapter(this, OrientationHelper.HORIZONTAL);
        mRv2 = findViewById(R.id.rv2);
        mRv2.setLayoutManager(new GridLayoutManager(this, 4, OrientationHelper.HORIZONTAL, false));
        mRv2.addItemDecoration(itemDecoration);
        mRv2.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private static class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
        private Context context;
        private int orientation;

        public DataAdapter(Context context, int orientation) {
            this.context = context;
            this.orientation = orientation;
        }

        @NonNull
        @Override
        public DataAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            ViewHolder holder = null;
            if (orientation == OrientationHelper.VERTICAL) {
                holder = new DataAdapter.ViewHolder(
                        LayoutInflater.from(context).inflate(R.layout.adapter_item,
                                viewGroup, false)
                );
            } else {
                holder = new DataAdapter.ViewHolder(
                        LayoutInflater.from(context).inflate(R.layout.adapter_horizontal_item,
                                viewGroup, false)
                );
            }
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull DataAdapter.ViewHolder viewHolder, int i) {
            viewHolder.tv.setText(String.format("android %d", i));
            viewHolder.itemView.setBackgroundColor(Color.RED);

//            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams)
//                    viewHolder.itemView.getLayoutParams();
//            int margin = DensityUtil.dp2px(10);
//            params.setMargins(margin, margin, margin, margin);

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
