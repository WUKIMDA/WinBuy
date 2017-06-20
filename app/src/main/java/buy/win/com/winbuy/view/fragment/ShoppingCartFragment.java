package buy.win.com.winbuy.view.fragment;

import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import buy.win.com.winbuy.R;
import buy.win.com.winbuy.model.net.SC_GoodsInfoBean;
import buy.win.com.winbuy.model.net.SC_StoreInfoBean;
import buy.win.com.winbuy.model.net.SelectCartBean;
import buy.win.com.winbuy.presenter.ShopCartPresenter;
import buy.win.com.winbuy.utils.ShareUtils;
import buy.win.com.winbuy.utils.UiUtils;
import buy.win.com.winbuy.utils.UtilToolForSC;
import buy.win.com.winbuy.utils.UtilsLogForSC;
import buy.win.com.winbuy.view.adapter.ShopcatAdapter;
import in.srain.cube.views.ptr.PtrClassicDefaultHeader;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

import static in.srain.cube.views.ptr.util.PtrLocalDisplay.dp2px;

public class ShoppingCartFragment extends Fragment implements View.OnClickListener, ShopcatAdapter.CheckInterface, ShopcatAdapter.ModifyCountInterface, ShopcatAdapter.GroupEditorListener {
    @Bind(R.id.listView)
    ExpandableListView listView;
    @Bind(R.id.all_checkBox)
    CheckBox allCheckBox;
    @Bind(R.id.total_price)
    TextView totalPrice;
    @Bind(R.id.go_pay)
    TextView goPay;
    @Bind(R.id.order_info)
    LinearLayout orderInfo;
    @Bind(R.id.share_goods)
    TextView shareGoods;
    @Bind(R.id.collect_goods)
    TextView collectGoods;
    @Bind(R.id.del_goods)
    TextView delGoods;
    @Bind(R.id.share_info)
    LinearLayout shareInfo;
    @Bind(R.id.ll_cart)
    LinearLayout llCart;
    @Bind(R.id.mPtrframe)
    PtrFrameLayout mPtrFrame;

    TextView shoppingcatNum;
    Button actionBarEdit;
    @Bind(R.id.layout_empty_shopcart)
    LinearLayout empty_shopcart;
    private Context mContext;
    private double mtotalPrice = 0.00;
    private int mtotalCount = 0;
    //false就是编辑，ture就是完成
    private boolean flag = false;
    private ShopcatAdapter adapter;
    private List<SC_StoreInfoBean> groups = new ArrayList<>(); //组元素的列表
    private Map<String, List<SC_GoodsInfoBean>> childs = new HashMap<>(); //子元素的列表
    private String mUserId;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: ++++++++++++++++++++++++++++++++oncreat+++++++++++++++++++++++++++++++++++");
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_shoppingcart, null);
        mContext = getActivity();
        ButterKnife.bind(this, root);
        initPtrFrame();
        initActionBar();
        mUserId = ShareUtils.getUserId(UiUtils.getContext(), "");
        if (TextUtils.isEmpty(mUserId)){
            Toast.makeText(UiUtils.getContext(),"请登录",Toast.LENGTH_SHORT).show();
        }

//        new ShopCartPresenter(this).loadShopCartFragment("20428");
        new ShopCartPresenter(this).loadShopCartFragment(mUserId);

//        setData();
        //initEvents();
        return root;
    }

    private static final String TAG = "ShoppingCartFragment";

    public void onSuccess(SelectCartBean bean) {
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                BufferedReader reader = null;
//                String result = null;
//                StringBuffer sbf = new StringBuffer();
//                try {
//                    HttpURLConnection connection = (HttpURLConnection) new URL("http://www.wukimda.win:8080/market/selectCart?userId=20428").openConnection();
//                    connection.connect();
//                    InputStream is = connection.getInputStream();
//                    reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
//                    String strRead = null;
//                    while ((strRead = reader.readLine()) != null) {
//                        sbf.append(strRead);
//                    }
//                    reader.close();
//                    result = sbf.toString();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                Log.d(TAG, "requestData: " + result);
//                final SelectCartBean selectCartBean = new Gson().fromJson(result, SelectCartBean.class);
//
//                getActivity().runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//
//
//                    }
//                });
//
//            }
//       }).start();

        setData(bean.getCart());
        setCartNum();
        initEvents();




    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.fragment_shoppingcart);
