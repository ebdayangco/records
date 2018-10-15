package com.example.deniellesoberano.record;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button addbtn,searchbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    public void init(){


        addbtn = (Button)findViewById(R.id.addbtn);
        searchbtn = (Button)findViewById(R.id.findbtn);

        addbtn.setOnClickListener(this);
        searchbtn.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {


        if(v == addbtn){

            startActivity(new Intent(getApplicationContext(), AddRecordActivity.class));
            overridePendingTransition(R.animator.enter, R.animator.exit);
            finish();


        }else if(v == searchbtn){

            startActivity(new Intent(getApplicationContext(), SearchRecordActivity.class));
            overridePendingTransition(R.animator.toright, R.animator.toleft);
            finish();
        }



    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
}
