package com.joker.picshowview.view;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ListPopupWindow;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.joker.picshowview.R;
import com.joker.picshowview.utils.CountDownUtil;

import java.util.ArrayList;
import java.util.List;



public class MainActivity extends AppCompatActivity {
    public TextView tv;
    List<String> data;
    private ListPopupWindow mListPop;

    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what ==1){
                tv.setText(msg.arg1+"");
            }else if (msg.what ==2){
                tv.setText("done");
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initList();
        tv = (TextView) findViewById(R.id.test_github);
        tv.setText("测试Github提交第二次");
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mListPop.show();
                startCountDown();

            }
        });
        showPop();




    }

    private void startCountDown() {
        new CountDownUtil(30, new CountDownUtil.ICountDownListener() {
            @Override
            public void onDone() {
                Message message = mHandler.obtainMessage(2);
                mHandler.sendMessage(message);
            }

            @Override
            public void onProgress(int current) {
                Message message = mHandler.obtainMessage(1);
                message.arg1 = current;
                mHandler.sendMessage(message);
            }
        }).start();
    }

    public void showPop(){
        mListPop = new ListPopupWindow(this);
        mListPop.setAdapter(new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,data));
        mListPop.setWidth(500);
        mListPop.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        mListPop.setAnchorView(tv);
        mListPop.setModal(true);
        mListPop.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                tv.setText(data.get(position));
                mListPop.dismiss();
            }
        });
    }

    private void initList() {
        data = new ArrayList();
        data.add("门禁机");
        data.add("闸机");
        data.add("访客机");
        data.add("桌面式人证通");
        data.add("人像平台");
        data.add("手持式人脸考勤");
        data.add("网吧项目");
    }
}
