package com.joker.picshowview.view;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ListPopupWindow;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.joker.picshowview.APP;
import com.joker.picshowview.R;
import com.joker.picshowview.entity.Student;
import com.joker.picshowview.entity.User;
import com.joker.picshowview.gen.DaoSession;
import com.joker.picshowview.gen.StudentDao;
import com.joker.picshowview.gen.UserDao;
import com.joker.picshowview.utils.CountDownUtil;

import java.util.ArrayList;
import java.util.List;



public class MainActivity extends AppCompatActivity {
    public TextView tv;
    List<String> data;
    List<String> dbData;
    private ListPopupWindow mListPop;
    public Button goSql;
    public Button insertStu;
    public UserDao userDao;
    public StudentDao studentDao;

    int i=3;

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
        initDao();
        initList();
        tv = (TextView) findViewById(R.id.test_github);
        tv.setText("测试Github提交第二次");
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                List<User> users = userDao.loadAll();
//                dbData.clear();
//                for (int i=0;i<users.size();i++){
//                    dbData.add(users.get(i).getId()+"");
//                }
//                mListPop.show();
////                startCountDown();

                List<Student> students = studentDao.loadAll();
                dbData.clear();
                for (int i=0;i<students.size();i++){
                    dbData.add(students.get(i).getId()+"");
                }
                mListPop.show();
//                startCountDown();

            }
        });
        showPop();

        goSql = (Button) findViewById(R.id.goto_sqlat);
        goSql.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Intent intent = new Intent(MainActivity.this,SqlActivity.class);
//                startActivity(intent);
                User user = new User();
//                user.setId(2L);
                user.setName("小明");
                user.setUserId(3L);

                userDao.insert(user);
                i++;
            }
        });

        insertStu = (Button) findViewById(R.id.insert_student);
        insertStu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Student student = new Student();
                student.setName("小王");
                studentDao.insert(student);
            }
        });


    }

    private void initDao() {
        DaoSession daoSession = ((APP)getApplication()).getDaoSession();
        userDao = daoSession.getUserDao();
        studentDao =daoSession.getStudentDao();
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
        mListPop.setAdapter(new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,dbData));
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
        dbData = new ArrayList<String>();
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
