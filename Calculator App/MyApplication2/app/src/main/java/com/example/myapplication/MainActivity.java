package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView resulttv,soltv;
    MaterialButton buttonC, buttonbracopen ,buttonbracclose;
    MaterialButton buttondiv, buttonmul, buttonadd, buttonsub, buttonequals;
    MaterialButton button0,button1, button2, button3, button4, button5, button6,button7, button8, button9;
    MaterialButton buttonAC, buttondot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resulttv=findViewById(R.id.result_tv);
        soltv=findViewById(R.id.solution_tv);

        assignId(buttonC,R.id.button_c);
        assignId(buttonbracopen,R.id.button_openbrac);
        assignId(buttonbracclose,R.id.button_closebrac);
        assignId(buttondiv,R.id.button_div);
        assignId(buttonmul,R.id.button_mul);
        assignId(buttonadd,R.id.button_plus);
        assignId(buttonsub,R.id.button_minus);
        assignId(button0,R.id.button_0);
        assignId(button1,R.id.button_1);
        assignId(button2,R.id.button_2);
        assignId(button3,R.id.button_3);
        assignId(button4,R.id.button_4);
        assignId(button5,R.id.button_5);
        assignId(button6,R.id.button_6);
        assignId(button7,R.id.button_seven);
        assignId(button8,R.id.button_eight);
        assignId(button9,R.id.button_nine);
        assignId(buttonAC,R.id.button_ac);
        assignId(buttondot,R.id.button_dot);



    }
    void assignId(MaterialButton btn, int id){
        btn=findViewById(id);
        btn.setOnClickListener(this);
    }
    @Override
    public void onClick(View view){
         MaterialButton button=(MaterialButton) view;
         String buttonText= button.getText().toString();
         String dataToCalculate = soltv.getText().toString();
         if (buttonText.equals("AC")){
             soltv.setText("");
             resulttv.setText("0");
             return;
         }
         if (button.equals("=")){
             soltv.setText(resulttv.getText());
             return;
         }
         if (buttonText.equals("C")){
             dataToCalculate=dataToCalculate.substring(0, dataToCalculate.length()-1);
         }else{
             dataToCalculate= dataToCalculate+buttonText;

         }
         soltv.setText(dataToCalculate);
         String finalResult = getResult(dataToCalculate);
         if (!finalResult.equals("Error")){
             resulttv.setText(finalResult);
         }

    }
    String getResult(String data){
        try{
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();

            String finalResult = context.evaluateString(scriptable,data, " Javascript",1, null).toString();
            if (finalResult.endsWith("0")){
                finalResult=finalResult.replace(".0", "");

            }
            return finalResult;

        }catch (Exception e){
            return "Error";
        }
    }

}