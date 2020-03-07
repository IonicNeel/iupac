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
    int resid,l=0;
    String ans,carbon,hydrogen,temp,var;
    Button go;
    TextView answer;
    String[] wordroot = new String[12];
    int carbons = 0, hydrogens = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        go = findViewById(R.id.go);
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
                        System.out.println("Value of \""+temp+"\" is: \""+var+"\"");
                        if(var.equals("C"))
                            carbons++;
                        else if(var.equals("H"))
                            hydrogens++;
                    }
                }
                System.out.println("Carbons = "+carbons);
                ans = wordroot[carbons];
                if(hydrogens == (carbons*2)+2){
                    ans = ans + "ane";
                }
                else if(hydrogens == carbons*2){
                    ans = ans+"ene";
                }
                else if(hydrogens == (carbons*2)-2 && hydrogens!=0){
                    ans = ans+"yne";
                }
                answer.setVisibility(View.VISIBLE);
                answer.setText(ans);

            }
        });
    }
}
