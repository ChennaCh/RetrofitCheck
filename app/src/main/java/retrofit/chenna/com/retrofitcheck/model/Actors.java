package retrofit.chenna.com.retrofitcheck.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by chinn on 12/19/2017.
 */

public class Actors {

    @SerializedName("name")
    private String name;

    @SerializedName("country")
    private String country;


    @SerializedName("spouse")
    private String spouse;

    @SerializedName("description")
    private String description;

    @SerializedName("image")
    private String image;

    public Actors(String name1, String country1, String spouse1, String description1, String image1){
        this.name = name1;
        this.country = country1;
        this.spouse = spouse1;
        this.description = description1;
        this.image = image1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getSpouse() {
        return spouse;
    }

    public void setSpouse(String spouse) {
        this.spouse = spouse;
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
}
