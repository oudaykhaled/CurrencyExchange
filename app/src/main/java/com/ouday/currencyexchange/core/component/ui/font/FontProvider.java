package com.ouday.currencyexchange.core.component.ui.font;

import android.content.Context;
import android.graphics.Typeface;

public class FontProvider {

    private Context context;

    public FontProvider(Context context) {
        this.context = context;
    }

    public Typeface getPrimaryRegularFont() {
        return Fonts.getFont(context, FontStyle.REGULAR);
    }

    public Typeface getPrimaryLightFont() {
        return Fonts.getFont(context, FontStyle.LIGHT);
    }

    public Typeface getPrimaryBoldFont() {
        return Fonts.getFont(context, FontStyle.BOLD);
    }

    public Typeface getSecondaryRegularFont() {
        return Fonts.getFont(context, FontStyle.REGULAR);
    }

    public Typeface getSecondaryLightFont() {
        return Fonts.getFont(context, FontStyle.LIGHT);
    }

    public Typeface getSecondaryBoldFont() {
        return Fonts.getFont(context, FontStyle.BOLD);
    }

}
