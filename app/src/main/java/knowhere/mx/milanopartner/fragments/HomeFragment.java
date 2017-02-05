package knowhere.mx.milanopartner.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

import knowhere.mx.milanopartner.MilanoApp;
import knowhere.mx.milanopartner.R;
import knowhere.mx.milanopartner.adapters.EventListAdapter;
import knowhere.mx.milanopartner.pojo.ImageEvent;
import knowhere.mx.milanopartner.pojo.TicketMasterEvent;
import knowhere.mx.milanopartner.pojo.TicketMasterResponse;
import knowhere.mx.milanopartner.utils.MilanoLogger;

/**
 * Created by Corona on 6/24/2016.
 */
public class HomeFragment extends Fragment {

    public static String TAG = HomeFragment.class.getSimpleName();
    private ProgressDialog pDialog;
    private MilanoApp myApp;
    private ListView eventList;
    private ArrayList<TicketMasterEvent> events;
    private EventListAdapter eventListAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        pDialog = new ProgressDialog(getContext());
        pDialog.setMessage("Cargando proximos eventos...");
        pDialog.setCancelable(false);
       // myApp = (MilanoApp) getContext().getApplicationContext();
        View myFragmentView = inflater.inflate(R.layout.home_fragment_layout, container, false);
        eventList = (ListView) myFragmentView.findViewById(R.id.event_list);
        events = new ArrayList<>();
        if(eventListAdapter == null){
            getEvenets("https://app.ticketmaster.com/discovery/v2/events.json?postalCode=08400&classificationName=Music&apikey=mP8a6NqOfAygNMzEzAVYvR00ki5wyO36");

        }else{
            eventList.setAdapter(eventListAdapter);
        }

        return myFragmentView;
    }


    private void getEvenets(String url){
        showpDialog();

        RequestQueue queue = Volley.newRequestQueue(getActivity());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            Gson gson = new Gson();
                            // JSONObject object = new JSONObject(response);
                            //String stringObject = object.getString("Usuario");
                            JSONObject jsonObject = new JSONObject(response);
                            JSONObject evenObject = new JSONObject(jsonObject.getString("_embedded"));
                            JSONArray jsonArray = evenObject.getJSONArray("events");
                            int cont =0;
                            for(cont = 0;cont<jsonArray.length();cont++){
                                if(cont== 4){
                                    break;
                                }
                                JSONObject event = jsonArray.getJSONObject(cont);
                                String url = (String) event.getJSONArray("images").getJSONObject(0).get("url");
                                TicketMasterEvent ticketMasterEvent = new TicketMasterEvent();
                                ticketMasterEvent.setName(event.getString("name"));
                                ArrayList<ImageEvent> arrayLiseve = new ArrayList<>();
                                ImageEvent imageEvent = new ImageEvent();
                                imageEvent.setUrl(url);
                                arrayLiseve.add(imageEvent);
                                ticketMasterEvent.setImages(arrayLiseve);
                                JSONObject jsonObject1 = event.getJSONObject("_embedded").getJSONArray("venues").getJSONObject(0);
                                ticketMasterEvent.setLocale(jsonObject1.getString("name"));
                                events.add(ticketMasterEvent);
                            }

                           // Type listType = new TypeToken<ArrayList<TicketMasterEvent>>(){}.getType();
                            //TicketMasterResponse temp = gson.fromJson(jsonObject.get("_embedded").toString(),TicketMasterResponse.class);
                            //events = new Gson().fromJson(response.toString(), listType);
                            eventListAdapter = new EventListAdapter(events,getActivity());
                            eventList.setAdapter(eventListAdapter);
                            hidepDialog();


                        }catch (Exception e){
                            Toast.makeText(getActivity(), "Algo salió mal al recuperar los eventos", Toast.LENGTH_SHORT).show();
                            MilanoLogger.getINSTANCE().escribeArchivo(TAG,"Parse Events",e.getMessage());
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "Algo salió mal al agregar tus puntos", Toast.LENGTH_SHORT).show();

            }
        });

        // Adding request to request queue
        queue.add(stringRequest);
    }

    private void showpDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hidepDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }


}
