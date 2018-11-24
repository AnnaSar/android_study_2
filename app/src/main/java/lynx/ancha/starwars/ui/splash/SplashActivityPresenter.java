package lynx.ancha.starwars.ui.splash;

public interface SplashActivityPresenter {
    /**
     * setter для View
     */
    void setView(SplashActivityPresenterView view);

    /**
     * обработка запуска активити
      */
    void onCreate();

    /**
     * обработка остановки анимации
     */
    void onStopAnimation();
}

