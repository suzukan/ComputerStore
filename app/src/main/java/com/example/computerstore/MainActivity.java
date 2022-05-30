package com.example.computerstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    private static final double CPU = 350.00;
    private static final double RAM = 250.00;
    private static final double MOUSE = 15.50;
    private static final double USB = 8.50;
    private static final double TAX = 0.09;
    private EditText txtCpu, txtRam, txtMouse;
    private Spinner spinner;
    private Button btnOrder;
    private String cpuNumber, ramNumber, mouseNumber, spinnerStringNumber;
    private int itemsTotal;
    private double cpuAmount, ramAmount, mouseAmount, usbAmount;
    private double tax, subTotal, total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();

        btnOrder.setOnClickListener(v -> {
            calculate();
        });
    }

    public void initialize() {
        txtCpu = findViewById(R.id.txtCpu);
        txtRam = findViewById(R.id.txtRam);
        txtMouse = findViewById(R.id.txtMouse);
        btnOrder = findViewById(R.id.btnOrder);
        spinner = findViewById(R.id.spinner);
    }

    public void calculate() {
        cpuNumber = txtCpu.getText().toString();
        ramNumber = txtRam.getText().toString();
        mouseNumber = txtMouse.getText().toString();
        spinnerStringNumber = spinner.getSelectedItem().toString();

        try {
            if (cpuNumber.equals("")) {
                Toast.makeText(MainActivity.this, "Please enter a number", Toast.LENGTH_SHORT).show();
                cpuAmount = 0.0;
            } else {
                cpuAmount = Integer.parseInt(cpuNumber) * CPU;
            }

            if (ramNumber.equals("")) {
                Toast.makeText(MainActivity.this, "Please enter a number", Toast.LENGTH_SHORT).show();
                ramAmount = 0.0;
            } else {
                ramAmount = Integer.parseInt(ramNumber) * RAM;
            }

            if (mouseNumber.equals("")) {
                Toast.makeText(MainActivity.this, "Please enter a number", Toast.LENGTH_SHORT).show();
                mouseAmount = 0.0;
            } else {
                mouseAmount = Integer.parseInt(mouseNumber) * MOUSE;
            }

            usbAmount = Integer.parseInt(spinnerStringNumber) * USB;

            itemsTotal = Integer.parseInt(cpuNumber) + Integer.parseInt(ramNumber)
                    + Integer.parseInt(mouseNumber) + Integer.parseInt(spinnerStringNumber);

            subTotal = cpuAmount + ramAmount + mouseAmount + usbAmount;
            tax = subTotal * TAX;
            total = subTotal + tax;

            saveData();

            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            startActivity(intent);
        } catch (NumberFormatException ex) {
            Log.d("FORMAT", ex.getMessage());
        } catch (Exception ex) {
            throw new RuntimeException("Something went wrong...");
        }
    }

    public void saveData() {
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        String subtotalFormatted = currency.format(subTotal);
        String totalFormatted = currency.format(total);

        SharedPreferences sharedPreferences = getSharedPreferences("order_file", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        //save data as key value pairs
        editor.putString("itemsTotal", String.valueOf(itemsTotal));
        editor.putString("cpu", cpuNumber);
        editor.putString("ram", ramNumber);
        editor.putString("mouse", mouseNumber);
        editor.putString("usb", spinnerStringNumber);
        editor.putString("subTotal", subtotalFormatted);
        editor.putString("total", totalFormatted);
        editor.apply();
    }
}