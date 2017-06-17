package buy.win.com.winbuy.view.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import buy.win.com.winbuy.R;


/**
 * Created by demo on 2017/6/15.
 */

public class MineHomeRelativeLayout extends RelativeLayout {

    public MineHomeRelativeLayout(Context context) {
        this(context,null);
    }

    public MineHomeRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

        View view = LayoutInflater.from(context).inflate(R.layout.view_mine_home_relativelayout, null);

        TextView mText = (TextView) view.findViewById(R.id.tv);

        TypedArray ob = context.obtainStyledAttributes(attrs, R.styleable.MineHomeRelativeLayout);

        //读取自定义的属性
        String string = ob.getString(R.styleable.MineHomeRelativeLayout_mivText);
        mText.setText(string);
        ob.recycle();
        addView(view);
    }
}
