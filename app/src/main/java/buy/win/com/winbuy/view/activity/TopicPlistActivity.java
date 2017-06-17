package buy.win.com.winbuy.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import java.util.List;

import buy.win.com.winbuy.R;
import buy.win.com.winbuy.model.net.TopPicBean;
import buy.win.com.winbuy.model.net.TopicPlistBean;
import buy.win.com.winbuy.presenter.PlistActivityPresenter;
import buy.win.com.winbuy.view.adapter.TopicPlistRecyViewAdapter;

/**
 * Created by Ziwen on 2017/6/17.
 */

public class TopicPlistActivity extends Activity {
    private PlistActivityPresenter mPlistActivityPresenter;
    private RecyclerView mRvToppicPlist;
    private TopicPlistRecyViewAdapter mTopicPlistRecyViewAdapter;

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

        mRvToppicPlist.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

    }

    private void loadView() {
        Intent intent = getIntent();
        TopPicBean.TopicBean topicBean = (TopPicBean.TopicBean) intent.getSerializableExtra("bean");
        String id = String.valueOf(topicBean.id);
        mPlistActivityPresenter.loadTopicPlistData(id);

        Toolbar toolbar = (Toolbar) findViewById(R.id.tv_topiclist_title);
        toolbar.setTitle(topicBean.name);

        mTopicPlistRecyViewAdapter = new TopicPlistRecyViewAdapter(this);
        mRvToppicPlist.setAdapter(mTopicPlistRecyViewAdapter);
    }

    public void onTopicPlistSuccess(TopicPlistBean bean) {
        List<TopicPlistBean.ProductListBean> productList = bean.productList;
        if (productList.size() == 0) {
            Toast.makeText(this, "服务器数据为空", Toast.LENGTH_SHORT).show();
        }
        mTopicPlistRecyViewAdapter.setListData(productList);
    }

    public void onTopicPlistServerBug(int code) {
        Toast.makeText(this, "服务器数据为空", Toast.LENGTH_SHORT).show();
    }
}

