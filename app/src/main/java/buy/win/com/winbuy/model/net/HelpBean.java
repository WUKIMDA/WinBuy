package buy.win.com.winbuy.model.net;

import java.util.List;

/**
 * Created by BUTTON on 2017-06-15.
 */

public class HelpBean extends BaseBean {

    /**
     * helpList : [{"id":1,"title":"购物指南"},{"id":2,"title":"配送方式"},{"id":3,"title":"售后服务"},{"id":4,"title":"产品更新"}]
     */

    private List<HelpListBean> helpList;

    public void setHelpList(List<HelpListBean> helpList) {
        this.helpList = helpList;
    }

    public List<HelpListBean> getHelpList() {
        return helpList;
    }

    public static class HelpListBean {
        /**
         * id : 1
         * title : 购物指南
         */

        private int id;
        private String title;

        public void setId(int id) {
            this.id = id;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }
    }
}
