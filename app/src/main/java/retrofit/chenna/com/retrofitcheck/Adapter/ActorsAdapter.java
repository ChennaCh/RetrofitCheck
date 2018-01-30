package retrofit.chenna.com.retrofitcheck.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import io.rmiri.skeleton.Master.AdapterSkeleton;
import retrofit.chenna.com.retrofitcheck.Activity.ActorDetailActivity;
import retrofit.chenna.com.retrofitcheck.R;
import retrofit.chenna.com.retrofitcheck.model.Actors;

/**
 * Created by chinn on 12/19/2017.
 */

//public class ActorsAdapter extends RecyclerView.Adapter<ActorsAdapter.ActorViewAdapter>

public class ActorsAdapter extends AdapterSkeleton<Actors,ActorsAdapter.ActorViewAdapter> {

    private ArrayList<Actors> dataList;
    private Context context;
    Pair<View, String> pair1,pair2;

    public ActorsAdapter(Context context1,ArrayList<Actors> dataList1)
    {
        this.context = context1;
        this.dataList = dataList1;

    }

    @Override
    public ActorViewAdapter onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_employee, parent, false);
        return new ActorViewAdapter(view);
    }

    @Override
    public void onBindViewHolder(final ActorViewAdapter holder, final int position) {

        final Actors actors = dataList.get(position);
        holder.actorname.setText(dataList.get(position).getName());
        holder.actorcountry.setText(dataList.get(position).getCountry());
        holder.actorspouse.setText(dataList.get(position).getSpouse());
        holder.actordescription.setText(dataList.get(position).getDescription());
        Picasso.with(context).load(actors.getImage()).into(holder.imageView);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                pair1 = Pair.create(holder.itemView.findViewById(R.id.imageView),"image_trans");
                pair2 = Pair.create(holder.itemView.findViewById(R.id.txt_actor_name),"text_trans");
                Intent intent = new Intent(view.getContext(), ActorDetailActivity.class);
                intent.putExtra("position",position);
                intent.putExtra("nmae",dataList.get(position).getName());
                intent.putExtra("image",dataList.get(position).getImage());
                ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat
                        .makeSceneTransitionAnimation((Activity)view.getContext(),pair1,pair2);
                view.getContext().startActivity(intent,activityOptionsCompat.toBundle());

            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class ActorViewAdapter extends RecyclerView.ViewHolder {

        TextView actorname, actorcountry, actorspouse,actordescription;
        ImageView imageView;

        ActorViewAdapter(View itemView) {
            super(itemView);
            actorname = (TextView) itemView.findViewById(R.id.txt_actor_name);
            actorcountry = (TextView) itemView.findViewById(R.id.txt_country);
            actorspouse = (TextView) itemView.findViewById(R.id.txt_spouse);
            actordescription = (TextView) itemView.findViewById(R.id.txt_description);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);


        }
    }
}
