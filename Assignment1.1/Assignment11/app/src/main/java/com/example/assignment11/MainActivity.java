package com.example.assignment11;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.MaterialToolbar;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    EditText price, down, years, rate;
    Button calc;
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        price = findViewById(R.id.price);
        down = findViewById(R.id.down);
        years = findViewById(R.id.years);
        rate = findViewById(R.id.rate);
        calc = findViewById(R.id.calc);
        result = findViewById(R.id.result);

        calc.setOnClickListener(v -> {

            if (price.getText().toString().isEmpty() ||
                    down.getText().toString().isEmpty() ||
                    years.getText().toString().isEmpty() ||
                    rate.getText().toString().isEmpty()) {

                result.setText("Please fill all fields");
                return;
            }

            try {
                double vehiclePrice = Double.parseDouble(price.getText().toString());
                double downPayment = Double.parseDouble(down.getText().toString());
                double loanYears = Double.parseDouble(years.getText().toString());
                double interestRate = Double.parseDouble(rate.getText().toString());

                double loanAmount = vehiclePrice - downPayment;
                double totalInterest = loanAmount * (interestRate / 100) * loanYears;
                double totalPayment = loanAmount + totalInterest;
                double monthlyPayment = totalPayment / (loanYears * 12);

                DecimalFormat df = new DecimalFormat("0.00");

                result.setText(
                        "Loan Amount: RM " + df.format(loanAmount) +
                                "\nTotal Interest: RM " + df.format(totalInterest) +
                                "\nTotal Payment: RM " + df.format(totalPayment) +
                                "\nMonthly Payment: RM " + df.format(monthlyPayment)
                );

            } catch (Exception e) {
                result.setText("Invalid input");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.about) {
            startActivity(new Intent(MainActivity.this, AboutActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
