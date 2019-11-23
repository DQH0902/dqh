package com.edu.huatec.httprequest.http.presenter;

import com.edu.huatec.httprequest.http.HttpMethods;
import com.edu.huatec.httprequest.http.entity.GoodsEntity;
import com.edu.huatec.httprequest.http.entity.MemberEntity;

import java.util.List;

import rx.Observable;
import rx.Subscriber;

public class GoodsPresenter extends HttpMethods {

    public static void listByKeywords(Subscriber<List<GoodsEntity>> subscriber, String Keywords) {
        Observable observable = goodsService.listByKeyworids(Keywords)
                .map(new HttpResultFunc<List<GoodsEntity>>());
        toSubscribe(observable, subscriber);
    }
    public static void list(Subscriber<List<GoodsEntity>> subscriber,int catId){
        Observable<List<GoodsEntity>> observable=goodsService.list(catId)
                .map(new HttpResultFunc<List<GoodsEntity>>());
        toSubscribe(observable,subscriber);
    }
}
