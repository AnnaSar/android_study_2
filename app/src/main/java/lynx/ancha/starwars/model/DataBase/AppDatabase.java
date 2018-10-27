package lynx.ancha.starwars.model.DataBase;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import lynx.ancha.starwars.model.DataBase.dao.DaoPeoples;
import lynx.ancha.starwars.model.DataBase.entity.EntFilm;
import lynx.ancha.starwars.model.DataBase.entity.EntPeoples;

@Database(entities = {EntFilm.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    //public abstract DaoPeoples getDaoPeoples();
}