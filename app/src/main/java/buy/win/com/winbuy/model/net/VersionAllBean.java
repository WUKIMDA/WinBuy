package buy.win.com.winbuy.model.net;

/**
 * Created by BUTTON on 2017-06-15.
 */

public class VersionAllBean extends  BaseBean {


    /**
     * version : {"url":"/apk/ec_shop2.0.apk","version":2}
     */

    private VersionBean version;

    public void setVersion(VersionBean version) {
        this.version = version;
    }

    public VersionBean getVersion() {
        return version;
    }

    public static class VersionBean {
        /**
         * url : /apk/ec_shop2.0.apk
         * version : 2
         */

        private String url;
        private int version;

        public void setUrl(String url) {
            this.url = url;
        }

        public void setVersion(int version) {
            this.version = version;
        }

        public String getUrl() {
            return url;
        }

        public int getVersion() {
            return version;
        }
    }
}
