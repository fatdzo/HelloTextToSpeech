package com.example.markomarksdev.hellotexttospeech;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by MarkoPhillipMarkovic on 4/25/2016.
 */
public class TextToSpeechManagerExtended  implements TextToSpeech.OnInitListener  {
    private TextToSpeech myTTS;
    private Context ctx;
    private TextToSpeechUtteranceListener textToSpeechUtteranceListener;
    private static  int MY_TTS_CHECK_CODE = 0;

    public TextToSpeechManagerExtended(Context context)
    {
        ctx = context;
        textToSpeechUtteranceListener = new TextToSpeechUtteranceListener(ctx);
    }

    public void speak(String text)
    {
        String utteranceId=this.hashCode() + "";
        myTTS.speak(text, TextToSpeech.QUEUE_ADD, null, utteranceId);
    }

    public void speakAll(ArrayList<String> list)
    {
        for(String txt: list)
        {
            speak(txt);
        }
    }

    public void stopReading()
    {
        myTTS.stop();
    }

    public void onDestroy()
    {
        if(myTTS != null)
        {
            myTTS.stop();
            myTTS.shutdown();
        }
    }

    public void initializeTTS()
    {
        if(myTTS == null)
        {
            myTTS = new TextToSpeech(ctx, this);
            myTTS.setSpeechRate(1.0f);
            myTTS.setOnUtteranceProgressListener(textToSpeechUtteranceListener);
        }
    }


    @Override
    public void onInit(int initStatus) {

        initializeTTS();

        if (initStatus == TextToSpeech.SUCCESS) {
            if(myTTS.isLanguageAvailable(Locale.US)==TextToSpeech.LANG_AVAILABLE)
            {
                myTTS.setLanguage(Locale.US);
            }
            else if(myTTS.isLanguageAvailable(Locale.UK)==TextToSpeech.LANG_AVAILABLE)
            {
                myTTS.setLanguage(Locale.UK);
            }
            else if(myTTS.isLanguageAvailable(Locale.ENGLISH)==TextToSpeech.LANG_AVAILABLE)
            {
                myTTS.setLanguage(Locale.ENGLISH);
            }
            else
            {
                //Initializing text to voice
                Intent checkTTSIntent = new Intent();
                checkTTSIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
                ((Activity)ctx).startActivityForResult(checkTTSIntent, MY_TTS_CHECK_CODE);
            }
        }
        else if (initStatus == TextToSpeech.ERROR) {
            Toast.makeText(ctx, "Text To Speech init failed...", Toast.LENGTH_LONG).show();
        }
    }
}
