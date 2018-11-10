package lynx.ancha.starwars;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class CardActivity extends AppCompatActivity {

    public static final String PARAM_ID = "star.wars.card.param.id";

    public TextView mPersonNameTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);
        mPersonNameTextView = findViewById(R.id.card_name_text_view);
    }

}
