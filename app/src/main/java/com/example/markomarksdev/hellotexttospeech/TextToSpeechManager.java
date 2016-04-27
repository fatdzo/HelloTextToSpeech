package com.example.markomarksdev.hellotexttospeech;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by MarkoPhillipMarkovic on 4/25/2016.
 */
//TextToSpeech.OnInitListener - Interface definition of a callback to be invoked indicating the completion of the TextToSpeech engine initialization.
public class TextToSpeechManager implements TextToSpeech.OnInitListener {
    private Context ctx;
    private TextToSpeech myTTS;
    private TextToSpeechUtteranceListener textToSpeechUtteranceListener;
    public static int MY_TTS_CHECK_CODE = 0;

    public TextToSpeechManager(Context context)
    {
        ctx = context;
        textToSpeechUtteranceListener = new TextToSpeechUtteranceListener(ctx);
    }

    @Override
    public void onInit(int initStatus) {

        initializeTTS();
        Log.e("HelloTextToSpeech", "Init TTS");
        if (initStatus == TextToSpeech.SUCCESS) {

            Locale currentLocale = ctx.getResources().getConfiguration().locale;
            Log.e("HelloTextToSpeech", "Voice name: " + myTTS.getDefaultVoice().getName());

            if(myTTS.isLanguageAvailable(Locale.US)==TextToSpeech.LANG_AVAILABLE && currentLocale == Locale.US)
            {
                myTTS.setLanguage(Locale.US);
            }
            else if(myTTS.isLanguageAvailable(Locale.UK)==TextToSpeech.LANG_AVAILABLE && currentLocale == Locale.UK)
            {
                myTTS.setLanguage(Locale.UK);
            }
            else if(myTTS.isLanguageAvailable(Locale.ENGLISH)==TextToSpeech.LANG_AVAILABLE && currentLocale == Locale.ENGLISH)
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

    public void initializeTTS()
    {
        if(myTTS == null)
        {
            myTTS = new TextToSpeech(ctx, this);
            myTTS.setSpeechRate(1.0f);
            myTTS.setPitch(1.0f);
            myTTS.setOnUtteranceProgressListener(textToSpeechUtteranceListener);
        }
    }

    public void onDestroy()
    {
        if(myTTS != null)
        {
            myTTS.stop();
            myTTS.shutdown();
        }
    }

    /*
        QUEUE_ADD - Queue mode where the new entry is added at the end of the playback queue.
        QUEUE_FLUSH - Queue mode where all entries in the playback queue (media to be played and text to be synthesized) are dropped and replaced by the new entry.
    */
    public void speak(String text, String utteranceId)
    {
        myTTS.speak(text, TextToSpeech.QUEUE_ADD, null, utteranceId);
    }

    public void speakAll(ArrayList<String> list, String utteranceId)
    {
        for(String txt: list)
        {
            String temputt = utteranceId + "_" + list.indexOf(txt);
            speak(txt, temputt);
        }
    }

    public void stopSpeaking()
    {
        myTTS.stop();
    }


}
