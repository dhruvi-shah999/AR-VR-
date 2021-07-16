package com.example.easylearn;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Toast;

import com.google.ar.core.Anchor;
import com.google.ar.core.HitResult;
import com.google.ar.core.Plane;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.ArSceneView;
import com.google.ar.sceneform.Scene;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.BaseArFragment;
import com.google.ar.sceneform.ux.TransformableNode;

import java.util.Locale;

public class Model extends AppCompatActivity {

    TextToSpeech mTTS;
    private ArFragment arFragment;
    private ModelRenderable tigerRenderable,lionRenderable,jagRenderable;
    private ArSceneView arView;
    private Scene scene;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_model);


        String model = getIntent().getStringExtra("keyname");
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
                else
                {
                    Log.e("TTS","Initialization failed");
                }

            }


        });



        arFragment = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);
        arView = arFragment.getArSceneView();
        scene = arView.getScene();


        setUpModel();
        if(model.equalsIgnoreCase("Tiger")) {
            setUpTiger();
        }
        else if(model.equalsIgnoreCase("Lion")){
            setUpLion();
        }
        else if(model.equalsIgnoreCase("Jaguar")){
            setUpJag();
        }




    }


    private void setUpModel() {
       /* ViewRenderable.builder()
                .setView(this,R.layout.animal_card)
                .build()
                .thenAccept(
                        (renderable)-> {
                            infoCard.setRenderable(renderable);
                            TextView textView = (TextView)renderable.getView();
                            Button button = (Button)renderable.getView();
                        }

                );*/
        ModelRenderable.builder()
                .setSource(this, R.raw.tiger)
                .build()
                .thenAccept(renderable -> tigerRenderable = renderable)
                .exceptionally(throwable -> {
                    Toast.makeText(Model.this,"Model can't be Loaded", Toast.LENGTH_SHORT).show();
                    return null;
                });
        ModelRenderable.builder()
                .setSource(this,R.raw.lion)
                .build()
                .thenAccept(renderable -> lionRenderable = renderable)
                .exceptionally(throwable -> {
                    Toast.makeText(Model.this,"Model can't be Loaded", Toast.LENGTH_SHORT).show();
                    return null;
                });
        ModelRenderable.builder()
                .setSource(this, R.raw.jaguar)
                .build()
                .thenAccept(renderable -> jagRenderable = renderable)
                .exceptionally(throwable -> {
                    Toast.makeText(Model.this,"Model can't be Loaded", Toast.LENGTH_SHORT).show();
                    return null;
                });

    }

    //private void create



    private void setUpTiger(){
        arFragment.setOnTapArPlaneListener(new BaseArFragment.OnTapArPlaneListener() {
            @Override
            public void onTapPlane(HitResult hitResult, Plane plane, MotionEvent motionEvent) {
                Anchor anchor = hitResult.createAnchor();
                AnchorNode anchorNode = new AnchorNode(anchor);
                anchorNode.setParent(scene);

                    createTiger(anchorNode);
                    speakTiger();


            }
        });
    }

    private void setUpLion(){
        arFragment.setOnTapArPlaneListener(new BaseArFragment.OnTapArPlaneListener() {
            @Override
            public void onTapPlane(HitResult hitResult, Plane plane, MotionEvent motionEvent) {
                Anchor anchor = hitResult.createAnchor();
                AnchorNode anchorNode = new AnchorNode(anchor);
                anchorNode.setParent(scene);

                createLion(anchorNode);
                speakLion();


            }
        });
    }

    private void setUpJag(){
        arFragment.setOnTapArPlaneListener(new BaseArFragment.OnTapArPlaneListener() {
            @Override
            public void onTapPlane(HitResult hitResult, Plane plane, MotionEvent motionEvent) {
                Anchor anchor = hitResult.createAnchor();
                AnchorNode anchorNode = new AnchorNode(anchor);
                anchorNode.setParent(scene);

                createJag(anchorNode);
                speakJag();


            }
        });
    }





    private void createTiger(AnchorNode anchorNode){
        TransformableNode node = new TransformableNode(arFragment.getTransformationSystem());
        //TransformableNode node2 = new TransformableNode(arFragment.getTransformationSystem());
        //node.setLocalScale(new Vector3(0.00025f,0.00025f,0.00025f));
        node.getScaleController().setMaxScale(0.4f);
        node.getScaleController().setMinScale(0.2f);
        node.setParent(anchorNode);
        //node2.setParent(node);
        node.setRenderable(tigerRenderable);


        node.select();


    }

    private void createLion(AnchorNode anchorNode){
        TransformableNode node = new TransformableNode(arFragment.getTransformationSystem());
        //TransformableNode node2 = new TransformableNode(arFragment.getTransformationSystem());
        //node.setLocalScale(new Vector3(0.00025f,0.00025f,0.00025f));
        node.getScaleController().setMaxScale(0.4f);
        node.getScaleController().setMinScale(0.2f);
        node.setParent(anchorNode);
        //node2.setParent(node);
        node.setRenderable(lionRenderable);


        node.select();


    }

    public void speakTiger()
    {
        String text = "Tigers are the largest cat species in the world reaching up to 3.3 meters in length and weighing up to 670 pounds!";
        float pitch = 1/2;
        float speed = 1/2;

        mTTS.setPitch(pitch);
        mTTS.setSpeechRate(speed);

        mTTS.speak(text , TextToSpeech.QUEUE_FLUSH,null);

    }

    public void speakLion()
    {
        String text = "Lion's roar can be heard from 8kilometers away.";
        float pitch = 1/2;
        float speed = 1/2;

        mTTS.setPitch(pitch);
        mTTS.setSpeechRate(speed);

        mTTS.speak(text , TextToSpeech.QUEUE_FLUSH,null);

    }

    private void createJag(AnchorNode anchorNode){
        TransformableNode node = new TransformableNode(arFragment.getTransformationSystem());
        //TransformableNode node2 = new TransformableNode(arFragment.getTransformationSystem());
        //node.setLocalScale(new Vector3(0.00025f,0.00025f,0.00025f));
        node.getScaleController().setMaxScale(0.4f);
        node.getScaleController().setMinScale(0.2f);
        node.setParent(anchorNode);
        //node2.setParent(node);
        node.setRenderable(jagRenderable);


        node.select();


    }

    public void speakJag()
    {
        String text = "They have a mighty name. The word 'jaguar' comes from the indigenous word 'yaguar', which means 'he who kills with one leap'. ...\n" +
                "Their territory is shrinking. ...\n" +
                "They're on the chunky side. ...\n" +
                "They've got spotty spots. ...\n" +
                "Jaguars are excellent swimmers. ...\n" +
                "Jaguars roar. ...\n" +
                "They'll eat almost anything. ...\n" +
                "They kill with a powerful bite.";
        float pitch = 1/2;
        float speed = 1/2;

        mTTS.setPitch(pitch);
        mTTS.setSpeechRate(speed);

        mTTS.speak(text , TextToSpeech.QUEUE_FLUSH,null);

    }




}
