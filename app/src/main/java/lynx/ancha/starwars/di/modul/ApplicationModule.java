package lynx.ancha.starwars.di.modul;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    private Context mContext;

    public ApplicationModule(Application app) {
        mContext = app.getApplicationContext();
    }

    @Singleton
    @Provides
    Context getmContext() {
        return mContext;
    }
}
