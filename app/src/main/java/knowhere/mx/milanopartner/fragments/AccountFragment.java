package knowhere.mx.milanopartner.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.LinkedList;

import knowhere.mx.milanopartner.R;
import knowhere.mx.milanopartner.adapters.ListButtonAdapter;
import knowhere.mx.milanopartner.widgetscoronado.FullScreenProgressBar;
import knowhere.mx.milanopartner.widgetscoronado.ListButton;

/**
 * Created by Corona on 6/24/2016.
 */
public class AccountFragment extends Fragment {

    private TextView editTextView,
                     selectTextView;

    private ListView optionList;
    private LinkedList<ListButton> listButtons;
    private ListButtonAdapter listButtonAdapter;
    private Button exitButton ;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View myFragmentView = inflater.inflate(R.layout.account_fragment_layout,container,false);
        editTextView = (TextView) myFragmentView.findViewById(R.id.edit_option);
        selectTextView = (TextView) myFragmentView.findViewById(R.id.edit_home_option);
        optionList = (ListView) myFragmentView.findViewById(R.id.list_option_account);
        exitButton = (Button) myFragmentView.findViewById(R.id.exit_button);
        return  myFragmentView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        listButtons = generateOptionList();
        listButtonAdapter = new ListButtonAdapter(listButtons);
        optionList.setAdapter(listButtonAdapter);
        optionList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListButton itemSelected= (ListButton)view;
                   switch (itemSelected.getMyService()){

                   }
            }
        });
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FullScreenProgressBar.getInstance(getActivity()).showProgress();
                new CountDownTimer(10000,1000){

                    @Override
                    public void onTick(long millisUntilFinished) {

                    }

                    @Override
                    public void onFinish() {
                      System.exit(0);
                    }
                };
            }
        });

    }

    private LinkedList<ListButton> generateOptionList(){
        LinkedList<ListButton> options = new LinkedList<>();
        ListButton helpButton = new ListButton(getActivity());
        helpButton.getTitleText().setText(getResources().getText(R.string.ayuda));
       // helpButton.getIconView().setImageResource(R.drawable.ic_help_white_24dp);
        helpButton.getIconView().setColorFilter(Color.argb(200, 200, 200, 200));
        helpButton.getArrowView().setVisibility(View.VISIBLE);
        ListButton helpButton2 = new ListButton(getActivity());
        helpButton2.getTitleText().setText(getResources().getText(R.string.documentos));
     //   helpButton2.getIconView().setImageResource(R.drawable.ic_description_white_24dp);
        helpButton2.getArrowView().setVisibility(View.VISIBLE);
        helpButton2.getIconView().setColorFilter(Color.argb(200, 200, 200, 200));
        ListButton helpButton3 = new ListButton(getActivity());
        helpButton3.getTitleText().setText(getResources().getText(R.string.pagos));
      //  helpButton3.getIconView().setImageResource(R.drawable.ic_credit_card_white_24dp);
        helpButton3.getArrowView().setVisibility(View.VISIBLE);
        helpButton3.getIconView().setColorFilter(Color.argb(200, 200, 200, 200));
        ListButton helpButton4 = new ListButton(getActivity());
        helpButton4.getTitleText().setText(getResources().getText(R.string.configuracion));
        helpButton4.getIconView().setImageResource(R.drawable.ic_settings_white_24dp);
        helpButton4.getArrowView().setVisibility(View.VISIBLE);
        helpButton4.getIconView().setColorFilter(Color.argb(200, 200, 200, 200));
        ListButton helpButton5 = new ListButton(getActivity());
        helpButton5.getTitleText().setText(getResources().getText(R.string.acercade));
        helpButton5.getIconView().setImageResource(R.drawable.ic_info_white_24dp);
        helpButton5.getIconView().setColorFilter(Color.argb(200, 200, 200, 200));
        helpButton5.getArrowView().setVisibility(View.VISIBLE);
        options.add(helpButton);
        options.add(helpButton2);
        options.add(helpButton3);
        options.add(helpButton4);
        options.add(helpButton5);




        return  options;

    }
}
