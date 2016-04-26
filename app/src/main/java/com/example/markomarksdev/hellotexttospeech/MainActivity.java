package com.example.markomarksdev.hellotexttospeech;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextToSpeechManager ttsManager;
    TextToSpeechManagerExtended ttsManagerExtended;
    EditText editText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText)findViewById(R.id.editText);

        ttsManager = new TextToSpeechManager(this);
        ttsManager.initializeTTS();

        //ttsManagerExtended = new TextToSpeechManagerExtended(this);
        //ttsManagerExtended.initializeTTS();
        //final ArrayList<String> toSpeak = getStringArrayList();


        Button read = (Button)findViewById(R.id.button);
        if(read!=null)
        {
            read.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ttsManager.speak(editText.getText().toString());
                    //ttsManagerExtended.speakAll(toSpeak);
                }
            });
        }

        Button stop = (Button)findViewById(R.id.buttonStop);
        if(stop!=null){
            stop.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ttsManager.stopReading();
                    //ttsManagerExtended.stopReading();
                }
            });
        }

    }

    private ArrayList<String> getStringArrayList(){
        final ArrayList<String> toSpeak = new ArrayList<>();
        toSpeak.add("Hello..., it's me");
        toSpeak.add("I was wondering if after all these years you'd like to meet");
        toSpeak.add("To go over... everything");
        toSpeak.add("They say that time's supposed to heal ya");
        toSpeak.add("But I ain't done much healing");

        return toSpeak;

    }
}
