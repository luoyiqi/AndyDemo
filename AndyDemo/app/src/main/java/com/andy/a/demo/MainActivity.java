package com.andy.a.demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.andy.a.demo.ui.DaggerTestActivity;
import com.andy.a.demo.ui.NormalTestActivity;

import org.xutils.view.annotation.Event;
import org.xutils.x;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        x.view().inject(this);
    }

    @Event(R.id.daggerTestButton)
    private void onMethod1(View view) {
        startActivity(new Intent(this, DaggerTestActivity.class));
    }

    @Event(R.id.normalTestButton)
    private void onMethod2(View view) {
        startActivity(new Intent(this, NormalTestActivity.class));
    }
}
