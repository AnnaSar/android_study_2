package lynx.ancha.starwars;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends AppCompatActivity implements Animator.AnimatorListener{

    @BindView(R.id.logo) ImageView mLogo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
       // ImageView logo = findViewById(R.id.logo);
        Animator animator = AnimatorInflater.loadAnimator(this,R.animator.logo_animator);
        animator.setTarget(mLogo);
        animator.addListener(this);
        animator.start();

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

    @Override
    public void onAnimationStart(Animator animation) {

    }

    @Override
    public void onAnimationEnd(Animator animation) {
        Intent intent = new Intent(
                        SplashActivity.this, HomeActivity.class
                );
        startActivity(intent);
        finish();
    }

    @Override
    public void onAnimationCancel(Animator animation) {

    }

    @Override
    public void onAnimationRepeat(Animator animation) {

    }
}
