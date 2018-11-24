package lynx.ancha.starwars.ui.splash;

public class SplashActivityPresenterImpl implements SplashActivityPresenter {
    private  SplashActivityPresenterView mView;

    public void setView(SplashActivityPresenterView mView) {
        this.mView = mView;
    }

    @Override
    public void onCreate() {
        validate();
        mView.onStartAnimation();

    }

    @Override
    public void onStopAnimation() {
        validate();
        mView.onStartHomeActivity();
    }

    private void validate() {
        if(mView == null) {
            throw new IllegalStateException("Не указана View");
        }
    }
}
