package com.galis.weixinstudy;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Author: galis
 * Date: 2014/9/15 18:46
 * Version: 1.4
 * Describe:主界面底部TabIcon按钮
 */


public class TabIconView extends ImageView {

    private int mAlpha;
    private Paint mPaint;
    private Bitmap clickedBitmap;
    private Bitmap unClickBitmap;

    public TabIconView(Context context) {
        super(context);
        mAlpha = 0;
    }

    public TabIconView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mAlpha = 0;
    }

    public TabIconView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mAlpha = 0;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mPaint != null) {
            mPaint.setAlpha(255 - mAlpha);//设置透明度
            canvas.drawBitmap(unClickBitmap, null, new Rect(0,0,unClickBitmap.getWidth(), unClickBitmap.getHeight()), mPaint);
            mPaint.setAlpha(mAlpha);//设置透明度
            canvas.drawBitmap(clickedBitmap, null, new Rect(0, 0, clickedBitmap.getWidth(), clickedBitmap.getHeight()),mPaint);
        }

    }

    public void init(int clickedDrawableRid, int unclickedDrawableRid) {
        clickedBitmap = BitmapFactory.decodeResource(getResources(), clickedDrawableRid);//点击的图片
        unClickBitmap = BitmapFactory.decodeResource(getResources(), unclickedDrawableRid);//未点击的图片
        setLayoutParams(new LinearLayout.LayoutParams(clickedBitmap.getWidth(),clickedBitmap.getHeight()));
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mAlpha = 0;
    }

    public void setmAlpha(int alpha) {
        mAlpha = alpha;
        invalidate();//重新调用OnDraw方法
    }
}
