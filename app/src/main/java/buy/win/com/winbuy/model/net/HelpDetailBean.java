package buy.win.com.winbuy.model.net;

import java.util.List;

/**
 * Created by BUTTON on 2017-06-15.
 */

public class HelpDetailBean extends  BaseBean {

    /**
     * helpDetailList : [{"content":"不退","title":"购买的商品如何退货？"}]
     */

    private List<HelpDetailListBean> helpDetailList;

    public void setHelpDetailList(List<HelpDetailListBean> helpDetailList) {
        this.helpDetailList = helpDetailList;
    }

    public List<HelpDetailListBean> getHelpDetailList() {
        return helpDetailList;
    }

    public static class HelpDetailListBean {
        /**
         * content : 不退
         * title : 购买的商品如何退货？
         */

        private String content;
        private String title;

        public void setContent(String content) {
            this.content = content;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public String getTitle() {
            return title;
        }
    }
}
