package com.joker.picshowview.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Power;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.joker.picshowview.R;
import com.joker.picshowview.utils.ToastUtils;
import com.weijuso.hardware.Keypad;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ToastActivity extends AppCompatActivity {

    @BindView(R.id.my_layout)
    Button myLayout;
    @BindView(R.id.automatic_toast)
    Button automaticToast;
    @BindView(R.id.automatic_toast2)
    Button automaticToast2;

    public Keypad ledControler;
    @BindView(R.id.launcher_app)
    Button launcherApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toast);
        ButterKnife.bind(this);
        ledControler = new Keypad();
        initOnclick();
    }

    private void initOnclick() {
        myLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.show(ToastActivity.this, "这是我的Toast");
//                ledControler.ledsetting(0,1);
//                ledControler.ledsetting(1,1);
//                ledControler.ledsetting(2,1);
//                ledControler.ledsetting(3,1);
//                ledControler.ledsetting(4,1);
//                ledControler.ledsetting(5,1);
//                ledControler.ledsetting(6,1);
//                ledControler.ledsetting(7,1);
//                ledControler.ledsetting(8,1);
//                ledControler.ledsetting(9,1);
                Power.set_zysj_gpio_value(0, 1);
                Power.set_zysj_gpio_value(1, 1);
                Power.set_zysj_gpio_value(2, 1);
                Power.set_zysj_gpio_value(3, 1);
                Power.set_zysj_gpio_value(4, 1);
                Power.set_zysj_gpio_value(5, 1);
                Power.set_zysj_gpio_value(6, 1);
                Power.set_zysj_gpio_value(7, 1);
                Power.set_zysj_gpio_value(8, 1);
                Power.set_zysj_gpio_value(9, 1);
            }
        });
        automaticToast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ToastUtils.showToast(ToastActivity.this, "自动覆盖", Toast.LENGTH_LONG);
                ToastUtils.ToastShow(ToastActivity.this, R.mipmap.offline, "上一个");
            }
        });
        automaticToast2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ToastUtils.showToast(ToastActivity.this, "自动覆盖上一个", Toast.LENGTH_LONG);
                ToastUtils.ToastShow(ToastActivity.this, R.mipmap.error, "覆盖");
//                ledControler.ledsetting(0,0);
//                ledControler.ledsetting(1,0);
//                ledControler.ledsetting(2,0);
//                ledControler.ledsetting(3,0);
//                ledControler.ledsetting(4,0);
//                ledControler.ledsetting(5,0);
//                ledControler.ledsetting(6,0);
//                ledControler.ledsetting(7,0);
//                ledControler.ledsetting(8,0);
//                ledControler.ledsetting(9,0);
                Power.set_zysj_gpio_value(0, 0);
                Power.set_zysj_gpio_value(1, 0);
                Power.set_zysj_gpio_value(2, 0);
                Power.set_zysj_gpio_value(3, 0);
                Power.set_zysj_gpio_value(4, 0);
                Power.set_zysj_gpio_value(5, 0);
                Power.set_zysj_gpio_value(6, 0);
                Power.set_zysj_gpio_value(7, 0);
                Power.set_zysj_gpio_value(8, 0);
                Power.set_zysj_gpio_value(9, 0);


            }
        });
    }

    @OnClick(R.id.launcher_app)
    public void openAppreceiver(View view){
        Intent intent2 = new Intent();
        intent2.setAction(Intent.ACTION_VIEW);
        intent2.setType("test/");
        startActivity(intent2);
    }
}
