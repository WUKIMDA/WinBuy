package buy.win.com.winbuy.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by demo on 2017/6/15.
 */

public class ShareUtils {

    //Context mContext;
    private static final String NAME = "buy";
    private static final String KEY_MINE_UESRID  = "userid";
    private static final String KEY_MINE_UESRNAME  = "userName";

    private static SharedPreferences getSp(Context context) {
        SharedPreferences sp = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        return sp;

    }

    public static void setUserId(Context context,String userid) {
        SharedPreferences sp = getSp(context);

        sp.edit().putString(KEY_MINE_UESRID,userid).commit();


    }

    public static String getUserId(Context context, String defValue) {

        SharedPreferences sp = getSp(context);

        String userid = sp.getString(KEY_MINE_UESRID, defValue);

        return userid;

    }

    public static void setUserName(Context context,String username) {

        SharedPreferences sp = getSp(context);

        sp.edit().putString(KEY_MINE_UESRNAME,username).commit();


    }

    public static String getUserName(Context context,String defValue) {

        SharedPreferences sp = getSp(context);

        String username = sp.getString(KEY_MINE_UESRNAME, defValue);

        return username;

    }
}
