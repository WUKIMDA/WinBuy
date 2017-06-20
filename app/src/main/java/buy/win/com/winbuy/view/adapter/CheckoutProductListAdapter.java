package buy.win.com.winbuy.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import buy.win.com.winbuy.R;
import buy.win.com.winbuy.model.net.CheckoutAllBean;
import buy.win.com.winbuy.utils.BeanToString;
import buy.win.com.winbuy.utils.Constant;
import buy.win.com.winbuy.view.activity.CheckoutActivity;

import static buy.win.com.winbuy.R.id.addressInfoname;
import static buy.win.com.winbuy.R.id.paymentList;

/**
 * Created by Ziwen on 2017/6/19.
 */

public class CheckoutProductListAdapter extends RecyclerView.Adapter {
    private CheckoutActivity mCheckoutActivity;

    public CheckoutProductListAdapter(CheckoutActivity checkoutActivity) {
        mCheckoutActivity = checkoutActivity;
    }

    public static final int TYPE_ADDRESS = 0;   // 地址信息
    public static final int TYPE_PRODUCT = 1;  // 商品条目
    public static final int TYPE_OTHER = 2;     // 其他信息

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_ADDRESS;
        }
        if (position == mProductListBeanList.size() + 1) {
            return TYPE_OTHER;
        }
        return TYPE_PRODUCT;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int getItemViewType) {
        Log.e(TAG, "getItemViewType: " + getItemViewType);
        switch (getItemViewType) {
            case TYPE_ADDRESS:
                View addressView = LayoutInflater.from(mCheckoutActivity).inflate(R.layout.item_checkout_address, parent, false);
                AddressViewHolder addressViewHolder = new AddressViewHolder(addressView);
                return addressViewHolder;
            case TYPE_PRODUCT:
                View productlistView = LayoutInflater.from(mCheckoutActivity).inflate(R.layout.item_checkout_productlist, parent, false);
                ProductlistViewHolder productlistViewHolder = new ProductlistViewHolder(productlistView);
                return productlistViewHolder;
            case TYPE_OTHER:
                View otherView = LayoutInflater.from(mCheckoutActivity).inflate(R.layout.item_checkout_other, parent, false);
                OtherViewHolder otherViewHolder = new OtherViewHolder(otherView);
                return otherViewHolder;
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        switch (viewType) {
            case TYPE_ADDRESS:
                AddressViewHolder addressViewHolder = (AddressViewHolder) holder;
                addressViewHolder.setData();
                break;
            case TYPE_PRODUCT:
                ProductlistViewHolder productlistViewHolder = (ProductlistViewHolder) holder;
                productlistViewHolder.setData(position);
                break;
            case TYPE_OTHER:
                OtherViewHolder otherViewHolder = (OtherViewHolder) holder;
                otherViewHolder.setData();
                break;
        }
    }

    @Override
    public int getItemCount() {
        if (mProductListBeanList == null || mProductListBeanList.size() == 0) {
            return 2;
        }
        Log.e(TAG, "mProductListBeanList.size(): "+mProductListBeanList.size() );
        return mProductListBeanList.size() + 2;
    }

    private static CheckoutAllBean.AddressInfoBean mAddressInfoBean = new CheckoutAllBean.AddressInfoBean();

    public void setAddressInfo(CheckoutAllBean.AddressInfoBean addressInfo) {
        mAddressInfoBean = addressInfo;
    }

    public static class AddressViewHolder extends RecyclerView.ViewHolder {
        public View rootView;
        public TextView mAddressInfoname;
        public TextView mAddressInfophoneNumber;
        public TextView mAddressInfoMerge;

        public AddressViewHolder(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.mAddressInfoname = (TextView) rootView.findViewById(addressInfoname);
            this.mAddressInfophoneNumber = (TextView) rootView.findViewById(R.id.addressInfophoneNumber);
            this.mAddressInfoMerge = (TextView) rootView.findViewById(R.id.addressInfoMerge);
        }

        public void setData() {
            mAddressInfoname.setText("收货人:" + mAddressInfoBean.getName());
            mAddressInfophoneNumber.setText(mAddressInfoBean.getPhoneNumber());
            mAddressInfoMerge.setText(BeanToString.addressInfoBean2String(mAddressInfoBean));
        }
    }


    private List<CheckoutAllBean.ProductListBean> mProductListBeanList = new ArrayList<>();

    public void setProductListBeanList(List<CheckoutAllBean.ProductListBean> productListBeanList) {
        this.mProductListBeanList = productListBeanList;
    }

    public class ProductlistViewHolder extends RecyclerView.ViewHolder {
        public View rootView;
        public ImageView mProductListProductPic;
        public TextView mProductListProductName;
        public TextView mProductListProductPrice;
        public TextView mProductListProdNum;
        public TextView mProductListProductProductProperty;

        public ProductlistViewHolder(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.mProductListProductPic = (ImageView) rootView.findViewById(R.id.productList_product_pic);
            this.mProductListProductName = (TextView) rootView.findViewById(R.id.productList_product_name);
            this.mProductListProductProductProperty = (TextView) rootView.findViewById(R.id.productList_product_productProperty);
            this.mProductListProductPrice = (TextView) rootView.findViewById(R.id.productList_product_price);
            this.mProductListProdNum = (TextView) rootView.findViewById(R.id.productList_prodNum);
        }

        public void setData(int position) {
            Log.e(TAG, "setData: " + position);
            position--;
            Log.e(TAG, "setData2: " + position);
            CheckoutAllBean.ProductListBean bean = mProductListBeanList.get(position);
            CheckoutAllBean.ProductListBean.ProductBean beanProduct = bean.getProduct();
            Glide.with(mCheckoutActivity).load(Constant.URL_HOST + beanProduct.getPic()).crossFade().into(mProductListProductPic);
            mProductListProductName.setText(beanProduct.getName());
            int price = beanProduct.getPrice();
            int prodNum = bean.getProdNum();
            mProductListProductPrice.setText("¥ " + String.valueOf(price / prodNum));
            mProductListProdNum.setText(String.valueOf(prodNum));
            List<CheckoutAllBean.ProductListBean.ProductBean.ProductPropertyBean> beanProductProperty = beanProduct.getProductProperty();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < beanProductProperty.size(); i++) {
                CheckoutAllBean.ProductListBean.ProductBean.ProductPropertyBean productPropertyBean = beanProductProperty.get(i);
                sb.append(productPropertyBean.getK() + ":");
                sb.append(productPropertyBean.getK() + " ");
            }
            mProductListProductProductProperty.setText(String.valueOf(prodNum));
        }
    }

    private static final String TAG = "CheckoutProductListAdap";
    private List<CheckoutAllBean.DeliveryListBean> mDeliveryBeanList = new ArrayList<>();

    public void setOtherDeliveryListInfo(List<CheckoutAllBean.DeliveryListBean> deliveryList) {
        mDeliveryBeanList = deliveryList;
    }

    private List<CheckoutAllBean.PaymentListBean> mPaymentBeanList = new ArrayList<>();

    public void setOtherPaymentListInfo(List<CheckoutAllBean.PaymentListBean> paymentList) {
        mPaymentBeanList = paymentList;
    }

    private List<String> mCheckoutPromListInfo = new ArrayList<>();

    public void setCheckoutPromListInfo(List<String> checkoutProm) {
        mCheckoutPromListInfo = checkoutProm;
        //notifyDataSetChanged();
    }
    public RadioGroup mMDeliveryList;
    public RadioGroup mMPaymentList;
    public class OtherViewHolder extends RecyclerView.ViewHolder {
        public View rootView;
        public RadioGroup mDeliveryList;
        public RadioGroup mPaymentList;
        public TextView mCheckoutProm;

        public OtherViewHolder(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.mDeliveryList = (RadioGroup) rootView.findViewById(R.id.deliveryList);
            mMDeliveryList = this.mDeliveryList;
            this.mPaymentList = (RadioGroup) rootView.findViewById(paymentList);
            mMPaymentList = this.mPaymentList;
            this.mCheckoutProm = (TextView) rootView.findViewById(R.id.checkoutProm);
        }

        public void setData() {
            for (int i = 0; i < mDeliveryBeanList.size(); i++) {

                RadioButton radioButton = new RadioButton(mCheckoutActivity);
                radioButton.setText(mDeliveryBeanList.get(i).getDes());
                mDeliveryList.addView(radioButton);
                if (i==0) {
                    radioButton.setChecked(true);
                }
            }
            for (int i = 0; i < mPaymentBeanList.size(); i++) {
                RadioButton radioButton = new RadioButton(mCheckoutActivity);
                radioButton.setText(mPaymentBeanList.get(i).getDes());
                mPaymentList.addView(radioButton);
                if (i==0) {
                    radioButton.setChecked(true);
                }
            }
            StringBuilder sb = new StringBuilder();
            sb.append("享受以下促销:");
            for (int i = 0; i < mCheckoutPromListInfo.size(); i++) {
                sb.append(mCheckoutPromListInfo.get(i) + " ");
            }
            mCheckoutProm.setText(sb.toString());
        }
    }
}
