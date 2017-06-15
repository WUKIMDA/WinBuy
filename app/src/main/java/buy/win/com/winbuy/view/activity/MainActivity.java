package buy.win.com.winbuy.view.activity;

import android.app.Activity;
import android.os.Bundle;

import buy.win.com.winbuy.R;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.print("git冲突测试");
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
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY ;
//            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
            //View.SYSTEM_UI_FLAG_HIDE_NAVIGATION  导航栏
            //View.SYSTEM_UI_FLAG_FULLSCREEN    底部虚拟按键
            //View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY 触摸就显示
        }
    }

}
