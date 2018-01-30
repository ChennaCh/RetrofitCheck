package retrofit.chenna.com.retrofitcheck.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by chinn on 12/19/2017.
 */

public class ActorList {

    @SerializedName("actors")
    public ArrayList<Actors> actors = new ArrayList<>();

    public ArrayList<Actors> getActors() {
        return actors;
    }

    public void setActors(ArrayList<Actors> actors) {
        this.actors = actors;
    }
}
