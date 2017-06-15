package buy.win.com.winbuy.view.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import buy.win.com.winbuy.R;
import buy.win.com.winbuy.view.searchview.mSearchLayout;


/**
 * Created by lenovo on 2017/6/15.
 */

public class SearchActivity extends Activity{
    private mSearchLayout mSearch;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        mSearch = (mSearchLayout)findViewById(R.id.search_layout);
        initData();
    }

    protected void initData() {
        String shareData = "澳洲美食,长沙美食,韩国料理,日本料理,舌尖上的中国,意大利餐,山西菜";
        List<String> skills = Arrays.asList(shareData.split(","));

        String shareHotData ="黑马咖啡,黑马男装,黑马红烧肉";
        List<String> skillHots = Arrays.asList(shareHotData.split(","));

        mSearch.initData(skills, skillHots, new mSearchLayout.setSearchCallBackListener() {
            @Override
            public void Search(String str) {
                //进行联网搜索
                Toast.makeText(SearchActivity.this, str, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void Back() {
                finish();
            }

            @Override
            public void ClearOldData() {
                //清除历史搜索记录  更新记录原始数据
            }
            @Override
            public void SaveOldData(ArrayList<String> AlloldDataList) {
                //保存所有的搜索记录
            }
        });
    }
}
