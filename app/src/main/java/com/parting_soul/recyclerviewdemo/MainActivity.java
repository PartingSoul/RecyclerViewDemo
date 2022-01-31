package com.parting_soul.recyclerviewdemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.parting_soul.recyclerviewdemo.item_decor.DividerActivity;
import com.parting_soul.recyclerviewdemo.item_drag.ItemDragAndRemoveActivity;
import com.parting_soul.recyclerviewdemo.pre_load.PreLoadActivity;

/**
 * @author parting_soul
 * @date 2022/1/23
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);
    }

    public void onClick(View view) {
        Class<?> clazz = null;
        switch (view.getId()) {
            case R.id.bt_simple_use:
                clazz = SimpleUseActivity.class;
                break;
            case R.id.bt_divider:
                clazz = DividerActivity.class;
                break;
            case R.id.bt_grade_item:
                clazz = GradeItemActivity.class;
                break;
            case R.id.bt_motion_event:
                clazz = MotionEventActivity.class;
                break;
            case R.id.bt_pre_load:
                clazz = PreLoadActivity.class;
                break;
            case R.id.bt_item_drag_and_remove:
                clazz = ItemDragAndRemoveActivity.class;
                break;
            default:
                break;
        }
        if (clazz != null) {
            startActivity(new Intent(this, clazz));
        }
    }
}
