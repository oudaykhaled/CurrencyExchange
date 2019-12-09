package com.ouday.currencyexchange.core.component.ui.textview;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

import com.ouday.currencyexchange.core.component.ui.font.FontProvider;

public abstract class BaseCustomTextView extends AppCompatTextView {

    protected FontProvider fontProvider;

    protected abstract Typeface getFont();

    public BaseCustomTextView(Context context) {
        super(context, null);
        init();
    }

    public BaseCustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
        init();
    }

    public BaseCustomTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        fontProvider = new FontProvider(getContext());
        if (!isInEditMode() && getFont() != null)
            setTypeface(getFont());
    }

}
