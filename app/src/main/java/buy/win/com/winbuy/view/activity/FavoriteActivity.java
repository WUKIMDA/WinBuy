package buy.win.com.winbuy.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.itheima.pulltorefreshlib.PullToRefreshGridView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import buy.win.com.winbuy.R;
import buy.win.com.winbuy.model.net.FavoriteBean;
import buy.win.com.winbuy.view.adapter.FavoriteAdapter;

/**
 * Created by demo on 2017/6/20.
 */

public class FavoriteActivity extends AppCompatActivity {

    @Bind(R.id.iv_back)
    ImageView mIvBack;
    @Bind(R.id.delete)
    ImageView mDelete;
    @Bind(R.id.gridview)
    PullToRefreshGridView mGridview;
    //List<FavoriteBean> list = new ArrayList<>();
    @Bind(R.id.full)
    LinearLayout mFull;
    @Bind(R.id.empty)
    LinearLayout mEmpty;
    private FavoriteBean mFavoriteBean;
    private List<FavoriteBean.ProductListBean> mProductList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_favorite);
        ButterKnife.bind(this);
        precessIntent();

        FavoriteAdapter adapter = new FavoriteAdapter(this);
        adapter.setList(mProductList);
        mGridview.setAdapter(adapter);


    }

    private void precessIntent() {
        if (getIntent() != null) {

            boolean booleanExtra = getIntent().getBooleanExtra("null.class", false);

            if (booleanExtra == false) {
                //TODO:返回空图片
                mFull.setVisibility(View.GONE);
                mEmpty.setVisibility(View.VISIBLE);

            }

        }else if(getIntent() != null){
            mFull.setVisibility(View.VISIBLE);
            mEmpty.setVisibility(View.GONE);
            mFavoriteBean = (FavoriteBean) getIntent().getSerializableExtra("FavoriteBean.class");
            //list.add(mFavoriteBean);
            mProductList = mFavoriteBean.getProductList();

        }


    }

    @OnClick({R.id.iv_back, R.id.delete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.delete:
                clickDelete();
                break;
        }
    }

    private void clickDelete() {

    }
}
