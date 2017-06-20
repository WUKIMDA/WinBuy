package buy.win.com.winbuy.model.net;

import java.util.List;

/**
 * Created by Ziwen on 2017/6/18.
 */

public class BrandBeanBean extends BaseBean{

    public List<BrandBean> brand;

    public static class BrandBean {
        /**
         * id : 1
         * key : 孕妈专区
         * value : [{"id":1218,"name":"雅培","pic":"/images/brand/c.png"},{"id":1219,"name":"贝因美","pic":"/images/brand/d.png"},{"id":1220,"name":"周生生","pic":"/images/brand/a.png"},{"id":1221,"name":"婴姿坊","pic":"/images/brand/e.png"}]
         */

        public int id;
        public String key;
        public List<ValueBean> value;

        public static class ValueBean {
            /**
             * id : 1218
             * name : 雅培
             * pic : /images/brand/c.png
             */

            public int id;
            public String name;
            public String pic;
        }
    }
}
