package lynx.ancha.starwars.model.DataBase.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import lynx.ancha.starwars.model.DataBase.entity.EntPeoples;

@Dao
public interface DaoPeoples {
    @Query("SELECT * FROM peoples")
    List<EntPeoples> getAllPeoples();

    @Query("SELECT * FROM peoples WHERE id = :id")
    EntPeoples getPeopleById(long id);

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    void insert(EntPeoples people);

    //@Update
    //void update(EntPeoples people);

   // @Delete
    //void delete(EntPeoples people);
}