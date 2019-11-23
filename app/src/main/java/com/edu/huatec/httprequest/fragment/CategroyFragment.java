package com.edu.huatec.httprequest.fragment;

import android.content.Intent;

import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.edu.huatec.httprequest.R;
import com.edu.huatec.httprequest.activity.GoodsListActivity;
import com.edu.huatec.httprequest.adapter.CategroyLeftAdapter;
import com.edu.huatec.httprequest.adapter.CategroyRightAdapter;
import com.edu.huatec.httprequest.comm.BaseFragment;
import com.edu.huatec.httprequest.http.ProgressDialogSubscriber;
import com.edu.huatec.httprequest.http.entity.CategoryEntity;
import com.edu.huatec.httprequest.http.presenter.CategoryPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


public class CategroyFragment extends BaseFragment {

//    @BindView(R.id.rv_left)
//    RecyclerView rv_left;
//    @BindView(R.id.rv_right)
//    RecyclerView rv_right;


    private RecyclerView rv_left;

    private  RecyclerView rv_right;

    private List<CategoryEntity> leftData;
    private List<CategoryEntity> rightData;
    private CategroyLeftAdapter leftAdapter;
    private CategroyRightAdapter rightAdapter;


    @Override
    public int getContentViewId() {
        return R.layout.fragment_categroy;
    }


    @OnClick(R.id.ll_search)
    void search() {
        toastShort("开发中...");
    }

    @Override
    protected void initData() {
        super.initData();

        //获取一级列表
        CategoryPresenter.getTopList(new ProgressDialogSubscriber<List<CategoryEntity>>(getActivity()) {
            @Override
            public void onNext(List<CategoryEntity> categoryEntities) {
                leftData.clear();
                leftData.addAll(categoryEntities);
                //刷新列表
                leftAdapter.notifyDataSetChanged();
                //左边选中的位置 刷新UI
                leftAdapter.setSelect(0);
                //加载二级列表
                loadSecondList(0);
            }
        });
    }

    private void loadSecondList(int pos) {
        if(leftData==null||leftData.size()==0){
            return;
        }

        CategoryEntity entity = leftData.get(pos);
        CategoryPresenter.getSecondList(new ProgressDialogSubscriber<List<CategoryEntity>>(getActivity()) {
            @Override
            public void onNext(List<CategoryEntity> categoryEntities) {
                rightData.clear();
                rightData.addAll(categoryEntities);
                rightAdapter.notifyDataSetChanged();
            }
        },entity.getCat_id());

    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        leftData=new ArrayList<>();
        rightData=new ArrayList<>();

        //设置列表样式
//        LinearLayoutManager leftManager = new LinearLayoutManager(getActivity());
//        leftManager.setOrientation(RecyclerView.VERTICAL);
        rv_left = view.findViewById(R.id.rv_left);
        rv_right = view.findViewById(R.id.rv_right);
        rv_left.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false));

        //垂直表格
        GridLayoutManager rightManager = new GridLayoutManager(getActivity(),3, RecyclerView.VERTICAL, false);
        rv_right.setLayoutManager(rightManager);

        leftAdapter = new CategroyLeftAdapter(getActivity(), leftData);
        leftAdapter.setOnItemClickListener(new CategroyLeftAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(View view, int position, CategoryEntity entity) {
                //左边选中的位置 刷新UI
                leftAdapter.setSelect(position);
                //加载二级列表
                loadSecondList(position);
            }

        });
        rightAdapter = new CategroyRightAdapter(getActivity(), rightData);
        rightAdapter.setOnItemClickListener(new CategroyRightAdapter.OnItemClickListener(){

            @Override
            public void onItemClick(View view, int position, CategoryEntity entity) {
                //跳转到商品列表界面
                Intent intent = new Intent(getActivity(), GoodsListActivity.class);
                intent.putExtra("cat_id", entity.getCat_id());
                startActivity(intent);
            }
        });

        rv_left.setAdapter(leftAdapter);
        rv_right.setAdapter(rightAdapter);
    }
}
