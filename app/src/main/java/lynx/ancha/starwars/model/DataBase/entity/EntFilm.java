package lynx.ancha.starwars.model.DataBase.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity (tableName = "films")
public class EntFilm {
    @PrimaryKey
    @ColumnInfo(name = "id")
    public long mId;

    public long getmId() {
        return mId;
    }

    public void setmId(long mId) {
        this.mId = mId;
    }
}
