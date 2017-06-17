package buy.win.com.winbuy.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by 林特烦 on 2017/6/17.
 */

public class SPUtils {
    private static final String FILE_NAME = "winbuy";
    private static final float DEFAULT_VERSION = 1.0f;
    private static final String VERSION = "version";

    public static void saveVersion(Context context, float value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        sharedPreferences.edit().putFloat(VERSION, value).apply();
    }

    public static float getVersion(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getFloat(VERSION, DEFAULT_VERSION);
    }
}