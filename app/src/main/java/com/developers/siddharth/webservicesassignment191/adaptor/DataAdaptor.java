package com.developers.siddharth.webservicesassignment191.adaptor;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.developers.siddharth.webservicesassignment191.R;
import com.developers.siddharth.webservicesassignment191.models.DataHandler;

import java.util.List;

/**
 * Created by siddharth on 7/20/2017.
 */

public class DataAdaptor extends RecyclerView.Adapter<DataAdaptor.ViewHolder> {

    Context context;
    List<DataHandler> data;
    ClickListener clickListener;

    public DataAdaptor(Context context, List<DataHandler> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      View view = LayoutInflater.from(context).inflate(R.layout.row,null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
//        holder.name.setText(data.get(position).getName());
//        holder.country.setText(data.get(position).getCountry());
//        holder.sunrise.setText(data.get(position).getSunrise()+"");
//        holder.sunset.setText(data.get(position).getSunset()+"");
//        holder.visibility.setText(data.get(position).getVisibility()+"");
//        holder.temperature.setText(data.get(position).getTemp()+"");
//        holder.pressure.setText(data.get(position).getPressure()+"");
//        holder.humidity.setText(data.get(position).getHumidity()+"");
//        holder.tempmin.setText(data.get(position).getTemp_min()+"");
//        holder.tempmax.setText(data.get(position).getTemp_max()+"");
        holder.longitude.setText((int) data.get(position).getLon());
        holder.latitude.setText((int) data.get(position).getLat());
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(clickListener!=null)
                {
                    clickListener.ItemClicked(view,position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView name,country,sunrise,sunset,visibility,temperature,pressure,humidity,tempmin,tempmax,longitude,latitude;
        RelativeLayout parent;
        public ViewHolder(View itemView) {
            super(itemView);
//            name = (TextView)itemView.findViewById(R.id.name);
//            country = (TextView)itemView.findViewById(R.id.country);
//            sunrise = (TextView)itemView.findViewById(R.id.sunrise);
//            sunset = (TextView)itemView.findViewById(R.id.sunset);
//            visibility = (TextView)itemView.findViewById(R.id.visibility);
//            temperature = (TextView)itemView.findViewById(R.id.temp);
//            pressure = (TextView)itemView.findViewById(R.id.pressure);
//            humidity = (TextView)itemView.findViewById(R.id.humidity);
//            tempmin = (TextView)itemView.findViewById(R.id.tempmin);
//            tempmax = (TextView)itemView.findViewById(R.id.tempmax);
            longitude = (TextView)itemView.findViewById(R.id.lon);
            latitude = (TextView)itemView.findViewById(R.id.lat);
            parent = (RelativeLayout)itemView.findViewById(R.id.parent);
        }
    }

    public interface ClickListener
    {
        void ItemClicked(View v, int position);
    }

    public void setClickListener(ClickListener clickListener)
    {
        this.clickListener = clickListener;
    }
}
