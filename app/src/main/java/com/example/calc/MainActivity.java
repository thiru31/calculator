package com.example.calc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView result_tv,solution_tv;
    MaterialButton button_C,button_ac,button_open,button_close,button_dot;
    MaterialButton button_divide,button_multiply,button_plus,button_minus,button_equal;
    MaterialButton button_1,button_2,button_3,button_4,button_5,button_6,button_7,button_8,button_9,button_0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result_tv=findViewById(R.id.result_tv);
        solution_tv=findViewById(R.id.solution_tv);
        assignId(button_C,R.id.button_C);
        assignId(button_ac,R.id.button_ac);
        assignId(button_open,R.id.button_open);
        assignId(button_close,R.id.button_close);
        assignId(button_dot,R.id.button_dot);
        assignId(button_divide,R.id.button_divide);
        assignId(button_multiply,R.id.button_multiply);
        assignId(button_plus,R.id.button_plus);
        assignId(button_minus,R.id.button_minus);
        assignId(button_equal,R.id.button_equal);
        assignId(button_1,R.id.button_1);
        assignId(button_2,R.id.button_2);
        assignId(button_3,R.id.button_3);
        assignId(button_4,R.id.button_4);
        assignId(button_5,R.id.button_5);
        assignId(button_6,R.id.button_6);
        assignId(button_7,R.id.button_7);
        assignId(button_8,R.id.button_8);
        assignId(button_9,R.id.button_9);
        assignId(button_0,R.id.button_0);
    }

    void assignId(MaterialButton btn,int id){
        btn =findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        MaterialButton button=(MaterialButton) view;
        String buttonText = button.getText().toString();
        String datatocalculate= solution_tv.getText().toString();
        if(buttonText.equals("AC")){
            solution_tv.setText("");
            result_tv.setText("0");
            return;
        }
        if(buttonText.equals("=")){
            solution_tv.setText(result_tv.getText());
            return;
        }
        if(buttonText.equals("c")) {
            datatocalculate = datatocalculate.substring(0, datatocalculate.length() - 1);
        }else{
            datatocalculate = datatocalculate+buttonText;
        }

        solution_tv.setText(datatocalculate);

        String finalresult= getResult(datatocalculate);

        if (!finalresult.equals("err")){
            result_tv.setText(finalresult);
        }

    }
    String getResult(String data){
        try{
            Context context =Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalresult=context.evaluateString(scriptable,data,"javascript",1,null).toString();
            if(finalresult.endsWith(".0")){
                finalresult=finalresult.replace(".0","");
            }
            return finalresult;
        }catch (Exception e){
            return "err";
        }

    }
}