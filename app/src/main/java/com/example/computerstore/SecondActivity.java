package com.example.computerstore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.NumberFormat;

public class SecondActivity extends AppCompatActivity {
    private TextView txtItemsOrdered;
    private TextView txtCpuOrdered;
    private TextView txtRamOrdered;
    private TextView txtMouseOrdered;
    private TextView txtSubTotal;
    private TextView txtTotal;
    private TextView txtUsbOrdered;
    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        initialize();

        getData();

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void initialize() {
        txtItemsOrdered = findViewById(R.id.txtItemsOrdered);
        txtCpuOrdered = findViewById(R.id.txtCpuOrdered);
        txtRamOrdered = findViewById(R.id.txtRamOrdered);
        txtMouseOrdered = findViewById(R.id.txtMouseOrdered);
        txtUsbOrdered = findViewById(R.id.txtUsbOrdered);
        txtSubTotal = findViewById(R.id.txtSubTotalOrdered);
        txtTotal = findViewById(R.id.txtTotalOrdered);
        btnBack = findViewById(R.id.btnBack);
    }

    public void getData() {
        SharedPreferences sharedPreferences = getSharedPreferences("order_file", Context.MODE_PRIVATE);
        String itemsOrdered = sharedPreferences.getString("itemsTotal", "0");
        String cpuNumber = sharedPreferences.getString("cpu", "0");
        String ramNumber = sharedPreferences.getString("ram", "0");
        String mouseNumber = sharedPreferences.getString("mouse", "0");
        String usbNumber = sharedPreferences.getString("usb", "0");
        String subTotal = sharedPreferences.getString("subTotal", "0");
        String total = sharedPreferences.getString("total", "0");

        //use retrieved values to update the textViews on the screen
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        txtItemsOrdered.setText(itemsOrdered);
        txtCpuOrdered.setText(cpuNumber);
        txtRamOrdered.setText(ramNumber);
        txtMouseOrdered.setText(mouseNumber);
        txtUsbOrdered.setText(usbNumber);
        txtSubTotal.setText(subTotal);
        txtTotal.setText(total);
//        txtTotal.setText(currency.format(total));
    }

//    @Override
//    protected void onSaveInstanceState(@NonNull Bundle outState) {
//        super.onSaveInstanceState(outState);
//        outState.putString("txtItemsOrdered", txtItemsOrdered.toString());
//        outState.putString("txtCpuOrdered", txtCpuOrdered.toString());
//        outState.putString("txtRamOrdered", txtRamOrdered.toString());
//        outState.putString("txtMouseOrdered", txtMouseOrdered.toString());
//        outState.putString("txtSubTotal", txtSubTotal.toString());
////        outState.putString("txtUsbOrdered", txtUsbOrdered.toString());
//        outState.putString("txtTotal", txtTotal.toString());
//    }
//
//    @Override
//    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
//        super.onRestoreInstanceState(savedInstanceState);
//        if (savedInstanceState != null){
//            txtItemsOrdered = (TextView) savedInstanceState.get("txtItemsOrdered");
//            txtCpuOrdered = (TextView) savedInstanceState.get("txtCpuOrdered");
//            txtRamOrdered = (TextView) savedInstanceState.get("txtRamOrdered");
//            txtMouseOrdered = (TextView) savedInstanceState.get("txtMouseOrdered");
////            txtUsbOrdered = (TextView) savedInstanceState.get("txtUsbOrdered");
//            txtSubTotal = (TextView) savedInstanceState.get("txtSubTotal");
//            txtTotal = (TextView) savedInstanceState.get("txtTotal");
//            txtItemsOrdered.setText(txtItemsOrdered.toString());
//            txtCpuOrdered.setText(txtCpuOrdered.toString());
//            txtRamOrdered.setText(txtRamOrdered.toString());
//            txtMouseOrdered.setText(txtMouseOrdered.toString());
////            txtUsbOrdered.setText(txtUsbOrdered.toString());
//            txtSubTotal.setText(txtSubTotal.toString());
//            txtTotal.setText(txtTotal.toString());
//        }
//    }
}