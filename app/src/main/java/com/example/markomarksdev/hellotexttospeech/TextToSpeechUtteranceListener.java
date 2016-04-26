package com.example.markomarksdev.hellotexttospeech;

import android.content.Context;
import android.speech.tts.UtteranceProgressListener;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by MarkoPhillipMarkovic on 4/25/2016.
 */
public class TextToSpeechUtteranceListener extends UtteranceProgressListener {
    Context context;
    public TextToSpeechUtteranceListener(Context ctx)
    {
        context = ctx;
    }

    @Override
    public void onStart(String utteranceId) {
        // TODO Auto-generated method stub
        Log.e("HelloTextToSpeech", "Text To Speech Started");
        //LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent(START_PLAYING_BROADCAST));
    }

    @Override
    public void onDone(String utteranceId) {
        // TODO Auto-generated method stub
        Log.e("HelloTextToSpeech", "Text To Speech Done");
    }

    @Override
    public void onError(String utteranceId) {
        // TODO Auto-generated method stub
        Log.e("HelloTextToSpeech", "Text To Speech ERROR");
    }

}

