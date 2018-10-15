package com.example.deniellesoberano.record;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.example.deniellesoberano.record.Model.Record;


public class AddRecordActivity extends Activity implements View.OnClickListener {

    private ImageView back;
    private EditText txtProductName,txtDescription,txtQuantity,txtPrice;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_record);
        init();

    }

    public void init(){


        back = (ImageView)findViewById(R.id.backbtn);
        txtProductName = (EditText)findViewById(R.id.productName);
        txtDescription = (EditText)findViewById(R.id.description);
        txtQuantity = (EditText)findViewById(R.id.quantity);
        txtPrice = (EditText)findViewById(R.id.price);
        btnSave = (Button)findViewById(R.id.savebtn);
        back.setOnClickListener(this);
        btnSave.setOnClickListener(this);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.animator.toright, R.animator.toleft);

    }

    @Override
    public void onClick(View v) {

        if(v == back){

            startActivity( new Intent(getApplicationContext(), MainActivity.class));
            overridePendingTransition(R.animator.toright, R.animator.toleft);
            finish();

        }
       else if(v == btnSave){



            if(!validateInput()){

             // new MyDatabase(this).dropTable();
             //new MyDatabase(this).createTable();

              if(new MyDatabase(this).insertData(getRecordEntry())){
                    Toast.makeText(getApplicationContext(),"Successfully Insert New Record",Toast.LENGTH_SHORT).show();
                    clearAll();
              }else{
                    Toast.makeText(getApplicationContext(),"Failed to Insert Ths Record",Toast.LENGTH_SHORT).show();
              }


            }


        }
    }

    public Record getRecordEntry(){

        Record record = new Record();

        record.setProductname(txtProductName.getText().toString());
        record.setDescription(txtDescription.getText().toString());
        record.setQuantity(Integer.parseInt(txtQuantity.getText().toString()));
        record.setPrice(Double.parseDouble(txtPrice.getText().toString()));
        return record;
    }
    public void clearAll(){
        txtProductName.setText("");
        txtDescription.setText("");
        txtQuantity.setText("");
        txtPrice.setText("");

    }

    public boolean validateInput(){

        boolean isValidate = (txtProductName.getText().toString().isEmpty() || txtDescription.getText().toString().isEmpty()
                || txtQuantity.getText().toString().isEmpty() || txtPrice.getText().toString().isEmpty());
        String message = "";

        if(txtProductName.getText().toString().isEmpty()){
            message+="Please insert name. \n";
        }
        if(txtDescription.getText().toString().isEmpty()){
            message+="Please insert Description. \n";
        }
        if(txtQuantity.getText().toString().isEmpty()){
            message+="Please insert Quantity. \n";
        }
        if(txtPrice.getText().toString().isEmpty()){
            message+="Please insert Price. \n";
        }

        if(isValidate){

            Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
        }

       return isValidate;
    }


}
