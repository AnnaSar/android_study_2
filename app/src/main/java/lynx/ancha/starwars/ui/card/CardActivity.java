package lynx.ancha.starwars.ui.card;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import lynx.ancha.starwars.App;
import lynx.ancha.starwars.R;
import lynx.ancha.starwars.data.model.database.entity.EntPeoples;
import lynx.ancha.starwars.ui.widgets.TemplateTextView;

public class CardActivity extends AppCompatActivity {

    public static final String PARAM_ID = "star.wars.card.param.id";

    @BindView(R.id.collapsing_toolbar_layout) CollapsingToolbarLayout mCollapsingToolbarLayout;
    @BindView(R.id.collapsing_image_view) ImageView mCollapsingImageView;
    @BindView(R.id.toolbar)  Toolbar mToolbar;
    //@BindView(R.id.card_name_text_view) TextView mHeroNameTextView;
    @BindView(R.id.card_gender_text_view)   TemplateTextView mHeroGenderTextView;
    @BindView(R.id.card_birth_text_view) TemplateTextView mHeroBirthTextView;
    @BindView(R.id.card_height_text_view) TemplateTextView mHeroHeightTextView;
    @BindView(R.id.card_mass_text_view) TemplateTextView mHeroMassTextView;

   // public TextView mPersonNameTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);
        ButterKnife.bind(this);
        loadData();
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mCollapsingToolbarLayout.setTitleEnabled(false);
        //mPersonNameTextView = findViewById(R.id.card_name_text_view);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private Consumer<EntPeoples> mObserver = new Consumer<EntPeoples>() {
        @Override
        public void accept(EntPeoples people) throws Exception {
            Glide.with(getApplicationContext()).load(people.getmImagePeople()).into(mCollapsingImageView);
            mToolbar.setTitle(people.getmName());

           // mHeroNameTextView.setText(people.getmName());
            mHeroGenderTextView.setmTemplatedText(people.getmGender());
            mHeroBirthTextView.setmTemplatedText(people.getmBirthYear());
            mHeroHeightTextView.setmTemplatedText(String.valueOf(people.getmHeight()));
            mHeroMassTextView.setmTemplatedText(String.valueOf(people.getmMass()));
        }
    };

    private void loadData() {
        App.getDatabase()
                .getDaoPeople()
                .getPeopleById(getPeopleId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mObserver);
    }


    private Long getPeopleId() {
        return getIntent().getLongExtra(PARAM_ID,0);
    }

}
