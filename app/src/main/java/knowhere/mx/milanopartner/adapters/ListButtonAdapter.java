package knowhere.mx.milanopartner.adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.LinkedList;

import knowhere.mx.milanopartner.widgetscoronado.ListButton;

/**
 * Created by cacorona on 05/10/2016.
 */
public class ListButtonAdapter extends BaseAdapter {

    private LinkedList<ListButton> buttonList;

    public ListButtonAdapter(LinkedList<ListButton> buttonList){
        this.buttonList = buttonList ;
    }

    @Override
    public int getCount() {
        return buttonList.size();
    }

    @Override
    public Object getItem(int position) {
       return buttonList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return buttonList.get(position);
    }
}
