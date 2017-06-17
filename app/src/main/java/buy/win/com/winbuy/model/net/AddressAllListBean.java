package buy.win.com.winbuy.model.net;

import java.util.List;

/**
 * Created by BUTTON on 2017-06-15.
 */

public class AddressAllListBean extends BaseBean {


    private List<AddressListBean> addressList;

    public void setAddressList(List<AddressListBean> addressList) {
        this.addressList = addressList;
    }

    public List<AddressListBean> getAddressList() {
        return addressList;
    }

    public static class AddressListBean {

        private String addressArea;
        private String addressDetail;
        private String city;
        private int id;
        private int isDefault;
        private String name;
        private String phoneNumber;
        private String province;
        private String zipCode;

        public void setAddressArea(String addressArea) {
            this.addressArea = addressArea;
        }

        public void setAddressDetail(String addressDetail) {
            this.addressDetail = addressDetail;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setIsDefault(int isDefault) {
            this.isDefault = isDefault;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public void setZipCode(String zipCode) {
            this.zipCode = zipCode;
        }

        public String getAddressArea() {
            return addressArea;
        }

        public String getAddressDetail() {
            return addressDetail;
        }

        public String getCity() {
            return city;
        }

        public int getId() {
            return id;
        }

        public int getIsDefault() {
            return isDefault;
        }

        public String getName() {
            return name;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public String getProvince() {
            return province;
        }

        public String getZipCode() {
            return zipCode;
        }
    }
}
