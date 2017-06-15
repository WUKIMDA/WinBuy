package buy.win.com.winbuy.view.activity;

import android.app.Activity;
import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import buy.win.com.winbuy.R;
import buy.win.com.winbuy.view.fragment.CategoryFragment;
import buy.win.com.winbuy.view.fragment.HomeFragment;
import buy.win.com.winbuy.view.fragment.MoreFragment;
import buy.win.com.winbuy.view.fragment.ShopCartFragment;
import buy.win.com.winbuy.view.fragment.UserFragment;

public class MainActivity extends Activity implements RadioGroup.OnCheckedChangeListener {

    @Bind(R.id.main_fragment_container)
    FrameLayout mFrameLayoutContainer;
    @Bind(R.id.rb_home)
    RadioButton mRbHome;
    @Bind(R.id.rb_category)
    RadioButton mRbCategory;
    @Bind(R.id.rb_shopcart)
    RadioButton mRbShopcart;
    @Bind(R.id.rb_user)
    RadioButton mRbUser;
    @Bind(R.id.rb_more)
    RadioButton mRbMore;
    @Bind(R.id.rg_navigation_bar)
    RadioGroup mRgNavigationBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initFragment();
        //默认打开选首页
        selectPageIndex(0);
        mRbHome.setChecked(true);
        //监听底部Tab按钮
        mRgNavigationBar.setOnCheckedChangeListener(this);
        // 测试github
    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        selectFragment(checkedId);
    }

    public void selectFragment(int selectID) {
        int index = 0;
        switch (selectID) {
            case R.id.rb_home:
                index = 0;
                break;
            case R.id.rb_category:
                index = 1;
                break;
            case R.id.rb_shopcart:
                index = 2;
                break;
            case R.id.rb_user:
                index = 3;
                break;
            case R.id.rb_more:
                index = 4;
                break;
        }

        selectPageIndex(index);
    }

    /*选择页面
     * @param index
     */
    private void selectPageIndex(int index) {
        //切换FrameLayout的数据
        getFragmentManager().beginTransaction().replace(R.id.main_fragment_container, mFragmentList.get(index)).commit();
    }

    List<Fragment> mFragmentList = new ArrayList<Fragment>();

    private void initFragment() {
        mFragmentList.add(new HomeFragment());
        mFragmentList.add(new CategoryFragment());
        mFragmentList.add(new ShopCartFragment());
        mFragmentList.add(new UserFragment());
        mFragmentList.add(new MoreFragment());

    }


    @Override
    protected void onStart() {
        super.onStart();
        hideBottomUIMenu();
    }

    /**
     * 隐藏虚拟按键，并且全屏
     */
    protected void hideBottomUIMenu() {
        //隐藏虚拟按键，并且全屏
        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) { // 版本判断
            View v = this.getWindow().getDecorView();
            v.setSystemUiVisibility(View.GONE);//如果是低版本  全部隐藏
        } else if (Build.VERSION.SDK_INT >= 19) {
            //for new api versions.
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
//            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
            //View.SYSTEM_UI_FLAG_HIDE_NAVIGATION  导航栏
            //View.SYSTEM_UI_FLAG_FULLSCREEN    底部虚拟按键
            //View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY 触摸就显示
        }
    }


}
