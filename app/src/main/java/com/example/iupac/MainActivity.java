package com.example.iupac;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.GridLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[][] grid = new String[3][6];
        GridLayout Maingrid = findViewById(R.id.maingrid);

        int i = 0;
        int j = 0;
        int var = 2;
        String p = "editText";
        while(i<3){
            j = 0;
            while(j<6){
                String o =  Integer.toString(var);
                p = p+o;
                int resid = getResources().getIdentifier(p,"id",getPackageName());
                String temp = getString(resid);                
                grid[i][j] = temp;
                j++;
                var++;
            }
            i++;
        }




    }

}
