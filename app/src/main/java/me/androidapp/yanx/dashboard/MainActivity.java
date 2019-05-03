package me.androidapp.yanx.dashboard;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_1);
//
//        View view = findViewById(R.id.view);
//        ObjectAnimator bottomFlipAnimator = ObjectAnimator.ofFloat(view, "bottomFlip", 30);
//        bottomFlipAnimator.setDuration(1500);
//        ObjectAnimator topFlipAnimator = ObjectAnimator.ofFloat(view, "topFlip", -30);
//        topFlipAnimator.setDuration(1500);
//        topFlipAnimator.setStartDelay(300);
//        ObjectAnimator flipRotationAnimator = ObjectAnimator.ofFloat(view, "flipRotation", 270);
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
