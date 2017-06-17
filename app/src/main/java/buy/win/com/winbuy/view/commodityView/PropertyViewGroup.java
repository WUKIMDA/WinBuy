package buy.win.com.winbuy.view.commodityView;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import buy.win.com.winbuy.R;

public class PropertyViewGroup<X extends TextView> extends ViewGroup {

    public static final String BTN_MODE = "BTNMODE"; //按钮模式  
    public static final String TEV_MODE = "TEVMODE"; //文本模式  

    private static final String TAG = "IViewGroup";
    private final int HorInterval = 10;    //水平间隔  
    private final int VerInterval = 10;    //垂直间隔  

    private int viewWidth;   //控件的宽度  
    private int viewHeight;  //控件的高度  

    private ArrayList<String> mTexts = new ArrayList<>();
    private Context mContext;
    private int textModePadding = 15;

    //正常样式  
    private float itemTextSize = 18;
    private int itemBGResNor = R.drawable.goods_item_btn_normal;
    private int itemTextColorNor = Color.parseColor("#000000");

    //选中的样式  
    private int itemBGResPre = R.drawable.goods_item_btn_selected;
    private int itemTextColorPre = Color.parseColor("#ffffff");

    public PropertyViewGroup(Context context) {
        this(context, null);
    }

    public PropertyViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    /**修改选中颜色
     * @param color
     */
    public void setItemBGResPre(String color) {
        switch (color) {
            case "红色":
                itemBGResNor = R.drawable.proprety_color_red;
                break;
            case "绿色":
                itemBGResNor = R.drawable.proprety_color_green;
                break;
            case "黄色":
                itemBGResNor = R.drawable.proprety_color_yellow;
                break;
            case "蓝色":
                itemBGResNor = R.drawable.proprety_color_blue;
                break;
        }
    }

