package com.parting_soul.recyclerviewdemo.item_decor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.parting_soul.recyclerviewdemo.R;

/**
 * @author parting_soul
 * @date 2022/1/23
 */
public class DividerActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_divider);
    }

    public void onClick(View view) {
        Class<?> clazz = null;
        switch (view.getId()) {
            case R.id.bt_linear_divider:
                clazz = LinearLayoutDividerActivity.class;
                break;
            case R.id.bt_Grid_divider:
                clazz = GridLayoutDividerActivity.class;
                break;
            default:
                break;
        }
        if (clazz != null) {
            startActivity(new Intent(this, clazz));
        }
    }
}
