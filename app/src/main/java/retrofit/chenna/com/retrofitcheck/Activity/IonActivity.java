package retrofit.chenna.com.retrofitcheck.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;

import retrofit.chenna.com.retrofitcheck.Adapter.IonAdapter;
import retrofit.chenna.com.retrofitcheck.R;
import retrofit.chenna.com.retrofitcheck.model.IonActors;

public class IonActivity extends Activity {

    private RecyclerView recyclerView;
    ArrayList<String> actorslist = new ArrayList<String>();
    private ArrayList<IonActors> ionActorses = new ArrayList<IonActors>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ion);

        recyclerView = (RecyclerView) findViewById(R.id.ion_recyclerview);

        Ion.with(this)
                .load("http://microblogging.wingnity.com/JSONParsingTutorial/jsonActors")
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {

                        JsonElement elem = result.get("actors");
                        JsonArray jso = elem.getAsJsonArray();
                        JsonParser jsonParser = new JsonParser();

                        for (int i = 0 ; i< jso.size(); i++){

                            JsonObject jsonObject = (JsonObject) jsonParser.parse(String.valueOf(elem.getAsJsonArray().get(i).getAsJsonObject()));

                            String name = jsonObject.get("name").toString();
                            String country = jsonObject.get("country").toString();
                            String image = jsonObject.get("image").getAsString();
                            ionActorses.add(new IonActors(name,country,image));
                        }

                        IonAdapter adapter =new IonAdapter(ionActorses,IonActivity.this);
                        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                        recyclerView.setLayoutManager(mLayoutManager);
                        recyclerView.setAdapter(adapter);

                    }
                });
    }
}
