package com.andyshon.diaryofgifts;

import android.app.DatePickerDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class AddActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private Date date;
    private DatePickerDialog datePickerDialog;
    private Calendar calendar;

    private EditText giftEditText;
    private EditText nameEditText;

    private AddGetGiftViewModel addGetGiftViewModel;

    private String giftId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.addActivity_name);
        setSupportActionBar(toolbar);

        giftEditText = findViewById(R.id.giftName);
        nameEditText = findViewById(R.id.personName);

        calendar = Calendar.getInstance();
        addGetGiftViewModel = ViewModelProviders.of(this).get(AddGetGiftViewModel.class);

        Intent intent = getIntent();
        giftId = intent.getStringExtra("giftId");
        System.out.println("giftId = " + giftId);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            datePickerDialog = new DatePickerDialog(AddActivity.this);
        }

        addGetGiftViewModel.getGiftAndPersonById(giftId).observe(this, giftModel -> {
            if (giftModel != null) {
                // update gift
                giftEditText.setText(giftModel.getGiftName());
                nameEditText.setText(giftModel.getPersonName());
                Date datepick = giftModel.getGiftDate();
                int curYear = 2018;
                int curMonth = datepick.getMonth();
                int curDay = Integer.parseInt(datepick.toLocaleString().substring(0,2));
                datePickerDialog = new DatePickerDialog(AddActivity.this, AddActivity.this, curYear, curMonth, curDay);

                calendar.set(Calendar.YEAR, curYear);
                calendar.set(Calendar.MONTH, curMonth);
                calendar.set(Calendar.DAY_OF_MONTH, curDay);
                date = calendar.getTime();
            }
            else {
                // add new gift
                datePickerDialog = new DatePickerDialog(AddActivity.this, AddActivity.this,
                        calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
            }
        });


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            if (giftEditText.getText() == null || nameEditText.getText() == null || date == null)
                Toast.makeText(AddActivity.this, "Missing fields", Toast.LENGTH_SHORT).show();
            else {
                GiftModel giftModel = new GiftModel(Integer.parseInt(giftId), giftEditText.getText().toString(), nameEditText.getText().toString(), date);
                addGetGiftViewModel.addGift(giftModel);
                finish();
            }
        });


        findViewById(R.id.dateButton).setOnClickListener(v -> datePickerDialog.show());
    }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        date = calendar.getTime();
    }

}
