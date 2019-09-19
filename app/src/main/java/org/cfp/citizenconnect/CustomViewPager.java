package org.cfp.citizenconnect;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class CustomViewPager extends ViewPager {
    private Boolean disable = true;

    public CustomViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.disable = false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {

        if(this.disable)
            return false;

        return super.onTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if(this.disable)
        return false;

        return super.onTouchEvent(event);
    }

    public void disableScroll(Boolean disable) {
        //When disable = true not work the scroll and when disable = false work the scroll
        this.disable = disable;
    }
}