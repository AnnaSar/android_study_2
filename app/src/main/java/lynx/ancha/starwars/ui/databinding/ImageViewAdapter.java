package lynx.ancha.starwars.ui.databinding;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import lynx.ancha.starwars.ui.widgets.TemplateTextView;

public class ImageViewAdapter {
    @BindingAdapter("image_url")
    public static void setImage(ImageView view, String url) {
        Glide.with(view.getContext()).load(url).into(view);
    }

}
