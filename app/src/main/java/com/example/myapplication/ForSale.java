package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ForSale extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_for_sale);

        Intent i = getIntent();
        String list = i.getStringExtra("stockNames");
        Toast.makeText(
                this,
                list,
                Toast.LENGTH_LONG
        ).show();


        String[] arrayOfItems = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k"};

        if(list != "Hello People"){
            arrayOfItems= list.split(" ");
        }

        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                R.layout.element, R.id.itemName, arrayOfItems);

        ListView listView = (ListView) findViewById(R.id.forSaleList);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

                String name = ((TextView) view).getText().toString();
                Toast.makeText(getBaseContext(), name, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(view.getContext(), Stock_details.class);
                intent.putExtra("name", name);
                startActivity(intent);
            }
        });


    }
}
