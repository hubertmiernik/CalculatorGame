package com.example.hubert.projekt1;

import android.provider.MediaStore;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import static android.R.attr.level;

public class SecondActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);


        final RadioButton radioButtonEasy = (RadioButton) findViewById(R.id.radioEasy);
        final RadioButton radioButtonNormal = (RadioButton) findViewById(R.id.radioNormal);
        final RadioButton radioButtonHard = (RadioButton) findViewById(R.id.radioHard);


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {



                if (radioButtonEasy.isChecked()){
                    MainActivity.setLevel(5);


                } else if (radioButtonNormal.isChecked()){
                    MainActivity.setLevel(10);


                } else if (radioButtonHard.isChecked()){
                    MainActivity.setLevel(20);

                }


            }


        });

        switch(MainActivity.getLevel()){
            case 5:
                radioButtonEasy.setChecked(true);
                break;

            case 10:
                radioButtonNormal.setChecked(true);
                break;

            case 20:
                radioButtonHard.setChecked(true);
                break;

        }



    }



    public void Background(View view){
        ImageButton imagebutton = (ImageButton) view;
        MainActivity.setBackground(imagebutton.getDrawable());
        super.onBackPressed();


    }



}
