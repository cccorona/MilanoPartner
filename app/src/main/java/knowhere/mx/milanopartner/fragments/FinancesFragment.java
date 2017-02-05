package knowhere.mx.milanopartner.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;

import java.util.Calendar;
import java.util.Currency;
import java.util.Locale;

import knowhere.mx.milanopartner.R;
import knowhere.mx.milanopartner.dao.DataBaseScript;
import knowhere.mx.milanopartner.netutils.NetUtil;
import knowhere.mx.milanopartner.utils.GlobalMember;
import knowhere.mx.milanopartner.utils.MilanoEnums;
import knowhere.mx.milanopartner.utils.UserInterfaceUtil;
import knowhere.mx.milanopartner.widgetscoronado.WeekPicker;


/**
 * Created by Corona on 6/24/2016.
 */
public class FinancesFragment extends Fragment{

    public static String TAG = FinancesFragment.class.getSimpleName();
    public static String WEEK_PICKER = "weekPickerDialog";

    private ImageButton leftArrow;
    private ImageButton rightArrow;
    private TextView arrowsTitle;
    private TextView paymentMount;
    private String jsonFinancesData;
    private TextView currentDate;
    private TextView currencyTitle;
    private TextView currencySimbol;
    private FloatingActionButton shareImageButton;

    private String currentDateSelected;
    private WeekPicker.onDateSelected dateSelectedListener;



