package com.edu.huatec.httprequest.comm;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());
        Unbinder bind = ButterKnife.bind(this);
        initView();
        initData();
    }
    protected  void  initData() {

    }
    protected  void initView() {

    }

    public abstract int getContentViewId();

    public void toastShort(String msg) { Toast.makeText(this,msg, Toast.LENGTH_SHORT).show();}

    public void toastLong(String msg) { Toast.makeText(this,msg, Toast.LENGTH_LONG).show(); }
}
