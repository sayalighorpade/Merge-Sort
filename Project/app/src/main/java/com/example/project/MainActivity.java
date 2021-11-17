package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    Button button;
    EditText editText;
    Button clear;
    RadioButton ascendingbutton, descendingbutton;
    RadioGroup group;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textview);//assigning a variable for textview
        editText = findViewById(R.id.editTextTextPersonName);//assigning a variable for edittext
        clear = findViewById(R.id.button2);
        descendingbutton = findViewById(R.id.radioButton2);
        ascendingbutton = findViewById(R.id.radioButton);

        group=findViewById(R.id.radioButton3);
    }

    public void SortButton(View view) {

        if(TextUtils.isEmpty(editText.getText().toString()))
        {
            Toast.makeText(this, "plz enter the number" +
                    "" +
                    "", Toast.LENGTH_SHORT).show();
            return;
        }


//        if(editText.toString().matches("[a-zA-Z]+"))
//        {
//           // String a;
//            editText.setText("");
//
//        }
        //else
        //{
            String[] stringsNumber = editText.getText().toString().split(","); //separating the comma from the numbers
            Integer[] integersNumber = new Integer[stringsNumber.length];// creating an integer array


//
            for (int i = 0; i < stringsNumber.length; i++) {
                integersNumber[i] = Integer.parseInt(stringsNumber[i]); //Converting from String Array to Integer Array
            }
//        textView.setText(Arrays.toString(integersNumber));
//        //ascendingbutton.setChecked(true);
//        if (ascendingbutton.isChecked()) {
//
//        } else {
//            descendingbutton.setOnClickListener(new View.OnClickListener() {
//                public void onClick(View v) {
            Integer[] sortedNumbers = mergesort(integersNumber);
            textView.setText(Arrays.toString(sortedNumbers));
//
        //}
        //          }
//            });
        }

        // Contains the sorted array

        //displaying the sortted numbers



    public void ClearButton(View view) {
        clear.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                textView.setText("");
                editText.setText("");
                ascendingbutton.setChecked(false);
                descendingbutton.setChecked(false);
                // Code here executes on main thread after user presses button

            }
        });
    }

    private Integer[] mergesort(Integer[] numbers) {
        int n = numbers.length;
        if (n <= 1) {
            return numbers;       //returning an array
        }
        int midpoint = numbers.length / 2;   //Taking a mid-point of initial array

        Integer[] leftnosorted = new Integer[midpoint];//defining the leftside and rightside of the array

        Integer[] rightarray;
        /* if we take array of {1,2,3,4,5}
        so 5/2=2;
        a[0]=1
        a[1]=2
        will be in leftnosorted
        rightnumbersorted will contain the rest array integer

         */


        if (numbers.length % 2 == 0) {
            rightarray = new Integer[midpoint];   //length of the right array


        } else {
            rightarray = new Integer[midpoint + 1];
        }


        for (int i = 0; i < midpoint; i++) {
            leftnosorted[i] = numbers[i];  //passing the number to left and right array
        }
        for (int j = 0; j < rightarray.length; j++) {
            rightarray[j] = numbers[midpoint + j];

        }
        Integer[] result = new Integer[numbers.length];
        leftnosorted = mergesort(leftnosorted);
        rightarray = mergesort(rightarray);
        if (ascendingbutton.isChecked()) {
            result = merge(leftnosorted, rightarray);
        } else {
            result = ascendingorder(leftnosorted, rightarray);
        }

        return result;
    }

    private Integer[] merge(Integer[] leftnosorted, Integer[] rightarray) {
        Integer[] result = new Integer[leftnosorted.length + rightarray.length];// Merging the arrays together
        int leftside = 0, rightside = 0, Descendingorder = 0;

        while (leftside < leftnosorted.length || rightside < rightarray.length) // check if there is value in the array
        {

            if (leftside < leftnosorted.length && rightside < rightarray.length) {

                if (leftnosorted[leftside] > rightarray[rightside])//descending order
                {
                    result[Descendingorder++] = leftnosorted[leftside++];
                } else {
                    result[Descendingorder++] = rightarray[rightside++];
                }

            } else if (leftside < leftnosorted.length) {
                result[Descendingorder++] = leftnosorted[leftside++];
            } else if (rightside < rightarray.length) {
                result[Descendingorder++] = rightarray[rightside++];
            }
        }
        return result;
    }

    private Integer[] ascendingorder(Integer[] leftnosorted, Integer[] rightarray) {
        Integer[] result = new Integer[leftnosorted.length + rightarray.length];// Merging the arrays together
        int leftside = 0, rightside = 0, ascendingorder = 0;

        while (leftside < leftnosorted.length || rightside < rightarray.length) // check if there is value in the array
        {

            if (leftside < leftnosorted.length && rightside < rightarray.length) {

                if (leftnosorted[leftside] < rightarray[rightside])//ascending order
                {
                    result[ascendingorder++] = leftnosorted[leftside++];
                } else {
                    result[ascendingorder++] = rightarray[rightside++];
                }

            } else if (leftside < leftnosorted.length) {
                result[ascendingorder++] = leftnosorted[leftside++];
            } else if (rightside < rightarray.length) {
                result[ascendingorder++] = rightarray[rightside++];
            }
        }
        return result;
    }
}

