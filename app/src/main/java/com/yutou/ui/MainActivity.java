package com.yutou.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.JsonObject;
import com.yutou.R;
import com.yutou.bean.ProductBean;
import com.yutou.bean.User;
import com.yutou.db.DBManager;
import com.yutou.net.BaseSubscriber;
import com.yutou.net.utils.RetrofitUtil;
import com.yutou.ui.activity.ChooseActivity;
import com.yutou.ui.adapter.FullyGridLayoutManager;
import com.yutou.ui.adapter.ProductAdapter;
import com.yutou.ui.callback.SimpleItemTouchCallback;

import java.util.List;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;


public class MainActivity extends AppCompatActivity implements BaseQuickAdapter.RequestLoadMoreListener {
    private int page = 1;
    private RecyclerView rv;
    private PtrClassicFrameLayout ptr;
    private Button btn, btn_database;
    private ProductAdapter productAdapter;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        ptr = (PtrClassicFrameLayout) findViewById(R.id.ptr);
        rv = (RecyclerView) findViewById(R.id.recyclerview);
        btn = (Button) findViewById(R.id.btn);
        btn_database = (Button) findViewById(R.id.btn_database);
        initPtr();
        //请求实体类数据
        initData();
        //请求json数据
//        initJsonData();
        initClick();
    }

    private void initData() {
        RetrofitUtil.getInstance().toSubscribe(RetrofitUtil.getInstance().getApiService().getProductList(page + ""), new BaseSubscriber<ProductBean>(MainActivity.this) {
            @Override
            public void onNext(ProductBean s) {
                super.onNext(s);
                Log.e("请求结果", "实体类数据----->" + s);
                if (page == 1) {
                    productAdapter = new ProductAdapter(context, null);
                    productAdapter.setOnLoadMoreListener(MainActivity.this);
                    productAdapter.setNewData(s.getList().getProduct_list());
                    FullyGridLayoutManager fullyGridLayoutManager = new FullyGridLayoutManager(context, 2);
                    rv.setLayoutManager(fullyGridLayoutManager);
                    rv.setAdapter(productAdapter);
                    ItemTouchHelper helper = new ItemTouchHelper(new SimpleItemTouchCallback(productAdapter, productAdapter.getData()));
                    helper.attachToRecyclerView(rv);
                    if (ptr.isShown()) {
                        ptr.refreshComplete();//下拉刷新完成关闭
                    }
                } else {
                    if (s.getList().getProduct_list().size() <= 0) {
                        //上拉加载没有数据了
                    } else {
//                        Log.e("添加的数量", "" + s.getList().getProduct_list().size());
                        productAdapter.addData(s.getList().getProduct_list());
                        productAdapter.notifyDataSetChanged();
                    }
                }

            }
        });
    }

    private void initJsonData() {
        RetrofitUtil.getInstance().toSubscribe(RetrofitUtil.getInstance().getApiService().getProductListJSON(page + ""), new BaseSubscriber<JsonObject>(MainActivity.this) {
            @Override
            public void onNext(JsonObject s) {
                super.onNext(s);
                Log.e("请求结果", "Json数据----->" + s);
            }
        });
    }

    private void initPtr() {
        ptr.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                page = 1;
                initData();
            }
        });
    }

    @Override
    public void onLoadMoreRequested() {
        page++;
        initData();
    }

    private void initClick() {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ChooseActivity.class);
                startActivity(intent);
            }
        });
        btn_database.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initDatabase();
            }
        });
    }

    /**
     * 数据库测试逻辑
     */
    private void initDatabase() {
        DBManager dbManager = DBManager.getInstance(this);
        for (int i = 0; i < 5; i++) {
            User user = new User();
            user.setId(i + "");
            user.setAge(i * 3 + "");
            user.setName("第" + i + "人");
            dbManager.insertUser(user);
        }
        List<User> userList = dbManager.queryUserList();
        for (User user : userList) {
            Log.e("TAG", "queryUserList--before-->" + user.getId() + "--" + user.getName() + "--" + user.getAge());
            if (user.getId().equals("0")) {
                dbManager.deleteUser(user);
            }
            if (user.getId().equals("3")) {
                user.setAge(10 + "");
                dbManager.updateUser(user);
            }
        }
        userList = dbManager.queryUserList();
        for (User user : userList) {
            Log.e("TAG", "queryUserList--after--->" + user.getId() + "---" + user.getName() + "--" + user.getAge());
        }
    }
}