    //Week
    private BarChart barChart;
    private BarData barData ;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        currentDateSelected = UserInterfaceUtil.getInstance().
        getDateSimpleFormat().format(Calendar.getInstance().getTime());
        dateSelectedListener = new WeekPicker.onDateSelected() {
            @Override
            public void selectedDate(String currentDataDate) {
                currentDateSelected = currentDataDate;
                Toast.makeText(getActivity(),currentDataDate,Toast.LENGTH_LONG).show();
                String periodicity = arrowsTitle.getText().toString();
                MilanoEnums.TimePeriod timePeriod = null ;
                timePeriod = getTimePeriod(periodicity);
                if(currentDate!=null)currentDate.setText(getDateTitle(timePeriod));

            }
        };



    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        currentDate.setText(UserInterfaceUtil.getInstance().gererateWeekRange());
        currentDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WeekPicker.getInstance(getActivity(),dateSelectedListener).showWeekPicker();
            }
        });

        initChart(MilanoEnums.TimePeriod.PerWeek);
        currencyTitle.setText(Currency.getInstance(Locale.getDefault()).getCurrencyCode());
        currencySimbol.setText(Currency.getInstance(Locale.getDefault()).getSymbol());







    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {

        View myFragmentView = inflater.inflate(R.layout.finances_fragment_layout,container,false);
        leftArrow = (ImageButton) myFragmentView.findViewById(R.id.left_arrow_chart);
        rightArrow = (ImageButton) myFragmentView.findViewById(R.id.right_arrow_chart);
        arrowsTitle =(TextView)myFragmentView.findViewById(R.id.arrows_title);
        currentDate =(TextView)myFragmentView.findViewById(R.id.current_date);
        paymentMount = (TextView)myFragmentView.findViewById(R.id.payment_mount);
        currencyTitle = (TextView)myFragmentView.findViewById(R.id.currency_title);
        currencySimbol = (TextView)myFragmentView.findViewById(R.id.currency_simbol);
        shareImageButton = (FloatingActionButton)myFragmentView.findViewById(R.id.share_button);
        barChart = (BarChart) myFragmentView.findViewById(R.id.finances_chart);
        leftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String periodicity = arrowsTitle.getText().toString();
                MilanoEnums.TimePeriod timePeriod = null ;
                timePeriod = getTimePeriod(periodicity);

                switch (timePeriod){
                    case PerWeek:
                        break;
                    default:
                        timePeriod = MilanoEnums.TimePeriod.GetValue(timePeriod.GetOpCode()-1);
                }
                arrowsTitle.setText(getPeriodicityTitle(timePeriod));
                currentDate.setText(getDateTitle(timePeriod));
                initChart(timePeriod);
            }
        });





        rightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String periodicity = arrowsTitle.getText().toString();
                MilanoEnums.TimePeriod timePeriod = null ;
                timePeriod = getTimePeriod(periodicity);
                switch (timePeriod){
                    case AllTime:
                        break;
                    default:
                        timePeriod = MilanoEnums.TimePeriod.GetValue(timePeriod.GetOpCode()+1);
                }
                arrowsTitle.setText(getPeriodicityTitle(timePeriod));
                currentDate.setText(getDateTitle(timePeriod));
                initChart(timePeriod);


            }
        });

        shareImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareMilano();

            }
        });

        return  myFragmentView ;

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private void init(){
    }



    private void generateChart(MilanoEnums.TimePeriod timePeriod,String jsonString){
          barData = UserInterfaceUtil.getInstance().getGraphicDataFromJson(getContext(),timePeriod,jsonString);
          if(barData != null){
              barChart.setData(barData);
              //barChart.setDescription(arrowsTitle.toString());
              barChart.animateX(5000);
          }
    }





    private MilanoEnums.TimePeriod getTimePeriod(String periodicity){
        MilanoEnums.TimePeriod timePeriod = null ;
        if(getActivity().getResources().getString(R.string.finances_per_week_title).equals(periodicity)){
            timePeriod = MilanoEnums.TimePeriod.PerWeek;
        }else if(getActivity().getResources().getString(R.string.finances_per_month_title).equals(periodicity)){
            timePeriod = MilanoEnums.TimePeriod.PerMonth;
        }else if(getActivity().getResources().getString(R.string.finances_per_year_title).equals(periodicity)){
            timePeriod = MilanoEnums.TimePeriod.PerYear;
        }else if(getActivity().getResources().getString(R.string.finances_per_all_title).equals(periodicity)){
            timePeriod = MilanoEnums.TimePeriod.AllTime;
        }
        return  timePeriod ;
    }


    private void initChart(MilanoEnums.TimePeriod period){
        try {
            if(NetUtil.getINSTANCE().iHaveInternetFromFragment(
                    getActivity().getSystemService(Context.CONNECTIVITY_SERVICE)) && !GlobalMember.DEBUG){
                //WS json

            }else{

                jsonFinancesData =DataBaseScript.getINSTANCE(getContext()).getLastKnowDataFinances(MilanoEnums.TimePeriod.PerWeek);
                generateChart(period,jsonFinancesData);
            }
        }catch (Exception e){
            //Red message error
            jsonFinancesData =DataBaseScript.getINSTANCE(getContext()).getLastKnowDataFinances(MilanoEnums.TimePeriod.PerWeek);
            generateChart(period,jsonFinancesData);

        }
    }

    private String getPeriodicityTitle(MilanoEnums.TimePeriod timePeriod){
        String periodicityTittle = "";
        switch (timePeriod){
            case PerWeek:
                periodicityTittle = getActivity().getResources().getString(R.string.finances_per_week_title) ;
                break;
            case PerMonth:
                periodicityTittle = getActivity().getResources().getString(R.string.finances_per_month_title) ;
                break;
            case PerYear:
                periodicityTittle = getActivity().getResources().getString(R.string.finances_per_year_title) ;
                break;
            case AllTime:
                periodicityTittle = getActivity().getResources().getString(R.string.finances_per_all_title) ;
                break;
        }
        return  periodicityTittle ;
    }

    private String getDateTitle(MilanoEnums.TimePeriod timePeriod){
        String dateTitle = "";
               switch (timePeriod){
                   case PerWeek:
                       dateTitle = UserInterfaceUtil.getInstance().gererateWeekRange(currentDateSelected);
                       break;
                   case PerMonth:
                       dateTitle = currentDateSelected.split("-")[0];
                       break;
                   case PerYear:

                       break;
                   case AllTime:

                       break;
               }
        return  dateTitle ;
    }

    private void shareMilano(){
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        startActivity(Intent.createChooser(shareIntent, getActivity().getResources().getString(R.string.share_title)));
    }

}
