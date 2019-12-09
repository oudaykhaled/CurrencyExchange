package com.ouday.currencyexchange.core.component.ui.edittext;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

public class EditTextRegular extends BaseCustomEditText {


    public EditTextRegular(Context context) {
        super(context);
    }

    public EditTextRegular(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EditTextRegular(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected Typeface getFont() {
        return fontProvider.getPrimaryRegularFont();
    }
}
