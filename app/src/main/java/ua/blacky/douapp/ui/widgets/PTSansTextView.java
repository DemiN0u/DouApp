package ua.blacky.douapp.ui.widgets;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by amatsegor on 07.07.15.
 */
public class PTSansTextView extends TextView {
    public PTSansTextView(Context context) {
        super(context);
        if (!isInEditMode()) {
            setTypeface(context);
        }
    }

    public PTSansTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!isInEditMode()) {
            setTypeface(context);
        }
    }

    public PTSansTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (!isInEditMode()) {
            setTypeface(context);
        }
    }

    public void setTypeface(Context context) {
        AssetManager assetManager = context.getAssets();
        int style = 0;
        if (getTypeface() != null) {
            style = getTypeface().getStyle();
        }
        switch (style) {
            case Typeface.BOLD: {
                setTypeface(Typeface.createFromAsset(assetManager, "fonts/pt_sans_narrow_bold.otf"));
                break;
            }

            case Typeface.NORMAL:
            default: {
                setTypeface(Typeface.createFromAsset(assetManager, "fonts/pt_sans_narrow.otf"));
                break;
            }
        }
    }
}