    /**
     * 计算控件的大小
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        viewWidth = measureWidth(widthMeasureSpec);
        viewHeight = measureHeight(heightMeasureSpec);
        Log.e(TAG, "onMeasure:" + viewWidth + ":" + viewHeight);
        // 计算自定义的ViewGroup中所有子控件的大小  
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        // 设置自定义的控件MyViewGroup的大小  
        setMeasuredDimension(viewWidth, getViewHeight());
    }


    private int measureWidth(int pWidthMeasureSpec) {
        int result = 0;
        int widthMode = MeasureSpec.getMode(pWidthMeasureSpec);
        int widthSize = MeasureSpec.getSize(pWidthMeasureSpec);
        switch (widthMode) {
            case MeasureSpec.AT_MOST:
            case MeasureSpec.EXACTLY:
                result = widthSize;
                break;
        }
        return result;
    }

    private int measureHeight(int pHeightMeasureSpec) {
        int result = 0;
        int heightMode = MeasureSpec.getMode(pHeightMeasureSpec);
        int heightSize = MeasureSpec.getSize(pHeightMeasureSpec);
        switch (heightMode) {
            case MeasureSpec.UNSPECIFIED:
                result = getSuggestedMinimumHeight();
                break;
            case MeasureSpec.AT_MOST:
            case MeasureSpec.EXACTLY:
                result = heightSize;
                break;
        }
        return result;
    }

    /**
     * 覆写onLayout，其目的是为了指定视图的显示位置，方法执行的前后顺序是在onMeasure之后，因为视图肯定是只有知道大小的情况下，
     * 才能确定怎么摆放
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        // 遍历所有子视图  
        int posLeft = HorInterval;
        int posTop = VerInterval;
        int posRight;
        int posBottom;
        for (int i = 0; i < getChildCount(); i++) {
            View childView = getChildAt(i);
            // 获取在onMeasure中计算的视图尺寸  
            int measureHeight = childView.getMeasuredHeight();
            int measuredWidth = childView.getMeasuredWidth();
            if (posLeft + getNextHorLastPos(i) > viewWidth) {
                posLeft = HorInterval;
                posTop += (measureHeight + VerInterval);
            }
            posRight = posLeft + measuredWidth;
            posBottom = posTop + measureHeight;
            childView.layout(posLeft, posTop, posRight, posBottom);
            posLeft += (measuredWidth + HorInterval);
        }
    }

    //获取控件的自适应高度  
    private int getViewHeight() {
        int viewwidth = HorInterval;
        int viewheight = VerInterval;
        if (getChildCount() > 0) {
            viewheight = getChildAt(0).getMeasuredHeight() + VerInterval;
        }
        for (int i = 0; i < getChildCount(); i++) {
            View childView = getChildAt(i);
            // 获取在onMeasure中计算的视图尺寸  
            int measureHeight = childView.getMeasuredHeight();
            int measuredWidth = childView.getMeasuredWidth();
            if (viewwidth + getNextHorLastPos(i) > viewWidth) {
                viewwidth = HorInterval;
                viewheight += (measureHeight + VerInterval);
            } else {
                viewwidth += (measuredWidth + HorInterval);
            }
        }
        return viewheight;
    }

    private int getNextHorLastPos(int i) {
        return getChildAt(i).getMeasuredWidth() + HorInterval;
    }

    private OnGroupItemClickListener onGroupItemClickListener;

    public void setGroupClickListener(OnGroupItemClickListener listener) {
        onGroupItemClickListener = listener;
        for (int i = 0; i < getChildCount(); i++) {
            final X childView = (X) getChildAt(i);
            final int itemPos = i;
            childView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    onGroupItemClickListener.onGroupItemClick(itemPos);
                    chooseItemStyle(itemPos);
                }
            });
        }
    }

    //选中那个的样式  
    public void chooseItemStyle(int pos) {
        clearItemsStyle();
        if (pos < getChildCount()) {
            X childView = (X) getChildAt(pos);
            childView.setBackgroundResource(itemBGResPre);
            childView.setTextColor(itemTextColorPre);
            setItemPadding(childView);
        }
    }


    private void setItemPadding(X view) {
        if (view instanceof Button) {
            view.setPadding(textModePadding, 0, textModePadding, 0);
        } else {
            view.setPadding(textModePadding, textModePadding, textModePadding, textModePadding);
        }
    }

    //清除Group所有的样式  
    public void clearItemsStyle() {
        for (int i = 0; i < getChildCount(); i++) {
            X childView = (X) getChildAt(i);
            childView.setBackgroundResource(itemBGResNor);
            childView.setTextColor(itemTextColorNor);
            setItemPadding(childView);
        }
    }

    public void addItemViews(ArrayList<String> texts, String mode) {
        mTexts = texts;
        removeAllViews();
        for (String text : texts) {
            addItemView(text, mode);
        }
    }

    private void addItemView(String text, String mode) {
        X childView = null;
        switch (mode) {
            case BTN_MODE:
                childView = (X) new Button(mContext);
                break;
            case TEV_MODE:
                childView = (X) new TextView(mContext);
                break;
        }
        childView.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT));
        childView.setTextSize(itemTextSize);
        childView.setBackgroundResource(itemBGResNor);
        setItemPadding(childView);
        childView.setTextColor(itemTextColorNor);
        childView.setText(text);
        this.addView(childView);
    }

    public String getChooseText(int itemID) {
        if (itemID >= 0) {
            return mTexts.get(itemID);
        }
        return null;
    }

    public void setItemTextSize(float itemTextSize) {
        this.itemTextSize = itemTextSize;
    }

    public void setItemBGResNor(int itemBGResNor) {
        this.itemBGResNor = itemBGResNor;
    }

    public void setItemTextColorNor(int itemTextColorNor) {
        this.itemTextColorNor = itemTextColorNor;
    }

    public void setItemBGResPre(int itemBGResPre) {
        this.itemBGResPre = itemBGResPre;
    }

    public void setItemTextColorPre(int itemTextColorPre) {
        this.itemTextColorPre = itemTextColorPre;
    }

    public interface OnGroupItemClickListener {
        void onGroupItemClick(int item);
    }
}  