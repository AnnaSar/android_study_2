package lynx.ancha.starwars;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.util.Log;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import javax.inject.Inject;

import lynx.ancha.starwars.data.model.database.AppDatabase;
import lynx.ancha.starwars.data.rest.RestApiPeoples;
import lynx.ancha.starwars.di.component.ApplicationComponent;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



public class App extends Application {

    private static ApplicationComponent mAppComponent;
    //private static RestApiPeoples mPeopleRestService;


    @Override
    public void onCreate() {
        super.onCreate();
        mAppComponent = ApplicationComponent.buildComponent(this);
        mAppComponent.inject(this);

        //buildRest();
        //buildDB();
        Log.d("TAG","OnCreateApplication");
    }

//    private void buildRest() {
//        Retrofit retrofit = new Retrofit
//                .Builder()
//                .baseUrl("https://swapi.co/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .build();
//        mPeopleRestService = retrofit.create(RestApiPeoples.class);
//    }

//    private void buildDB() {
//        mAppDatabase = Room
//                .databaseBuilder(getApplicationContext(), AppDatabase.class, "star-wars")
//                .build();
//    }

//    public static RestApiPeoples getPeopleRestService(){
////        return mPeopleRestService;
////    }

    public static RestApiPeoples getPeopleRestService(){
        return mAppComponent.getRestApiPeoples();
    }

//    public static AppDatabase getDatabase() {
////        return mAppDatabase;
////    }

    public static AppDatabase getDatabase() {
        return mAppComponent.getDataBase();
    }

}

