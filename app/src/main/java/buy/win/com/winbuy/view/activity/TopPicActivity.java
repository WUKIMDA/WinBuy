package buy.win.com.winbuy.view.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import java.util.List;

import buy.win.com.winbuy.R;
import buy.win.com.winbuy.model.net.TopPicBean;
import buy.win.com.winbuy.presenter.TopPicActivityPresenter;
import buy.win.com.winbuy.view.adapter.ToppicRecyViewAdapter;

/**
 * Created by Ziwen on 2017/6/17.
 */

public class TopPicActivity extends Activity {

    private RecyclerView mRvToppic;
    public List<TopPicBean.TopicBean> mTopicList;
    public ToppicRecyViewAdapter mToppicRecyViewAdapter;
    public TopPicActivityPresenter mTopPicActivityPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toppic);
        mTopPicActivityPresenter = new TopPicActivityPresenter(this);
        initView();
    }

    private void initView() {
        mRvToppic = (RecyclerView) findViewById(R.id.rv_toppic);
        mRvToppic.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        mToppicRecyViewAdapter = new ToppicRecyViewAdapter(this);
        mTopPicActivityPresenter.loadTopicData();

        mRvToppic.setAdapter(mToppicRecyViewAdapter);
    }

    public void onTopicSuccess(TopPicBean bean) {
        mTopicList = bean.topic;
        mTopPicActivityPresenter.updateHeightsArr();

        mToppicRecyViewAdapter.setListData(mTopicList);
    }
}
