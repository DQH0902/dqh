package com.edu.huatec.httprequest.http.presenter;

import com.edu.huatec.httprequest.http.HttpMethods;
import com.edu.huatec.httprequest.http.entity.MemberEntity;

import rx.Observable;
import rx.Subscriber;

public class MemberPresenter extends HttpMethods {
//用户注册
    public static void register(Subscriber<MemberEntity> subscriber, String userName, String emial, String password) {
        Observable observable = memberService.register(userName, password, emial).map(new HttpResultFunc<MemberEntity>());
        toSubscribe(observable, subscriber);
    }
//用户登录
    public static void login(Subscriber<MemberEntity> subscriber, String userName, String password) {
        Observable observable = memberService.login2(userName, password).map(new HttpResultFunc<MemberEntity>());
        toSubscribe(observable, subscriber);
    }
//修改密码

    public static void changePassword(Subscriber<MemberEntity> subscriber,String memberId, String old_pwd, String new_pwd, String password) {
        Observable observable = memberService.changePassword(memberId, old_pwd,new_pwd);
        toSubscribe(observable, subscriber);
    }

}
