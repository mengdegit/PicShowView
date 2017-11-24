package com.joker.picshowview.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.joker.picshowview.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListProActivity extends AppCompatActivity {

    @BindView(R.id.animationText)
    Button animationText;
    @BindView(R.id.iFlytek_test)
    Button iFlytekTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_pro);
        ButterKnife.bind(this);
        initClick();
    }

    private void initClick() {
        animationText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ListProActivity.this, AnimationActivity.class));
            }
        });
        iFlytekTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ListProActivity.this, iFlytekActivity.class));
            }
        });
    }
}
