package com.reversecoder.dailyreport;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.Calendar;

public class DailyReportMainActivity extends Activity implements View.OnClickListener {

    Button btnStartTime, btnEndTime, btnSync;
    EditText edtStartTime, edtEndTime;
    private int mYear, mMonth, mDay, mHour, mMinute;
    TextView txtSplitedTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_report_main);

        btnStartTime = (Button) findViewById(R.id.btn_start_time);
        btnEndTime = (Button) findViewById(R.id.btn_end_time);
        btnSync = (Button) findViewById(R.id.btn_sync);
        txtSplitedTime = (TextView) findViewById(R.id.txt_splited_time);

        edtStartTime = (EditText) findViewById(R.id.set_start_time);
        edtEndTime = (EditText) findViewById(R.id.set_end_time);

        btnStartTime.setOnClickListener(this);
        btnEndTime.setOnClickListener(this);
        btnSync.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        if (v == btnStartTime) {

            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {

                            //edtStartTime.setText(hourOfDay + ":" + minute);
                            edtStartTime.setText(String.format("%02d:%02d", hourOfDay, minute));
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }
        if (v == btnEndTime) {

            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {

                            //edtEndTime.setText(hourOfDay + ":" + minute);
                            edtEndTime.setText(String.format("%02d:%02d", hourOfDay, minute));
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }

        if(v==btnSync){
            String startTime = edtStartTime.getText().toString();
            String endTime = edtEndTime.getText().toString();

            ArrayList<String> splitedTime = splitTime(Integer.parseInt(startTime.split(":")[0]),Integer.parseInt(endTime.split(":")[0]),30);

            String tempTime="";
            for(int i=0;i<splitedTime.size();i++){
                Log.d("splitedTime",i+" no data is: "+splitedTime.get(i));
                tempTime=tempTime+splitedTime.get(i)+"\n";
            }

            txtSplitedTime.setText(tempTime);
        }
    }

    public ArrayList<String> splitTime(int begin, int end, int interval) {
        ArrayList<String> tempTimeRange =new ArrayList<String>();
        for (int time = begin; time <= end; time += interval) {
            System.out.println(String.format("%02d:%02d", time / 60, time % 60));
            tempTimeRange.add(String.format("%02d:%02d", time / 60, time % 60));
        }

        return tempTimeRange;
    }
}
