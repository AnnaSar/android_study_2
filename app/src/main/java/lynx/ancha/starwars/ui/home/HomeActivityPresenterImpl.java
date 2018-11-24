package lynx.ancha.starwars.ui.home;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import lynx.ancha.starwars.App;
import lynx.ancha.starwars.data.model.database.entity.EntPeoples;
import lynx.ancha.starwars.data.model.rest.RawPeople;
import lynx.ancha.starwars.data.model.rest.RawResult;

public class HomeActivityPresenterImpl implements HomeActivityPresenter {

    private static final int LIMIT = 10;
    private  HomeActivityView mView;
    private  int mPage = 0;

//    private Observer<List<EntPeoples>> mRestObserver = new Observer<List<EntPeoples>>() {
//
//        @Override
//        public void onSubscribe(Disposable d) {
//            Log.d("TAG", "onSubscribe");
//        }
//
//        @Override
//        public void onNext(List<EntPeoples> peopleList) {
//            mAdapter.hideProgress();
//            mScrollListener.setFullLoaded(peopleList.size() < LIMIT);
//            mAdapter.addAll(peopleList);
//            showContent();
//            Log.d("TAG", "onNext");
//        }
//
//        @Override
//        public void onError(Throwable e) {
//            mScrollListener.setLoading(false);
//            showError();
//            Log.d("TAG", "onError");
//        }
//
//        @Override
//        public void onComplete() {
//            mScrollListener.setLoading(false);
//            Log.d("TAG", "onComplete");
//        }
//    };

    private Observer<List<EntPeoples>> mRestObserver = new Observer<List<EntPeoples>>() {
        @Override
        public void onSubscribe(Disposable d) {
            Log.d("TAG", "onSubscribe");
        }

        @Override
        public void onNext(List<EntPeoples> peopleList) {
            mView.hideLoader();
            mView.setFullLoaded(peopleList.size() < LIMIT);
            mView.addPeoples(peopleList);
            mView.showContent();
        }

        @Override
        public void onError(Throwable e) {
            mView.setLoading(false);
            mView.showError();
        }

        @Override
        public void onComplete() {
            mView.setLoading(false);
        }
    };


    @Override
    public void setView(HomeActivityView view) {
        mView = view;
    }

    @Override
    public void fetchData(int page) {
        mPage = page;
        loadData(mPage);
    }

    @Override
    public void onCreate() {
        mView.showProgress();
        mPage = 1;
        loadData(mPage);
    }

    @Override
    public void onClickPeople(EntPeoples people) {
        mView.openCardActivity(people.getmId());
    }

    private void loadData(int page){
        App.getPeopleRestService()
                .getAllPeoples(page)
                .map(result -> saveDb(result))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mRestObserver);
    }


    private List<EntPeoples> saveDb(RawResult result) {
        List<EntPeoples> peopleList = new ArrayList<>();
        if (result != null && result.getResults() != null && !result.getResults().isEmpty()) {
            for (RawPeople rawPeople : result.getResults()) {
                EntPeoples people = new EntPeoples();
                String id = rawPeople.getmUrl().replaceAll("[\\D+]","");
                people.setmId(Long.parseLong(id));
                people.setmImagePeople(
                        String.format("https://starwars-visualguide.com/assets/img/characters/%s.jpg", people.getmId())
                );
                people.setmName(rawPeople.getmName());
                try {
                    people.setmHeight(Integer.parseInt(rawPeople.getmHeight()));
                } catch (NumberFormatException ignored) {}
                try {
                    people.setmMass(Integer.parseInt(rawPeople.getmMass()));
                } catch (NumberFormatException ignored) {}

                people.setmHairColor(rawPeople.getmHairColor());
                people.setmEyeColor(rawPeople.getmEyeColor());
                people.setmBirthYear(rawPeople.getmBirthYear());
                people.setmGender(rawPeople.getmGender());
                people.setmHomeWorld(rawPeople.getmHomeWorld());
                //((App)getApplication()).getDatabase().getDaoPeople().insert(people);
                App.getDatabase().getDaoPeople().insert(people);
                peopleList.add(people);
            }
        }
        return peopleList;
    }

}
