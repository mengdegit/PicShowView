package com.joker.picshowview.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.joker.picshowview.R;
import com.joker.picshowview.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ToastActivity extends AppCompatActivity {

    @BindView(R.id.my_layout)
    Button myLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toast);
        ButterKnife.bind(this);
        initOnclick();
    }

    private void initOnclick() {
        myLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.show(ToastActivity.this,"这是我的Toast");
            }
        });
    }
}
