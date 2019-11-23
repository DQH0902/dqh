package com.edu.huatec.httprequest.http.service;


import com.edu.huatec.httprequest.http.entity.HttpResult;
import com.edu.huatec.httprequest.http.entity.MemberEntity;

import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

public interface MemberService {

    @FormUrlEncoded
    @POST("member/login2")
    Observable<HttpResult<MemberEntity>> login2
            (@Field("input") String input,
             @Field("password") String password
            );

    @FormUrlEncoded
    @POST("member")
    Observable<HttpResult<MemberEntity>> register
            (@Field("uname") String uname,
             @Field("password") String password,
              @Field("email") String email
            );


    @FormUrlEncoded
    @POST("member/{memberId}")
    Observable<HttpResult> changePassword
            (@Field("memberId") String memberId,
             @Field("old_pwd") String old_pwd,
             @Field("new_pwd") String new_pwd
            );
}
