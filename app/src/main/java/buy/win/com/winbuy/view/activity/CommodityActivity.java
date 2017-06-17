package buy.win.com.winbuy.view.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.joanzapata.android.BaseAdapterHelper;
import com.joanzapata.android.QuickAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import buy.win.com.winbuy.R;
import buy.win.com.winbuy.model.net.CommodityProductBean;
import buy.win.com.winbuy.presenter.CommodityProductPresenter;
import buy.win.com.winbuy.presenter.ScrollViewContainer;
import buy.win.com.winbuy.utils.Constans;
import buy.win.com.winbuy.utils.MyImageLoader;
import buy.win.com.winbuy.utils.StatusBarUtil;
import buy.win.com.winbuy.view.commodityView.GradationScrollView;
import buy.win.com.winbuy.view.commodityView.NoScrollListView;

/**
 * Created by BUTTON on 2017-06-16.
 */

public class CommodityActivity extends Activity implements GradationScrollView.ScrollViewListener {

    @Bind(R.id.scrollview)
    GradationScrollView scrollView;

    @Bind(R.id.slider)
    SliderLayout mSlider;
    @Bind(R.id.ll_good_detail)
    RelativeLayout llTitle;
    @Bind(R.id.ll_offset)
    LinearLayout llOffset;
    @Bind(R.id.iv_good_detai_collect_select)
    ImageView ivCollectSelect;//收藏选中
    @Bind(R.id.iv_good_detai_collect_unselect)
    ImageView ivCollectUnSelect;//收藏未选中
    @Bind(R.id.sv_container)
    ScrollViewContainer container;
    @Bind(R.id.tv_good_detail_title_good)
    TextView tvGoodTitle;
    @Bind(R.id.nlv_good_detial_imgs)
    NoScrollListView nlvImgs;//图片详情
    @Bind(R.id.custom_indicator)
    PagerIndicator mCustomIndicator;
    @Bind(R.id.tv_commdoty_name)
    TextView mTvCommdotyName;
    @Bind(R.id.tv_commdoty_buyLimit)
    TextView mTvCommdotyBuyLimit;
    @Bind(R.id.tv_limitPrice)
    TextView mTvLimitPrice;
    @Bind(R.id.tv_vipPrice)
    TextView mTvVipPrice;
    @Bind(R.id.tv_good_detail_discount)
    TextView mTvGoodDetailDiscount;
    @Bind(R.id.tv_commdoty_inventoryArea)
    TextView mTvCommdotyInventoryArea;
    @Bind(R.id.ll_good_detail_service)
    LinearLayout mLlGoodDetailService;
    @Bind(R.id.tv_good_detail_cate)
    TextView mTvGoodDetailCate;
    @Bind(R.id.tv_commdoty_commentCount)
    TextView mTvCommdotyCommentCount;
    @Bind(R.id.tv_talent_detail_open)
    TextView mTvTalentDetailOpen;
    @Bind(R.id.nlv_)
    NoScrollListView mNlv;
    @Bind(R.id.tv_good_detail_tuodong)
    TextView mTvGoodDetailTuodong;
    @Bind(R.id.tv_good_detail_daodi)
    TextView mTvGoodDetailDaodi;
    @Bind(R.id.iv_good_detai_back)
    ImageView mIvGoodDetaiBack;
    @Bind(R.id.iv_good_detai_shop)
    ImageView mIvGoodDetaiShop;
    @Bind(R.id.iv_good_detai_share)
    ImageView mIvGoodDetaiShare;
    @Bind(R.id.tv_good_detail_shop)
    TextView mTvGoodDetailShop;
    @Bind(R.id.ll_good_detail_collect)
    LinearLayout mLlGoodDetailCollect;
    @Bind(R.id.tv_good_detail_shop_cart)
    TextView mTvGoodDetailShopCart;
    @Bind(R.id.tv_good_detail_buy)
    TextView mTvGoodDetailBuy;
    @Bind(R.id.ll_good_detail_bottom)
    LinearLayout mLlGoodDetailBottom;
    @Bind(R.id.bottom)
    LinearLayout mBottom;

    private QuickAdapter<String> imgAdapter;
    private List<String> imgsUrl;

    private int height;
    private int width;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commodity);

        Intent intent = getIntent();
        if (intent != null) {
            String pId = intent.getStringExtra("pId");
        }
