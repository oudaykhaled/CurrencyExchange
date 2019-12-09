package com.ouday.currencyexchange.core.component.ui.edittext;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

public class EditTextBold extends BaseCustomEditText {
    public EditTextBold(Context context) {
        super(context);
    }

    public EditTextBold(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EditTextBold(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected Typeface getFont() {
        return fontProvider.getPrimaryBoldFont();
    }
}
