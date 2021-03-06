package buy.win.com.winbuy.view.fragment;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import buy.win.com.winbuy.R;
import buy.win.com.winbuy.model.net.CategoryAllBean;
import buy.win.com.winbuy.presenter.CategoryPresenter;
import buy.win.com.winbuy.view.activity.SearchActivity;
import buy.win.com.winbuy.view.adapter.CategoryListAdapter;
import buy.win.com.winbuy.view.adapter.CategoryRcvAdapter;

/**
 * Created by Administrator on 2017/6/15 0015.
 */

public class CategoryFragment extends Fragment implements AdapterView.OnItemClickListener {

    @Bind(R.id.category_list)
    ListView mCategoryList;
    @Bind(R.id.category_rcv)
    RecyclerView mCategoryRcv;
    @Bind(R.id.et_searchtext_search)
    TextView mEtSearchtextSearch;
    @Bind(R.id.linearLayout)
    LinearLayout mLinearLayout;
    private List<CategoryAllBean.CategoryBean> mDatas = new ArrayList<>();
    private CategoryListAdapter mListAdapter;
    private List<CategoryAllBean.CategoryBean> mLvDatas = new ArrayList<>();
    private int mSelectedId;
    private Context mContext;
    private CategoryRcvAdapter mRcvAdapter;
    private List<CategoryAllBean.CategoryBean> mRcvDatas = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        mContext = getActivity();

        View root = inflater.inflate(R.layout.fragment_category, null);
        ButterKnife.bind(this, root);
        new CategoryPresenter(this).loadCategoryData();
        mListAdapter = new CategoryListAdapter(mContext);
        mCategoryList.setAdapter(mListAdapter);
        mCategoryRcv.setLayoutManager(new LinearLayoutManager(mContext));
        mRcvAdapter = new CategoryRcvAdapter(mContext);
        mCategoryRcv.setAdapter(mRcvAdapter);
        return root;
    }

    private static final String TAG = "CategoryFragment";

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG, "onActivityCreated: " + mDatas.toString());
    }

    private void recDataSet() {
        mRcvDatas.clear();
        for (int i = 0; i < mDatas.size(); i++) {
            if (mDatas.get(i).getParentId() == mSelectedId) {
                mRcvDatas.add(mDatas.get(i));
            }
        }
        mRcvAdapter.setDatas(mDatas, mSelectedId, mRcvDatas);
    }

    private void listDataSet() {
        mLvDatas = new ArrayList<>();
        for (int i = 0; i < mDatas.size(); i++) {
            if (mDatas.get(i).getParentId() == 0) {
                mLvDatas.add(mDatas.get(i));
            }
        }
        mListAdapter.setDatas(mLvDatas);
        mSelectedId = mLvDatas.get(0).getId();
        if (mCategoryList == null) {
            return;
        }
        mCategoryList.setOnItemClickListener(this);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    public void setDatas(List<CategoryAllBean.CategoryBean> datas) {
        mDatas = datas;
        //     Log.d(TAG, "setDatas: " + mDatas.toString());
        listDataSet();
        recDataSet();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        mListAdapter.setSelection(position);
        mSelectedId = mLvDatas.get(position).getId();
        mListAdapter.notifyDataSetChanged();
        recDataSet();
    }

    @Override
    public void onStart() {
        super.onStart();
        ((AppCompatActivity) mContext).getSupportActionBar().hide();
    }

    public void onConnectError(String message) {
        getFragmentManager().beginTransaction().replace(R.id.main_fragment_container, new ConnectionErrorFragment()).commit();
        Toast.makeText(getActivity(), "网络连接失败", Toast.LENGTH_SHORT).show();
    }

    public void onServerBug(int code) {
        getFragmentManager().beginTransaction().replace(R.id.main_fragment_container, new ConnectionErrorFragment()).commit();
        Toast.makeText(getActivity(), "服务器正在修复中", Toast.LENGTH_SHORT).show();
    }

    public void onSuccess(CategoryAllBean bean) {
        setDatas(bean.getCategory());
    }

    @OnClick(R.id.et_searchtext_search)
    public void onViewClicked() {
        Intent intent = new Intent(mContext, SearchActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(intent);
    }
}
