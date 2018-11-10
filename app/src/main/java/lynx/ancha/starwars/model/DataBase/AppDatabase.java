package lynx.ancha.starwars.model.DataBase;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import lynx.ancha.starwars.model.DataBase.dao.DaoFilms;
import lynx.ancha.starwars.model.DataBase.dao.DaoPeoples;
import lynx.ancha.starwars.model.DataBase.entity.EntFilm;
import lynx.ancha.starwars.model.DataBase.entity.EntPeoples;

@Database(entities = {EntPeoples.class, EntFilm.class }, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract DaoPeoples getDaoPeople();

    public abstract DaoFilms getDaoFilm();

}
