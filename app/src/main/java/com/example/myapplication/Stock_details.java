package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Stock_details extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_details);

        Intent intent = getIntent();
        String title = intent.getStringExtra("name");
        boolean b = intent.getBooleanExtra("owned", false);
        if (b) {
            Button button = findViewById(R.id.buyButton);
            button.setVisibility(View.GONE);
        }

        TextView v = findViewById(R.id.detailTitle);
        v.setText(title);

        final Button button = (Button) findViewById(R.id.buyButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ForSale.class);
                Toast.makeText(getBaseContext(), "Patent has been added to your account", Toast.LENGTH_LONG).show();
                startActivity(intent);
            }
        });
    }
}
