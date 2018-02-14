package com.example.hubert.projekt1;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;



//zmienic wersje jezykowa, layout, wpisywanie liter do inputu

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private TextView textView2;
    private EditText editText;
    private Button button;
    private int solution;
    private boolean answer = false;
    private static int level = 0;
    private static Drawable background;

    private java.util.Random random = new java.util.Random();

    static void setBackground(Drawable background){
        MainActivity.background = background;
    }


    int get( int max ) {
        return random.nextInt( max + 1 );

    }

    static void setLevel (int level){
        MainActivity.level = level;
    }

    static int getLevel(){
        return level;
    }




    protected void onResume(){
        super.onResume();
        equation(level);
      //  editText.setText("");
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.relativeLayout);
        relativeLayout.setBackground(background);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

     super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (level == 0) {

            level = 10;
        }

        editText=(EditText) findViewById(R.id.editText);
        textView = (TextView) findViewById(R.id.textView);
        textView2 = (TextView) findViewById(R.id.textView2);


        equation(level);

        final Button buttonOptions = (Button) findViewById(R.id.buttonOpcje);

        buttonOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SecondActivity.class));
            }
        });



        final Button buttonCheck = (Button) findViewById(R.id.buttonSprawdz);

       final int[] buttonsID = new int[10];

        buttonsID[0] = findViewById(R.id.button0).getId();
        buttonsID[1] = findViewById(R.id.button1).getId();
        buttonsID[2] = findViewById(R.id.button2).getId();
        buttonsID[3] = findViewById(R.id.button3).getId();
        buttonsID[4] = findViewById(R.id.button4).getId();
        buttonsID[5] = findViewById(R.id.button5).getId();
        buttonsID[6] = findViewById(R.id.button6).getId();
        buttonsID[7] = findViewById(R.id.button7).getId();
        buttonsID[8] = findViewById(R.id.button8).getId();
        buttonsID[9] = findViewById(R.id.button9).getId();



        buttonCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                String text = editText.getText().toString();


                if(answer){
                    buttonCheck.setText("SPRAWDZ");
                    editText.setText("");
                    textView2.setText("");
                    equation(level);
                    answer=false;

                    for(int i=0; i<10; i++){
                        findViewById(buttonsID[i]).setEnabled(true);
                    }

                }

                else {

                    if (text.equals("") || text.isEmpty() || text.equals(" ")){
                        textView2.setText("Wprowadz odpowiedz");
                    }

                    else {
                        answer = true;
                        int input = Integer.parseInt(text);

                        if (input == solution) {


                            textView2.setText("DOBRZE");
                            MediaPlayer ring_good =  MediaPlayer.create(getApplicationContext(), R.raw.good);
                            ring_good.start();

                            for(int i=0; i<10; i++){
                                findViewById(buttonsID[i]).setEnabled(false);
                            }


                        } else {


                            textView2.setText("ZLE, prawodlowa odpowiedz to " + solution);
                            MediaPlayer ring_wrong =  MediaPlayer.create(getApplicationContext(), R.raw.wrong);
                            ring_wrong.start();

                            for(int i=0; i<10; i++){
                                findViewById(buttonsID[i]).setEnabled(false);
                            }

                        }



                        buttonCheck.setText("NASTEPNY");

                    }




                }

            }
        });
        equation(level);


    }





    public void onClick(View view){
        editText = (EditText) findViewById(R.id.editText);
        editText.setText(editText.getText()+(String)((Button) view).getText());
    }



    private void equation(int level){
        TextView textView = (TextView) findViewById(R.id.textView);
        int liczba1 = get(level);
        int liczba2  = get(level);
        textView.setText(liczba1 + " x " + liczba2 + " = ");
        solution = liczba1 * liczba2;

    }






}
