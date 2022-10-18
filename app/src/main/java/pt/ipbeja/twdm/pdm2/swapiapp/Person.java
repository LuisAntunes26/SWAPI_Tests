package pt.ipbeja.twdm.pdm2.swapiapp;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Person {
    private String name;
    private String height;
    private String mass;
    @SerializedName("hair_color")
    private String hairColor;
    @SerializedName("skin_color")
    private String skinColor;
    @SerializedName("eye_color")
    private String eyeColor;
    private String gender;
    @SerializedName("homeworld")
    private String homeWorldURL;
    @SerializedName("films")
    private List<String> filmURLs;
    @SerializedName("species")
    private List<String> speciesURLs;


    public Person(String name, String height, String mass,
                  String hairColor, String skinColor,
                  String eyeColor, String gender, String homeWorldURL,
                  List<String> filmURLs, List<String> speciesURLs) {
        this.name = name;
        this.height = height;
        this.mass = mass;
        this.hairColor = hairColor;
        this.skinColor = skinColor;
        this.eyeColor = eyeColor;
        this.gender = gender;
        this.homeWorldURL = homeWorldURL;
        this.filmURLs = filmURLs;
        this.speciesURLs = speciesURLs;
    }

    public String getName() {
        return name;
    }

    public String getHeight() {
        return height;
    }

    public String getMass() {
        return mass;
    }

    public String getHairColor() {
        return hairColor;
    }

    public String getSkinColor() {
        return skinColor;
    }

    public String getEyeColor() {
        return eyeColor;
    }

    public String getGender() {
        return gender;
    }

    public String getHomeWorldURL() {
        return homeWorldURL;
    }

    public List<String> getFilmURLs() {
        return filmURLs;
    }

    public List<String> getSpeciesURLs() {
        return speciesURLs;
    }
}
