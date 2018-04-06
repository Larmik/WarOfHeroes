package fr.wcs.warofheroes;
import android.os.Parcel;
import android.os.Parcelable;

public class HeroesModel implements Parcelable {

    private String name;
    private int intelligence;
    private int strength;
    private int speed;
    private int durability;
    private int power;
    private int combat;
    private String description;
    private String image;
    private int life;
    private int damage;
    private int mana;

    public HeroesModel(int life, int damage, int mana) {
        this.life = life;
        this.damage = damage;
        this.mana = mana;

    }


    public HeroesModel(String name, int intelligence, int strength, int speed, int durability,
                       int power, int combat, String description, String image) {
        this.name = name;
        this.intelligence = intelligence;
        this.strength = strength;
        this.speed = speed;
        this.durability = durability;
        this.power = power;
        this.combat = combat;
        this.description = description;
        this.image = image;

    }

    protected HeroesModel(Parcel in) {
        name = in.readString();
        intelligence = in.readInt();
        strength = in.readInt();
        speed = in.readInt();
        durability = in.readInt();
        power = in.readInt();
        combat = in.readInt();
        description = in.readString();
        image = in.readString();
        life = in.readInt();
        damage = in.readInt();
        mana = in.readInt();
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

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getDurability() {
        return durability;
    }

    public void setDurability(int durability) {
        this.durability = durability;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getCombat() {
        return combat;
    }

    public void setCombat(int combat) {
        this.combat = combat;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }



    public boolean isKo(){
        if (life<= 0){
            return true;

        }
        return false;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeInt(intelligence);
        parcel.writeInt(strength);
        parcel.writeInt(speed);
        parcel.writeInt(durability);
        parcel.writeInt(power);
        parcel.writeInt(combat);
        parcel.writeString(description);
        parcel.writeString(image);
        parcel.writeInt(life);
        parcel.writeInt(damage);
        parcel.writeInt(mana);
    }
}


