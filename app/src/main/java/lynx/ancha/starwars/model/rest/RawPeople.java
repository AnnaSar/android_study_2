package lynx.ancha.starwars.model.rest;

import com.google.gson.annotations.SerializedName;

public class RawPeople {
    @SerializedName("name")
    private String mName;

    @SerializedName("height")
    private String mHeight;

    @SerializedName("mass")
    private String mMass;

    @SerializedName("hair_color")
    private String mHairColor;

    @SerializedName("skin_color")
    private String mSkinColor;

    @SerializedName("eye_color")
    private String mEyeColor;

    @SerializedName("birth_year")
    private String mBirthYear;

    @SerializedName("gender")
    private String mGender;

    @SerializedName("homeworld")
    private String mHomeWorld;

    @SerializedName("url")
    private String mUrl;

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmHeight() {
        return mHeight;
    }

    public void setmHeight(String mHeight) {
        this.mHeight = mHeight;
    }

    public String getmMass() {
        return mMass;
    }

    public void setmMass(String mMass) {
        this.mMass = mMass;
    }

    public String getmHairColor() {
        return mHairColor;
    }

    public void setmHairColor(String mHairColor) {
        this.mHairColor = mHairColor;
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

    public void setmMale(String mGender) {
        this.mGender = mGender;
    }

    public String getmHomeWorld() {
        return mHomeWorld;
    }

    public void setmHomeWorld(String mHomeworld) {
        this.mHomeWorld = mHomeworld;
    }

    public String getmUrl() {
        return mUrl;
    }

    public void setmUrl(String mUrl) {
        this.mUrl = mUrl;
    }
    //public static class Empty extends RawPeople { }
}
