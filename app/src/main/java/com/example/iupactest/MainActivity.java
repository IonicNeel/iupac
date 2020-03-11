package com.example.iupactest;

import androidx.appcompat.app.AppCompatActivity;

import android.inputmethodservice.Keyboard;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    String[][] grid = new String[3][10];
    int[][] bond = new int[3][10];
    char var2;
    int resid,l=0, fic,fjc,j;
    String ans,carbon,hydrogen,temp,var;
    Button go,clear;
    ArrayList<Integer> posdoublebond = new ArrayList<Integer>(), postriplebond = new ArrayList<Integer>();
    TextView answer;
    String[] wordroot = new String[12], count = new String[10];
    int carbons = 0, hydrogens = 0, oxygens =0,e, doublebonds, triplebonds, singlebonds;

    Boolean found,error = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        doublebonds = triplebonds = singlebonds = 0;
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
        count[2] = "di";
        count[3] = "tri";
        count[4] = "tetra";
        count[5] = "penta";

        go.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                error = false;
                doublebonds = triplebonds = singlebonds = 0;
                posdoublebond.clear();
                postriplebond.clear();
                found = false;
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
                        bond[i][j] = 0;
//                        System.out.println(temp+" - carbs- "+carbons);
                    }
                }
//                System.out.println("Carbons = "+carbons);
                carbons=0;hydrogens=0;
//                System.out.println(fic+" "+fjc);
                for(int j = fjc;j<9;j++){
                    e = 0;
                    if(grid[fic][j].equals("C"))
                        hydrogens+=0;
                    else
                        break;

                    carbons++;
                    if(grid[fic-1][j].equals("H")) {
                        hydrogens++;
                        e++;
                    }
                    if(grid[fic+1][j].equals("H")){
                        hydrogens++;
                        e++;
                    }

                    if(grid[fic][j-1].equals("H")){
                        e++;
                        hydrogens++;
                    }

                    if(grid[fic][j+1].equals("H")){
                        e++;
                        hydrogens++;
                    }


                    bond[fic][j] = 4 - e - bond[fic][j-1];

                    if(bond[fic][j]<0){
                        error = true;
                        break;
                    }

                    if(!grid[fic][j+1].equals("C")){
                        if(bond[fic][j]!=0){
                            System.out.println("j - "+j);
                            error = true;
                            break;
                        }
                    }

                    if(bond[fic][j] == 2){
                        doublebonds++;
                        posdoublebond.add(j - fjc + 1);
                    }
                    else if(bond[fic][j] == 3){
                        triplebonds++;
                        postriplebond.add(j - fjc + 1);
                    }
                    else
                        singlebonds++;
//                    System.out.println(j+" "+(bond[fic][j]!=0));

                }
                System.out.println("Hy - "+hydrogens);


                ans = wordroot[carbons];
                if(oxygens == 0) {
                    if (hydrogens == (carbons * 2) + 2 && doublebonds ==0 && triplebonds == 0) {
                        ans = ans + "ane";
                    }
                    else if (hydrogens == carbons * 2 && carbons > 1 && doublebonds == 1 && triplebonds == 0) {
                        ans = ans + "ene";
                    }
                    else if (hydrogens == (carbons * 2) - 2 && hydrogens != 0 && doublebonds==0 && triplebonds == 1) {
                        ans = ans + "yne";
                    }

                    else if(doublebonds > 1 && triplebonds == 0){
                        ans = ans+"-";
                        for(int i = 0; i<posdoublebond.size();i++){
                            ans = ans + Integer.toString(posdoublebond.get(i));
                            if(i != posdoublebond.size()-1){
                                ans = ans + ",";
                            }
                        }
                        ans = ans + "-";
                        ans = ans + count[doublebonds];
                        ans = ans + "ene";
                    }
                }
                answer.setVisibility(View.VISIBLE);
                if(error)
                    answer.setText("ERROR");
                else
                    answer.setText(ans);
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                error = false;
                doublebonds = triplebonds = singlebonds = 0;
                posdoublebond.clear();
                postriplebond.clear();
                found = false;
                carbons = 0;
                hydrogens = 0;
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
