package lynx.ancha.starwars.ui.splash;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import lynx.ancha.starwars.R;
import lynx.ancha.starwars.ui.home.HomeActivity;

public class SplashActivity extends AppCompatActivity
        implements
        Animator.AnimatorListener,
        SplashActivityPresenterView
{

    @BindView(R.id.logo) ImageView mLogo;

    private SplashActivityPresenter mPresenter;

    @Override
    public void onAnimationStart(Animator animation) {

    }

    @Override
    public void onAnimationEnd(Animator animation) {
        mPresenter.onStopAnimation();
    }

    @Override
    public void onAnimationCancel(Animator animation) {

    }

    @Override
    public void onAnimationRepeat(Animator animation) {

    }

    @Override
    public void onStartAnimation() {
        Animator animator = AnimatorInflater.loadAnimator(this,R.animator.logo_animator);
        animator.setTarget(mLogo);
        animator.addListener(this);
        animator.start();
    }

    @Override
    public void onStartHomeActivity() {
        Intent intent = new Intent(
                SplashActivity.this, HomeActivity.class
        );
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        mPresenter = new SplashActivityPresenterImpl();
        mPresenter.setView(this);
        mPresenter.onCreate();

        // ImageView logo = findViewById(R.id.logo);


//        new Handler().postDelayed(new Runnable(){
//            @Override
//            public void run() {
//                Intent intent = new Intent(
//                        SplashActivity.this, HomeActivity.class
//                );
//                startActivity(intent);
//                finish();
//            }
//        }, 3200);
    }
}
