package buy.win.com.winbuy.launcher;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Author:    Diamond_Lin
 * Version    V1.0
 * Date:      16/10/17 上午11:28
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 16/10/17      Diamond_Lin            1.0                    1.0
 * Why & What is modified:
 */
public class Utils {
    private static final String FILE_NAME = "winbuy";

    /**
     * dp转px
     *
     * @param context 上下文
     * @param dpValue dp值
     * @return px值
     */
    public static int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static void saveBoolean(Context context, String key, boolean value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        sharedPreferences.edit().putBoolean(key, value).apply();
    }

    public static boolean getBoolean(Context context, String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(key, false);
    }

    public static final String SP_KEY_STARTED = "started";

}
