package buy.win.com.winbuy.application;

import android.app.Application;

import buy.win.com.winbuy.utils.UiUtils;

/**
 * Created by BUTTON on 2017-06-15.
 */

public class WinBuyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //工具类的初始化
        UiUtils.init(this);
    }
}
