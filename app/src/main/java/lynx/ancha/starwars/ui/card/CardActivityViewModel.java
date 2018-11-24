package lynx.ancha.starwars.ui.card;

import android.annotation.SuppressLint;
import android.databinding.BaseObservable;

import io.reactivex.android.schedulers.AndroidSchedulers;
//import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import lynx.ancha.starwars.App;
import lynx.ancha.starwars.data.model.database.entity.EntPeoples;

public class CardActivityViewModel extends BaseObservable{

    private EntPeoples mPeople;

    CardActivityViewModel() {
        mPeople = new EntPeoples.Empty();
    }

    public EntPeoples getPeople() {
        return mPeople;
    }
    public void setPeople (EntPeoples people) {
        mPeople = people;
        notifyChange();
    }

    //private Consumer<EntPeoples> mObserver = this::setPeople;

    @SuppressLint("CheckResult")
    public void loadData(long peopleId) {
        App.getDatabase()
                .getDaoPeople()
                .getPeopleById(peopleId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::setPeople);
    }
}
