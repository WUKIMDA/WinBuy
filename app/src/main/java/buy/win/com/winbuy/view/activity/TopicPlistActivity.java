package buy.win.com.winbuy.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;

import buy.win.com.winbuy.R;
import buy.win.com.winbuy.model.net.TopPicBean;
import buy.win.com.winbuy.model.net.TopicPlistBean;
import buy.win.com.winbuy.presenter.PlistActivityPresenter;
import buy.win.com.winbuy.view.adapter.TopicPlistRecyViewAdapter;

/**
 * Created by Ziwen on 2017/6/17.
 */

public class TopicPlistActivity extends Activity implements View.OnClickListener {
    private PlistActivityPresenter mPlistActivityPresenter;
    private RecyclerView mRvToppicPlist;
    private TopicPlistRecyViewAdapter mTopicPlistRecyViewAdapter;
    private Button mBtnSort;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_plist);
        mPlistActivityPresenter = new PlistActivityPresenter(this);
        initView();
        loadView();
    }

    private void initView() {
        mRvToppicPlist = (RecyclerView) findViewById(R.id.rv_toppic_plist);
        (mBtnSort = (Button) findViewById(R.id.btn_topiclist_sort)).setOnClickListener(this);
        mRvToppicPlist.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

    }

    private void loadView() {
        Intent intent = getIntent();
        TopPicBean.TopicBean topicBean = (TopPicBean.TopicBean) intent.getSerializableExtra("bean");
        String id = String.valueOf(topicBean.id);
        mPlistActivityPresenter.loadTopicPlistData(id,"saleDown");

        Toolbar toolbar = (Toolbar) findViewById(R.id.tv_topiclist_title);
        toolbar.setTitle(topicBean.name);

        mTopicPlistRecyViewAdapter = new TopicPlistRecyViewAdapter(this);
        mRvToppicPlist.setAdapter(mTopicPlistRecyViewAdapter);


    }

    public void onTopicPlistSuccess(TopicPlistBean bean) {
        List<TopicPlistBean.ProductListBean> productList = bean.productList;
        if (productList==null||productList.size() == 0) {
            Toast.makeText(this, "服务器数据为空", Toast.LENGTH_SHORT).show();
        }
        mTopicPlistRecyViewAdapter.setListData(productList);
    }

    public void onTopicPlistServerBug(int code) {
        Toast.makeText(this, "服务器数据为空", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_topiclist_sort :
                sort();
                break;
        }
    }
    HashMap<String,String> sortMap = new HashMap<>();
    int count = 1;
    private void sort() {
        sortMap.put("saleDown", "销量降序");
        sortMap.put("priceUp",  "价格升序");
        sortMap.put("priceDown","价格降序");
        sortMap.put("commentDown","评价降序");
        sortMap.put("shelvesDown","上架降序");
        switch (++count%5) {
            case 1:
                mPlistActivityPresenter.loadTopicPlistData("1","saleDown");
                Toast.makeText(this, "销量降序", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                mPlistActivityPresenter.loadTopicPlistData("1","priceUp");
                Toast.makeText(this, "价格升序", Toast.LENGTH_SHORT).show();
                break;
            case 3:
                mPlistActivityPresenter.loadTopicPlistData("1","priceDown");
                Toast.makeText(this, "价格降序", Toast.LENGTH_SHORT).show();
                break;
            case 4:
                mPlistActivityPresenter.loadTopicPlistData("1","commentDown");
                Toast.makeText(this, "评价降序", Toast.LENGTH_SHORT).show();
                break;
            case 5:
                mPlistActivityPresenter.loadTopicPlistData("1","shelvesDown");
                Toast.makeText(this, "上架降序", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}

