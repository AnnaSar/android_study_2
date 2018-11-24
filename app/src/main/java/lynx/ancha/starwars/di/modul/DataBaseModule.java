package lynx.ancha.starwars.di.modul;

import android.arch.persistence.room.Room;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import lynx.ancha.starwars.data.model.database.AppDatabase;

@Module
public class DataBaseModule {

    private AppDatabase mAppDatabase;

    public DataBaseModule(Context context) {
        mAppDatabase = Room
                .databaseBuilder(context, AppDatabase.class, "star-wars")
                .build();
    }

    @Singleton
    @Provides
    AppDatabase getAppDatabase() {
        return mAppDatabase;
    }
}
