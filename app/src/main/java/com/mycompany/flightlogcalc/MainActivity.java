package com.mycompany.flightlogcalc;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.Period;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;

public class MainActivity extends ActionBarActivity {

    EditText edtTxtOutTime1, edtTxtOffTime1, edtTxtOnTime1, edtTxtInTime1;
    TextView txtViewFlightTime1, txtViewBlockTime1;
    EditText edtTxtOutTime2, edtTxtOffTime2, edtTxtOnTime2, edtTxtInTime2;
    TextView txtViewFlightTime2, txtViewBlockTime2;
    EditText edtTxtOutTime3, edtTxtOffTime3, edtTxtOnTime3, edtTxtInTime3;
    TextView txtViewFlightTime3, txtViewBlockTime3;
    EditText edtTxtOutTime4, edtTxtOffTime4, edtTxtOnTime4, edtTxtInTime4;
    TextView txtViewFlightTime4, txtViewBlockTime4;
    EditText edtTxtOutTime5, edtTxtOffTime5, edtTxtOnTime5, edtTxtInTime5;
    TextView txtViewFlightTime5, txtViewBlockTime5;
    TextView txtViewTotalFlightTime, txtViewTotalBlockTime;
    
// So no calculations are done until a pair is complete
    // this is a test for merging files
    int out1 = 0, off1 = 0, on1 = 0, in1 = 0;
    int out2 = 0, off2 = 0, on2 = 0, in2 = 0;
    int out3 = 0, off3 = 0, on3 = 0, in3 = 0;
    int out4 = 0, off4 = 0, on4 = 0, in4 = 0;
    int out5 = 0, off5 = 0, on5 = 0, in5 = 0;

    public static String timeAddColon (String time){

        String hours = time.substring(0, 2);
        String minutes = time.substring(2, 4);

        return hours + ":" + minutes;
    }

    public static String addTotals (String time1, String time2, String time3, String time4, String time5){
        PeriodFormatter formatter = new PeriodFormatterBuilder()
                .minimumPrintedDigits(2)
                .printZeroAlways()
                .appendHours()
                .appendLiteral(":")
                .appendMinutes()
                .toFormatter();

        Period period1 = formatter.parsePeriod(time1);
        Period period2 = formatter.parsePeriod(time2);
        Period period3 = formatter.parsePeriod(time3);
        Period period4 = formatter.parsePeriod(time4);
        Period period5 = formatter.parsePeriod(time5);

        Period periodTotal = period1.plus(period2).plus(period3).plus(period4).plus(period5);

        Period periodFinal = new Period(periodTotal).normalizedStandard();

        return formatter.print(periodFinal);
    }



