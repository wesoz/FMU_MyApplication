package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView txtResultado;
    TextView txtFirstNumber;
    TextView txtSecondNumber;

    private interface ICalc {
        float calculate(float param1, float param2);
    }

    private class sum implements ICalc {
        @Override
        public float calculate(float param1, float param2) {
            return param1 + param2;
        }
    }

    private class minus implements ICalc {

        @Override
        public float calculate(float param1, float param2) {
            return param1 - param2;
        }
    }

    private class multiply implements ICalc {

        @Override
        public float calculate(float param1, float param2) {
            return param1 * param2;
        }
    }

    private class divide implements ICalc {

        @Override
        public float calculate(float param1, float param2) {
            return param1 / param2;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnSomar = (Button)this.findViewById(R.id.btnSomar);
        Button btnSubtrair = (Button)this.findViewById(R.id.btnSubtrair);
        Button btnMultiplicar = (Button)this.findViewById(R.id.btnMultiplicar);
        Button btnDividir = (Button)this.findViewById(R.id.btnDividir);

        txtResultado = (TextView)this.findViewById(R.id.txtResultado);
        txtFirstNumber = (TextView)this.findViewById(R.id.txtFirstNumber);
        txtSecondNumber = (TextView)this.findViewById(R.id.txtSecondNumber);

        btnSomar.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                process(new sum());
            }
        });

        btnSubtrair.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                process(new minus());
            }
        });

        btnMultiplicar.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                process(new multiply());
            }
        });

        btnDividir.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                process(new divide());
            }
        });
    }

    void process(ICalc calc) {
        float firstNumber = validate(txtFirstNumber);
        float secondNumber = validate(txtSecondNumber);

        float result = calc.calculate(firstNumber, secondNumber);
        txtResultado.setText(String.valueOf(result));
        //new AlertDialog.Builder(MainActivity.this).setMessage(String.valueOf(result)).show();
    }

    float validate(TextView txt) {
        String stringValue = txt.getText().toString();
        if (!stringValue.matches("\\d+(?:\\.\\d+)?")) {
            stringValue = "0";
        }

        float floatValue = Float.valueOf(stringValue);

        txt.setText(String.valueOf(floatValue));
        return floatValue;
    }
}