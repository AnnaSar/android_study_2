package lynx.ancha.starwars;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class CardActivity extends AppCompatActivity {

    public static final String PARAM_NAME = "star.wars.card.param.id";
    TextView mPersonNameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);
        mPersonNameTextView = findViewById(R.id.card_name_text_view);
        setData();
    }

    private void setData() {
        if (getIntent().hasExtra(PARAM_NAME)) {
            mPersonNameTextView.setText(getIntent().getStringExtra(PARAM_NAME));
        }
    }
}
