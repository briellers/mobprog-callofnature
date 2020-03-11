package com.example.callofnature;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

// codes from SQLiteDemo in our Mobile Programming Lecture!
// Thank you sir hehehehe


public class ListActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        TextView tv_list = findViewById(R.id.tvStudentList);
        DBase db = new DBase(this);
        db.open();
        String recs = db.getAllRecords();
        db.close();

        if(recs.isEmpty()){
            Dialog d = new Dialog(this);
            d.setTitle("Message");
            TextView tv = new TextView(this);
            tv.setText("There are no records");
            d.setContentView(tv);
            d.show();
        } else {
            tv_list.setText(recs);
        }
    }

    public void doBack(View v){
        finish();
    }


}
