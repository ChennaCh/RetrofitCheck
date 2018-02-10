package retrofit.chenna.com.retrofitcheck.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit.chenna.com.retrofitcheck.R;
import retrofit.chenna.com.retrofitcheck.model.IonActors;

/**
 * Created by chinn on 02/08/18.
 */

public class IonAdapter extends RecyclerView.Adapter<IonAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<IonActors> ionActores;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView ion_actor_name ,ion_txt_country;
        public ImageView ion_imageView;

        public MyViewHolder(View view) {
            super(view);
            ion_actor_name = (TextView) view.findViewById(R.id.ion_actor_name);
            ion_imageView = (ImageView) view.findViewById(R.id.ion_imageView);
            ion_txt_country = (TextView) view.findViewById(R.id.ion_txt_country);
        }
    }

    public IonAdapter(ArrayList<IonActors> iondatalist, Context context) {
        this.ionActores = iondatalist;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.ion_get_data, parent, false);
        return new MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        IonActors actors = ionActores.get(position);
        holder.ion_txt_country.setText(ionActores.get(position).getCountry());
        holder.ion_actor_name.setText(ionActores.get(position).getName());
        Picasso.with(context).load(actors.getImage()).into(holder.ion_imageView);

    }

    @Override
    public int getItemCount() {
        return ionActores.size();
    }
}