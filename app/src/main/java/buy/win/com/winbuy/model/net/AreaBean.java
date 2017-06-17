package buy.win.com.winbuy.model.net;

import java.util.List;

/**
 * Created by demo on 2017/6/16.
 */

public class AreaBean extends ErrorBean{


    private List<AreaListBean> areaList;

    public List<AreaListBean> getAreaList() {
        return areaList;
    }

    public void setAreaList(List<AreaListBean> areaList) {
        this.areaList = areaList;
    }

    public static class AreaListBean {
        /**
         * id : 1
         * value : 北京
         */

        private String id;
        private String value;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
