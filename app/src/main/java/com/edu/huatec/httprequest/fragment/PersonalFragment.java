package com.edu.huatec.httprequest.fragment;


import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.edu.huatec.httprequest.R;
import com.edu.huatec.httprequest.activity.ChangePwdActivity;
import com.edu.huatec.httprequest.activity.LoginActivity;
import com.edu.huatec.httprequest.activity.MyAddressActivity;
import com.edu.huatec.httprequest.activity.MyCollectActivity;
import com.edu.huatec.httprequest.activity.MyOrderActivity;
import com.edu.huatec.httprequest.comm.BaseFragment;
import com.edu.huatec.httprequest.comm.Constants;
import com.edu.huatec.httprequest.utils.SystemConfig;
import com.nostra13.universalimageloader.core.ImageLoader;

import butterknife.BindView;
import butterknife.OnClick;

public class PersonalFragment extends BaseFragment {

    //已登录
//    @BindView(R.id.personal_for_login)
//    RelativeLayout personal_for_login;
//    @BindView(R.id.user_img_view)
//    ImageView user_img_view;
//    @BindView(R.id.user_name)
//    TextView user_name;
//    @BindView(R.id.user_level)
//    TextView user_level;
//
//    //未登录
//    @BindView(R.id.personal_for_not_login)
//    RelativeLayout personal_for_not_login;
//
//    //退出登录
//    @BindView(R.id.person_logout_layout)
//    RelativeLayout person_logout_layout;

    RelativeLayout personal_for_login;
    ImageView user_img_view;
    TextView user_name;
    TextView user_level;
    RelativeLayout personal_for_not_login;
    RelativeLayout person_logout_layout;

    private BroadcastReceiver receiver;

    @Override
    public int getContentViewId() {
        return R.layout.fragment_personal;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        personal_for_login = view.findViewById(R.id.personal_for_login);
        user_img_view = view.findViewById(R.id.user_img_view);
        user_name = view.findViewById(R.id.user_name);
        user_level = view.findViewById(R.id.user_level);
        personal_for_not_login = view.findViewById(R.id.personal_for_not_login);
        person_logout_layout = view.findViewById(R.id.person_logout_layout);
        registerLoginReceiver();
        //初始状态配置
        resetUI();
    }
    private void registerLoginReceiver() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(Constants.ACTION_LOGIN);
        // filter.addAction("21312312dasd");
        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                resetUI();
            }
        };
        getActivity().registerReceiver(receiver, filter);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().unregisterReceiver(receiver);
    }

    private void resetUI() {
        if (SystemConfig.isLogin()) {
            //已登录，显示已登录的UI，隐藏未登录的UI
            personal_for_login.setVisibility(View.VISIBLE);
            personal_for_not_login.setVisibility(View.GONE);
            person_logout_layout.setVisibility(View.VISIBLE);

            //显示已登录的信息
            //显示头像
           // String imgHead = SystemConfig.getLoginUserHead();
            String imgHead="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1574444769405&di=490f4f9d7ccd96be258cd4304108954c&imgtype=0&src=http%3A%2F%2Fpic1.win4000.com%2Fwallpaper%2Fb%2F5476e878ad5aa.jpg";

            ImageLoader.getInstance().displayImage(imgHead, user_img_view);
            //显示用户名
            String name = SystemConfig.getLoginUsername();
            user_name.setText(name);
            //显示邮箱
            user_level.setText(SystemConfig.getLoginUserEmail());
        } else {
            //未登录，显示未登录的UI，隐藏已登录的UI
            personal_for_login.setVisibility(View.GONE);
            personal_for_not_login.setVisibility(View.VISIBLE);
            person_logout_layout.setVisibility(View.GONE);
        }
    }

    @OnClick(R.id.personal_login)
    void login() {
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivityForResult(intent, 1000);
    }

    @OnClick(R.id.person_my_order)
    void person_my_order() {
        //我的订单
        if (SystemConfig.isLogin()) {
            startActivity(new Intent(getActivity(), MyOrderActivity.class));
        } else {
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivityForResult(intent, 1001);
        }
    }

    @OnClick(R.id.my_collect)
    void my_collect() {
        //我的收藏
        if (SystemConfig.isLogin()) {
            startActivity(new Intent(getActivity(), MyCollectActivity.class));
        } else {
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivityForResult(intent, 1002);
        }
    }

    @OnClick(R.id.my_address)
    void my_address() {
        //我的地址
        if (SystemConfig.isLogin()) {
            startActivity(new Intent(getActivity(), MyAddressActivity.class));
        } else {
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivityForResult(intent, 1003);
        }
    }

    @OnClick(R.id.my_account)
    void my_account() {
        //修改密码
        if (SystemConfig.isLogin()) {
            startActivity(new Intent(getActivity(), ChangePwdActivity.class));
        } else {
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivityForResult(intent, 1004);
        }
    }

    @OnClick(R.id.person_logout_layout)
    void logout() {
        //退出登录
        SystemConfig.logout();
        resetUI();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            //重置UI
            resetUI();

            //打开登录之前想要进入的页面
            Intent intent = new Intent();
            if (requestCode == 1000) {

            }else if (requestCode == 1001) {
                intent.setClass(getActivity(),MyOrderActivity.class);
                startActivity(intent);
            } else if (requestCode == 1002) {
                intent.setClass(getActivity(),MyCollectActivity.class);
                startActivity(intent);
            } else if (requestCode == 1003) {
                intent.setClass(getActivity(),MyAddressActivity.class);
                startActivity(intent);
            } else if (requestCode == 1004) {
                intent.setClass(getActivity(),ChangePwdActivity.class);
                startActivity(intent);
            }

        }
    }
}