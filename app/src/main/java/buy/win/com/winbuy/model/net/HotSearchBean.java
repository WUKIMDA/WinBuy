package buy.win.com.winbuy.model.net;

import java.util.List;

/**
 * Created by lenovo on 2017/6/15.
 */

public class HotSearchBean extends BaseBean{

    private List<String> searchKeywords;

    public List<String> getSearchKeywords() {
        return searchKeywords;
    }

    public void setSearchKeywords(List<String> searchKeywords) {
        this.searchKeywords = searchKeywords;
    }

    @Override
    public String toString() {
        return "HotSearchBean{" +
                "searchKeywords=" + searchKeywords +
                '}';
    }
}
