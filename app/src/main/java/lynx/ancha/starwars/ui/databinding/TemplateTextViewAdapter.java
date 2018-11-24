package lynx.ancha.starwars.ui.databinding;

import android.databinding.BindingAdapter;

import lynx.ancha.starwars.ui.widgets.TemplateTextView;

public class TemplateTextViewAdapter {
    @BindingAdapter("templated_text")
    public static void setTempletedText(TemplateTextView view, String text) {
        view.setmTemplatedText(text);
    }

    @BindingAdapter("templated_text")
    public static void setTempletedText(TemplateTextView view, Integer int_text) {
        view.setmTemplatedText(int_text == null ? "-" : int_text.toString());
    }
}
