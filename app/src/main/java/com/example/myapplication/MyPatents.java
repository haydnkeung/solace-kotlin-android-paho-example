package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MyPatents extends AppCompatActivity {


    public void toProfile(View v) {
        Intent intent = new Intent(v.getContext(), MyPatents.class);

        startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_patents);

        Intent i = getIntent();
        String list = "Quadramet Hydrochloride Quinidex Quinidine";


        String[] arrayOfItems = {""};

        arrayOfItems = list.split(" ");


        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                R.layout.element, R.id.itemName, arrayOfItems);

        ListView listView = (ListView) findViewById(R.id.forSaleList);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

                String name = ((TextView) view).getText().toString();
                //Toast.makeText(getBaseContext(), name, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(view.getContext(), Stock_details.class);
                intent.putExtra("name", name);
                startActivity(intent);
            }
        });


    }
}
