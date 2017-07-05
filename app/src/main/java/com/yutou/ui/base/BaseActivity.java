package com.yutou.ui.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.yutou.R;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        Log.e("123","onCreate");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("123","onResume");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("123","onDestroy");
    }
}
