package com.example.eniyanilavan.teseract;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import java.util.Locale;
import java.util.ArrayList;
import android.content.ActivityNotFoundException;
import android.widget.TextView;
import android.widget.Toast;

public class speechtotext extends AppCompatActivity {
    public static final int REQ_CODE_SPEECH_INPUT = 100;
    TextToSpeech t1;
    Button speech;
    TextView mVoiceInputTv;
    ArrayList<String> result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speechtotext);
        speech = (Button) findViewById(R.id.b1);
        mVoiceInputTv = (TextView) findViewById(R.id.l);
        t1 = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                t1.setLanguage(Locale.UK);
                float a = 1;
                t1.setPitch(a);
            }
        });
        speech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                speech();
            }
        });
        }
        void speech()
        {
            Intent a = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            a.putExtra(RecognizerIntent.EXTRA_PROMPT, "Hello, How can I help you?");
            try {
                startActivityForResult(a, 100);
            } catch (ActivityNotFoundException b) {
                Toast.makeText(getApplicationContext(),"Mic not oppened",Toast.LENGTH_LONG).show();
            }
        }
        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);

            switch (requestCode) {
                case REQ_CODE_SPEECH_INPUT: {
                    if (resultCode == RESULT_OK && null != data) {
                        result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                        mVoiceInputTv.setText(result.get(0));
                        Toast.makeText(getApplicationContext(),mVoiceInputTv.getText().toString(),Toast.LENGTH_SHORT).show();
                        if(mVoiceInputTv.getText().toString().equals("hi")) {
                            t1.speak("Hello how can i help you", TextToSpeech.QUEUE_FLUSH, null);
                        }
                        else if (mVoiceInputTv.getText().toString().equals("how are you"))
                        {
                            t1.speak("i am fine,how can i help you", TextToSpeech.QUEUE_FLUSH, null);
                        }
                        else
                        {
                            t1.speak(result.get(0).toString(), TextToSpeech.QUEUE_FLUSH, null);
                        }
                    }
                    break;

                }
            }
        }
}