package buy.win.com.winbuy.view.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;

import buy.win.com.winbuy.R;
import buy.win.com.winbuy.launcher.LauncherView;

/**
 * Created by Administrator on 2017/6/15.
 */

public class SplashActivity extends Activity {
    private Context mContext;
    private LauncherView mLauncherView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        init();

    }

    private void init() {
        mLauncherView = (LauncherView) findViewById(R.id.load_view);
        //mLauncherView.setLayoutAnimationListener(mAnimationListener);
        mLauncherView.setEndAnimationListener(new LauncherView.endAnimation() {
            @Override
            public void endShow() {
                navigateToTutorial();
            }
        });
        
        new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(50);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //开启动画
                        mLauncherView.start();
                    }
                });
            }
        }).start();



    }


      private void navigateToTutorial() {
        Intent intent = new Intent(this, TutorialActivity.class);
        startActivity(intent);
        finish();
    }
}
