package com.parting_soul.recyclerviewdemo.pre_load;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.parting_soul.recyclerviewdemo.R;

/**
 * 数据预加载
 *
 * @author parting_soul
 * @date 1/30/22
 */
public class PreLoadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_pre_load);
    }

    public void onClick(View view) {
        Class<?> clazz = null;
        switch (view.getId()) {
            case R.id.bt_pre_load_1:
                clazz = PreLoad1Activity.class;
                break;
            case R.id.bt_pre_load_2:
                clazz = PreLoad2Activity.class;
                break;
            default:
                break;
        }
        if (clazz != null) {
            startActivity(new Intent(this, clazz));
        }
    }

}