    public static String timeCalc (String beginTime, String endTime){

        LocalDate beginDate = new LocalDate();
        LocalDate endDate = new LocalDate();


        String dateTimeBeginTime = beginDate.toString()+ " " + beginTime + ":00";
        String dateTimeEndTime = endDate.toString()+ " " + endTime + ":00";

        DateTime finalDateTimeBegin = DateTime.parse(dateTimeBeginTime,DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss"));
        DateTime finalDateTimeEnd = DateTime.parse(dateTimeEndTime,DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss"));
        if (finalDateTimeBegin.isAfter(finalDateTimeEnd)){
            finalDateTimeEnd = finalDateTimeEnd.plusDays(1);
        }
        Period period = new Period(finalDateTimeBegin,finalDateTimeEnd);

        PeriodFormatter formatter = new PeriodFormatterBuilder()
                .minimumPrintedDigits(2)
                .printZeroAlways()
                .appendHours()
                .appendSuffix(":")
                .appendMinutes()
                .toFormatter();

        return formatter.print(period);
    }

   // public static String superCalc(){
   //     String totals = "";
   //     return totals;
   // }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        edtTxtOutTime1 = (EditText)findViewById(R.id.editTextLeg1TimeOut);
        edtTxtOffTime1 = (EditText) findViewById(R.id.editTextLeg1TimeOff);
        edtTxtOnTime1 = (EditText) findViewById(R.id.editTextLeg1TimeOn);
        edtTxtInTime1 = (EditText)findViewById(R.id.editTextLeg1TimeIn);

        edtTxtOutTime2 = (EditText)findViewById(R.id.editTextLeg2TimeOut);
        edtTxtOffTime2 = (EditText) findViewById(R.id.editTextLeg2TimeOff);
        edtTxtOnTime2 = (EditText) findViewById(R.id.editTextLeg2TimeOn);
        edtTxtInTime2 = (EditText)findViewById(R.id.editTextLeg2TimeIn);

        edtTxtOutTime3 = (EditText)findViewById(R.id.editTextLeg3TimeOut);
        edtTxtOffTime3 = (EditText) findViewById(R.id.editTextLeg3TimeOff);
        edtTxtOnTime3 = (EditText) findViewById(R.id.editTextLeg3TimeOn);
        edtTxtInTime3 = (EditText)findViewById(R.id.editTextLeg3TimeIn);

        edtTxtOutTime4 = (EditText)findViewById(R.id.editTextLeg4TimeOut);
        edtTxtOffTime4 = (EditText) findViewById(R.id.editTextLeg4TimeOff);
        edtTxtOnTime4 = (EditText) findViewById(R.id.editTextLeg4TimeOn);
        edtTxtInTime4 = (EditText)findViewById(R.id.editTextLeg4TimeIn);

        edtTxtOutTime5 = (EditText)findViewById(R.id.editTextLeg5TimeOut);
        edtTxtOffTime5 = (EditText) findViewById(R.id.editTextLeg5TimeOff);
        edtTxtOnTime5 = (EditText) findViewById(R.id.editTextLeg5TimeOn);
        edtTxtInTime5 = (EditText)findViewById(R.id.editTextLeg5TimeIn);

        txtViewFlightTime1 = (TextView)findViewById(R.id.textViewLeg1FlightTime);
        txtViewBlockTime1 = (TextView)findViewById(R.id.textViewLeg1BlockTime);
        txtViewFlightTime2 = (TextView)findViewById(R.id.textViewLeg2FlightTime);
        txtViewBlockTime2 = (TextView)findViewById(R.id.textViewLeg2BlockTime);
        txtViewFlightTime3 = (TextView)findViewById(R.id.textViewLeg3FlightTime);
        txtViewBlockTime3 = (TextView)findViewById(R.id.textViewLeg3BlockTime);
        txtViewFlightTime4 = (TextView)findViewById(R.id.textViewLeg4FlightTime);
        txtViewBlockTime4 = (TextView)findViewById(R.id.textViewLeg4BlockTime);
        txtViewFlightTime5 = (TextView)findViewById(R.id.textViewLeg5FlightTime);
        txtViewBlockTime5 = (TextView)findViewById(R.id.textViewLeg5BlockTime);

        txtViewTotalFlightTime = (TextView)findViewById(R.id.textViewTotalFlightTime);
        txtViewTotalBlockTime = (TextView)findViewById(R.id.textViewTotalBlockTime);

        // LEG 1 Listeners   11111111111111111111111111111111111111111111111111111111111111111111111

        edtTxtOutTime1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 4) {
                    int n = Integer.parseInt(edtTxtOutTime1.getText().toString());
                    int m = Integer.parseInt(edtTxtOutTime1.getText().toString().substring(2, 3));
                    if (n > 2359 || m > 5) {
                        Toast.makeText(getApplicationContext(), getString(R.string.toast_enter_valid_time), Toast.LENGTH_SHORT).show();
                        edtTxtOutTime1.setText("");
                        edtTxtOutTime1.requestFocus();
                    } else {
                        String temp = timeAddColon(edtTxtOutTime1.getText().toString());
                        edtTxtOutTime1.setText(temp);
                        if (in1 != 0){
                            String et = timeCalc(edtTxtOutTime1.getText().toString(), edtTxtInTime1.getText().toString());
                            txtViewBlockTime1.setText(et);
                        }
                        out1 = 1;
                        edtTxtOffTime1.requestFocus();
                    }

                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        edtTxtOffTime1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() ==4){
                    int n = Integer.parseInt(edtTxtOffTime1.getText().toString());
                    int m = Integer.parseInt(edtTxtOffTime1.getText().toString().substring(2, 3));
                    if (n > 2359 || m > 5){
                        Toast.makeText(getApplicationContext(),getString(R.string.toast_enter_valid_time),Toast.LENGTH_SHORT).show();
                        edtTxtOffTime1.setText("");
                        edtTxtOffTime1.requestFocus();
                    }
                    else {
                        String temp = timeAddColon(edtTxtOffTime1.getText().toString());
                        edtTxtOffTime1.setText(temp);
                        if (on1 != 0){
                            String et = timeCalc(edtTxtOffTime1.getText().toString(),edtTxtOnTime1.getText().toString());
                            txtViewFlightTime1.setText(et);
                        }
                        off1 = 1;
                        edtTxtOnTime1.requestFocus();
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
        edtTxtOnTime1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() ==4){
                    int n = Integer.parseInt(edtTxtOnTime1.getText().toString());
                    int m = Integer.parseInt(edtTxtOnTime1.getText().toString().substring(2, 3));
                    if (n > 2359 || m > 5){
                        Toast.makeText(getApplicationContext(),getString(R.string.toast_enter_valid_time),Toast.LENGTH_SHORT).show();
                        edtTxtOnTime1.setText("");
                        edtTxtOnTime1.requestFocus();
                    }
                    else {
                        String temp = timeAddColon(edtTxtOnTime1.getText().toString());
                        edtTxtOnTime1.setText(temp);
                        if (off1 != 0) {
                            String et = timeCalc(edtTxtOffTime1.getText().toString(), edtTxtOnTime1.getText().toString());
                            txtViewFlightTime1.setText(et);
                        }
                        on1 = 1;
                        edtTxtInTime1.requestFocus();
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
        edtTxtInTime1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() ==4){
                    int n = Integer.parseInt(edtTxtInTime1.getText().toString());
                    int m = Integer.parseInt(edtTxtInTime1.getText().toString().substring(2, 3));
                    if (n > 2359 || m > 5){
                        Toast.makeText(getApplicationContext(),getString(R.string.toast_enter_valid_time),Toast.LENGTH_SHORT).show();
                        edtTxtInTime1.setText("");
                        edtTxtInTime1.requestFocus();
                    }
                    else {
                        String temp = timeAddColon(edtTxtInTime1.getText().toString());
                        edtTxtInTime1.setText(temp);
                        if (out1 != 0) {
                            String et = timeCalc(edtTxtOutTime1.getText().toString(), edtTxtInTime1.getText().toString());
                            txtViewBlockTime1.setText(et);
                        }
                        in1 = 1;
                        edtTxtOutTime2.requestFocus();
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        // LEG 2 Listeners   22222222222222222222222222222222222222222222222222222222222222222222222

        edtTxtOutTime2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (s.length() == 4){
                    int n = Integer.parseInt(edtTxtOutTime2.getText().toString());
                    int m = Integer.parseInt(edtTxtOutTime2.getText().toString().substring(2, 3));
                    if (n > 2359 || m > 5){
                        Toast.makeText(getApplicationContext(),getString(R.string.toast_enter_valid_time),Toast.LENGTH_SHORT).show();
                        edtTxtOutTime2.setText("");
                        edtTxtOutTime2.requestFocus();
                    }
                    else {
                        String temp = timeAddColon(edtTxtOutTime2.getText().toString());
                        edtTxtOutTime2.setText(temp);
                        if (in2 != 0){
                            String et = timeCalc(edtTxtOutTime2.getText().toString(), edtTxtInTime2.getText().toString());
                            txtViewBlockTime2.setText(et);
                        }
                        out2 = 1;
                        edtTxtOffTime2.requestFocus();
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
        edtTxtOffTime2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() ==4){
                    int n = Integer.parseInt(edtTxtOffTime2.getText().toString());
                    int m = Integer.parseInt(edtTxtOffTime2.getText().toString().substring(2, 3));
                    if (n > 2359 || m > 5){
                        Toast.makeText(getApplicationContext(),getString(R.string.toast_enter_valid_time),Toast.LENGTH_SHORT).show();
                        edtTxtOffTime2.setText("");
                        edtTxtOffTime2.requestFocus();
                    }
                    else {
                        String temp = timeAddColon(edtTxtOffTime2.getText().toString());
                        edtTxtOffTime2.setText(temp);
                        if (on2 != 0){
                            String et = timeCalc(edtTxtOffTime2.getText().toString(),edtTxtOnTime2.getText().toString());
                            txtViewFlightTime2.setText(et);
                        }
                        off2 = 1;
                        edtTxtOnTime2.requestFocus();
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
        edtTxtOnTime2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() ==4){
                    int n = Integer.parseInt(edtTxtOnTime2.getText().toString());
                    int m = Integer.parseInt(edtTxtOnTime2.getText().toString().substring(2, 3));
                    if (n > 2359 || m > 5){
                        Toast.makeText(getApplicationContext(),getString(R.string.toast_enter_valid_time),Toast.LENGTH_SHORT).show();
                        edtTxtOnTime2.setText("");
                        edtTxtOnTime2.requestFocus();
                    }
                    else {
                        String temp = timeAddColon(edtTxtOnTime2.getText().toString());
                        edtTxtOnTime2.setText(temp);
                        if (off2 != 0) {
                            String et = timeCalc(edtTxtOffTime2.getText().toString(), edtTxtOnTime2.getText().toString());
                            txtViewFlightTime2.setText(et);
                        }
                        on2 = 1;
                        edtTxtInTime2.requestFocus();
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
        edtTxtInTime2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() ==4){
                    int n = Integer.parseInt(edtTxtInTime2.getText().toString());
                    int m = Integer.parseInt(edtTxtInTime2.getText().toString().substring(2, 3));
                    if (n > 2359 || m > 5){
                        Toast.makeText(getApplicationContext(),getString(R.string.toast_enter_valid_time),Toast.LENGTH_SHORT).show();
                        edtTxtInTime2.setText("");
                        edtTxtInTime2.requestFocus();
                    }
                    else {
                        String temp = timeAddColon(edtTxtInTime2.getText().toString());
                        edtTxtInTime2.setText(temp);
                        if (out2 != 0) {
                            String et = timeCalc(edtTxtOutTime2.getText().toString(), edtTxtInTime2.getText().toString());
                            txtViewBlockTime2.setText(et);
                        }
                        in2 = 1;
                        edtTxtOutTime3.requestFocus();
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        // LEG 3 Listeners   33333333333333333333333333333333333333333333333333333333333333333333333

        edtTxtOutTime3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (s.length() == 4){
                    int n = Integer.parseInt(edtTxtOutTime3.getText().toString());
                    int m = Integer.parseInt(edtTxtOutTime3.getText().toString().substring(2, 3));
                    if (n > 2359 || m > 5){
                        Toast.makeText(getApplicationContext(),getString(R.string.toast_enter_valid_time),Toast.LENGTH_SHORT).show();
                        edtTxtOutTime3.setText("");
                        edtTxtOutTime3.requestFocus();
                    }
                    else {
                        String temp = timeAddColon(edtTxtOutTime3.getText().toString());
                        edtTxtOutTime3.setText(temp);
                        if (in3 != 0){
                            String et = timeCalc(edtTxtOutTime3.getText().toString(), edtTxtInTime3.getText().toString());
                            txtViewBlockTime3.setText(et);
                        }
                        out3 = 1;
                        edtTxtOffTime3.requestFocus();
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
        edtTxtOffTime3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() ==4){
                    int n = Integer.parseInt(edtTxtOffTime3.getText().toString());
                    int m = Integer.parseInt(edtTxtOffTime3.getText().toString().substring(2, 3));
                    if (n > 2359 || m > 5){
                        Toast.makeText(getApplicationContext(),getString(R.string.toast_enter_valid_time),Toast.LENGTH_SHORT).show();
                        edtTxtOffTime3.setText("");
                        edtTxtOffTime3.requestFocus();
                    }
                    else {
                        String temp = timeAddColon(edtTxtOffTime3.getText().toString());
                        edtTxtOffTime3.setText(temp);
                        if (on3 != 0){
                            String et = timeCalc(edtTxtOffTime3.getText().toString(),edtTxtOnTime3.getText().toString());
                            txtViewFlightTime3.setText(et);
                        }
                        off3 = 1;
                        edtTxtOnTime3.requestFocus();
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
        edtTxtOnTime3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() ==4){
                    int n = Integer.parseInt(edtTxtOnTime3.getText().toString());
                    int m = Integer.parseInt(edtTxtOnTime3.getText().toString().substring(2, 3));
                    if (n > 2359 || m > 5){
                        Toast.makeText(getApplicationContext(),getString(R.string.toast_enter_valid_time),Toast.LENGTH_SHORT).show();
                        edtTxtOnTime3.setText("");
                        edtTxtOnTime3.requestFocus();
                    }
                    else {
                        String temp = timeAddColon(edtTxtOnTime3.getText().toString());
                        edtTxtOnTime3.setText(temp);
                        if (off3 != 0) {
                            String et = timeCalc(edtTxtOffTime3.getText().toString(), edtTxtOnTime3.getText().toString());
                            txtViewFlightTime3.setText(et);
                        }
                        on3 = 1;
                        edtTxtInTime3.requestFocus();
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
        edtTxtInTime3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() ==4){
                    int n = Integer.parseInt(edtTxtInTime3.getText().toString());
                    int m = Integer.parseInt(edtTxtInTime3.getText().toString().substring(2, 3));
                    if (n > 2359 || m > 5){
                        Toast.makeText(getApplicationContext(),getString(R.string.toast_enter_valid_time),Toast.LENGTH_SHORT).show();
                        edtTxtInTime3.setText("");
                        edtTxtInTime3.requestFocus();
                    }
                    else {
                        String temp = timeAddColon(edtTxtInTime3.getText().toString());
                        edtTxtInTime3.setText(temp);
                        if (out3 != 0) {
                            String et = timeCalc(edtTxtOutTime3.getText().toString(), edtTxtInTime3.getText().toString());
                            txtViewBlockTime3.setText(et);
                        }
                        in3 = 1;
                        edtTxtOutTime4.requestFocus();
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        // LEG 4 Listeners   44444444444444444444444444444444444444444444444444444444444444444444444

        edtTxtOutTime4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (s.length() == 4){
                    int n = Integer.parseInt(edtTxtOutTime4.getText().toString());
                    int m = Integer.parseInt(edtTxtOutTime4.getText().toString().substring(2, 3));
                    if (n > 2359 || m > 5){
                        Toast.makeText(getApplicationContext(),getString(R.string.toast_enter_valid_time),Toast.LENGTH_SHORT).show();
                        edtTxtOutTime4.setText("");
                        edtTxtOutTime4.requestFocus();
                    }
                    else {
                        String temp = timeAddColon(edtTxtOutTime4.getText().toString());
                        edtTxtOutTime4.setText(temp);
                        if (in4 != 0){
                            String et = timeCalc(edtTxtOutTime4.getText().toString(), edtTxtInTime4.getText().toString());
                            txtViewBlockTime4.setText(et);
                        }
                        out4 = 1;
                        edtTxtOffTime4.requestFocus();
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
        edtTxtOffTime4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() ==4){
                    int n = Integer.parseInt(edtTxtOffTime4.getText().toString());
                    int m = Integer.parseInt(edtTxtOffTime4.getText().toString().substring(2, 3));
                    if (n > 2359 || m > 5){
                        Toast.makeText(getApplicationContext(),getString(R.string.toast_enter_valid_time),Toast.LENGTH_SHORT).show();
                        edtTxtOffTime4.setText("");
                        edtTxtOffTime4.requestFocus();
                    }
                    else {
                        String temp = timeAddColon(edtTxtOffTime4.getText().toString());
                        edtTxtOffTime4.setText(temp);
                        if (on4 != 0){
                            String et = timeCalc(edtTxtOffTime4.getText().toString(),edtTxtOnTime4.getText().toString());
                            txtViewFlightTime4.setText(et);
                        }
                        off4 = 1;
                        edtTxtOnTime4.requestFocus();
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
        edtTxtOnTime4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() ==4){
                    int n = Integer.parseInt(edtTxtOnTime4.getText().toString());
                    int m = Integer.parseInt(edtTxtOnTime4.getText().toString().substring(2, 3));
                    if (n > 2359 || m > 5){
                        Toast.makeText(getApplicationContext(),getString(R.string.toast_enter_valid_time),Toast.LENGTH_SHORT).show();
                        edtTxtOnTime4.setText("");
                        edtTxtOnTime4.requestFocus();
                    }
                    else {
                        String temp = timeAddColon(edtTxtOnTime4.getText().toString());
                        edtTxtOnTime4.setText(temp);
                        if (off4 != 0) {
                            String et = timeCalc(edtTxtOffTime4.getText().toString(), edtTxtOnTime4.getText().toString());
                            txtViewFlightTime4.setText(et);
                        }
                        on4 = 1;
                        edtTxtInTime4.requestFocus();
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
        edtTxtInTime4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() ==4){
                    int n = Integer.parseInt(edtTxtInTime4.getText().toString());
                    int m = Integer.parseInt(edtTxtInTime4.getText().toString().substring(2, 3));
                    if (n > 2359 || m > 5){
                        Toast.makeText(getApplicationContext(),getString(R.string.toast_enter_valid_time),Toast.LENGTH_SHORT).show();
                        edtTxtInTime4.setText("");
                        edtTxtInTime4.requestFocus();
                    }
                    else {
                        String temp = timeAddColon(edtTxtInTime4.getText().toString());
                        edtTxtInTime4.setText(temp);
                        if (out4 != 0) {
                            String et = timeCalc(edtTxtOutTime4.getText().toString(), edtTxtInTime4.getText().toString());
                            txtViewBlockTime4.setText(et);
                        }
                        in4 = 1;
                        edtTxtOutTime5.requestFocus();
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        // LEG 5 Listeners   55555555555555555555555555555555555555555555555555555555555555555555555

        edtTxtOutTime5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (s.length() == 4){
                    int n = Integer.parseInt(edtTxtOutTime5.getText().toString());
                    int m = Integer.parseInt(edtTxtOutTime5.getText().toString().substring(2, 3));
                    if (n > 2359 || m > 5){
                        Toast.makeText(getApplicationContext(),getString(R.string.toast_enter_valid_time),Toast.LENGTH_SHORT).show();
                        edtTxtOutTime5.setText("");
                        edtTxtOutTime5.requestFocus();
                    }
                    else {
                        String temp = timeAddColon(edtTxtOutTime5.getText().toString());
                        edtTxtOutTime5.setText(temp);
                        if (in5 != 0){
                            String et = timeCalc(edtTxtOutTime5.getText().toString(), edtTxtInTime5.getText().toString());
                            txtViewBlockTime5.setText(et);
                        }
                        out5 = 1;
                        edtTxtOffTime5.requestFocus();
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
        edtTxtOffTime5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() ==4){
                    int n = Integer.parseInt(edtTxtOffTime5.getText().toString());
                    int m = Integer.parseInt(edtTxtOffTime5.getText().toString().substring(2, 3));
                    if (n > 2359 || m > 5){
                        Toast.makeText(getApplicationContext(),getString(R.string.toast_enter_valid_time),Toast.LENGTH_SHORT).show();
                        edtTxtOffTime5.setText("");
                        edtTxtOffTime5.requestFocus();
                    }
                    else {
                        String temp = timeAddColon(edtTxtOffTime5.getText().toString());
                        edtTxtOffTime5.setText(temp);
                        if (on5 != 0){
                            String et = timeCalc(edtTxtOffTime5.getText().toString(),edtTxtOnTime5.getText().toString());
                            txtViewFlightTime5.setText(et);
                        }
                        off5 = 1;
                        edtTxtOnTime5.requestFocus();
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
        edtTxtOnTime5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() ==4){
                    int n = Integer.parseInt(edtTxtOnTime5.getText().toString());
                    int m = Integer.parseInt(edtTxtOnTime5.getText().toString().substring(2, 3));
                    if (n > 2359 || m > 5){
                        Toast.makeText(getApplicationContext(),getString(R.string.toast_enter_valid_time),Toast.LENGTH_SHORT).show();
                        edtTxtOnTime5.setText("");
                        edtTxtOnTime5.requestFocus();
                    }
                    else {
                        String temp = timeAddColon(edtTxtOnTime5.getText().toString());
                        edtTxtOnTime5.setText(temp);
                        if (off5 != 0) {
                            String et = timeCalc(edtTxtOffTime5.getText().toString(), edtTxtOnTime5.getText().toString());
                            txtViewFlightTime5.setText(et);
                        }
                        on5 = 1;
                        edtTxtInTime5.requestFocus();
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
        edtTxtInTime5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() ==4){
                    int n = Integer.parseInt(edtTxtInTime5.getText().toString());
                    int m = Integer.parseInt(edtTxtInTime5.getText().toString().substring(2, 3));
                    if (n > 2359 || m > 5){
                        Toast.makeText(getApplicationContext(),getString(R.string.toast_enter_valid_time),Toast.LENGTH_SHORT).show();
                        edtTxtInTime5.setText("");
                        edtTxtInTime5.requestFocus();
                    }
                    else {
                        String temp = timeAddColon(edtTxtInTime5.getText().toString());
                        edtTxtInTime5.setText(temp);
                        if (out5 != 0) {
                            String et = timeCalc(edtTxtOutTime5.getText().toString(), edtTxtInTime5.getText().toString());
                            txtViewBlockTime5.setText(et);
                        }
                        in5 = 1;
                        edtTxtOutTime1.requestFocus();
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        // Flight Time Total Listeners * Flight ** Flight ** Flight ** Flight ** Flight ** Flight *

        txtViewFlightTime1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String total = addTotals(
                        txtViewFlightTime1.getText().toString(),
                        txtViewFlightTime2.getText().toString(),
                        txtViewFlightTime3.getText().toString(),
                        txtViewFlightTime4.getText().toString(),
                        txtViewFlightTime5.getText().toString()
                );
                txtViewTotalFlightTime.setText(total);
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        txtViewFlightTime2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String total = addTotals(
                        txtViewFlightTime1.getText().toString(),
                        txtViewFlightTime2.getText().toString(),
                        txtViewFlightTime3.getText().toString(),
                        txtViewFlightTime4.getText().toString(),
                        txtViewFlightTime5.getText().toString()
                );
                txtViewTotalFlightTime.setText(total);
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        txtViewFlightTime3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String total = addTotals(
                        txtViewFlightTime1.getText().toString(),
                        txtViewFlightTime2.getText().toString(),
                        txtViewFlightTime3.getText().toString(),
                        txtViewFlightTime4.getText().toString(),
                        txtViewFlightTime5.getText().toString()
                );
                txtViewTotalFlightTime.setText(total);
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        txtViewFlightTime4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String total = addTotals(
                        txtViewFlightTime1.getText().toString(),
                        txtViewFlightTime2.getText().toString(),
                        txtViewFlightTime3.getText().toString(),
                        txtViewFlightTime4.getText().toString(),
                        txtViewFlightTime5.getText().toString()
                );
                txtViewTotalFlightTime.setText(total);
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        txtViewFlightTime5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String total = addTotals(
                        txtViewFlightTime1.getText().toString(),
                        txtViewFlightTime2.getText().toString(),
                        txtViewFlightTime3.getText().toString(),
                        txtViewFlightTime4.getText().toString(),
                        txtViewFlightTime5.getText().toString()
                );
                txtViewTotalFlightTime.setText(total);
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        // Block Time Total Listeners Block ** Block ** Block ** Block ** Block ** Block ** Block **

        txtViewBlockTime1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String total = addTotals(
                        txtViewBlockTime1.getText().toString(),
                        txtViewBlockTime2.getText().toString(),
                        txtViewBlockTime3.getText().toString(),
                        txtViewBlockTime4.getText().toString(),
                        txtViewBlockTime5.getText().toString()
                );
                txtViewTotalBlockTime.setText(total);
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        txtViewBlockTime2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String total = addTotals(
                        txtViewBlockTime1.getText().toString(),
                        txtViewBlockTime2.getText().toString(),
                        txtViewBlockTime3.getText().toString(),
                        txtViewBlockTime4.getText().toString(),
                        txtViewBlockTime5.getText().toString()
                );
                txtViewTotalBlockTime.setText(total);
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        txtViewBlockTime3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String total = addTotals(
                        txtViewBlockTime1.getText().toString(),
                        txtViewBlockTime2.getText().toString(),
                        txtViewBlockTime3.getText().toString(),
                        txtViewBlockTime4.getText().toString(),
                        txtViewBlockTime5.getText().toString()
                );
                txtViewTotalBlockTime.setText(total);
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        txtViewBlockTime4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String total = addTotals(
                        txtViewBlockTime1.getText().toString(),
                        txtViewBlockTime2.getText().toString(),
                        txtViewBlockTime3.getText().toString(),
                        txtViewBlockTime4.getText().toString(),
                        txtViewBlockTime5.getText().toString()
                );
                txtViewTotalBlockTime.setText(total);
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        txtViewBlockTime5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String total = addTotals(
                        txtViewBlockTime1.getText().toString(),
                        txtViewBlockTime2.getText().toString(),
                        txtViewBlockTime3.getText().toString(),
                        txtViewBlockTime4.getText().toString(),
                        txtViewBlockTime5.getText().toString()
                );
                txtViewTotalBlockTime.setText(total);
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.exit_the_app){
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
