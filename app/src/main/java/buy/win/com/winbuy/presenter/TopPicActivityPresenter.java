package buy.win.com.winbuy.presenter;

import android.graphics.Bitmap;
import android.util.Log;

import buy.win.com.winbuy.model.net.TopPicBean;
import buy.win.com.winbuy.utils.Constans;
import buy.win.com.winbuy.utils.RetrofitUtil;
import buy.win.com.winbuy.utils.UiUtils;
import buy.win.com.winbuy.utils.UrlToBitmap;
import buy.win.com.winbuy.view.activity.TopPicActivity;

/**
 * Created by Ziwen on 2017/6/17.
 */

public class TopPicActivityPresenter extends BaseNetPresenter<TopPicBean> {
    private TopPicActivity mTopPicActivity;

    public TopPicActivityPresenter(TopPicActivity topPicActivity) {
        mTopPicActivity = topPicActivity;
    }

    public void loadTopicData() {
        RetrofitUtil.getApiService().getTopicAllProduct("1", "10").enqueue(mCallBack);
    }

    @Override
    public void onConnectError(String message) {

    }

    @Override
    public void onServerBug(int code) {

    }

    @Override
    public void onSuccess(TopPicBean bean) {
        mTopPicActivity.onTopicSuccess(bean);
    }

    public float[] mHeighsArr;
    public Bitmap[] mBitmapsArr;

    public void updateHeightsArr() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mHeighsArr = new float[mTopPicActivity.mTopicList.size()];
                mBitmapsArr = new Bitmap[mTopPicActivity.mTopicList.size()];
                for (int i = 0; i < mTopPicActivity.mTopicList.size(); i++) {
                    Bitmap bitmap = UrlToBitmap.getImageFromNet(Constans.URL_HOST + mTopPicActivity.mTopicList.get(i).pic);
                    mBitmapsArr[i] = bitmap;
                    mHeighsArr[i] = (float) bitmap.getHeight() / (float) bitmap.getWidth();
                    Log.e("TopPicActivityPresenter", "run: "+ mHeighsArr[i] );
                }
                UiUtils.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mTopPicActivity.mToppicRecyViewAdapter.notifyDataSetChanged();
                    }
                });
            }
        }).start();

    }
}
