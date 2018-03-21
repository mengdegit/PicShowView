package com.joker.picshowview.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.joker.picshowview.R;
import com.joker.picshowview.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ToastActivity extends AppCompatActivity {

    @BindView(R.id.my_layout)
    Button myLayout;
    @BindView(R.id.automatic_toast)
    Button automaticToast;
    @BindView(R.id.automatic_toast2)
    Button automaticToast2;

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
                ToastUtils.show(ToastActivity.this, "这是我的Toast");
            }
        });
        automaticToast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ToastUtils.showToast(ToastActivity.this, "自动覆盖", Toast.LENGTH_LONG);
                ToastUtils.ToastShow(ToastActivity.this,R.mipmap.offline,"上一个");
            }
        });
        automaticToast2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ToastUtils.showToast(ToastActivity.this, "自动覆盖上一个", Toast.LENGTH_LONG);
                ToastUtils.ToastShow(ToastActivity.this,R.mipmap.error,"覆盖");


            }
        });
    }
}
