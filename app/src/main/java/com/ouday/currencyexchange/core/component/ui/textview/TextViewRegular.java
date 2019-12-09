package com.ouday.currencyexchange.core.component.ui.textview;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

public class TextViewRegular extends BaseCustomTextView{
    public TextViewRegular(Context context) {
        super(context);
    }

    public TextViewRegular(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TextViewRegular(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected Typeface getFont() {
        return fontProvider.getPrimaryRegularFont();
    }
}
