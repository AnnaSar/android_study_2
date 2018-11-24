package lynx.ancha.starwars.ui.home;

import lynx.ancha.starwars.data.model.database.entity.EntPeoples;

public interface HomeActivityPresenter {
    /**
     * setter view
     * @param view - страница
     */
    void setView(HomeActivityView view);

    /**
     * Fetch data
     * @param page - страница
     */
    void fetchData(int page);

    /**
     * Start activity
     */
    void onCreate();

    /**
     * Обработка клика на персонаже
     * @param people - персонаж
     */
    void onClickPeople(EntPeoples people);

}
