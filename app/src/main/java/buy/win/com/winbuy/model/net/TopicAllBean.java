package buy.win.com.winbuy.model.net;

import java.util.List;

/**
 * Created by BUTTON on 2017-06-15.
 */

public class TopicAllBean extends BaseBean {


    private List<TopicBean> topic;

    public void setTopic(List<TopicBean> topic) {
        this.topic = topic;
    }

    public List<TopicBean> getTopic() {
        return topic;
    }

    public static class TopicBean {
        /**
         * id : 2
         * name : 儿童玩具聚划算
         * pic : /images/topic/2.jpg
         */

        private int id;
        private String name;
        private String pic;

        public void setId(int id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getPic() {
            return pic;
        }
    }
}
