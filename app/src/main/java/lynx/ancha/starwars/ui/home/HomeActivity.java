package lynx.ancha.starwars.ui.home;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
//import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import lynx.ancha.starwars.App;
import lynx.ancha.starwars.ui.card.CardActivity;
import lynx.ancha.starwars.ui.home.adapter.HeroRecyclerAdapter;
import lynx.ancha.starwars.R;
import lynx.ancha.starwars.data.model.database.entity.EntPeoples;
import lynx.ancha.starwars.data.model.rest.RawPeople;
import lynx.ancha.starwars.data.model.rest.RawResult;

public class HomeActivity
        extends
        AppCompatActivity
        implements
        HeroRecyclerAdapter.Listener,
        HomeActivityView
{
//public class HomeActivity extends AppCompatActivity {


    @BindView(R.id.toolbar) Toolbar mToolbar;
    @BindView(R.id.progress) FrameLayout mProgressLayout;
    @BindView(R.id.content) FrameLayout mContentLayout;
    @BindView(R.id.error) FrameLayout mErrorLayout;
    @BindView(R.id.recycler_view) RecyclerView mRecyclerView;

    private HeroRecyclerAdapter mAdapter;
    private RecyclerScrollListener mScrollListener;

    private HomeActivityPresenter mPresenter;


    @Override
    public void onClickPeople(EntPeoples people) {
        //startCardActivity(people.getmId());
        mPresenter.onClickPeople(people);
    }

    @Override
    public void showContent() {
        mContentLayout.setVisibility(View.VISIBLE);
        mProgressLayout.setVisibility(View.GONE);
        mErrorLayout.setVisibility(View.GONE);
    }

    @Override
    public  void showProgress() {
        mContentLayout.setVisibility(View.GONE);
        mProgressLayout.setVisibility(View.VISIBLE);
        mErrorLayout.setVisibility(View.GONE);
    }

    @Override
    public  void showError() {
        mContentLayout.setVisibility(View.GONE);
        mProgressLayout.setVisibility(View.GONE);
        mErrorLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void showLoader() {
        mAdapter.showProgress();
    }

    @Override
    public void hideLoader() {
        mAdapter.hideProgress();
    }

    @Override
    public void openCardActivity(long id) {
        Intent intent = new Intent(this, CardActivity.class);
        intent.putExtra(CardActivity.PARAM_ID, id);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @Override
    public void setFullLoaded(boolean isFullLoaded) {
        mScrollListener.setFullLoaded(isFullLoaded);
    }

    @Override
    public void addPeoples(List<EntPeoples> peopleList) {
        mAdapter.addAll(peopleList);
    }


    @Override
    public void setLoading(boolean loading) {
        mScrollListener.setLoading(loading);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_main);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle(R.string.app_name);
        //bindViews();
        setupList();
        setupListener();
//        showProgress();
//        loadData(1);
        mPresenter = new HomeActivityPresenterImpl();
        mPresenter.setView(this);

        mPresenter.onCreate();
    }



    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        } else {
            mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        }
    }


//    private void startCardActivity(Long id) {
//        Intent intent = new Intent(this, CardActivity.class);
//        intent.putExtra(CardActivity.PARAM_ID, id);
//        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        startActivity(intent);
//    }

//    private void bindViews() {
//        mProgressLayout = findViewById(R.id.progress);
//        mContentLayout = findViewById(R.id.content);
//        mErrorLayout = findViewById(R.id.error);
//        mRecyclerView = findViewById(R.id.recycler_view);
//        //mRecyclerView.addOnScrollListener();
//    }

    private void setupList() {
        mAdapter = new HeroRecyclerAdapter();
        mAdapter.setListener(this);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setupListener() {
        mScrollListener = new RecyclerScrollListener();
        mRecyclerView.addOnScrollListener(mScrollListener);
    }



//    private void loadData(int page) {
//        ((App)getApplication())
//                .getPeopleRestService()
//                .getAllPeoples(page)
//                .map(result -> saveDb(result))
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(mRestObserver);
//    }

//    private List<EntPeoples> saveDb(RawResult result) {
//        List<EntPeoples> peopleList = new ArrayList<>();
//        if (result != null && result.getResults() != null && !result.getResults().isEmpty()) {
//            for (RawPeople rawPeople : result.getResults()) {
//                EntPeoples people = new EntPeoples();
//                String id = rawPeople.getmUrl().replaceAll("[\\D+]","");
//                people.setmId(Long.parseLong(id));
//                people.setmImagePeople(
//                        String.format("https://starwars-visualguide.com/assets/img/characters/%s.jpg", people.getmId())
//                );
//                people.setmName(rawPeople.getmName());
//                try {
//                    people.setmHeight(Integer.parseInt(rawPeople.getmHeight()));
//                } catch (NumberFormatException ignored) {}
//                try {
//                    people.setmMass(Integer.parseInt(rawPeople.getmMass()));
//                } catch (NumberFormatException ignored) {}
//
//                people.setmHairColor(rawPeople.getmHairColor());
//                people.setmEyeColor(rawPeople.getmEyeColor());
//                people.setmBirthYear(rawPeople.getmBirthYear());
//                people.setmGender(rawPeople.getmGender());
//                people.setmHomeWorld(rawPeople.getmHomeWorld());
//                ((App)getApplication()).getDatabase().getDaoPeople().insert(people);
//                peopleList.add(people);
//            }
//        }
//        return peopleList;
//    }

    private class RecyclerScrollListener extends RecyclerView.OnScrollListener {

        private Integer mTotalCount;
        private Integer mLastItem;
        private Integer mThreshold = 5;
        private Boolean mIsLoading = false;
        private Boolean mIsFullLoaded = false;

        @Override
        public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            if (mRecyclerView.getLayoutManager() == null) {
                return;
            }
            mTotalCount = mRecyclerView.getLayoutManager().getItemCount();
            mLastItem = ((LinearLayoutManager)mRecyclerView.getLayoutManager())
                    .findLastVisibleItemPosition();
            if (!mIsLoading && mTotalCount < (mLastItem + mThreshold) && !mIsFullLoaded) {
                mAdapter.showProgress();
                //loadData((mTotalCount / 10) + 1);
                mPresenter.fetchData((mTotalCount / 10) + 1);
                setLoading(true);
            }
        }

        @Override
        public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }

        public void setLoading(Boolean loading) {
            mIsLoading = loading;
        }

        public void setFullLoaded(Boolean fullLoaded) {
            mIsFullLoaded = fullLoaded;
        }
    }

}