//        ButterKnife.bind(this);
//        initPtrFrame();
//        initActionBar();
//        setData();
//        initEvents();
//    }

    private void initPtrFrame() {
//        final StoreHouseHeader header=new StoreHouseHeader(this);
//        header.setPadding(dp2px(20), dp2px(20), 0, 0);
//        header.initWithString("xiaoma is good");
        final PtrClassicDefaultHeader header = new PtrClassicDefaultHeader(mContext);
        header.setPadding(dp2px(20), dp2px(20), 0, 0);
        mPtrFrame.setHeaderView(header);
        mPtrFrame.addPtrUIHandler(header);
        mPtrFrame.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                //// TODO: 2017/6/19 0019   下拉刷新  Id已写死;
                Toast.makeText(mContext,"> ~ < 客官莫急,刷新中~",Toast.LENGTH_SHORT).show();
//                new ShopCartPresenter(ShoppingCartFragment.this).loadShopCartFragment("20428");
                new ShopCartPresenter(ShoppingCartFragment.this).loadShopCartFragment(mUserId);

                mPtrFrame.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mPtrFrame.refreshComplete();
                    }
                }, 2000);
            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }
        });
    }


    private void initEvents() {
        actionBarEdit.setOnClickListener(this);
        adapter = new ShopcatAdapter(groups, childs, mContext);
        adapter.setCheckInterface(this);//关键步骤1：设置复选框的接口
        adapter.setModifyCountInterface(this); //关键步骤2:设置增删减的接口
        adapter.setGroupEditorListener(this);//关键步骤3:监听组列表的编辑状态
        listView.setGroupIndicator(null); //设置属性 GroupIndicator 去掉向下箭头
        listView.setAdapter(adapter);
        for (int i = 0; i < adapter.getGroupCount(); i++) {
            listView.expandGroup(i); //关键步骤4:初始化，将ExpandableListView以展开的方式显示
        }
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                int firstVisiablePostion = view.getFirstVisiblePosition();
                int top = -1;
                View firstView = view.getChildAt(firstVisibleItem);
                UtilsLogForSC.i("childCount=" + view.getChildCount());//返回的是显示层面上的所包含的子view的个数
                if (firstView != null) {
                    top = firstView.getTop();
                }
                UtilsLogForSC.i("firstVisiableItem=" + firstVisibleItem + ",fistVisiablePosition=" + firstVisiablePostion + ",firstView=" + firstView + ",top=" + top);
                if (firstVisibleItem == 0 && top == 0) {
                    mPtrFrame.setEnabled(true);
                } else {
                    mPtrFrame.setEnabled(false);
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    //    @Override
//    protected void onResume() {
//        super.onResume();
//        setCartNum();
//    }

    /**
     * 设置购物车的数量
     */
    private void setCartNum() {
        int count = 0;
        for (int i = 0; i < groups.size(); i++) {
            SC_StoreInfoBean group = groups.get(i);
            group.setChoosed(allCheckBox.isChecked());
            List<SC_GoodsInfoBean> Children = childs.get(group.getId());
            if (Children != null){
            for (SC_GoodsInfoBean childs : Children) {
                count++;
            }
            }
        }

        Log.d(TAG, "setCartNum: ....." + count);

        //购物车已经清空
        if (count == 0) {
            clearCart();
        } else {
            shoppingcatNum.setText("(" + count + ")");
        }

    }

    private void clearCart() {
        shoppingcatNum.setText("(0)");
        actionBarEdit.setVisibility(View.GONE);
        llCart.setVisibility(View.GONE);
        Log.d(TAG, "clearCart: " + "cart empty");
        empty_shopcart.setVisibility(View.VISIBLE);//这里发生过错误
    }

    /**
     * 模拟数据<br>
     * 遵循适配器的数据列表填充原则，组元素被放在一个list中，对应着组元素的下辖子元素被放在Map中
     * 其Key是组元素的Id
     */
    private void setData(List<SelectCartBean.CartBean> data) {
        List<SelectCartBean.CartBean> mDatas = data;
        if(mDatas == null || mDatas.size() == 0){ clearCart(); return;}
        Log.d(TAG, "setData: " + mDatas.get(0).getProduct().getName().toString());
        groups = new ArrayList<SC_StoreInfoBean>();
        childs = new HashMap<String, List<SC_GoodsInfoBean>>();


//        for (int i = 1; i < 6; i++) {
//            groups.add(new SC_StoreInfoBean(i + "", "派大星的第" + i + "号店铺"));
//        }

//
//        }
//            List<GoodsInfo> goods = new ArrayList<>();
//            for (int j = 0; j <= i; j++) {
//                int[] img = {R.drawable.cmaz, R.drawable.cmaz, R.drawable.cmaz, R.drawable.cmaz, R.drawable.cmaz, R.drawable.cmaz};
//                //i-j 就是商品的id， 对应着第几个店铺的第几个商品，1-1 就是第一个店铺的第一个商品
//                goods.add(new GoodsInfo(i + "-" + j, "商品", groups.get(i).getName() + "的第" + (j + 1) + "个商品", 255.00 + new Random().nextInt(1500), 1555 + new Random().nextInt(3000), "第一排", "出头天者", img[j], new Random().nextInt(100)));
//            }
        SelectCartBean.CartBean bean = null;
        SelectCartBean.CartBean.ProductBean product = null;
        int groupsId;
        for (int i = 0; i < mDatas.size(); i++) {
            bean = mDatas.get(i);
            product = bean.getProduct();
            groupsId = bean.getProperty().getId();
            if (childs.containsKey(groupsId + "")) {
                childs.get(groupsId + "").add(new SC_GoodsInfoBean(bean.getProductId() + "", product.getName(), "钜惠限购:" + product.getBuyLimit(), product.getPrice(), product.getPrice() + new Random().nextInt(100), bean.getProperty().getK(), bean.getProperty().getV(), product.getPic(), bean.getProductCount()));
            } else {
                List<SC_GoodsInfoBean> goodslist = new ArrayList<>();
                goodslist.add(new SC_GoodsInfoBean(bean.getProductId() + "", product.getName(), "钜惠限购:" + product.getBuyLimit(), product.getPrice(), product.getPrice() + new Random().nextInt(100), bean.getProperty().getK(), bean.getProperty().getV(), product.getPic(), bean.getProductCount()));
                childs.put(groupsId + "", goodslist);
            }
        }
        for (String str : childs.keySet()) {
            groups.add(new SC_StoreInfoBean(str, "派大星的第" + str + "号店铺"));
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "onDestroyView: ++++++++++++++++++++++++++++++ondestry++++++++++++++++++++++++++++++");
    }

    /**
     * 删除操作
     * 1.不要边遍历边删除,容易出现数组越界的情况
     * 2.把将要删除的对象放进相应的容器中，待遍历完，用removeAll的方式进行删除
     */
    private void doDelete() {
        List<SC_StoreInfoBean> toBeDeleteGroups = new ArrayList<SC_StoreInfoBean>(); //待删除的组元素
        for (int i = 0; i < groups.size(); i++) {
            SC_StoreInfoBean group = groups.get(i);
            if (group.isChoosed()) {
                toBeDeleteGroups.add(group);
            }
            List<SC_GoodsInfoBean> toBeDeleteChilds = new ArrayList<SC_GoodsInfoBean>();//待删除的子元素
            List<SC_GoodsInfoBean> child = childs.get(group.getId());
            if (child != null){
            for (int j = 0; j < child.size(); j++) {
                if (child.get(j).isChoosed()) {

                    //// TODO: 2017/6/19 0019 Id已写死     全选      遍历删除
//                    new ShopCartPresenter().deleteSelectCartProduct("20428",child.get(j).getId());
                    new ShopCartPresenter().deleteSelectCartProduct(mUserId,child.get(j).getId());


                    toBeDeleteChilds.add(child.get(j));
                }
            }
            child.removeAll(toBeDeleteChilds);
            }
        }
        groups.removeAll(toBeDeleteGroups);
        //重新设置购物车
        setCartNum();
        adapter.notifyDataSetChanged();

    }


    private void findView(View view) {
        shoppingcatNum = (TextView) view.findViewById(R.id.shoppingcat_num);
        actionBarEdit = (Button) view.findViewById(R.id.actionBar_edit);
        //不知道为什么，ButterKnife不知道BindView
//        empty_shopcart = (LinearLayout) view.findViewById(R.id.layout_empty_shopcart);
    }

    @Override
    public void onStart() {
        super.onStart();
        ((AppCompatActivity)getActivity()).getSupportActionBar().show();
    }

    private void initActionBar() {
        //隐藏标题栏
        AppCompatActivity activity = (AppCompatActivity) mContext;
        ActionBar actionBar = activity.getSupportActionBar();
        if (actionBar != null) {
            //去掉阴影
            actionBar.setElevation(0);
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayShowCustomEnabled(true);
            View view = activity.getLayoutInflater().inflate(R.layout.acitonbar,null);
            findView(view);
            actionBar.setCustomView(view, new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            ActionBar.LayoutParams lp = (ActionBar.LayoutParams) view.getLayoutParams();
            lp.gravity = Gravity.HORIZONTAL_GRAVITY_MASK | Gravity.CENTER_HORIZONTAL;
            actionBar.setCustomView(view, lp);
        }


    }


    /**
     * @param groupPosition 组元素的位置
     * @param isChecked     组元素的选中与否
     *                      思路:组元素被选中了，那么下辖全部的子元素也被选中
     */
    @Override
    public void checkGroup(int groupPosition, boolean isChecked) {
        SC_StoreInfoBean group = groups.get(groupPosition);
        List<SC_GoodsInfoBean> child = childs.get(group.getId());
        if(child != null){
        for (int i = 0; i < child.size(); i++) {
            child.get(i).setChoosed(isChecked);
        }
        if (isCheckAll()) {
            allCheckBox.setChecked(true);//全选
        } else {
            allCheckBox.setChecked(false);//反选
        }
        }
        adapter.notifyDataSetChanged();
        calulate();
    }

    /**
     * @return 判断组元素是否全选
     */
    private boolean isCheckAll() {
        for (SC_StoreInfoBean group : groups) {
            if (!group.isChoosed()) {
                return false;
            }
        }
        return true;
    }

    /**
     * @param groupPosition 组元素的位置
     * @param childPosition 子元素的位置
     * @param isChecked     子元素的选中与否
     */
    @Override
    public void checkChild(int groupPosition, int childPosition, boolean isChecked) {
        boolean allChildSameState = true; //判断该组下面的所有子元素是否处于同一状态
        SC_StoreInfoBean group = groups.get(groupPosition);
        List<SC_GoodsInfoBean> child = childs.get(group.getId());
        for (int i = 0; i < child.size(); i++) {
            //不选全中
            if (child.get(i).isChoosed() != isChecked) {
                allChildSameState = false;
                break;
            }
        }

        if (allChildSameState) {
            group.setChoosed(isChecked);//如果子元素状态相同，那么对应的组元素也设置成这一种的同一状态
        } else {
            group.setChoosed(false);//否则一律视为未选中
        }

        if (isCheckAll()) {
            allCheckBox.setChecked(true);//全选
        } else {
            allCheckBox.setChecked(false);//反选
        }

        adapter.notifyDataSetChanged();
        calulate();

    }

    @Override
    public void doIncrease(int groupPosition, int childPosition, View showCountView, boolean isChecked) {
        SC_GoodsInfoBean good = (SC_GoodsInfoBean) adapter.getChild(groupPosition, childPosition);
        int count = good.getCount();
        String id = good.getId();
        count++;

        //// TODO: 2017/6/19 0019             Id已写死

//        new ShopCartPresenter().updataSelectCartProduct("20428",id,count + "","");
        new ShopCartPresenter().updataSelectCartProduct(mUserId,id,count + "","");


        good.setCount(count);
        ((TextView) showCountView).setText(String.valueOf(count));
        adapter.notifyDataSetChanged();
        calulate();
    }

    /**
     * @param groupPosition
     * @param childPosition
     * @param showCountView
     * @param isChecked
     */
    @Override
    public void doDecrease(int groupPosition, int childPosition, View showCountView, boolean isChecked) {
        SC_GoodsInfoBean good = (SC_GoodsInfoBean) adapter.getChild(groupPosition, childPosition);
        int count = good.getCount();
        String id = good.getId();
        if (count == 1) {
            return;
        }
        count--;
        //// TODO: 2017/6/19 0019             Id已写死
//        new ShopCartPresenter().updataSelectCartProduct("20428",id,count + "","");
        new ShopCartPresenter().updataSelectCartProduct(mUserId,id,count + "","");
        good.setCount(count);
        ((TextView) showCountView).setText("" + count);
        adapter.notifyDataSetChanged();
        calulate();
    }

    /**
     * @param groupPosition
     * @param childPosition 思路:当子元素=0，那么组元素也要删除
     */
    @Override
    public void childDelete(int groupPosition, int childPosition) {
        SC_StoreInfoBean group = groups.get(groupPosition);
        List<SC_GoodsInfoBean> child = childs.get(group.getId());
        //// TODO: 2017/6/19 0019 删除 childItem Id已写死;

//        new ShopCartPresenter().deleteSelectCartProduct("20428",child.get(childPosition).getId());
        new ShopCartPresenter().deleteSelectCartProduct(mUserId,child.get(childPosition).getId());

        child.remove(childPosition);
        if (child.size() == 0) {
            groups.remove(groupPosition);
        }
        adapter.notifyDataSetChanged();
        calulate();

    }

    public void doUpdate(int groupPosition, int childPosition, View showCountView, boolean isChecked) {
        SC_GoodsInfoBean good = (SC_GoodsInfoBean) adapter.getChild(groupPosition, childPosition);
        int count = good.getCount();
        UtilsLogForSC.i("进行更新数据，数量" + count + "");
        ((TextView) showCountView).setText(String.valueOf(count));
        adapter.notifyDataSetChanged();
        calulate();


    }

    @Override
    public void groupEditor(int groupPosition) {

    }

    @OnClick({R.id.all_checkBox, R.id.go_pay, R.id.share_goods, R.id.collect_goods, R.id.del_goods})
    public void onClick(View view) {
        AlertDialog dialog;
        switch (view.getId()) {
            case R.id.all_checkBox:
                doCheckAll();
                break;
            case R.id.go_pay:
                if (mtotalCount == 0) {
                    UtilToolForSC.toast(mContext, "请选择要支付的商品");
                    return;
                }
                dialog = new AlertDialog.Builder(mContext).create();
                dialog.setMessage("总计:" + mtotalCount + "种商品，" + mtotalPrice + "元");
                dialog.setButton(DialogInterface.BUTTON_POSITIVE, "支付", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //// TODO: 2017/6/19 0019  支付中心
                        //   initSettlementCenter();
                        return;

                    }
                });
                dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        return;
                    }
                });
                dialog.show();
                break;
            case R.id.share_goods:
                if (mtotalCount == 0) {
                    UtilToolForSC.toast(mContext, "请选择要分享的商品");
                    return;
                }
                UtilToolForSC.toast(mContext, "分享成功");
                break;
            case R.id.collect_goods:
                if (mtotalCount == 0) {
                    UtilToolForSC.toast(mContext, "请选择要收藏的商品");
                    return;
                }
                UtilToolForSC.toast(mContext, "收藏成功");
                break;
            case R.id.del_goods:
                if (mtotalCount == 0) {
                    UtilToolForSC.toast(mContext, "请选择要删除的商品");
                    return;
                }
                dialog = new AlertDialog.Builder(mContext).create();
                dialog.setMessage("确认要删除该商品吗?");
                dialog.setButton(DialogInterface.BUTTON_POSITIVE, "确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        doDelete();
                    }
                });
                dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        return;
                    }
                });
                dialog.show();
                break;
            case R.id.actionBar_edit:
                flag = !flag;
                setActionBarEditor();
                setVisiable();
                break;
        }
    }

    /**
     * ActionBar标题上点编辑的时候，只显示每一个店铺的商品修改界面
     * ActionBar标题上点完成的时候，只显示每一个店铺的商品信息界面
     */
    private void setActionBarEditor() {
        for (int i = 0; i < groups.size(); i++) {
            SC_StoreInfoBean group = groups.get(i);
            if (group.isActionBarEditor()) {
                group.setActionBarEditor(false);
            } else {
                group.setActionBarEditor(true);
            }
        }
        adapter.notifyDataSetChanged();
    }


    /**
     * 全选和反选
     * 错误标记：在这里出现过错误
     */
    private void doCheckAll() {
        for (int i = 0; i < groups.size(); i++) {
            SC_StoreInfoBean group = groups.get(i);
            group.setChoosed(allCheckBox.isChecked());
            List<SC_GoodsInfoBean> child = childs.get(group.getId());
            if (child != null){
            for (int j = 0; j < child.size(); j++) {
                child.get(j).setChoosed(allCheckBox.isChecked());//这里出现过错误
            }
            }
        }
        adapter.notifyDataSetChanged();
        calulate();
    }

    /**
     * 计算商品总价格，操作步骤
     * 1.先清空全局计价,计数
     * 2.遍历所有的子元素，只要是被选中的，就进行相关的计算操作
     * 3.给textView填充数据
     */
    private void calulate() {
        mtotalPrice = 0.00;
        mtotalCount = 0;
        for (int i = 0; i < groups.size(); i++) {
            SC_StoreInfoBean group = groups.get(i);
            List<SC_GoodsInfoBean> child = childs.get(group.getId());
            if (child != null){
            for (int j = 0; j < child.size(); j++) {
                SC_GoodsInfoBean good = child.get(j);
                if (good.isChoosed()) {
                    mtotalCount++;
                    mtotalPrice += good.getPrice() * good.getCount();
                }}
            }
        }
        totalPrice.setText("￥" + mtotalPrice + "");
        goPay.setText("去支付(" + mtotalCount + ")");
        if (mtotalCount == 0) {
            setCartNum();
        } else {
            shoppingcatNum.setText("(" + mtotalCount + ")");
        }


    }

    private void setVisiable() {
        if (flag) {
            orderInfo.setVisibility(View.GONE);
            shareInfo.setVisibility(View.VISIBLE);
            actionBarEdit.setText("完成");
        } else {
            orderInfo.setVisibility(View.VISIBLE);
            shareInfo.setVisibility(View.GONE);
            actionBarEdit.setText("编辑");
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        adapter = null;
        childs.clear();
        groups.clear();
        mtotalPrice = 0.00;
        mtotalCount = 0;
    }

    //    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        adapter = null;
//        childs.clear();
//        groups.clear();
//        mtotalPrice = 0.00;
//        mtotalCount = 0;
//    }
    // TODO: 跳转到结算中心
    private void initSettlementCenter() {
        Intent intent = new Intent(mContext, SettlementCenterFragment.class);
        startActivity(intent);

    }

    public void onServerBug(int code) {
    }

    public void onConnectError(String message) {
    }
}
