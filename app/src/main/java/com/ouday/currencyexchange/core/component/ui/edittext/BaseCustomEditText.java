package com.ouday.currencyexchange.core.component.ui.edittext;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import com.google.android.material.textfield.TextInputEditText;
import com.ouday.currencyexchange.core.component.ui.font.FontProvider;

public abstract class BaseCustomEditText extends TextInputEditText {

    protected FontProvider fontProvider;

    public BaseCustomEditText(Context context) {
        super(context);
        init();
    }

    public BaseCustomEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BaseCustomEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    protected abstract Typeface getFont();

    private void init() {
        fontProvider = new FontProvider(getContext());
        if (!isInEditMode() && getFont() != null)
            setTypeface(getFont());
    }


}