//        loadService(pId);
        loadService();

        ButterKnife.bind(this);
        //透明状态栏
        StatusBarUtil.setTranslucentForImageView(this, llOffset);
        LinearLayout.LayoutParams params1 = (LinearLayout.LayoutParams) llOffset.getLayoutParams();
        params1.setMargins(0, -StatusBarUtil.getStatusBarHeight(this) / 4, 0, 0);
        llOffset.setLayoutParams(params1);

        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mSlider.getLayoutParams();
        params.height = getScreenHeight(this) * 2 / 3;
        mSlider.setLayoutParams(params);


        container = new ScrollViewContainer(getApplicationContext());

        initImgDatas();

        initListeners();
    }

    @Override
    protected void onStart() {
        super.onStart();
        //如果收藏,回显
    }

    //private void loadService(String pId) {
    private void loadService() {
        CommodityProductPresenter commodityProductPresenter = new CommodityProductPresenter(this);
        //TODO:模拟加载第pId是"1"的商品
//        commodityProductPresenter.loadCommdityProductData(pId);
        commodityProductPresenter.loadCommdityProductData("1");
    }

    @OnClick({R.id.iv_good_detai_back, R.id.iv_good_detai_shop, R.id.iv_good_detai_share, R.id.tv_good_detail_shop_cart,
            R.id.tv_good_detail_buy, R.id.tv_good_detail_cate})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_good_detai_back:
                finish();
                break;
            case R.id.iv_good_detai_shop:
                //进入购物车
                break;
            case R.id.iv_good_detai_share:
                //第三方分享
                break;
            case R.id.ll_good_detail_collect:
                //收藏:取反,GONE

                break;
            case R.id.tv_good_detail_shop_cart:
                //加入购物车
                break;
            case R.id.tv_good_detail_buy:
                //立即购买
                break;
            case R.id.tv_good_detail_cate:
                //产品分类选择,popw
                dialogShow();

                break;
        }
    }

    private void dialogShow() {

        Dialog commodityDialog = new Dialog(this, R.style.my_dialog);
        LinearLayout root = (LinearLayout) LayoutInflater.from(this).inflate(
                R.layout.dialog_commodity, null);
        commodityDialog.setContentView(root);
        Window dialogWindow = commodityDialog.getWindow();
        dialogWindow.setGravity(Gravity.BOTTOM);
        dialogWindow.setWindowAnimations(R.style.dialogstyle); // 添加动画
        WindowManager.LayoutParams lp = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        lp.x = 0; // 新位置X坐标
        lp.y = -20; // 新位置Y坐标
        lp.width = (int) getResources().getDisplayMetrics().widthPixels; // 宽度
//		lp.height = WindowManager.LayoutParams.WRAP_CONTENT; // 高度
//		lp.alpha = 9f; // 透明度
        root.measure(0, 0);
        lp.height = root.getMeasuredHeight();
        lp.alpha = 9f; // 透明度
        dialogWindow.setAttributes(lp);
        commodityDialog.setCanceledOnTouchOutside(true);
        commodityDialog.show();
    }


    public int getScreenHeight(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.heightPixels;
    }

    public static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }

    private void initImgDatas() {
        width = getScreenWidth(getApplicationContext());

        imgAdapter = new QuickAdapter<String>(this, R.layout.adapter_good_detail_imgs) {
            @Override
            protected void convert(BaseAdapterHelper helper, String item) {
                ImageView iv = helper.getView(R.id.iv_adapter_good_detail_img);
                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) iv.getLayoutParams();
                params.width = width;
                params.height = width / 2;
                iv.setLayoutParams(params);
                MyImageLoader.getInstance().displayImageCen(getApplicationContext(), item, iv, width, width / 2);
            }
        };

    }

    private void initListeners() {

        ViewTreeObserver vto = mSlider.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                llTitle.getViewTreeObserver().removeGlobalOnLayoutListener(
                        this);
                height = mSlider.getHeight();

                scrollView.setScrollViewListener(CommodityActivity.this);
            }
        });
    }

    /**
     * 滑动监听
     *
     * @param scrollView
     * @param x
     * @param y
     * @param oldx
     * @param oldy
     */
    @Override
    public void onScrollChanged(GradationScrollView scrollView, int x, int y,
                                int oldx, int oldy) {
        // TODO Auto-generated method stub
        if (y <= 0) {   //设置标题的背景颜色
            llTitle.setBackgroundColor(Color.argb((int) 0, 255, 255, 255));
        } else if (y > 0 && y <= height) { //滑动距离小于banner图的高度时，设置背景和字体颜色颜色透明度渐变
            float scale = (float) y / height;
            float alpha = (255 * scale);
            tvGoodTitle.setTextColor(Color.argb((int) alpha, 1, 24, 28));
            llTitle.setBackgroundColor(Color.argb((int) alpha, 255, 255, 255));
        } else {    //滑动到banner下面设置普通颜色
            llTitle.setBackgroundColor(Color.argb((int) 255, 255, 255, 255));
        }
    }

    public void onSuccess(CommodityProductBean bean) {
        CommodityProductBean.ProductBean product = bean.getProduct();

        //大图bigPics
        List<String> bigPicsLists = product.getBigPic();
        imgsUrl = new ArrayList<>();
        for (int i = 0; i < bigPicsLists.size(); i++) {
            imgsUrl.add(Constans.URL_HOST + bigPicsLists.get(i));
            System.out.println("我获取的图片" + Constans.URL_HOST + bigPicsLists.get(i));
        }
        imgAdapter.addAll(imgsUrl);
        nlvImgs.setAdapter(imgAdapter);
        imgAdapter.notifyDataSetChanged();

        //top的详情图
        List<String> picsLists = product.getPics();

        HashMap<String, String> url_maps = new HashMap<String, String>();
        for (int i = 0; i < picsLists.size(); i++) {
            url_maps.put("" + i, Constans.URL_HOST + picsLists.get(i));
        }

        for (String desc : url_maps.keySet()) {
            TextSliderView textSliderView = new TextSliderView(this);
            textSliderView
                    .image(url_maps.get(desc));
            mSlider.addSlider(textSliderView);
            mSlider.stopAutoCycle();
        }

        //基本数据填充
        mTvCommdotyBuyLimit.setText("限购:" + product.getBuyLimit());
        mTvCommdotyCommentCount.setText("商品评论(" + product.getCommentCount() + ")");
        mTvCommdotyInventoryArea.setText(product.getInventoryArea());
        mTvCommdotyName.setText(product.getName());
        mTvLimitPrice.setText("抢购价:" + product.getLimitPrice());
        mTvVipPrice.setText("会员价:" + product.getPrice());
        mTvGoodDetailDiscount.setText("市场价:" + product.getMarketPrice());


    }

}