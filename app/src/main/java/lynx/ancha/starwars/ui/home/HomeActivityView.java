package lynx.ancha.starwars.ui.home;

import java.util.List;

import lynx.ancha.starwars.data.model.database.entity.EntPeoples;

public interface HomeActivityView {

    /**
     * Показать лоадер
     */
    void showProgress();

    /**
     * Показать контент
     */
    void showContent();

    /**
     * Показать ошибку
     */
    void showError();

    /**
     * Показать лоадер в списке
     */
    void showLoader();

    /**
     * Скрыть лоадер в списке
     */
    void hideLoader();

    /**
     * Открыть карточку персонажа
     * @param id - Id персонажа
     */
    void openCardActivity(long id);

    /**
     * Установка полной загрузки списка
     * @param isFullLoaded - список загружен?
     */
    void setFullLoaded(boolean isFullLoaded);

    /**
     * Добавление всех персонажей
     * @param peopleList - персонажи
     */
    void addPeoples (List<EntPeoples> peopleList);

    /**
     * Загрузка окончена
     * @param loading - статус
     */
    void setLoading(boolean loading);
}
