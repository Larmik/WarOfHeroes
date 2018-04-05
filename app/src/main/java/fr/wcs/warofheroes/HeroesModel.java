package fr.wcs.warofheroes;

import android.os.Parcel;
import android.os.Parcelable;

public class HeroesModel implements Parcelable {

    private String name;
    private int[] stats;
    private String description;
    private  int image;

    public HeroesModel(String name, int[] stats, String description, int image) {
        this.name = name;
        this.stats = stats;
        this.description = description;
        this.image = image;
    }

    protected HeroesModel(Parcel in) {
        name = in.readString();
        stats = in.createIntArray();
        description = in.readString();
        image = in.readInt();
    }

    public static final Creator<HeroesModel> CREATOR = new Creator<HeroesModel>() {
        @Override
        public HeroesModel createFromParcel(Parcel in) {
            return new HeroesModel(in);
        }

        @Override
        public HeroesModel[] newArray(int size) {
            return new HeroesModel[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int[] getStats() {
        return stats;
    }

    public void setStats(int[] stats) {
        this.stats = stats;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeIntArray(stats);
        parcel.writeString(description);
        parcel.writeInt(image);
    }
}
