package com.edu.huatec.httprequest.comm;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import butterknife.ButterKnife;

public abstract class BaseFragment extends Fragment {


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getContentViewId(),container,false);
        ButterKnife.bind(this,view);
        initView(view);
        initData();
        return  view;
    }

    protected  void initData() {

    }

    protected void initView(View view) {

    }
    public  abstract  int getContentViewId();
    public void toastShort(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }
    public void toastLong(String msg) {
        Toast.makeText(getActivity(),msg, Toast.LENGTH_LONG).show();

    }

}
