package lynx.ancha.starwars.data.model.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import io.reactivex.Single;
import lynx.ancha.starwars.data.model.database.entity.EntPeoples;

@Dao
public interface DaoPeoples {
    @Query("SELECT * FROM peoples")
    List<EntPeoples> getAllPeoples();

    @Query("SELECT * FROM peoples WHERE id = :id")
    Single<EntPeoples> getPeopleById(long id);

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    void insert(EntPeoples people);

    //@Update
    //void update(EntPeoples people);

   // @Delete
    //void delete(EntPeoples people);
}