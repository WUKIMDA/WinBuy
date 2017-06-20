package buy.win.com.winbuy.utils;

import java.util.List;

import buy.win.com.winbuy.model.net.CheckoutAllBean;

/**
 * Created by Ziwen on 2017/6/19.
 */

public class BeanToString {
    public static String addressInfoBean2String(CheckoutAllBean.AddressInfoBean bean) {
        StringBuilder sb = new StringBuilder();
        sb.append("收货地址:" + bean.getProvince() + bean.getCity() + bean.getAddressArea());
        return sb.toString();
    }

    // 总计
    public static String checkoutAddupBean2String(CheckoutAllBean.CheckoutAddupBean bean){
        StringBuilder sb = new StringBuilder();
        sb.append("运费:" + bean.getFreight())
                .append("商品数量总计:"+bean.getTotalCount())
                .append("商品金额总计:"+bean.getTotalPrice())
                .append("商品积分总计:"+bean.getTotalPoint());
        return sb.toString();
    }

    // 享受促销信息
    public static String CheckoutProm2String(List<String> list) {
        StringBuilder sb = new StringBuilder();
        sb.append("享受促销信息:");
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
        }
        return sb.toString();
    }


}
