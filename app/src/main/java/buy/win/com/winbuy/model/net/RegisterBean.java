package buy.win.com.winbuy.model.net;

/**
 * Created by demo on 2017/6/15.
 */

public class RegisterBean extends ErrorBean{


    /**
     * userInfo : {"userid":"113356"}
     */

    private UserInfoBean userInfo;

    public UserInfoBean getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfoBean userInfo) {
        this.userInfo = userInfo;
    }

    public static class UserInfoBean {
        /**
         * userid : 113356
         */

        private String userid;

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }
    }
}
