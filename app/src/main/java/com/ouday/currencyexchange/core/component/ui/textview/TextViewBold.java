package com.ouday.currencyexchange.core.component.ui.textview;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

public class TextViewBold extends BaseCustomTextView{
    public TextViewBold(Context context) {
        super(context);
    }

    public TextViewBold(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TextViewBold(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected Typeface getFont() {
        return fontProvider.getPrimaryBoldFont();
    }
}
