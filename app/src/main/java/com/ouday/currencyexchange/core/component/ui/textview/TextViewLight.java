package com.ouday.currencyexchange.core.component.ui.textview;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

public class TextViewLight extends BaseCustomTextView{
    public TextViewLight(Context context) {
        super(context);
    }

    public TextViewLight(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TextViewLight(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected Typeface getFont() {
        return fontProvider.getPrimaryLightFont();
    }
}
