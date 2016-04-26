package com.example.markomarksdev.hellotexttospeech;

import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextToSpeechManager ttsManager;
    EditText editText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText)findViewById(R.id.editText);

        ttsManager = new TextToSpeechManager(this);
        ttsManager.initializeTTS();

        final ArrayList<String> toSpeak = getStringArrayList();

        Button read = (Button)findViewById(R.id.button);
        if(read!=null)
        {
            read.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ttsManager.speak(editText.getText().toString());
                    ttsManager.speakAll(toSpeak);
                }
            });
        }

        Button stop = (Button)findViewById(R.id.buttonStop);
        if(stop!=null){
            stop.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ttsManager.stopReading();
                }
            });
        }

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == TextToSpeechManager.MY_TTS_CHECK_CODE) {
            if (resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS) {
                // success, create the TTS instance
                ttsManager.initializeTTS();
            } else {
                // missing data, install it
                Intent installIntent = new Intent();
                installIntent.setAction(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
                startActivity(installIntent);
            }
        }
    }

    @Override
    protected void onDestroy ()
    {
        super.onDestroy();
        ttsManager.onDestroy();
    }


    private ArrayList<String> getStringArrayList(){
        final ArrayList<String> toSpeak = new ArrayList<>();
        toSpeak.add("Hello..., it's me");
        toSpeak.add("I was wondering if after all these years you'd like to meet");
        toSpeak.add("To go over... everything");
        toSpeak.add("They say that time's supposed to heal ya");
        toSpeak.add("But I ain't done much healing");
        toSpeak.add("Hello..., can you hear me?");

        return toSpeak;

    }
}
