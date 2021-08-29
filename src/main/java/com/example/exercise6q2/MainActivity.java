package com.example.exercise6q2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Matrix;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.util.Pair;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView imgCirle;
    private ImageView imgRectangle;
    private ImageView imgAndroid;
    private ImageView imgSquare;

    public static final String ANIMATION = "Animation";
    public static final String EXPLODE_ANIMATION = "Explode Animation";
    public static final String FADE_TRANSITION = "Fade Transition";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*Call this before setContentView() is called to enable transition*/
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);

        // or enable windowContentTransitions in the AppTheme os style.xml
        //<item name="android:windowContentTransitions">true</item>

        setContentView(R.layout.activity_main);


        imgCirle = findViewById(R.id.imgCircle);
        imgRectangle = findViewById(R.id.imgRectangle);
        imgAndroid = findViewById(R.id.imgAndroid);
        imgSquare = findViewById(R.id.imgSquare);

        if (getIntent().hasExtra(ANIMATION)) {
            switch (getIntent().getStringExtra(ANIMATION)) {
                case EXPLODE_ANIMATION:
                    Explode explode = new Explode();
                    getWindow().setEnterTransition(explode);
                    break;
                case FADE_TRANSITION:
                    Fade fade = new Fade();
                    getWindow().setEnterTransition(fade);
                    break;
                default:
                    break;
            }
        }

        imgCirle.setOnClickListener(this);
        imgRectangle.setOnClickListener(this);
        imgAndroid.setOnClickListener(this);
        imgSquare.setOnClickListener(this);

        // relaunch activity
/*        Intent relaunchActivity = getIntent();
        finish();
        startActivity(relaunchActivity);*/

    }

    @Override
    public void onClick(View view) {


        switch (view.getId()) {

            case R.id.imgCircle:
                Intent intent = new Intent(this, MainActivity.class);
                Explode explode = new Explode();
                explode.setDuration(400);
                getWindow().setExitTransition(explode);
                intent.putExtra(ANIMATION, EXPLODE_ANIMATION);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this);
                startActivity(intent, options.toBundle());
                break;

            case R.id.imgRectangle:
                Intent intent2 = new Intent(this, MainActivity.class);
                intent2.putExtra(ANIMATION, FADE_TRANSITION);
                Fade fade = new Fade();
                fade.setDuration(400);
                getWindow().setExitTransition(fade);
                startActivity(intent2,
                        ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
                break;

            case R.id.imgAndroid:
                Intent intent3 = new Intent(MainActivity.this, SecondActivity.class);
                ActivityOptions options2 = ActivityOptions.makeSceneTransitionAnimation(this,
                        Pair.create((View) imgAndroid, "swap_shared_transition_android_icon"),
                        Pair.create((View) imgSquare, "swap_shared_transition_square")
                );
                startActivity(intent3, options2.toBundle());
                break;

            case R.id.imgSquare:
                Animation rotateAnimation = AnimationUtils.loadAnimation(this, R.anim.rotate);
                imgSquare.startAnimation(rotateAnimation);

                break;
        }

    }


}
