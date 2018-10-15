package com.example.deniellesoberano.record;

import android.app.Activity;
import android.os.Bundle;

import com.example.deniellesoberano.record.Model.Record;

public class EditRecordActivity extends Activity {

    private Record editRecord;

    public EditRecordActivity(){}

    public EditRecordActivity(Record record){
        this.editRecord = record;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_record);
        init();
    }

    public void init(){
        this.editRecord = new Record();

    }

    public void display(){


    }

}
