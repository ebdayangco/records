package com.example.deniellesoberano.record;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.deniellesoberano.record.Model.Record;

public class ViewDialog extends AlertDialog.Builder implements DialogInterface.OnShowListener {


    private Record viewedRecord;
    private TextView name,desc,quantity,price;
    private AlertDialog alertDialog;


    public ViewDialog(Context context,Record record) {
        super(context);

        this.viewedRecord = record;
        setTitle("ID number: "+this.viewedRecord.getId());

        View mainView = LayoutInflater.from(getContext())
                .inflate(R.layout.view_record, null);

        init(mainView);
        setView(mainView);
        setPositiveButton("BACK", null);

        alertDialog = create();
        alertDialog.setOnShowListener(this);
    }


    public void init(View mainView){

        name = (TextView)mainView.findViewById(R.id.namedisplay);
        desc = (TextView)mainView.findViewById(R.id.descDisplay);
        quantity = (TextView)mainView.findViewById(R.id.quantitydisplay);
        price = (TextView)mainView.findViewById(R.id.priceDisplay);


        name.setText(this.viewedRecord.getProductname());
        desc.setText(this.viewedRecord.getDescription());
        quantity.setText(this.viewedRecord.getQuantity()+"");
        price.setText(this.viewedRecord.getPrice()+"");

    }
    public void onShow(){

        alertDialog.show();
    }

    @Override
    public void onShow(DialogInterface dialog) {

    }
}
