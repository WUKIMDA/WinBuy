package buy.win.com.winbuy.view.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import buy.win.com.winbuy.R;
import buy.win.com.winbuy.model.net.AddressBean;
import buy.win.com.winbuy.model.net.DelectBean;
import buy.win.com.winbuy.presenter.ApiService;
import buy.win.com.winbuy.utils.RetrofitUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by demo on 2017/6/16.
 */

public class AddressListAdapter extends BaseAdapter {


    public Context mContext;

    public AddressListAdapter(Context context) {
        mContext = context;
    }

    List<AddressBean.AddressListBean> mList = new ArrayList<>();

    public void setList(List<AddressBean.AddressListBean> list) {
        mList = list;

        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (mList != null) {
            return mList.size();
        }

        return 0;
    }


    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;
        if (convertView == null) {

            convertView = LayoutInflater.from(mContext).inflate(R.layout.address_list, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {

            holder = (ViewHolder) convertView.getTag();
        }

        holder.setData(mList.get(position));


        return convertView;
    }

    class ViewHolder {
        @Bind(R.id.name)
        TextView mName;
        @Bind(R.id.phone)
        TextView mPhone;
        @Bind(R.id.address)
        TextView mAddress;
        private int mId;
        private AddressBean.AddressListBean currentBean;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);

            view.setOnLongClickListener(new View.OnLongClickListener() {

                @Override
                public boolean onLongClick(View v) {

                    //Log.e("onlongclick", "onLongClick =" +"长按的条目");

                    AlertDialog.Builder bulider = new AlertDialog
                            .Builder(mContext)
                            .setTitle("您确定删除吗?")
                            .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            })
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    //删除条目  请求网络
                                    ApiService deleteService = RetrofitUtils.getService();

                                    deleteService.deleteAddress(mId).enqueue(new Callback<DelectBean>() {
                                        @Override
                                        public void onResponse(Call<DelectBean> call, Response<DelectBean> response) {
                                            if (response.isSuccessful()) {
                                                //把当前条目的数据从集合中清除掉
                                                mList.remove(currentBean);
                                                //删除成功
                                                notifyData();
                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<DelectBean> call, Throwable t) {

                                        }
                                    });

                                }
                            });


                    bulider.show();
                    return false;
                }
            });
        }

        private void notifyData() {
            notifyDataSetChanged();
        }

        public void setData(AddressBean.AddressListBean addressListBean) {
            this.currentBean = addressListBean;
            mId = addressListBean.getId();
            mName.setText(addressListBean.getName());
            mPhone.setText(addressListBean.getPhoneNumber());
            mAddress.setText(addressListBean.getProvince() + addressListBean.getAddressDetail());

        }
    }
}
