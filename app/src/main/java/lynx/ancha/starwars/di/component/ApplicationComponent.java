package lynx.ancha.starwars.di.component;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import lynx.ancha.starwars.App;
import lynx.ancha.starwars.data.model.database.AppDatabase;
import lynx.ancha.starwars.data.rest.RestApiPeoples;
import lynx.ancha.starwars.di.modul.ApplicationModule;
import lynx.ancha.starwars.di.modul.DataBaseModule;
import lynx.ancha.starwars.di.modul.NetModule;

@Singleton
@Component(
        modules = {
                ApplicationModule.class,
                DataBaseModule.class,
                NetModule.class
        }
        )

public interface ApplicationComponent {

    Context getContext();

    AppDatabase getDataBase();

    RestApiPeoples getRestApiPeoples();

    public static ApplicationComponent buildComponent(App app) {
        return DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(app))
                .dataBaseModule(new DataBaseModule(app))
                .netModule(new NetModule())
                .build();
    }

    void inject(App app);
}


