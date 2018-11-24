package lynx.ancha.starwars.data.model.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import lynx.ancha.starwars.data.model.database.dao.DaoFilms;
import lynx.ancha.starwars.data.model.database.dao.DaoPeoples;
import lynx.ancha.starwars.data.model.database.entity.EntFilm;
import lynx.ancha.starwars.data.model.database.entity.EntPeoples;

@Database(entities = {EntPeoples.class, EntFilm.class }, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract DaoPeoples getDaoPeople();

    public abstract DaoFilms getDaoFilm();

}
