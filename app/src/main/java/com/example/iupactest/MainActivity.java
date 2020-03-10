package com.example.iupactest;

import androidx.appcompat.app.AppCompatActivity;

import android.inputmethodservice.Keyboard;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    String[][] grid = new String[3][10];
    char var2;
    int resid,l=0, fic,fjc,j;
    String ans,carbon,hydrogen,temp,var;
    Button go,clear;
    TextView answer;
    String[] wordroot = new String[12];
    int carbons = 0, hydrogens = 0, oxygens =0;
    Boolean found;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        found = false;
        setContentView(R.layout.activity_main);
        go = findViewById(R.id.go);
        clear = findViewById(R.id.clear);
        answer = findViewById(R.id.answer);
        carbon = "C";
        hydrogen = "H";
        wordroot[1] = "meth";
        wordroot[2] = "eth";
        wordroot[3] = "prop";
        wordroot[4] = "but";
        wordroot[5] = "pent";
        wordroot[6] = "hex";
        wordroot[7] = "hept";
        wordroot[8] = "oct";
        wordroot[9] = "non";
        wordroot[10] = "dec";
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                carbons = 0;
                hydrogens = 0;
                for(int i = 0;i<3;i++){
                    for(int j = 0;j<10;j++){
                        temp = "cell"+i+j;
                        resid = getResources().getIdentifier(temp,"id", getPackageName());
                        EditText t = findViewById(resid);
                        var = ((EditText) findViewById(resid)).getText().toString();
//                        System.out.println("Value of \""+temp+"\" is: \""+var+"\"");
                        if(var.equals("C"))
                            carbons=carbons+1;
                        else if(var.equals("H"))
                            hydrogens++;
                        else if(var.equals("O"))
                            oxygens++;

                        if(carbons == 1 && found.equals(false)){
                            fic = i;
                            fjc = j;
                            found = true;
                        }
                        grid[i][j] = var;
//                        System.out.println(temp+" - carbs- "+carbons);
                    }
                }
//                System.out.println("Carbons = "+carbons);
                carbons=0;hydrogens=0;
                System.out.println(fic+" "+fjc);
                for(int j = fjc;j<9;j++){
                    if(grid[fic][j].equals("C"))
                        hydrogens+=0;
                    else
                        break;

                    carbons++;
                    if(grid[fic-1][j].equals("H"))
                        hydrogens++;
                    if(grid[fic+1][j].equals("H"))
                        hydrogens++;
                    if(grid[fic][j-1].equals("H"))
                        hydrogens++;
                    if(grid[fic][j+1].equals("H")){
                        hydrogens++;
                    }
                }
                System.out.println("Hy - "+hydrogens);


                ans = wordroot[carbons];
                if(oxygens == 0) {
                    if (hydrogens == (carbons * 2) + 2) {
                        ans = ans + "ane";
                    }
                    else if (hydrogens == carbons * 2 && carbons > 1) {
                        ans = ans + "ene";
                    }
                    else if (hydrogens == (carbons * 2) - 2 && hydrogens != 0) {
                        ans = ans + "yne";
                    }
                }
                answer.setVisibility(View.VISIBLE);
                answer.setText(ans);
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i = 0;i<3;i++){
                    for(int j = 0;j<10;j++){
                        temp = "cell"+i+j;
                        resid = getResources().getIdentifier(temp,"id", getPackageName());
                        EditText t = findViewById(resid);
                        t.setText("");
                        answer.setVisibility(View.INVISIBLE);
                    }
                }
            }
        });
    }
}
