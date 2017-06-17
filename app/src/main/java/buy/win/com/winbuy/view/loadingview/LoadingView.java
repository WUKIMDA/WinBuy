package buy.win.com.winbuy.view.loadingview;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;

/*
  loadingView= (LoadingView) findViewById(R.id.lv_circularJump);
        loadingView.setViewColor(Color.rgb(133, 66, 99));
        loadingView.startAnim();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                loadingView.stopAnim();
            }
        }, 5000);


  <buy.win.com.text.LoadingView
        android:id="@+id/lv_circularJump"
        android:layout_width="50dp"
        android:layout_height="50dp"
        android:onClick="startAnim"/>
 */


public class LoadingView extends LVBase {

    private Paint mPaint;

    private float mWidth = 0f;
    private float mHigh = 0f;
    private float mMaxRadius = 6;
    private int circularCount = 4;
    private float mAnimatedValue = 0f;
    private int mJumpValue = 0;

    public LoadingView(Context context) {
        super(context);
    }

    public LoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = getMeasuredWidth();
        mHigh = getMeasuredHeight();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        float circularX = mWidth / circularCount;

        for (int i = 0; i < circularCount; i++) {

            if (i == mJumpValue % circularCount) {

                canvas.drawCircle(i * circularX + circularX / 2f,
                        mHigh / 2 - mHigh / 2 * mAnimatedValue,
                        mMaxRadius, mPaint);

            } else {
                canvas.drawCircle(i * circularX + circularX / 2f,
                        mHigh / 2,
                        mMaxRadius, mPaint);
            }

        }


    }


    private void initPaint() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.WHITE);


    }

    public void setViewColor(int color)
    {
        mPaint.setColor(color);
        postInvalidate();
    }

    @Override
    protected void InitPaint() {
        initPaint();
    }

    @Override
    protected void OnAnimationUpdate(ValueAnimator valueAnimator) {
        mAnimatedValue = (float) valueAnimator.getAnimatedValue();


        if (mAnimatedValue > 0.5) {
            mAnimatedValue = 1 - mAnimatedValue;
        }

        invalidate();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {

        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void OnAnimationRepeat(Animator animation) {
        mJumpValue++;
    }


    @Override
    protected int SetAnimRepeatMode() {
        return ValueAnimator.RESTART;
    }

    @Override
    protected int OnStopAnim() {
        mAnimatedValue = 0f;
        mJumpValue = 0;
        return 0;
    }
    @Override
    protected void AinmIsRunning() {

    }
    @Override
    protected int SetAnimRepeatCount() {
        return ValueAnimator.INFINITE;
    }
}
