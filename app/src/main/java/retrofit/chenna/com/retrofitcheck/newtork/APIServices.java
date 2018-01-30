package retrofit.chenna.com.retrofitcheck.newtork;

import retrofit.chenna.com.retrofitcheck.model.ActorList;
import retrofit.chenna.com.retrofitcheck.model.Post;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by chinn on 12/19/2017.
 */

public interface APIServices {

    @GET("jsonActors")
    Call<ActorList> getActorData(@Query("values") int values);

    @POST("/posts")
    @FormUrlEncoded
    Call<Post> savePost(@Field("title") String title,
                        @Field("body") String body,
                        @Field("userId") long userId);
}
