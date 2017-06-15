package buy.win.com.winbuy.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.viewpagerindicator.CirclePageIndicator;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import buy.win.com.winbuy.R;
import buy.win.com.winbuy.launcher.Utils;

/**
 * Created by Administrator on 2017/6/15.
 */

public class TutorialActivity extends Activity {


    @Bind(R.id.vp)
    ViewPager mVp;
    @Bind(R.id.indicator)
    CirclePageIndicator mIndicator;
    @Bind(R.id.start)
    Button mStart;
    private int[] mImages = {R.mipmap.guide_1, R.mipmap.guide_2, R.mipmap.guide_3};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        mVp.setAdapter(mPagerAdapter);
        mIndicator.setViewPager(mVp);

        //设置监听器
        mVp.addOnPageChangeListener(mOnPageChangeListener);
    }

    private ViewPager.OnPageChangeListener mOnPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            //最后一个页面显示按钮
            if (position == mImages.length - 1) {
                mStart.setVisibility(View.VISIBLE);
            } else {
                mStart.setVisibility(View.GONE);
            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    private PagerAdapter mPagerAdapter = new PagerAdapter() {

        @Override
        public int getCount() {
            return mImages.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = new ImageView(TutorialActivity.this);
            imageView.setImageResource(mImages[position]);
            //缩放图片
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);//拉伸 不是等比缩放
            container.addView(imageView);

            //返回标记
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    };


    @OnClick(R.id.start)
    public void onClick() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
        Utils.saveBoolean(this, Utils.SP_KEY_STARTED, true);
    }
}
