package lynx.ancha.starwars.ui.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import lynx.ancha.starwars.R;


public class TemplateTextView extends android.support.v7.widget.AppCompatTextView {

    private int mTextTemplate;

    public TemplateTextView(Context context) {
        super(context);
    }

    public TemplateTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initAttrs(attrs);
    }

    public TemplateTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(attrs);
    }

    private void initAttrs(AttributeSet attrs) {
        TypedArray attributes = getContext().obtainStyledAttributes(attrs, R.styleable
                .TemplateTextView);
        mTextTemplate = attributes.getResourceId(R.styleable.TemplateTextView_template,0);
        attributes.recycle();
    }

    public void setmTemplatedText (String text) {
        if (mTextTemplate != 0) {
            setText(getContext().getString(mTextTemplate, text));
        }
    }
}
