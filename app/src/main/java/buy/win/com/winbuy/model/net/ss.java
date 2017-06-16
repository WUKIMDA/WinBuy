package buy.win.com.winbuy.model.net;

import java.util.List;

/**
 * Created by BUTTON on 2017-06-15.
 */

public class ss {

    /**
     * response : searchrecommend
     * searchKeywords : ["帽子","时尚女裙","时尚秋装","韩版外套","情女装","女鞋","韩版棉袄","韩版秋装","女士外套"]
     */

    private String response;
    private List<String> searchKeywords;

    public void setResponse(String response) {
        this.response = response;
    }

    public void setSearchKeywords(List<String> searchKeywords) {
        this.searchKeywords = searchKeywords;
    }

    public String getResponse() {
        return response;
    }

    public List<String> getSearchKeywords() {
        return searchKeywords;
    }
}
