package com.example.easylearn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    ImageButton button,button2,button3;
    TextToSpeech mTTS;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar ab=getSupportActionBar();



        button = (ImageButton) findViewById(R.id.imageButton);
        button2 = (ImageButton) findViewById(R.id.imageButton2);
        button3 = (ImageButton) findViewById(R.id.imageButton3);

        mTTS =new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status==TextToSpeech.SUCCESS)
                {
                    int result = mTTS.setLanguage(Locale.ENGLISH);

                    if (result == TextToSpeech.LANG_MISSING_DATA
                            || result == TextToSpeech.LANG_NOT_SUPPORTED){
                        Log.e("TTS","Langauge not supported");
                    }

                }

            }


        });





        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openModel();
                //speak();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openModel2();
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openModel3();
            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu , menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu:
                speak();
            case R.id.settings:
                openSettings();

        }
        return super.onOptionsItemSelected(item);
    }

    public void openModel() {
        Intent i = new Intent(this, Model.class);
        i.putExtra("keyname","Tiger");
        startActivity(i);
    }

    public void openModel2()
    {
        Intent i = new Intent(this, Model.class);
        i.putExtra("keyname","Lion");
        startActivity(i);
    }

    public void openModel3() {
        Intent i = new Intent(this, Model.class);
        i.putExtra("keyname","Jaguar");
        startActivity(i);
    }

    public void openSettings()
    {
        Intent i = new Intent(this,Settings.class);
        startActivity(i);
    }

    public void speak()
    {
        String text = "Welcome to our app of Animal Virtual World.Here you will see various wild animals in augmented reality form. Click the animal which you want to see and tap the plane surface in camera after clicking any of the below buttons.";
        float pitch =1/2;
        float speed = 1/2;

        mTTS.setPitch(pitch);
        mTTS.setSpeechRate(speed);

        mTTS.speak(text , TextToSpeech.QUEUE_FLUSH,null);

    }






}



















/*package com.example.easylearn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.ar.core.Anchor;
import com.google.ar.core.HitResult;
import com.google.ar.core.Plane;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.ArSceneView;
import com.google.ar.sceneform.HitTestResult;
import com.google.ar.sceneform.Node;
import com.google.ar.sceneform.Scene;
import com.google.ar.sceneform.math.Vector3;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.rendering.ViewRenderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.BaseArFragment;
import com.google.ar.sceneform.ux.TransformableNode;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    TextToSpeech mTTS;
    ImageButton button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

        button = (ImageButton)findViewById(R.id.imageButton);
        mTTS =new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status==TextToSpeech.SUCCESS)
                {
                    int result = mTTS.setLanguage(Locale.ENGLISH);

                    if (result == TextToSpeech.LANG_MISSING_DATA
                            || result == TextToSpeech.LANG_NOT_SUPPORTED){
                        Log.e("TTS","Langauge not supported");
                    }

                }

            }


        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,IndexActivity.class);
                startActivity(i);
                speak();

            }
        });
    }

    public void speak()
    {
        String text = "Welcome to our app of Animal Virtual World.Here you will see various wild animals in augmented reality form. Click the animal which you want to see and tap the plane surface in camera after clicking any of the below buttons.";
        float pitch = 1/2;
        float speed = 1/2;

        mTTS.setPitch(pitch);
        mTTS.setSpeechRate(speed);

        mTTS.speak(text , TextToSpeech.QUEUE_FLUSH,null);

    }





}*/











