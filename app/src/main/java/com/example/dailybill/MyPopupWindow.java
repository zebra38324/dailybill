package com.example.dailybill;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

public class MyPopupWindow extends PopupWindow {
    private Context mContext;
    private View mContentView;

    public MyPopupWindow(Context context) {
        super(context);
        mContext = context;
        init();
    }

    private void init() {
        setOutsideTouchable(false);
        setFocusable(true);
        setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        mContentView = LayoutInflater.from(mContext).inflate(R.layout.popupwindow_layout, null, false);
        setContentView(mContentView);
    }

    public void Show(View view) {
        mContentView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        int xOff = mContentView.getMeasuredWidth();
        int yOff = mContentView.getMeasuredHeight() / 2;
        this.showAsDropDown(view, xOff, -yOff);

    }


}
