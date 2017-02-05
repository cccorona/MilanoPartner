package knowhere.mx.milanopartner.widgetscoronado;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import knowhere.mx.milanopartner.R;
import knowhere.mx.milanopartner.utils.MilanoEnums;

/**
 * Created by cacorona on 05/10/2016.
 */
public class ListButton extends LinearLayout {


    private ImageView iconView;
    private ImageView arrowView;
    private TextView titleText;
    private TextView descriptionText;
    private Context context;

    private MilanoEnums.Service myService ;

    public ListButton(Context context) {
        super(context);
        this.context = context ;
        this.myService = MilanoEnums.Service.Unknown;
        init(context);

    }

    public ListButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context ;
        this.myService = MilanoEnums.Service.Unknown;
        init(context);

    }

    private void init(Context context){
        inflate(getContext(), R.layout.list_button_layout,this);
        iconView = (ImageView) findViewById(R.id.icon_button);
        arrowView = (ImageView) findViewById(R.id.arrow_icon_button);
        titleText = (TextView) findViewById(R.id.title_button);
        descriptionText = (TextView) findViewById(R.id.description_button);

    }


    public ImageView getIconView() {
        return iconView;
    }

    public void setIconView(ImageView iconView) {
        this.iconView = iconView;
    }

    public ImageView getArrowView() {
        return arrowView;
    }

    public void setArrowView(ImageView arrowView) {
        this.arrowView = arrowView;
    }

    public TextView getTitleText() {
        return titleText;
    }

    public void setTitleText(TextView titleText) {
        this.titleText = titleText;
    }

    public TextView getDescriptionText() {
        return descriptionText;
    }

    public void setDescriptionText(TextView descriptionText) {
        this.descriptionText = descriptionText;
    }

    public MilanoEnums.Service getMyService() {
        return myService;
    }

    public void setMyService(MilanoEnums.Service myService) {
        this.myService = myService;
    }
}
