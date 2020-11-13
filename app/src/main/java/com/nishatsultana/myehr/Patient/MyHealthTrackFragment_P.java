package com.nishatsultana.myehr.Patient;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;


import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.installations.Utils;
import com.nishatsultana.myehr.Model.BP_Pojo;
import com.nishatsultana.myehr.Prevalent.Prevalent;
import com.nishatsultana.myehr.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class MyHealthTrackFragment_P extends Fragment {

    EditText y1Value, y2Value, y3Value, dateTime;
    Button btn_insert, btn_viewChart;
    //final Calendar myCalendar = Calendar.getInstance();

    //int day, month, year, hour, minute;
    //

    XAxis xAxis;

    FirebaseDatabase database2;
    DatabaseReference reference2;
    LineDataSet lineDataSet = new LineDataSet( null,null);
    LineDataSet lineDataSet2 = new LineDataSet( null,null);
    LineDataSet lineDataSet3 = new LineDataSet( null,null);

    ArrayList<LineDataSet> lines = new ArrayList<>();

    ArrayList<ILineDataSet> iLineDataSets = new ArrayList<>();
    ArrayList<ILineDataSet> iLineDataSets2 = new ArrayList<>();

    LineData  lineData, lineData2;

    SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");

    private static final String TAG = "MyHealthTrackFragment_P";

    private LineChart mChart;

    View v;

    public MyHealthTrackFragment_P() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_my_health_track, container, false);


        y1Value = v.findViewById(R.id.sys_value);
        y2Value = v.findViewById(R.id.dias_value);
        y3Value = v.findViewById(R.id.heartRate_value);
        dateTime = (EditText) v.findViewById(R.id.enter_date_time);


        btn_insert = v.findViewById(R.id.btn_insert);
        btn_viewChart = v.findViewById(R.id.btn_viewChart);

        mChart = v.findViewById(R.id.linechart);//graphView = (GraphView) v.findViewById(R.id.graphView);

        database2 = FirebaseDatabase.getInstance();
        reference2 = database2.getReference("BPTable");

        mChart.setDragEnabled(true);
        mChart.setScaleEnabled(false);

        //get Time and Date values
        /*dateTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), getActivity().this,year, month,day);
                datePickerDialog.show();
            }
        });*/


        //inserting Data to the database
        insertData();

        //Retrievig data from database
        retriveData();

        //remove right Y axis
        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.removeAllLimitLines();
        leftAxis.setAxisMaximum(200f);
        leftAxis.setAxisMinimum(20f);
        leftAxis.enableGridDashedLine(10f,10f,0);
        leftAxis.setDrawLimitLinesBehindData(true);
        mChart.getAxisRight().setEnabled(false);

        xAxis = mChart.getXAxis();

        //xAxis.setValueFormatter(new MyAxisValueFormatter(values));
        xAxis.setGranularity(1);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setLabelRotationAngle(90);
        xAxis.enableGridDashedLine(2f, 7f, 0f);
        xAxis.setAxisMaximum(5f);
        xAxis.setAxisMinimum(0f);
        xAxis.setLabelCount(6, true);
        xAxis.setGranularityEnabled(true);
        xAxis.setGranularity(7f);
        xAxis.setLabelRotationAngle(315f);


        xAxis.setCenterAxisLabels(true);


        xAxis.setDrawLimitLinesBehindData(true);



        mChart.getDescription().setEnabled(true);
        Description description = new Description();

        description.setText("Time");
        description.setTextSize(15f);

        return v;

    }
    /*
    //@Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        myYear = year;
        myday = day;
        myMonth = month;
        Calendar c = Calendar.getInstance();
        hour = c.get(Calendar.HOUR);
        minute = c.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), (TimePickerDialog.OnTimeSetListener) getActivity(), hour, minute, DateFormat.is24HourFormat(getActivity()));
        timePickerDialog.show();
    }
    //@Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        myHour = hourOfDay;
        myMinute = minute;
        dateTime.setText("Year: " + myYear + "\n" +
                "Month: " + myMonth + "\n" +
                "Day: " + myday + "\n" +
                "Hour: " + myHour + "\n" +
                "Minute: " + myMinute);
    }*/

    private void showChart(String[] xValue, ArrayList<Entry> dataVals, ArrayList<Entry> dataVals2,ArrayList<Entry> dataVals3) {


        //xAxis.setValueFormatter(new ClaimsXAxisValueFormatter(xValue));


        //styleSystolic
        lineDataSet.setColor(Color.GREEN);
        lineDataSet.setLineWidth(3f);
        lineDataSet.setValueTextSize(10f);
        lineDataSet.setValueTextColor(Color.GREEN);

        //styleDiastlic
        lineDataSet2.setColor(Color.YELLOW);
        lineDataSet2.setLineWidth(3f);
        lineDataSet2.setValueTextSize(10f);
        lineDataSet2.setValueTextColor(Color.YELLOW);

        //styleHeartRate
        lineDataSet3.setColor(Color.RED);
        lineDataSet3.setLineWidth(3f);
        lineDataSet3.setValueTextSize(10f);
        lineDataSet3.setValueTextColor(Color.RED);

        //systolic
        lineDataSet.setValues(dataVals);
        lineDataSet.setLabel("Systolic Pressure");
        iLineDataSets.clear();
        iLineDataSets.add(lineDataSet);

        //diastolic
        lineDataSet2.setValues(dataVals2);
        lineDataSet2.setLabel("Diastolic Pressure");
        //iLineDataSets2.clear();
        iLineDataSets.add(lineDataSet2);
        //lineData2 = new LineData(iLineDataSets2);

        //heartRate
        lineDataSet3.setValues(dataVals3);
        lineDataSet3.setLabel("Diastolic Pressure");
        //iLineDataSets2.clear();
        iLineDataSets.add(lineDataSet3);
        //lineData2 = new LineData(iLineDataSets2);

        lineData = new LineData(iLineDataSets);

        lines.add(lineDataSet);
        lines.add(lineDataSet2);
        lines.add(lineDataSet3);


        mChart.clear();
        //mChart.setData(lineData);//systolic
       // mChart.setData(lineData2);//Diastolic
        mChart.setData(lineData);// value of x axis, linens
        mChart.invalidate();

    }

    private void insertData() {

        btn_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = reference2.push().getKey();
                final String phone = Prevalent.CurrentOnlineUser.getPhone();

                // final String time = String.valueOf(new Date().getTime());
                //final long x = Long.valueOf(time);
                final long x = new Date().getTime();

                final int y1 = Integer.parseInt(y1Value.getText().toString());
                final int y2 = Integer.parseInt(y2Value.getText().toString());
                final int y3 = Integer.parseInt(y3Value.getText().toString());


                BP_Pojo pointValue = new BP_Pojo(x, y1, y2, y3);

                reference2.child(phone).child(id).setValue(pointValue);

                retriveData();
            }

        });

    }

    private void retriveData() {
        final String phone = Prevalent.CurrentOnlineUser.getPhone();

        reference2.child(phone).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<Entry>dataVals = new ArrayList<Entry>();
                ArrayList<Entry>dataVals2 = new ArrayList<Entry>();
                ArrayList<Entry>dataVals3 = new ArrayList<Entry>();

                ArrayList<String> xAxisLabel = new ArrayList<>();

                final String phone = Prevalent.CurrentOnlineUser.getPhone();

                Log.e("Data Retriving...",dataSnapshot.getChildrenCount()+"Done");
                if(dataSnapshot.hasChildren()){
                    String[] xValue = new String[(int)dataSnapshot.getChildrenCount()];
                    for(DataSnapshot myDataSnamdhot: dataSnapshot.getChildren()){
                        BP_Pojo datapoint = myDataSnamdhot.getValue(BP_Pojo.class);

                        dataVals.add(new Entry(datapoint.getTimeValue(), datapoint.getSysValue()));
                        dataVals2.add(new Entry(datapoint.getTimeValue(), datapoint.getDiasValue()));
                        dataVals3.add(new Entry(datapoint.getTimeValue(), datapoint.getHeartRateValue()));
//                        xValue[xValue.length] = datapoint.getTimeValue() + "";
                    }


                    showChart(xValue,dataVals, dataVals2, dataVals3);
                    //showChart(dataVals2);
                }

                else{
                    mChart.clear();
                    mChart.invalidate();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }



/*
    public static long getDateInMilliSeconds(String givenDateString, String format) {
        String DATE_TIME_FORMAT = format;
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_TIME_FORMAT, Locale.US);
        long timeInMilliseconds = 1;
        try {
            Date mDate = sdf.parse(givenDateString);
            timeInMilliseconds = mDate.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return timeInMilliseconds;
    }


    public class ClaimsXAxisValueFormatter extends ValueFormatter {

        List<String> datesList;

        public ClaimsXAxisValueFormatter(List<String> arrayOfDates) {
            this.datesList = arrayOfDates;
        }


        @Override
        public String getAxisLabel(float value, AxisBase axis) {

            Integer position = Math.round(value);
            SimpleDateFormat sdf = new SimpleDateFormat("MMM dd");

            if (value > 1 && value < 2) {
                position = 0;
            } else if (value > 2 && value < 3) {
                position = 1;
            } else if (value > 3 && value < 4) {
                position = 2;
            } else if (value > 4 && value <= 5) {
                position = 3;
            }
            if (position < datesList.size())
                return sdf.format(new Date((getDateInMilliSeconds(datesList.get(position), "yyyy-MM-dd"))));
            return "";
        }
    }*/


}




