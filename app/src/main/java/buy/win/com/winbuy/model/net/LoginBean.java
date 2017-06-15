package buy.win.com.winbuy.model.net;



public class LoginBean extends BaseBean {

    /**
     * userInfo : {"userid":"20428"}
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
         * userid : 20428
         */

        private String userid;

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        @Override
        public String toString() {
            return "UserInfoBean{" +
                    "userid='" + userid + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "LoginBean{" +
                "userInfo=" + userInfo +
                '}';
    }
}
