package buy.win.com.winbuy.other.xzwtempfile;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import buy.win.com.winbuy.R;
import buy.win.com.winbuy.model.net.BrandBeanBean;

/**
 * Created by Ziwen on 2017/6/18.
 */

public class TjppActivity extends Activity {
   private Context mContext  = this;
    private TjppActivityPresenter mTjppActivityPresenter;
    private RecyclerView mListView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.temp_layout_xzw);
        initView();
    }

    private void initView() {
        mListView = (RecyclerView) findViewById(R.id.lv_temp);
        mTjppActivityPresenter = new TjppActivityPresenter(this);
        mTjppActivityPresenter.loadBrandData();


        mListView.setAdapter( new thisAdapter());
    }
    List<String> mList = new ArrayList<>();
    public void onBrandSuccess(BrandBeanBean bean) {
        List<BrandBeanBean.BrandBean> brand = bean.brand;
        for (int i = 0; i < brand.size(); i++) {
            String key = brand.get(i).key;
            mList.add(key);
        }
    }

    class thisAdapter extends RecyclerView.Adapter{

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View rootView = LayoutInflater.from(mContext).inflate(R.layout.item_topic_plist_recycler, parent, false);
            PlistViewHolder plistViewHolder = new PlistViewHolder(rootView);
            return plistViewHolder;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            PlistViewHolder plistViewHolder = (PlistViewHolder) holder;
            plistViewHolder.setData(position);
        }

        @Override
        public int getItemCount() {
            return mList.size();
        }
    }
    private class PlistViewHolder extends RecyclerView.ViewHolder {


        private TextView mNameTextView;

        public PlistViewHolder(View view) {
            super(view);
            mNameTextView = (TextView) view.findViewById(R.id.tv_topiclist_name);
//            view.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    TopicPlistBean.ProductListBean bean = mList.get(getPosition());
//                    int id = bean.id;
//                    Intent intent = new Intent(mTopicPlistActivity, CommodityActivity.class);
//                    intent.putExtra("pId", String.valueOf(id));
//                    mTopicPlistActivity.startActivity(intent);
//                }
//            });
        }

        public void setData(int position) {
            String s = mList.get(position);
            mNameTextView.setText(s);
        }
    }
}
