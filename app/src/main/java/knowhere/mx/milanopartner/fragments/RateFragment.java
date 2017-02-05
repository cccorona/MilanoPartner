package knowhere.mx.milanopartner.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.security.SecureRandom;
import java.text.DecimalFormat;
import java.util.LinkedList;

import knowhere.mx.milanopartner.R;
import knowhere.mx.milanopartner.adapters.ListButtonAdapter;
import knowhere.mx.milanopartner.dao.DataBaseScript;
import knowhere.mx.milanopartner.netutils.NetUtil;
import knowhere.mx.milanopartner.pojo.MilanoRate;
import knowhere.mx.milanopartner.utils.GlobalMember;
import knowhere.mx.milanopartner.widgetscoronado.FullScreenProgressBar;
import knowhere.mx.milanopartner.widgetscoronado.ListButton;

/**
 * Created by Corona on 6/24/2016.
 */
public class RateFragment extends Fragment {


    private TextView rateText,
                     servicesNumber,
                     servicesText,
                     acceptedServices,
                     accepterServicesText,
                     fiveStarServices,
                     fiveServicesText;
    private ListView optionListRate;
    private LinkedList<ListButton> listOptions ;
    private ListButtonAdapter buttonListAdapter;

    private String jsonData;
    private MilanoRate rateData;

    private SwipeRefreshLayout refreshLayout;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listOptions = generateListOptions();
        buttonListAdapter = new ListButtonAdapter(listOptions);



    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
        optionListRate.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                  ListButton buttonSelected = (ListButton)view;
                Intent actionIntent = null ;
                  switch (buttonSelected.getMyService()){
                      case Advices:
                          break;
                      case Comments:
                          break;
                      case Unknown:
                          break;
                      default:
                  }
                if(GlobalMember.DEBUG){
                   // startActivity(new Intent(getActivity(), DummyActivity.class));
                }else{
                    //Start activity action
                }
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View myFragmentView = inflater.inflate(R.layout.rate_fragment_layout,container,false);
        rateText =(TextView)myFragmentView.findViewById(R.id.rate_text);
        servicesNumber =(TextView)myFragmentView.findViewById(R.id.services_number);
        servicesText =(TextView)myFragmentView.findViewById(R.id.services_text);
        acceptedServices =(TextView)myFragmentView.findViewById(R.id.accepted_services);
        accepterServicesText =(TextView)myFragmentView.findViewById(R.id.accepted_services_text);
        fiveStarServices =(TextView)myFragmentView.findViewById(R.id.number_five_services);
        fiveServicesText =(TextView)myFragmentView.findViewById(R.id.number_five_services_text);
        optionListRate = (ListView)myFragmentView.findViewById(R.id.optionListRate);
        refreshLayout = (SwipeRefreshLayout)myFragmentView.findViewById(R.id.swipeLayout);
        optionListRate.setAdapter(buttonListAdapter);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

              initData();
            }
        });
        return  myFragmentView;
    }

    private void initData(){


        FullScreenProgressBar.getInstance(getActivity()).showProgress();
        try {
            if(NetUtil.getINSTANCE().iHaveInternetFromFragment(
                    getActivity().getSystemService(Context.CONNECTIVITY_SERVICE)) && !GlobalMember.DEBUG){
                //WS json

            }else{

                jsonData = DataBaseScript.getINSTANCE(getActivity()).getLastKnowDataRate();
                fillRateData(jsonData);
            }
        }catch (Exception e){
            //Red message error
            jsonData = DataBaseScript.getINSTANCE(getActivity()).getLastKnowDataRate();
            fillRateData(jsonData);
        }
        FullScreenProgressBar.getInstance(getActivity()).hideProgress();

    }


    private void fillRateData(String jsonData){
        Gson gson = new Gson();
        rateData = gson.fromJson(jsonData,MilanoRate.class);
        if(GlobalMember.DEBUG) rateData = generateTestData();
        rateText.setText(String.valueOf(rateData.getRateValue()));
        servicesNumber.setText(String.valueOf(rateData.getServicesNumber()));
        acceptedServices.setText(String.valueOf(rateData.getAcceptedServices()));
        fiveStarServices.setText(String.valueOf(rateData.getFiveStarServices()));

    }

    private LinkedList<ListButton> generateListOptions(){

        LinkedList<ListButton> listButtons = new LinkedList<>();
        ListButton commnet = new ListButton(getActivity());
        commnet.getTitleText().setText("Comentarios");
        commnet.getDescriptionText().setText("Comentarios de los usuarios de tu lugar de estacionamiento");
        commnet.getIconView().setImageResource(R.drawable.ic_comment_black_48dp);
        commnet.getArrowView().setVisibility(View.VISIBLE);
        ListButton comment2 = new ListButton(getActivity());
        comment2.getTitleText().setText("Consejos profesionales");
        comment2.getDescriptionText().setText("Mejora continua en para brindar un mejor servicio");
        comment2.getIconView().setImageResource(R.drawable.ic_assignment_black_48dp);
        listButtons.add(commnet);
        listButtons.add(comment2);
        return  listButtons ;
    }

    private MilanoRate generateTestData(){

        SecureRandom secureRandom = new SecureRandom();
        MilanoRate testData = new MilanoRate();
        DecimalFormat df = new DecimalFormat("#.#");
        testData.setAcceptedServices(secureRandom.nextInt(5000));
        testData.setFiveStarServices(secureRandom.nextInt(4000));
        String numer = df.format(secureRandom.nextDouble()*5.0);
        numer = numer.replace(",",".");
        testData.setRateValue(Double.parseDouble(numer));
        testData.setServicesNumber(secureRandom.nextInt(3000));
        return  testData;
    }
}
