package com.parting_soul.recyclerviewdemo;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @author parting_soul
 * @date 1/29/22
 */
public class MotionEventActivity extends AppCompatActivity {

    private String TAG = "dispatchTouchEvent";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_motion_event);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        int actionMask = ev.getActionMasked();


//        switch (ev.getActionMasked()) {
//            case MotionEvent.ACTION_DOWN:
//                Log.e(TAG, "action = " + action);
//                Log.e(TAG, "actionMask = " + actionMask);
//                break;
//            case MotionEvent.ACTION_POINTER_DOWN:
//                Log.e(TAG, "action = " + ParseUtil.decimalToHexString(action));
//                Log.e(TAG, "actionIndex = " + ev.getActionIndex());
//                Log.e(TAG, "pointerId = " + ev.getPointerId(ev.getActionIndex()));
//                break;
//            case MotionEvent.ACTION_POINTER_UP:
//                Log.e(TAG, "action = " + ParseUtil.decimalToHexString(action));
//                Log.e(TAG, "actionIndex = " + ev.getActionIndex());
//                Log.e(TAG, "pointerId = " + ev.getPointerId(ev.getActionIndex()));
//                break;
//            case MotionEvent.ACTION_MOVE:
//                Log.e(TAG, "ACTION_MOVE" + ev.getActionIndex());
//                break;
//        }

        return onTouchEvent(ev);
    }


    private int mPointerId;
    private int mDownX;
    private int mLastX;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
                mPointerId = event.getPointerId(event.getActionIndex());
                mDownX = (int) event.getX();
                mLastX = (int) event.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                int index = event.findPointerIndex(mPointerId);
                int dx = (int) (mLastX - event.getX(index));
                if (Math.abs(dx) > 0) {
                    // 活动手指发生了移动
                    Log.e("onTouchEvent", "index = " + index + " mPointerId = " + mPointerId + " dx = " + dx);
                    mLastX = (int) event.getX(index);
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
            case MotionEvent.ACTION_POINTER_UP:
                int activeIndex = event.findPointerIndex(mPointerId);
                if (activeIndex == event.getActionIndex()) {
                    // 若抬起的手指是激活的手指，重新选择第二新的手指作为活动手指
                    mPointerId = event.getPointerId(event.getPointerCount() - 2);
                }
                break;
        }
        return super.onTouchEvent(event);
    }
}
