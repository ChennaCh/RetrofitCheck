package retrofit.chenna.com.retrofitcheck.Activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import retrofit.chenna.com.retrofitcheck.R;

public class ActorDetailActivity extends Activity {
    int postion;
    String name,image1;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actor_detail);

        Bundle b = getIntent().getExtras();
        postion = b.getInt("position");
        name = b.getString("nmae");
        image1 = b.getString("image");
        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        TextView textView = (TextView) findViewById(R.id.txt_actor_name);
        textView.setText(name);
        Picasso.with(context).load(image1).into(imageView);





    }
}
