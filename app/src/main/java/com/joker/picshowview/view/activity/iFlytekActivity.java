package com.joker.picshowview.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.SpeechSynthesizer;
import com.joker.picshowview.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class iFlytekActivity extends AppCompatActivity {

    @BindView(R.id.read_text)
    Button readText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_i_flytek);
        ButterKnife.bind(this);
        initSDK();
        initClick();
    }

    private void initSDK() {
        SpeechSynthesizer.createSynthesizer(iFlytekActivity.this, new InitListener() {
            @Override
            public void onInit(int i) {

            }
        });
    }

    private void initClick() {
        readText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
