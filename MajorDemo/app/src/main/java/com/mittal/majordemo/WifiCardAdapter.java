package com.mittal.majordemo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class WifiCardAdapter extends ArrayAdapter<WifiCardVariable> {

    public WifiCardAdapter(Context context, ArrayList<WifiCardVariable> tyreDataVariable) {
        super(context, 0, tyreDataVariable);

    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.card_layout, parent, false);
        }

        TextView tyreSize = listItemView.findViewById(R.id.list_treadname);
        TextView tyreType = listItemView.findViewById(R.id.list_treadtype);
        TextView Price = listItemView.findViewById(R.id.list_tyre_position);
        ImageView tyreImage = (ImageView) listItemView.findViewById(R.id.list_imagethumb);

        //the below statements get the text from the class TyreData

        WifiCardVariable currentWifiCardVariable = getItem(position);

        assert currentWifiCardVariable != null;
        tyreSize.setText(currentWifiCardVariable.getTyreSize());
        tyreType.setText(currentWifiCardVariable.getTreadName());
        Price.setText(currentWifiCardVariable.getPrice());
        tyreImage.setImageResource(currentWifiCardVariable.getResourceImageView());

        return listItemView;
    }

}
