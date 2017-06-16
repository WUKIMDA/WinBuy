package buy.win.com.winbuy.other.xzwtempfile;


public class AppBean {

    private int mDrawable;
    private String mName;
    private float mRating;

    public AppBean(String name, int drawable, float rating) {
        mName = name;
        mDrawable = drawable;
        mRating = rating;
    }

    public float getRating() {
        return mRating;
    }

    public int getDrawable() {
        return mDrawable;
    }

    public String getName() {
        return mName;
    }
}

