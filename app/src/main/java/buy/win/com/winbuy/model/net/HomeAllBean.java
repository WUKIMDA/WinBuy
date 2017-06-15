package buy.win.com.winbuy.model.net;

import java.util.List;

/**
 * Created by BUTTON on 2017-06-15.
 */

public class HomeAllBean {

    private String response;
    private List<HomeTopicBean> homeTopic;

    public void setResponse(String response) {
        this.response = response;
    }

    public void setHomeTopic(List<HomeTopicBean> homeTopic) {
        this.homeTopic = homeTopic;
    }

    public String getResponse() {
        return response;
    }

    public List<HomeTopicBean> getHomeTopic() {
        return homeTopic;
    }

    public static class HomeTopicBean {
        /**
         * id : 123
         * pic : /images/home/image1.jpg
         * title : 活动1
         */

        private int id;
        private String pic;
        private String title;

        public void setId(int id) {
            this.id = id;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getId() {
            return id;
        }

        public String getPic() {
            return pic;
        }

        public String getTitle() {
            return title;
        }
    }
}
