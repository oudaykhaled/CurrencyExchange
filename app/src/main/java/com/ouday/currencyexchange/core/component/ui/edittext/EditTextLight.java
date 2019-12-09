package com.ouday.currencyexchange.core.component.ui.edittext;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

public class EditTextLight extends BaseCustomEditText {
    public EditTextLight(Context context) {
        super(context);
    }

    public EditTextLight(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EditTextLight(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected Typeface getFont() {
        return fontProvider.getPrimaryLightFont();
    }
}
