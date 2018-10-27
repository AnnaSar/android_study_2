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

    @SerializedName("male")
    private String mMale;

    @SerializedName("homeworld")
    private String mHomeworld;

    @SerializedName("url")
    private String mUrl;


    public String getName() {
        return mName;
    }

    public static class Empty extends RawPeople { }
}
