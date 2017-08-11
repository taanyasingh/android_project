package com.tanya.myquiz;

/**
 * Created by Tanya on 08-02-2017.
 */
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
public class CustomAdapter extends ArrayAdapter<String> {
    Context c;
    String[] players;
    int[] images;
    LayoutInflater inflater;
    public CustomAdapter(Context context,String[] players, int[] images) {
        super(context, R.layout.topic,players);
        this.c=context;
        this.players=players;
        this.images=images;
    }

    public class ViewHolder{
        TextView topic;
        ImageView img;
    }

    @Override
    public View getView(int position,View convertView,ViewGroup parent)
    {
        if(convertView==null)
        {
            inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.topic,null);
        }

        ViewHolder holder = new ViewHolder();
        holder.topic = (TextView)convertView.findViewById(R.id.tvtopic);
        holder.img = (ImageView)convertView.findViewById(R.id.imageview);

        holder.topic.setText(players[position]);
        holder.img.setImageResource(images[position]);

        return convertView;
    }
}
