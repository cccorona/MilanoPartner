package knowhere.mx.milanopartner.widgetscoronado;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.threeten.bp.LocalDate;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import knowhere.mx.milanopartner.R;
import knowhere.mx.milanopartner.utils.UserInterfaceUtil;
import solar.blaz.date.week.WeekDatePicker;


/**
 * Created by cacorona on 27/09/2016.
 */
public class WeekCalendarPicker extends DialogFragment {

    private WeekDatePicker weekCalendar;
    private String beginDate;
    private String finishDate;
    private TextView monthTitle,
                     yearTitle,
                     dateTitle,
                     monthYearTitle;
    private SimpleDateFormat simpleDateFormat;
    private Button accepButton;
    private LocalDate weekSelected;





    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd",Locale.getDefault());
    }



    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
        View rootView = layoutInflater.inflate(R.layout.week_picker_layout,container,false);
        weekCalendar = (WeekDatePicker) rootView.findViewById(R.id.date_picker);
        weekCalendar.setDateIndicator(LocalDate.now().plusDays(1), true);
       // weekCalendar.setLimits(LocalDate.now().minusWeeks(1), null);
        monthTitle = (TextView) rootView.findViewById(R.id.month_title);
        yearTitle = (TextView) rootView.findViewById(R.id.year_title);
        dateTitle = (TextView) rootView.findViewById(R.id.date_title);
        monthYearTitle = (TextView) rootView.findViewById(R.id.month_year_title);
        monthTitle.setText(UserInterfaceUtil.getInstance().getMonthForInt(Calendar.getInstance().get(Calendar.MONTH)));
        yearTitle.setText(String.valueOf(Calendar.getInstance().get(Calendar.YEAR)));
        dateTitle.setText(UserInterfaceUtil.getInstance().gererateWeekRange());
        monthYearTitle.setText(monthTitle.getText().toString()+"\n"+yearTitle.getText().toString());

        weekCalendar.setOnDateSelectedListener(new WeekDatePicker.OnDateSelected() {
            @Override
            public void onDateSelected(LocalDate date) {
                weekSelected = date;
                Toast.makeText(getActivity(),weekSelected.toString(),Toast.LENGTH_LONG).show();
            }
        });

        weekCalendar.setOnWeekChangedListener(new WeekDatePicker.OnWeekChanged() {
            @Override
            public void onItemSelected(LocalDate firstDay) {
                weekSelected = firstDay;
                Toast.makeText(getActivity(),weekSelected.toString(),Toast.LENGTH_LONG).show();
            }
        });

        accepButton = (Button) rootView.findViewById(R.id.getWeekButton);
        accepButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });





        return rootView;
    }
}
