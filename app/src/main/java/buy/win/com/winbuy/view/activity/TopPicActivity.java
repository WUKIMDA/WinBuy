package buy.win.com.winbuy.view.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import java.util.List;

import buy.win.com.winbuy.R;
import buy.win.com.winbuy.model.net.TopPicBean;
import buy.win.com.winbuy.presenter.TopPicActivityPresenter;
import buy.win.com.winbuy.utils.Constans;
import buy.win.com.winbuy.utils.UrlToBitmap;
import buy.win.com.winbuy.view.adapter.ToppicRecyViewAdapter;

/**
 * Created by Ziwen on 2017/6/17.
 */

public class TopPicActivity extends Activity {

    private RecyclerView mRvToppic;
    private List<TopPicBean.TopicBean> mTopicList;
    private ToppicRecyViewAdapter mToppicRecyViewAdapter;
    private TopPicActivityPresenter mTopPicActivityPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toppic);
        mTopPicActivityPresenter = new TopPicActivityPresenter(this);
        mRvToppic = (RecyclerView) findViewById(R.id.rv_toppic);
        mRvToppic.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        init();
    }

    private void init() {
        mToppicRecyViewAdapter = new ToppicRecyViewAdapter(this);
        mRvToppic.setAdapter(mToppicRecyViewAdapter);
        mTopPicActivityPresenter.loadTopicData();
    }

    public void onTopicSuccess(TopPicBean bean) {
        mTopicList = bean.topic;
        temp();
        mToppicRecyViewAdapter.setListData(mTopicList);
        mToppicRecyViewAdapter.notifyDataSetChanged();
    }
    public int[] heighsArr = new int[10];
    public void temp(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < mTopicList.size(); i++) {
                    Bitmap bitmap = UrlToBitmap.getImageFromNet(Constans.URL_HOST + mTopicList.get(i).pic);
                    heighsArr[i] = 170 * bitmap.getHeight() / bitmap.getWidth();
                }
            }
        }).start();

    }
}
