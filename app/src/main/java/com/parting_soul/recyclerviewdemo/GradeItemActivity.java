package com.parting_soul.recyclerviewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

import java.util.ArrayList;
import java.util.List;

public class GradeItemActivity extends AppCompatActivity {
    private SmartRefreshLayout mSmartRefreshLayout;
    private RecyclerView mRv;
    private BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> mAdapter;
    private List<MultiItemEntity> mLists;

    public static final int TYPE_ITEM = 0;
    public static final int TYPE_EMPTY = 1;
    public static final int TYPE_HEAD = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_grade_item);
        mRv = findViewById(R.id.mRv);
        mSmartRefreshLayout = findViewById(R.id.mSmartRefreshLayout);

        initSmartRefreshLayout();
        initRecyclerView();

        mSmartRefreshLayout.autoRefresh();
    }

    private void initSmartRefreshLayout() {
        mSmartRefreshLayout.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                addMoreData();
                mSmartRefreshLayout.finishLoadmore(200, true);
            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                initData();
                mSmartRefreshLayout.finishRefresh(200, true);
            }
        });
    }

    private void initData() {
        mLists.clear();

        Group group;
        for (int i = 0; i < 3; i++) {
            group = new Group();
            group.title = "标题" + i;

            List<SubItem> items = new ArrayList<>();

            SubItem item;
            if (i == 1) {
                item = new SubItem("", TYPE_EMPTY);
                items.add(item);
            } else {
                for (int j = 0; j < 10; j++) {
                    item = new SubItem(i + "-子分组" + j);
                    items.add(item);
                }
            }

            group.setSubItems(items);
            mLists.add(group);
        }
        mAdapter.notifyDataSetChanged();
        mAdapter.expandAll();
    }

    private void addMoreData() {
        Group group = findLastGroup();
        if (group == null) {
            return;
        }

        for (int i = 0; i < 10; i++) {
            SubItem item = new SubItem("load more " + group.getSubItems().size());
            group.addSubItem(item);
            mLists.add(item);
        }
        mAdapter.notifyDataSetChanged();
    }

    private Group findLastGroup() {
        for (int i = mLists.size() - 1; i >= 0; i--) {
            MultiItemEntity entity = mLists.get(i);
            if (entity instanceof Group) {
                return (Group) entity;
            }
        }
        return null;
    }

    private void initRecyclerView() {
        mLists = new ArrayList<>();
        mRv.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder>(mLists) {

            {
                addItemType(TYPE_HEAD, R.layout.adapter_header);
                addItemType(TYPE_EMPTY, R.layout.adapter_empty);
                addItemType(TYPE_ITEM, R.layout.adapter_item);
            }

            @Override
            protected void convert(final BaseViewHolder helper, MultiItemEntity entity) {
                switch (helper.getItemViewType()) {
                    case TYPE_ITEM:
                        SubItem item = (SubItem) entity;
                        helper.setText(R.id.tv_content, item.content);
                        break;
                    case TYPE_HEAD:
                        final Group group = (Group) entity;
                        helper.setText(R.id.tv_title, group.title);
                        helper.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                int pos = helper.getAdapterPosition();
                                if (group.isExpanded()) {
                                    collapse(pos);
                                } else {
                                    expand(pos);
                                }
                            }
                        });
                        break;
                    case TYPE_EMPTY:
                        break;
                    default:
                        break;
                }
            }


        };
        mRv.setAdapter(mAdapter);
    }

    public static class Group extends AbstractExpandableItem<SubItem> implements MultiItemEntity {
        private String title;

        @Override
        public int getLevel() {
            return 0;
        }

        @Override
        public int getItemType() {
            return TYPE_HEAD;
        }

        public String getTitle() {
            return title;
        }
    }


    public static class SubItem implements MultiItemEntity {
        private String content;
        private int type = TYPE_ITEM;

        public SubItem(String content) {
            this.content = content;
        }

        public SubItem(String content, int type) {
            this.content = content;
            this.type = type;
        }

        public void setType(int type) {
            this.type = type;
        }

        @Override
        public int getItemType() {
            return type;
        }

        public String getContent() {
            return content;
        }
    }

}
