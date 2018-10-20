package lynx.ancha.starwars.model;

import com.google.gson.annotations.SerializedName;

public class People {
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


    public String getName() {
        return mName;
    }
}
