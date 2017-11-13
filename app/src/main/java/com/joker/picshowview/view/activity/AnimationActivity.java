package com.joker.picshowview.view.activity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.joker.picshowview.R;
import com.joker.picshowview.animation.SpringScaleInterpolator;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 测试动画的activity
 */

public class AnimationActivity extends AppCompatActivity {

    @BindView(R.id.interpolator)
    Button interpolator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        ButterKnife.bind(this);
        initUI();
    }

    private void initUI() {
        interpolator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //第一个参数用于指定这个动画要操作的是哪个控件
                //第二个参数用于指定这个动画要操作这个控件的哪个属性
                //第三个参数是可变长参数，这个就跟ValueAnimator中的可变长参数的意义一样了，就是指这个属性值是从哪变到哪
                ObjectAnimator animatorX = ObjectAnimator.ofFloat(interpolator,"scaleX",0f,1.0f);
                ObjectAnimator animatorY = ObjectAnimator.ofFloat(interpolator,"scaleY",0f,1.0f);
                AnimatorSet set = new AnimatorSet();
                set.setDuration(1000);
                set.setInterpolator(new SpringScaleInterpolator(0.2f));
                set.playTogether(animatorX,animatorY);
                set.start();
            }
        });
    }
}
