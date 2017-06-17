package buy.win.com.winbuy.model.net;

/**
 * Created by 林特烦 on 2017/6/16.
 */

public class UserVersionBean {
    /**
     * response : version
     * version : {"url":"/apk/ec_shop2.0.apk","version":2}
     */
    public String response;
    /**
     * url : /apk/ec_shop2.0.apk
     * version : 2
     */

    public VersionBean version;

    public static class VersionBean {
        public String url;
        public int version;
    }
}
