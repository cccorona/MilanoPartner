package knowhere.mx.milanopartner.utils;

import android.content.Context;

import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import knowhere.mx.milanopartner.R;

/**
 * Created by Corona on 7/3/2016.
 */
public class UserInterfaceUtil {

    public static String TAG = UserInterfaceUtil.class.getSimpleName();

    private static UserInterfaceUtil INSTANCE;

    private UserInterfaceUtil(){

    }

    public  static UserInterfaceUtil getInstance(){
        if(INSTANCE == null){
            INSTANCE = new UserInterfaceUtil();
        }
        return  INSTANCE;
    }

    public  List<String> getLabels(Context context , MilanoEnums.TimePeriod periodLabels){
        List<String> labels = new ArrayList<>();

        switch (periodLabels){
            case PerWeek:
                labels.add(context.getString(R.string.lunes));
                labels.add(context.getString(R.string.martes));
                labels.add(context.getString(R.string.miercoles));
                labels.add(context.getString(R.string.jueves));
                labels.add(context.getString(R.string.viernes));
                labels.add(context.getString(R.string.sabado));
                labels.add(context.getString(R.string.domingo));
                break;
            case PerMonth:
                labels.add(context.getString(R.string.enero));
                labels.add(context.getString(R.string.febrero));
                labels.add(context.getString(R.string.marzo));
                labels.add(context.getString(R.string.abril));
                labels.add(context.getString(R.string.mayo));
                labels.add(context.getString(R.string.junio));
                labels.add(context.getString(R.string.julio));
                labels.add(context.getString(R.string.agosto));
                labels.add(context.getString(R.string.septiembre));
                labels.add(context.getString(R.string.octubre));
                labels.add(context.getString(R.string.noviembre));
                labels.add(context.getString(R.string.diciembre));
                break;
            case PerYear:
                labels.add(context.getString(R.string.enero));

                break;
            case AllTime:
                labels.add(context.getString(R.string.enero));
                break;

        }
        return  labels ;
    }

    public BarData getGraphicDataFromJson(Context context,MilanoEnums.TimePeriod timePeriod , String json){
        List<BarEntry> entryList ;
        List<String> labels = new ArrayList<>();
        BarData data ;
        BarDataSet barDataSet = null;
        Gson gson = new Gson();
        Type listType = new TypeToken<List<BarEntry>>() {}.getType();
        float maxXWeek = 7000.0f;
        float maxXMonth = 30000.0f;
        float maxXYear = 200000.0f;
        float maxAllTime =200000.0f;
        int WEEK_DAYS= 7;
        int NUM_MONTHS= 12 ;
        entryList = gson.fromJson(json,listType);

            switch (timePeriod){
                case PerWeek:
                    if(GlobalMember.DEBUG)entryList = generateRandomData(WEEK_DAYS,maxXWeek);
                    barDataSet = new BarDataSet(entryList,context.getString(R.string.finances_per_week_title));
                    labels = UserInterfaceUtil.getInstance().getLabels(context, MilanoEnums.TimePeriod.PerWeek);
                    break;
                case PerMonth:
                    if(GlobalMember.DEBUG)entryList = generateRandomData(NUM_MONTHS,maxXMonth);
                    barDataSet = new BarDataSet(entryList,context.getString(R.string.finances_per_month_title));
                    labels = UserInterfaceUtil.getInstance().getLabels(context, MilanoEnums.TimePeriod.PerMonth);
                    break;
                case PerYear:
                    if(GlobalMember.DEBUG)entryList = generateRandomData(1,maxXYear);
                    barDataSet = new BarDataSet(entryList,context.getString(R.string.finances_per_year_title));
                    labels = UserInterfaceUtil.getInstance().getLabels(context, MilanoEnums.TimePeriod.PerYear);
                    break;
                case AllTime:
                    if(GlobalMember.DEBUG)entryList = generateRandomData(1,maxAllTime);
                    barDataSet = new BarDataSet(entryList,context.getString(R.string.finances_per_all_title));
                    labels = UserInterfaceUtil.getInstance().getLabels(context, MilanoEnums.TimePeriod.AllTime);
                    break;
            }
                   // barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
                    data = new BarData(labels,barDataSet);


        return data;

    }

    private  List<BarEntry> generateRandomData(int numData,float maxValueData){
        List<BarEntry> entries = new ArrayList<>();
        Random rand = new Random();
        int counter = 0;
           while(counter < numData){
               float yValue = rand.nextFloat() * (maxValueData );
              entries.add(new BarEntry(yValue,counter));
               counter++;
           }
        return  entries ;
    }

    public String gererateWeekRange(){
        StringBuilder builder = new StringBuilder();
        Date date = new Date();
        Calendar c = Calendar.getInstance();
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        c.setTime(date);
        int i = c.get(Calendar.DAY_OF_WEEK) - c.getFirstDayOfWeek();
        c.add(Calendar.DATE, -i - 7);
        String start = format1.format(c.getTime());
        c.add(Calendar.DATE, 6);
        String end = format1.format(c.getTime());
        return builder.append(start).append("--").append(end).toString();
    }

    public String gererateWeekRange(String dateString){
        StringBuilder builder = new StringBuilder();
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format1.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            date = new Date();
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int i = c.get(Calendar.DAY_OF_WEEK) - c.getFirstDayOfWeek();
        c.add(Calendar.DATE, -i - 7);
        String start = format1.format(c.getTime());
        c.add(Calendar.DATE, 6);
        String end = format1.format(c.getTime());
        return builder.append(start).append("--").append(end).toString();
    }

    public String getMonthForInt(int num) {
        String month = "wrong";
        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] months = dfs.getMonths();
        if (num >= 0 && num <= 11 ) {
            month = months[num];
        }
        return month;
    }

    public String getDayForInt(int num){
       return Calendar.getInstance().getDisplayName(num,Calendar.LONG, Locale.getDefault());
    }

    public   SimpleDateFormat getDateSimpleFormat(){
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        return  format1 ;
    }
}
