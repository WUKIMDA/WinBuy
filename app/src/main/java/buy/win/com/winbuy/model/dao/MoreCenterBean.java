package buy.win.com.winbuy.model.dao;

/**
 * Created by 林特烦 on 2017/6/17.
 */

public class MoreCenterBean {
    private String title;
    private int imageId;

    public MoreCenterBean() {
        super();
    }

    public MoreCenterBean(String title, int imageId) {
        super();
        this.title = title;
        this.imageId = imageId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
