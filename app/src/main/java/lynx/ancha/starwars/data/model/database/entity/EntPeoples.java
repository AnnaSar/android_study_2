package lynx.ancha.starwars.data.model.database.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName="peoples", indices = {@Index("id")})
public class EntPeoples {
    @PrimaryKey
    @ColumnInfo(name = "id")
    public long mId;

    @ColumnInfo(name = "name")
    public String mName;

    @ColumnInfo(name = "height")
    public Integer mHeight;

    @ColumnInfo(name = "mass")
    public Integer mMass;

    @ColumnInfo(name = "hair_color")
    public String mHairColor;

    @ColumnInfo(name = "skin_color")
    public String mSkinColor;

    @ColumnInfo(name = "eye_color")
    public String mEyeColor;

    @ColumnInfo(name = "birth_year")
    public String mBirthYear;

    @ColumnInfo(name = "gender")
    public String mGender;

    @ColumnInfo(name = "homeworld")
    public String mHomeworld;

    @ColumnInfo(name = "image_people")
    public String mImagePeople;

    public  EntPeoples() { }

    public long getmId() {
        return mId;
    }

    public void setmId(long mId) {
        this.mId = mId;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public Integer getmHeight() {
        return mHeight;
    }

    public void setmHeight(Integer mHeight) {
        this.mHeight = mHeight;
    }

    public Integer getmMass() {
        return mMass;
    }

    public void setmMass(Integer mMass) {
        this.mMass = mMass;
    }

    public String getmHairColor() {
        return mHairColor;
    }

    public void setmHairColor(String mHair_color) {
        this.mHairColor = mHair_color;
    }

    public String getmSkinColor() {
        return mSkinColor;
    }

    public void setmSkinColor(String mSkinColor) {
        this.mSkinColor = mSkinColor;
    }

    public String getmEyeColor() {
        return mEyeColor;
    }

    public void setmEyeColor(String mEyeColor) {
        this.mEyeColor = mEyeColor;
    }

    public String getmBirthYear() {
        return mBirthYear;
    }

    public void setmBirthYear(String mBirthYear) {
        this.mBirthYear = mBirthYear;
    }

    public String getmGender() {
        return mGender;
    }

    public void setmGender(String mGender) {
        this.mGender = mGender;
    }

    public String getmHomeworld() { return mHomeworld; }

    public void setmHomeWorld(String mHomeworld) {
        this.mHomeworld = mHomeworld;
    }

    public String getmImagePeople() {
        return mImagePeople;
    }

    public void setmImagePeople(String mImagePeople) {
        this.mImagePeople = mImagePeople;
    }

    public static class Empty extends EntPeoples { }

}
