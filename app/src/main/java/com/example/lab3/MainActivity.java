package com.example.lab3;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import java.math.BigDecimal;

public class MainActivity extends AppCompatActivity {
    Boolean rightFlag = false;
    String operator = "";
    String operatorStorage= "";
    Boolean positive = true;
    String leftSide;
    String rightSide = "";
    Boolean textDelete = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);





    }
    public void plusMinusPressed(View view) {
        TextView numBox = (TextView) findViewById(R.id.NumBox);
        BigDecimal check = new BigDecimal(numBox.getText().toString());
        numBox.setText(check.negate().toString());
    }
    public void numPressed(View view){
        int id = view.getId();
        TextView numBox = (TextView) findViewById(R.id.NumBox);
        Button buttonPressed = (Button) findViewById(id);
        if(textDelete){
            leftSide = numBox.getText().toString();
            numBox.setText(buttonPressed.getText().toString());
            rightFlag = true;
        }
        else{
            numBox.setText(numBox.getText() + buttonPressed.getText().toString());
        }
        textDelete = false;
    }
    public void calculationPressed(View view) {
        TextView numBox = (TextView) findViewById(R.id.NumBox);
        if (!numBox.getText().toString().equals("")){
            System.out.println("Made it in here!");
            int id = view.getId();
            Button buttonPressed = (Button) findViewById(id);
            operator = buttonPressed.getText().toString();

            if (rightFlag == false){
                operatorStorage = operator;
                leftSide = numBox.getText().toString();
                numBox.setText("");
                rightFlag = true;

            }
            else{

                    rightSide = numBox.getText().toString();
                    calculate();

            }
        }



    }
    public void clear(View view){
        TextView numBox = (TextView) findViewById(R.id.NumBox);
        numBox.setText("");
        leftSide = "";
        rightSide= "";
        rightFlag = false;

    }
    public void squareRoot(View view){
        TextView numBox = (TextView) findViewById(R.id.NumBox);
        double sqrdNumber = Double.valueOf(numBox.getText().toString());
        sqrdNumber = Math.sqrt(sqrdNumber);
        numBox.setText(String.valueOf(sqrdNumber));
    }
    public void calculate(){
        if(leftSide ==""){
            return;
        }
        BigDecimal leftNumber = new BigDecimal(leftSide);
        BigDecimal rightNumber = new BigDecimal(rightSide);
        BigDecimal answer = new BigDecimal(0);
        TextView numBox = (TextView) findViewById(R.id.NumBox);
        if (operator.equals("=")){
            switch(operatorStorage) {
                case "\u00D7":  //Multiplication
                    answer = leftNumber.multiply(rightNumber);
                    break;
                case "\u00F7":  //Division
                    answer = leftNumber.divide(rightNumber);
                    break;
                case "+":
                    answer = leftNumber.add(rightNumber);
                    break;
                case "-":
                    answer = leftNumber.subtract(rightNumber);
                    break;
                case "%":
                    answer = leftNumber.remainder(rightNumber);
                    break;
                default:

            }
        }
        else{
            switch(operator) {
                case "\u00D7":  //Multiplication
                    answer = leftNumber.multiply(rightNumber);
                    break;
                case "\u00F7":  //Division
                    answer = leftNumber.divide(rightNumber);
                    break;
                case "+":
                    answer = leftNumber.add(rightNumber);
                    break;
                case "-":
                    answer = leftNumber.subtract(rightNumber);
                    break;
                case "%":
                    answer = leftNumber.remainder(rightNumber);
                    break;
                default:

        }
            textDelete = true;

        }
        numBox.setText(answer.toString());
        leftSide = answer.toString();
        rightSide = "";
        rightFlag = false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
