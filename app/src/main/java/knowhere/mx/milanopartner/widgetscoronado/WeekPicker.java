package knowhere.mx.milanopartner.widgetscoronado;

import android.app.DatePickerDialog;
import android.content.Context;
import android.widget.DatePicker;

import java.util.Calendar;

import knowhere.mx.milanopartner.utils.MilanoLogger;

/**
 * Created by cacorona on 26/09/2016.
 */
public class WeekPicker extends DatePickerDialog {

    private static String TAG = WeekPicker.class.getSimpleName();

    private static WeekPicker instance;
    private static OnDateSetListener dateSetListener;

    private static int yearSelected;
    private static int monthSelected;
    private static int daySelected;
    private String currentDateSelected;
    private static onDateSelected dateListener;

    public interface onDateSelected{
        void selectedDate(String currentDataDate);
    }



    public WeekPicker(Context context, OnDateSetListener callBack, int year, int monthOfYear, int dayOfMonth,onDateSelected dateListener) {
        super(context, callBack, year, monthOfYear, dayOfMonth);
        this.dateListener = dateListener;

    }



    public static  WeekPicker getInstance(Context context, final onDateSelected dateListener){
        if(instance == null){
            yearSelected = Calendar.getInstance().get(Calendar.YEAR);
            monthSelected = Calendar.getInstance().get(Calendar.MONTH);
            daySelected = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
            dateSetListener = new OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    MilanoLogger.getINSTANCE().escribeArchivo(TAG,"DATE SELECTED",""+monthOfYear);
                    StringBuilder dateBuilder = new StringBuilder();
                    dateBuilder.append(year).append("-").append(monthOfYear).append("-").append(dayOfMonth);
                    dateListener.selectedDate(dateBuilder.toString());

                }
            };
         instance = new WeekPicker(context,dateSetListener,yearSelected,monthSelected,daySelected,dateListener);


        }
        return  instance;
    }

    public void showWeekPicker(){
        instance.show();
    }


}
