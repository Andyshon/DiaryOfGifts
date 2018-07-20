package com.andyshon.diaryofgifts;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnLongClickListener, View.OnClickListener {

    private GiftListViewModel viewModel;
    private RecyclerViewAdapter recyclerViewAdapter;
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.mainActivity_name);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, AddActivity.class)));

        //deleteDatabase("gift_db");

        final TextView tvNoGifts = findViewById(R.id.tvNoGifts);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerViewAdapter = new RecyclerViewAdapter(new ArrayList<>(), this, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(recyclerViewAdapter);

        viewModel = ViewModelProviders.of(this).get(GiftListViewModel.class);

        viewModel.getGiftAndPersonList().observe(MainActivity.this, giftAndPeople -> {
            if (giftAndPeople.size() == 0)
                tvNoGifts.setVisibility(View.VISIBLE);
            else
                tvNoGifts.setVisibility(View.INVISIBLE);

            recyclerViewAdapter.addItems(giftAndPeople);
        });

    }


    @Override
    public boolean onLongClick(View v) {

        showConfirmDialog(v);

        return true;
    }


    private void showConfirmDialog(final View v) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Delete");
        alertDialogBuilder.setMessage("Are you sure, You wanted to delete " + ((GiftModel)v.getTag()).getGiftName() + "?");
        alertDialogBuilder.setPositiveButton("Yes",
                (arg0, arg1) -> {
                    GiftModel giftModel = (GiftModel) v.getTag();
                    viewModel.deleteItem(giftModel);
                    Toast.makeText(MainActivity.this,giftModel.getGiftName() + " was deleted!",Toast.LENGTH_LONG).show();
                });

        alertDialogBuilder.setNegativeButton("No", (dialog, which) -> Toast.makeText(MainActivity.this, "Cancel", Toast.LENGTH_SHORT).show());

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }


    @Override
    public void onClick(View view) {
        Intent intent = new Intent(MainActivity.this, AddActivity.class);
        intent.putExtra("giftId", String.valueOf(((GiftModel) view.getTag()).id));
        startActivity(intent);
    }
}
