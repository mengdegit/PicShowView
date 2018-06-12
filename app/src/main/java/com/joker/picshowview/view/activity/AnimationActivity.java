package com.joker.picshowview.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.joker.picshowview.R;
import com.joker.picshowview.animation.explosion.ExplosionField;
import com.joker.picshowview.view.MarqueeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 测试动画的activity
 */

public class AnimationActivity extends Activity {

    @BindView(R.id.interpolator)
    Button interpolator;
    @BindView(R.id.broke_animation)
    TextView brokeAnimation;
    @BindView(R.id.root_view)
    RelativeLayout rootView;

    @BindView(R.id.hide_textview)
    Button hideTextview;
    @BindView(R.id.focusable_view)
    EditText focusableView;
    @BindView(R.id.shutdown)
    Button shutdown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        ButterKnife.bind(this);
        initUI();
    }

    private void initUI() {
//        ExplosionField explosionField = new ExplosionField(this);
//        explosionField.addListener(rootView);
//        interpolator.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                brokeAnimation.callOnClick();
//                brokeAnimation.setVisibility(View.GONE);
//                //第一个参数用于指定这个动画要操作的是哪个控件
//                //第二个参数用于指定这个动画要操作这个控件的哪个属性
//                //第三个参数是可变长参数，这个就跟ValueAnimator中的可变长参数的意义一样了，就是指这个属性值是从哪变到哪
//                ObjectAnimator animatorX = ObjectAnimator.ofFloat(interpolator, "scaleX", 0f, 1.0f);
//                ObjectAnimator animatorY = ObjectAnimator.ofFloat(interpolator, "scaleY", 0f, 1.0f);
//                AnimatorSet set = new AnimatorSet();
//                set.setDuration(1000);
//                set.setInterpolator(new SpringScaleInterpolator(0.2f));
//                set.playTogether(animatorX, animatorY);
//                set.start();
//            }
//        });

//        tvCarouselCustomerTwo.startWithText("其实，跑马灯效果就是不断的滚动位置，而自定义速度的跑马灯就是以自定义的时间间隔去滚动位置，明白了这个思想之后，做起来就比较的简单了。");
//        tvCarouselCustomerTwo.setTextSize(50);
//        tvCarouselCustomerTwo.setText("其实，跑马灯效果就是不断的滚动位置，而自定义速度的跑马灯就是以自定义的时间间隔去滚动位置，明白了这个思想之后，做起来就比较的简单了。");
//        tvCarouselCustomerTwo.setSpeed(5000);

    }
    @OnClick(R.id.shutdown)
    public void shutdown(View view){
        Intent intent = new Intent("android.intent.action.ACTION_REQUEST_SHUTDOWN");
        intent.putExtra("android.intent.extra.KEY_CONFIRM", false);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
