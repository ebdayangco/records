package com.example.deniellesoberano.record;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.media.Image;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.deniellesoberano.record.Model.EditDialog;
import com.example.deniellesoberano.record.Model.Record;


import java.util.ArrayList;
import java.util.List;

public class SearchRecordActivity extends Activity implements View.OnClickListener,TextWatcher {

    private ListView lister;
    private ArrayList<Record> records;
    private ImageView back;
    private EditText txtsearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_record);
        init();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

    }

    public void init(){
        lister = (ListView)findViewById(R.id.displayer);
        back = (ImageView)findViewById(R.id.backbtn);
        txtsearch = (EditText)findViewById(R.id.txtSearch);

        txtsearch.addTextChangedListener(this);


        back.setOnClickListener(this);
        getAllDatas("");
        lister.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {



              ViewDialog viewInfo = new ViewDialog(SearchRecordActivity.this,records.get(position));
                viewInfo.onShow();
            }
        });


    }
    public void getAllDatas(String cond){
        records = new ArrayList<>();

        Cursor res = new MyDatabase(this).getData(cond);

        if(res.getCount() != 0){

                while(res.moveToNext()){

                    Record eachRecord = new Record();
                    eachRecord.setId(res.getInt(0));
                    eachRecord.setDescription(res.getString(1));
                    eachRecord.setQuantity(res.getInt(2));
                    eachRecord.setPrice(res.getDouble(3));
                    eachRecord.setProductname(res.getString(4));
                    this.records.add(eachRecord);

                }

            ListAdapter adapter = new ListAdapter(this);
            adapter.notifyDataSetChanged();
            lister.setAdapter(adapter);
        }



    }

    public ArrayList<Record> getRecords() {
        return records;
    }

    @Override
    public void onClick(View v) {

        if(v == back){

            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            overridePendingTransition(R.animator.enter, R.animator.exit);
            finish();
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        String value = txtsearch.getText().toString();
        getAllDatas(" WHERE productname LIKE '%"+value+"%'");

    }



    class ListAdapter extends BaseAdapter{

        SearchRecordActivity activity;

        public ListAdapter(SearchRecordActivity activity) {
            this.activity = activity;
        }

        @Override
        public int getCount() {
            return activity.getRecords().size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            convertView = getLayoutInflater().inflate(R.layout.content_listview,null);
            TextView productNameView = (TextView)convertView.findViewById(R.id.productName);
            ImageView editbtn = (ImageView)convertView.findViewById(R.id.editbtn);
            ImageView delbtn = (ImageView)convertView.findViewById(R.id.deletebtn);


            productNameView.setText(activity.getRecords().get(position).getProductname());

            editbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    EditDialog editInfo = new EditDialog(activity,activity.getRecords().get(position),activity);
                    editInfo.onShow();

                }
            });
            delbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Record record = activity.getRecords().get(position);
                    if(new MyDatabase(activity).deleteData(record)){
                        activity.init();

                    }
                }
            });

            return convertView;
        }
    }
}
