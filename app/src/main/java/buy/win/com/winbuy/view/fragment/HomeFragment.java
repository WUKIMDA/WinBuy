package buy.win.com.winbuy.view.fragment;

import android.animation.ArgbEvaluator;
import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechUtility;
import com.iflytek.cloud.ui.RecognizerDialog;
import com.iflytek.cloud.ui.RecognizerDialogListener;
import com.ljs.lovelytoast.LovelyToast;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.zhouwei.mzbanner.MZBannerView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import buy.win.com.winbuy.R;
import buy.win.com.winbuy.model.net.HomeAllBean;
import buy.win.com.winbuy.model.net.LimitbuyBean;
import buy.win.com.winbuy.model.net.ResultBean;
import buy.win.com.winbuy.presenter.HomePresenter;
import buy.win.com.winbuy.presenter.HomePresenterLimit;
import buy.win.com.winbuy.view.activity.SearchActivity;
import buy.win.com.winbuy.view.adapter.HomeFrgmRecyViewAdapter;

/**
 * Created by Ziwen on 2017/6/15.
 */

public class HomeFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = "HomeFragment";
    private static final int REQUEST_CODE = 111;

    public MZBannerView mMZBanner;
    @Bind(R.id.rl_title)
    RelativeLayout mRlTitle;
    private View mRootView;
    private HomePresenter mHomePresenter;
    private HomePresenterLimit mHomePresenterLimit;
    private RecyclerView mRv_homeFrgm;
    private HomeFrgmRecyViewAdapter mHomeFrgmRecyViewAdapter;
    private Gson mGson = new Gson();
    private EditText mEditText;
    private Button mBtnHomeConnectDefeate;
    private LinearLayout mBtnHomeConnectSuccess;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        SpeechUtility.createUtility(getActivity(), SpeechConstant.APPID + "=56f22e12");

        mRootView = View.inflate(getActivity(), R.layout.fragment_home, null);

        mHomePresenter = new HomePresenter(HomeFragment.this);
        mHomePresenterLimit = new HomePresenterLimit(HomeFragment.this);

        tempBtn(mRootView);
        initRecyclerView(mRootView);
        ButterKnife.bind(this, mRootView);
        initView(mRootView);
        return mRootView;
    }

    private void initRecyclerView(View rootView) {
        mRv_homeFrgm = (RecyclerView) rootView.findViewById(R.id.rv_home_fragment);
        mRv_homeFrgm.setLayoutManager(new LinearLayoutManager(getActivity()));
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        //默认分两列,但是头View需要占两格
        //http://blog.csdn.net/kyleceshen/article/details/50296273
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return position == 0 ? 2 : 1;
            }
        });
        mRv_homeFrgm.setLayoutManager(gridLayoutManager);

        mHomeFrgmRecyViewAdapter = new HomeFrgmRecyViewAdapter(getActivity());
        // mHomeFrgmRecyViewAdapter.setAppBeanList(mHomePresenter.getApps());
        mRv_homeFrgm.setAdapter(mHomeFrgmRecyViewAdapter);
    }

    int sumY = 0;
    float distance = 350;  //最远滚动距离，超过这个距离还是最蓝色
    int startColor = 0x00FFFFFF;
    int endColor = 0xffFF4081;
    ArgbEvaluator mEvaluator = new ArgbEvaluator();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mHomePresenter.loadHomeData();
        mHomePresenterLimit.loadHomeBottomData();
        mRv_homeFrgm.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                sumY += dy;
                int bgColor;
                if (sumY < 0) {
                    bgColor = startColor;
                } else if (sumY > distance) {
                    bgColor = endColor;
                } else {
                    bgColor = (int) mEvaluator.evaluate(sumY / distance, startColor, endColor);
                }
                mRlTitle.setBackgroundColor(bgColor);
            }
        });
    }

    private void tempBtn(View rootView) {


        mEditText = (EditText) rootView.findViewById(R.id.btn_search);
        mEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                startActivity(intent);
            }
        });

        ImageView qrcodeView = (ImageView) rootView.findViewById(R.id.iv_qrcode_topbar);
        qrcodeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CaptureActivity.class);
                startActivityForResult(intent, REQUEST_CODE);

            }
        });

        ImageView speakView = (ImageView) rootView.findViewById(R.id.tv_message_topbar);
        speakView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //1.创建RecognizerDialog对象
                RecognizerDialog mDialog = new RecognizerDialog(getActivity(), null);
                //2.设置accent、language等参数
                mDialog.setParameter(SpeechConstant.LANGUAGE, "zh_cn");
                mDialog.setParameter(SpeechConstant.ACCENT, "mandarin");
                //若要将UI控件用于语义理解，必须添加以下参数设置，设置之后onResult回调返回将是语义理解//结果
                // mDialog.setParameter("asr_sch", "1");
                // mDialog.setParameter("nlp_version", "2.0");
                //3.设置回调接口
                mDialog.setListener(mRecognizerDialogListener);//识别的结果
                //4.显示dialog，接收语音输入
                mDialog.show();

            }
        });

    }


    RecognizerDialogListener mRecognizerDialogListener = new RecognizerDialogListener() {

        /**
         *
         * @param recognizerResult
         * @param b 是否是结束 通常标点符号
         */
        @Override
        public void onResult(RecognizerResult recognizerResult, boolean b) {
            //            Toast.makeText(MainActivity.this, recognizerResult.getResultString(), Toast.LENGTH_SHORT).show();
            if (b) {//过滤掉句号
                return;
            }

            ResultBean resultBean = mGson.fromJson(recognizerResult.getResultString(), ResultBean.class);
            List<ResultBean.WsBean> ws = resultBean.getWs();
            String w = "";
            for (int i = 0; i < ws.size(); i++) {
                ResultBean.WsBean wsBean = ws.get(i);
                List<ResultBean.WsBean.CwBean> cw = wsBean.getCw();
                for (int j = 0; j < cw.size(); j++) {
                    ResultBean.WsBean.CwBean cwBean = cw.get(j);
                    w += cwBean.getW();
                }
            }


            Toast.makeText(getActivity(), w, Toast.LENGTH_SHORT).show();
            mEditText.setText(w);
        }

        @Override
        public void onError(SpeechError speechError) {

        }
    };
    //    private void initBanner(View view) {
    //        mMZBanner = (MZBannerView) view.findViewById(R.id.banner);
    //        // 设置页面点击事件
    //        mMZBanner.setBannerPageClickListener(new MZBannerView.BannerPageClickListener() {
    //            @Override
    //            public void onPageClick(View view, int position) {
    //                Toast.makeText(getActivity(), "click page:" + position, Toast.LENGTH_LONG).show();
    //            }
    //        });
    //        Log.e(TAG, "mHomeTopicListsmHomeTopicListsmHomeTopicLists" + mHomeTopicLists.toString());
    //    }


    /**
     * 二维码回调
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /**
         * 处理二维码扫描结果
         */
        if (requestCode == REQUEST_CODE) {
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    //用默认浏览器打开扫描得到的地址
                    Intent intent = new Intent();
                    intent.setAction("android.intent.action.VIEW");
                    Uri content_url = Uri.parse(result.toString());
                    Log.d(TAG, "onActivityResult: " + result);
                    intent.setData(content_url);
                    getActivity().startActivity(intent);
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(getActivity(), "解析二维码失败", Toast.LENGTH_LONG).show();
                }
            }
        }

    }


    public void onHomeSuccess(HomeAllBean bean) {
        setVisibiliti(true);
        List<HomeAllBean.HomeTopicBean> mHomeTopicLists = bean.getHomeTopic();
        mHomeTopicLists.remove(mHomeTopicLists.size() - 1);
        mHomeFrgmRecyViewAdapter.setHomeAllBeenList(mHomeTopicLists);
        //        Toast.makeText(getActivity(), "数据获取成功", Toast.LENGTH_SHORT).show();
    }

    public void onHomeSuccessLimit(LimitbuyBean bean) {
        List<LimitbuyBean.ProductListBean> productList = bean.productList;
        mHomeFrgmRecyViewAdapter.setHomeBottomBeenList(productList);
        Log.e(TAG, "onHomeSuccessLimit: " + productList.toString());
    }

    public void onHomeConnectError(String message) {
        setVisibiliti(false);
        Toast.makeText(getActivity(), "网络连接失败", Toast.LENGTH_SHORT).show();
        reConnecting = false;
    }

    public void onHomeServerBug(int code) {
        setVisibiliti(false);
        Log.d(TAG, "onHomeServerBug " + code);
        Toast.makeText(getActivity(), "服务器正在修复中", Toast.LENGTH_SHORT).show();
        reConnecting = false;
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mMZBanner == null) {
            return;
        }
        mMZBanner.pause();//暂停轮播
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mMZBanner == null) {
            return;
        }
        mMZBanner.start();//开始轮播
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
    }

    private void initView(View mRootView) {
        mBtnHomeConnectDefeate = (Button) mRootView.findViewById(R.id.btn_home_connect_defeate);
        mBtnHomeConnectSuccess = (LinearLayout) mRootView.findViewById(R.id.btn_home_connect_success);

        mBtnHomeConnectDefeate.setOnClickListener(this);
    }

    private boolean reConnecting = false;
    private int reCount = 0;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_home_connect_defeate:
                if (reConnecting) {
                    if (reCount > 150) {
                        LovelyToast.makeText(getActivity(), "臭傻逼!我是比亚迪,你来咬我啊.", LovelyToast.LENGTH_SHORT, LovelyToast.SUCCESS, LovelyToast.LEFT_RIGHT);
                    } else if (reCount > 100) {
                        LovelyToast.makeText(getActivity(), "加油,已经第" + (reCount++) + "次了", LovelyToast.LENGTH_SHORT, LovelyToast.SUCCESS, LovelyToast.LEFT_RIGHT);
                    } else {
                        LovelyToast.makeText(getActivity(), "第" + (reCount++) + "次点击,达到一定次数可激活加速", LovelyToast.LENGTH_SHORT, LovelyToast.SUCCESS, LovelyToast.LEFT_RIGHT);
                    }
                    return;
                }
                Toast.makeText(getActivity(), "重试中...", Toast.LENGTH_SHORT).show();
                mHomePresenter.loadHomeData();
                mHomePresenterLimit.loadHomeBottomData();
                reConnecting = true;
                break;
        }
    }

    private void setVisibiliti(boolean b) {
        if (b) {
            mBtnHomeConnectSuccess.setVisibility(View.VISIBLE);
            mBtnHomeConnectDefeate.setVisibility(View.GONE);
        } else {
            mBtnHomeConnectSuccess.setVisibility(View.GONE);
            mBtnHomeConnectDefeate.setVisibility(View.VISIBLE);
        }
    }
}
