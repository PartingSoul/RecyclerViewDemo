package com.parting_soul.recyclerviewdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

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
            case R.id.bt_grade_item:
                clazz = GradeItemActivity.class;
                break;
            default:
                break;
        }
        if (clazz != null) {
            startActivity(new Intent(this, clazz));
        }
    }
}
