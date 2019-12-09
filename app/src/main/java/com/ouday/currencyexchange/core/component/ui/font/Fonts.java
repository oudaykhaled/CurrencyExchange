package com.ouday.currencyexchange.core.component.ui.font;

import android.content.Context;
import android.graphics.Typeface;


public interface Fonts {


    static Typeface getFont(Context context, FontStyle fontStyle) {
        switch (fontStyle) {
            case BOLD:
                return Typeface.createFromAsset(context.getAssets(), "Roboto-Bold.ttf");
            case LIGHT:
                return Typeface.createFromAsset(context.getAssets(), "Roboto-Light.ttf");
            default:
                return Typeface.createFromAsset(context.getAssets(), "Roboto-Regular.ttf");
        }
    }
}
