package buy.win.com.winbuy.model.net;

import java.util.List;

/**
 * Created by BUTTON on 2017-06-15.
 */

public class CategoryAllBean extends BaseBean{


    private List<CategoryBean> category;

    public void setCategory(List<CategoryBean> category) {
        this.category = category;
    }

    public List<CategoryBean> getCategory() {
        return category;
    }

    public static class CategoryBean {
        /**
         * id : 1
         * isLeafNode : false
         * name : 妈妈专区
         * parentId : 0
         * pic : /images/category/ym.png
         * tag : 妈妈内衣  祛纹纤体
         */

        private int id;
        private boolean isLeafNode;
        private String name;
        private int parentId;
        private String pic;
        private String tag;

        public void setId(int id) {
            this.id = id;
        }

        public void setIsLeafNode(boolean isLeafNode) {
            this.isLeafNode = isLeafNode;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setParentId(int parentId) {
            this.parentId = parentId;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public int getId() {
            return id;
        }

        public boolean getIsLeafNode() {
            return isLeafNode;
        }

        public String getName() {
            return name;
        }

        public int getParentId() {
            return parentId;
        }

        public String getPic() {
            return pic;
        }

        public String getTag() {
            return tag;
        }

        @Override
        public String toString() {
            return "CategoryBean{" +
                    "id=" + id +
                    ", isLeafNode=" + isLeafNode +
                    ", name='" + name + '\'' +
                    ", parentId=" + parentId +
                    ", pic='" + pic + '\'' +
                    ", tag='" + tag + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "CategoryAllBean{" +
                "category=" + category +
                '}';
    }
}
