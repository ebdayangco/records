package com.example.deniellesoberano.record.Model;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.deniellesoberano.record.MyDatabase;
import com.example.deniellesoberano.record.R;
import com.example.deniellesoberano.record.SearchRecordActivity;

public class EditDialog extends AlertDialog.Builder implements DialogInterface.OnShowListener  {

    private Record viewedRecord;
    private TextView name,desc,quantity,price;
    private AlertDialog alertDialog;
    private SearchRecordActivity activity;


    public EditDialog(Context context,Record record,SearchRecordActivity activity) {
        super(context);
        this.viewedRecord = record;
        this.activity = activity;
        setTitle("ID number: "+this.viewedRecord.getId());

        View mainView = LayoutInflater.from(getContext())
                .inflate(R.layout.edit_record, null);
        init(mainView);
        setView(mainView);
        setPositiveButton("SAVE", null);
        setNegativeButton("CANCEL",null);



        alertDialog = create();
        alertDialog.setOnShowListener(this);

    }

    public void init(View mainView){

        name = (TextView)mainView.findViewById(R.id.editName);
        desc = (TextView)mainView.findViewById(R.id.editDescription);
        quantity = (TextView)mainView.findViewById(R.id.editQuantity);
        price = (TextView)mainView.findViewById(R.id.editPrice);


        name.setText(this.viewedRecord.getProductname());
        desc.setText(this.viewedRecord.getDescription());
        quantity.setText(this.viewedRecord.getQuantity()+"");
        price.setText(this.viewedRecord.getPrice()+"");

    }

    public void onShow(){

        alertDialog.show();
    }

    @Override
    public void onShow(final DialogInterface dialog) {

        Button btnPositive = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
        btnPositive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                viewedRecord.setProductname(name.getText().toString());
                viewedRecord.setDescription(desc.getText().toString());
                viewedRecord.setQuantity(Integer.parseInt(quantity.getText().toString()));
                viewedRecord.setPrice(Double.parseDouble(price.getText().toString()));

                if(new MyDatabase(getContext()).updateData(viewedRecord)){

                    activity.init();
                    dialog.dismiss();

                }else{

                    Toast.makeText(getContext(),"Cannot Edit this Record",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
