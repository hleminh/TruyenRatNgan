package com.example.hoang.truyenratngan.utils;

import android.view.ViewTreeObserver;
import android.widget.TextView;

/**
 * Created by huynq on 5/15/17.
 */

public class TextViewUtils {
    public static void makeFit(final TextView textView) {
        textView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                textView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                int maxLines = textView.getHeight() / textView.getLineHeight();
                if (textView.getLineCount() > maxLines) {
                    textView.setMaxLines(maxLines);
                }
            }
        });
    }
}
