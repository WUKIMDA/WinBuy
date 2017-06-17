package buy.win.com.winbuy.model.net;

import java.util.List;

/**
 * Created by BUTTON on 2017-06-16.
 */

public class CommentDataBean extends  BaseBean{

    /**
     * comment : [{"content":"裙子不错","time":2014,"title":"裙子","username":"赵日天"},{"content":"好裙子","time":2015,"title":"裙子","username":"李"}]
     */

    private List<CommentBean> comment;

    public void setComment(List<CommentBean> comment) {
        this.comment = comment;
    }

    public List<CommentBean> getComment() {
        return comment;
    }



    public static class CommentBean {
        /**
         * content : 裙子不错
         * time : 2014
         * title : 裙子
         * username : 赵日天
         */

        private String content;
        private int time;
        private String title;
        private String username;

        public void setContent(String content) {
            this.content = content;
        }

        public void setTime(int time) {
            this.time = time;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getContent() {
            return content;
        }

        public int getTime() {
            return time;
        }

        public String getTitle() {
            return title;
        }

        public String getUsername() {
            return username;
        }

        @Override
        public String toString() {
            return "CommentBean{" +
                    "content='" + content + '\'' +
                    ", time=" + time +
                    ", title='" + title + '\'' +
                    ", username='" + username + '\'' +
                    '}';
        }
    }
}
