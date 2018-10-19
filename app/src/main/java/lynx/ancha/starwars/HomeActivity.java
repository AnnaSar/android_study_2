package lynx.ancha.starwars;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;

public class HomeActivity extends AppCompatActivity {
    FrameLayout mProgressLayout;
    FrameLayout mContentLayout;
    RecyclerView mRecyclerView;
    private HeroRecyclerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_main);
        bindViews();
        setupList();
        showProgress();
        loadData();
    }

    private void startCardActivity(String name) {
        Intent intent = new Intent(this, CardActivity.class);
        intent.putExtra(CardActivity.PARAM_NAME, name);
        startActivity(intent);
    }

    private void bindViews() {
        mProgressLayout = findViewById(R.id.progress);
        mContentLayout = findViewById(R.id.content);
        mRecyclerView = findViewById(R.id.recycler_view);
    }

    private void setupList() {
        mAdapter = new HeroRecyclerAdapter();
        mRecyclerView.setAdapter(mAdapter);
    }

    private void showContent() {
        mContentLayout.setVisibility(View.VISIBLE);
        mProgressLayout.setVisibility(View.GONE);
    }

    private void showProgress() {
        mContentLayout.setVisibility(View.GONE);
        mProgressLayout.setVisibility(View.VISIBLE);
    }

    private void loadData() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                String[] names = getResources().getStringArray(R.array.persons);
                mAdapter.addAll(names);
                showContent();
            }
        }, 3200);
    }
}
