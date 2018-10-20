package lynx.ancha.starwars;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import lynx.ancha.starwars.model.Result;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {
    FrameLayout mProgressLayout;
    FrameLayout mContentLayout;
    FrameLayout mErrorLayout;
    RecyclerView mRecyclerView;
    private HeroRecyclerAdapter mAdapter;
    private RecycleScrollListener mScrollListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_main);
        bindViews();
        setupList();
        setupListener();
        showProgress();
        loadData();
    }

    private void startCardActivity(String name) {
        Intent intent = new Intent(this, CardActivity.class);
        intent.putExtra(CardActivity.PARAM_NAME, name);
        startActivity(intent);
    }

    private void setupListener() {
        mScrollListener = new RecycleScrollListener();
        mRecyclerView.addOnScrollListener(mScrollListener);
    }
    private void bindViews() {
        mProgressLayout = findViewById(R.id.progress);
        mContentLayout = findViewById(R.id.content);
        mErrorLayout = findViewById(R.id.error);
        mRecyclerView = findViewById(R.id.recycler_view);
        //mRecyclerView.addOnScrollListener();
    }

    private void setupList() {
        mAdapter = new HeroRecyclerAdapter();
        mRecyclerView.setAdapter(mAdapter);
    }

    private void showContent() {
        mContentLayout.setVisibility(View.VISIBLE);
        mProgressLayout.setVisibility(View.GONE);
        mErrorLayout.setVisibility(View.GONE);
    }

    private void showProgress() {
        mContentLayout.setVisibility(View.GONE);
        mProgressLayout.setVisibility(View.VISIBLE);
        mErrorLayout.setVisibility(View.GONE);
    }

    private void showError() {
        mContentLayout.setVisibility(View.GONE);
        mProgressLayout.setVisibility(View.GONE);
        mErrorLayout.setVisibility(View.VISIBLE);
    }

    private void loadData() {
        ((App)getApplication()).getPeopleRestService()
            .getAllPeoples(1).enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                if (response.isSuccessful()) {
                    if(response.body() != null) {
                        mAdapter.addAll(response.body().getResults());
                        showContent();
                    }
                } else {
                    showError();
                    Log.d("Tag", "load error");
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                showError();
                Log.d("Tag", "error");
            }
        });
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                String[] names = getResources().getStringArray(R.array.persons);
//                mAdapter.addAll(names);
//                showContent();
//            }
//        }, 3200);
    }

    private void fetchData(Integer page){
        ((App)getApplication()).getPeopleRestService()
                        .getAllPeoples(page).enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                mScrollListener.setLoading(false);
                if (response.isSuccessful() && response.body() != null) {
                    mAdapter.addAll(response.body().getResults());
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
               mScrollListener.setLoading(false);
            }
        });
    }

    private class RecycleScrollListener extends RecyclerView.OnScrollListener {
        private Integer mTotalCount;
        private Integer mLastItem;
        private Integer mThreshold = 5;
        private Boolean mIsLoading = false;

        @Override
        public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }

        @Override
        public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            if (mRecyclerView.getLayoutManager() == null) {
                return;
            }
            mTotalCount = mRecyclerView.getLayoutManager().getItemCount();
            mLastItem = ((LinearLayoutManager)mRecyclerView.getLayoutManager())
                    .findLastVisibleItemPosition();
            if (!mIsLoading && mTotalCount < (mLastItem + mThreshold)) {
                fetchData((mTotalCount/10) + 1);
                setLoading(true);
            }
        }

        public void setLoading(Boolean loading) {
            mIsLoading = loading;
        }

    }
}
