package me.androidapp.yanx.dashboard;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        View view = findViewById(R.id.view);
//        ObjectAnimator bottomFlipAnimator = ObjectAnimator.ofFloat(view, "bottomFlip", 30);
//        bottomFlipAnimator.setDuration(1500);
//        ObjectAnimator topFlipAnimator = ObjectAnimator.ofFloat(view, "topFlip", -30);
//        topFlipAnimator.setDuration(1500);
//        topFlipAnimator.setStartDelay(300);
//        ObjectAnimator flipRotationAnimator = ObjectAnimator.ofFloat(view, "flipRotation", 720);
//        flipRotationAnimator.setDuration(1500);
//        flipRotationAnimator.setStartDelay(300);
//
//        AnimatorSet animatorSet = new AnimatorSet();
//        animatorSet.playSequentially(
//                bottomFlipAnimator,
//                flipRotationAnimator,
//                topFlipAnimator
//        );
//        animatorSet.setStartDelay(1000);
//        animatorSet.start();
    }
}
