package knowhere.mx.milanopartner.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import knowhere.mx.milanopartner.R;
import knowhere.mx.milanopartner.pojo.DateEvent;
import knowhere.mx.milanopartner.pojo.ImageEvent;
import knowhere.mx.milanopartner.pojo.TicketMasterEvent;


/**
 * Created by Corona on 11/29/2016.
 */

public class EventListAdapter extends BaseAdapter {


    private ArrayList<TicketMasterEvent> events;
    private Context context;


    public EventListAdapter(ArrayList<TicketMasterEvent> products, Context context) {
        this.events = products;
        this.context = context;
    }

    @Override
    public int getCount() {
        return events.size();
    }

    @Override
    public Object getItem(int position) {
        return events.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View rootView = inflater.inflate(R.layout.event_item_layout,parent,false);
        TextView nombreAforo,nombreEvento,fechaEvento;
        nombreAforo = (TextView) rootView.findViewById(R.id.nombre_recinto);
        nombreEvento = (TextView) rootView.findViewById(R.id.nombre_evento);
        fechaEvento = (TextView) rootView.findViewById(R.id.fecha_evento);
        ImageView imageEvento = (ImageView) rootView.findViewById(R.id.icon_evento);
        ArrayList<ImageEvent> images = events.get(position).getImages();
        if(images != null && images.size()>=1){
            Picasso.with(context).load(images.get(0).getUrl()).resize(400,400).onlyScaleDown().into(imageEvento);
        }


        nombreAforo.setText(events.get(position).getLocale());
        nombreEvento.setText(events.get(position).getName());
        DateEvent dateEvent = events.get(position).getDates();
        if(dateEvent!= null){
            fechaEvento.setText(dateEvent.getTimezone());
        }

        return rootView;

    }
}
