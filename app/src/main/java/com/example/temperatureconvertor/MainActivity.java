package com.example.temperatureconvertor;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.DecimalFormat;

import static android.view.Gravity.*;

public class MainActivity extends AppCompatActivity {

    Spinner spin1, spin2;
    ArrayAdapter<CharSequence> adpt1, adpt2;
    Button button1;
    EditText value, result;
    double tempValue, tempResult;
    String select1, select2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Creating Spinner1
        spin1 = (Spinner) findViewById(R.id.spinner1);
        adpt1= ArrayAdapter.createFromResource(this,R.array.Scales,android.R.layout.simple_list_item_1);
        adpt1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin1.setAdapter(adpt1);
        spin1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                select1 = parent.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

        //Creating Spinner2
        spin2 = (Spinner) findViewById(R.id.spinner2);
        adpt2 = ArrayAdapter.createFromResource(this,R.array.Scales,android.R.layout.simple_list_item_1);
        adpt2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin2.setAdapter(adpt2);
        spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long l) {
                select2 = parent.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

        //Creating Button
        button1 = (Button) findViewById(R.id.button);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value = (EditText) findViewById(R.id.editText4);
                result = (EditText) findViewById(R.id.editText3);
                String temp = value.getText().toString();

                if(temp.isEmpty() == false)
                    tempValue = Double.valueOf(temp);

                if(temp.isEmpty()){
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Enter any Temperature value",Toast.LENGTH_SHORT);
                    toast.setGravity(TOP, 0, 180);
                    toast.show();
                }
                else if(select1.equals("Celcius °C")){
                    if(select2.equals("Celcius °C"))
                        tempResult = tempValue;
                    else if(select2.equals("Fahrenheit °F"))
                        tempResult = tempValue*9/5 + 32;
                    else if(select2.equals("Kelvin K"))
                        tempResult = tempValue + 273.15;
                    else if(select2.equals("Rankine °R"))
                        tempResult = tempValue*9/5 + 491.67;
                }
                else if(select1.equals("Fahrenheit °F")){
                    if(select2.equals("Fahrenheit °F"))
                        tempResult = tempValue;
                    else if(select2.equals("Celcius °C"))
                        tempResult = (tempValue - 32)*5/9;
                    else if(select2.equals("Kelvin K"))
                        tempResult = (tempValue - 32)*5/9 + 273.15;
                    else if(select2.equals("Rankine °R"))
                        tempResult = tempValue + 459.67;
                }
                else if(select1.equals("Kelvin K")){
                    if(select2.equals("Kelvin K"))
                        tempResult = tempValue;
                    else if(select2.equals("Celcius °C"))
                        tempResult = tempValue - 273.15;
                    else if(select2.equals("Fahrenheit °F"))
                        tempResult = (tempValue - 273.15)*9/5 + 32;
                    else if(select2.equals("Rankine °R"))
                        tempResult = (tempValue - 273.15)*9/5 + 491.67;
                }
                else if(select1.equals("Rankine °R")){
                    if(select2.equals("Rankine °R"))
                        tempResult = tempValue;
                    else if(select2.equals("Celcius °C"))
                        tempResult = (tempValue - 491.67)*5/9;
                    else if(select2.equals("Fahrenheit °F"))
                        tempResult = tempValue - 459.67;
                    else if(select2.equals("Kelvin K"))
                        tempResult = (tempValue - 491.67)*5/9 + 273.15;
                }

                if(temp.isEmpty() == false){
                    DecimalFormat formatVal = new DecimalFormat("##########.######");
                    result.setText(formatVal.format(tempResult));
                }

            }
        });

    }

    public void OpenInfo(View view){
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }

    public void Suggesations(View view){
        Toast.makeText(getApplicationContext(),
                "Email Suggesations at rk123@outlook.com",Toast.LENGTH_SHORT).show();
    }

}

