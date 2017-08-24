package com.joker.picshowview.view;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.support.v7.widget.ListPopupWindow;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.facebook.drawee.view.SimpleDraweeView;
import com.joker.picshowview.APP;
import com.weijuso.hardware.Keypad;
import com.joker.picshowview.R;
import com.joker.picshowview.entity.Student;
import com.joker.picshowview.entity.User;
import com.joker.picshowview.gen.DaoSession;
import com.joker.picshowview.gen.StudentDao;
import com.joker.picshowview.gen.UserDao;
import com.joker.picshowview.utils.CountDownUtil;
import com.joker.picshowview.utils.MainPresenter;
import com.joker.picshowview.utils.TextUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;



public class MainActivity extends Activity implements MainPresenter.IMainView ,OnItemClickListener{
    //继电器
    public Keypad ledControler;

    public TextView tv;
    List<String> data;
    List<String> dbData;
    private ArrayList<Integer> localImages = new ArrayList<Integer>();
    private ArrayList<String> transformerList = new ArrayList<String>();
    private ListPopupWindow mListPop;
    public Button goSql;
    public Button insertStu;
    public Button saveBtn;
    public UserDao userDao;
    public StudentDao studentDao;
    public EditText writeText;
    public TextView Longitude;
    public TextView Latitude;
    public View Banner;
    public SimpleDraweeView SDV;
    public ConvenientBanner convenientBanner;

    private MainPresenter mPresenter;

    public LocationManager locationManager;
    private String locationProvider;
    public Location location;
//    private ArrayAdapter transformerArrayAdapter;
    public Button openControl;
    public Button closeControl;

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
        //继电器初始化
//        ledControler = new Keypad();

        initLocation();
        initDao();
        initList();
        initUI();
        initClick();
        mPresenter = new MainPresenter(this);

//        Banner = findViewById(R.id.banner);
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
        writeText = (EditText) findViewById(R.id.write_et);
        saveBtn = (Button) findViewById(R.id.write_bt);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = TextUtils.getId();
                writeText.setText(id);
            }
        });

        //广告层操作
        bannerWork();
        //加载图片
        SDV.setImageURI("192.168.18.55\\Users\\Administrator\\Desktop\\22.jpg");

        //位置信息
        Longitude.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (location!=null){
                    Longitude.setText(location.getLongitude()+"");
                }
            }
        });
        Latitude.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (location != null){
                    Latitude.setText(location.getLatitude()+"");
                }
            }
        });

    }

    private void initClick() {
        openControl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ledControler.ledsetting(0,1);
            }
        });
        closeControl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ledControler.ledsetting(0,0);
            }
        });
    }

    private void initLocation() {
        //获取地理位置管理器
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        //获取所有可用的位置提供器
        List<String> providers = locationManager.getProviders(true);
        if (providers.contains(LocationManager.GPS_PROVIDER)){
            locationProvider = LocationManager.GPS_PROVIDER;
        }else if (providers.contains(LocationManager.NETWORK_PROVIDER)){
            locationProvider = LocationManager.NETWORK_PROVIDER;
        }else {
            Toast.makeText(this, "没有可用的位置提供器", Toast.LENGTH_SHORT).show();
            return;
        }

        //获取Location
        location = locationManager.getLastKnownLocation(locationProvider);

    }

    private void bannerWork() {
        convenientBanner.setPages(new CBViewHolderCreator<LocalImageHolderView>() {
            @Override
            public LocalImageHolderView createHolder() {
                return new LocalImageHolderView();
            }
        },localImages)
                //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
                .setPageIndicator(new int[]{R.mipmap.ic_page_indicator, R.mipmap.ic_page_indicator_focused})
                //设置指示器的方向
//                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT)
//                .setOnPageChangeListener(this)//监听翻页事件
                .setOnItemClickListener(this);
        convenientBanner.notifyDataSetChanged();
    }

    private void initUI() {
        convenientBanner = (ConvenientBanner) findViewById(R.id.convenientBanner);
        SDV = (SimpleDraweeView) findViewById(R.id.simpledraweeview);
        loadTestDatas();

        Longitude = (TextView) findViewById(R.id.longitude);
        Latitude = (TextView) findViewById(R.id.latitude);

        openControl = (Button) findViewById(R.id.open);
        closeControl = (Button) findViewById(R.id.close);

    }

    private void loadTestDatas() {
        //本地图片集合
        for (int position = 0; position < 7; position++)
            localImages.add(getResId("ic_test_" + position, R.mipmap.class));

        //        //各种翻页效果
//        transformerList.add(DefaultTransformer.class.getSimpleName());
//        transformerList.add(AccordionTransformer.class.getSimpleName());
//        transformerList.add(BackgroundToForegroundTransformer.class.getSimpleName());
//        transformerList.add(CubeInTransformer.class.getSimpleName());
//        transformerList.add(CubeOutTransformer.class.getSimpleName());
//        transformerList.add(DepthPageTransformer.class.getSimpleName());
//        transformerList.add(FlipHorizontalTransformer.class.getSimpleName());
//        transformerList.add(FlipVerticalTransformer.class.getSimpleName());
//        transformerList.add(ForegroundToBackgroundTransformer.class.getSimpleName());
//        transformerList.add(RotateDownTransformer.class.getSimpleName());
//        transformerList.add(RotateUpTransformer.class.getSimpleName());
//        transformerList.add(StackTransformer.class.getSimpleName());
//        transformerList.add(ZoomInTransformer.class.getSimpleName());
//        transformerList.add(ZoomOutTranformer.class.getSimpleName());

//        transformerArrayAdapter.notifyDataSetChanged();
    }

    /**
     * 通过文件名获取资源id 例子：getResId("icon", R.drawable.class);
     *
     * @param variableName
     * @param c
     * @return
     */
    public static int getResId(String variableName, Class<?> c) {
        try {
            Field idField = c.getDeclaredField(variableName);
            return idField.getInt(idField);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
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

    @Override
    public void showTipsView() {
        Log.i("visibility",convenientBanner.getVisibility()+"之前");
        convenientBanner.setVisibility(View.VISIBLE);
        Log.i("visibility",convenientBanner.getVisibility()+"之后");
//        Banner.setVisibility(View.VISIBLE);
//        Intent intent = new Intent(MainActivity.this,AdvertisementActivity.class);
//        startActivity(intent);
    }

    public void showLocation(Location location){

    }

    @Override
    protected void onDestroy() {
        Log.i("destory","xiaohui");
        super.onDestroy();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        mPresenter.resetTipsTimer();
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onResume() {
        mPresenter.startTipsTimer();
        super.onResume();
    }

    @Override
    protected void onPause() {
        mPresenter.endTipsTimer();
        super.onPause();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mPresenter.resetTipsTimer();

        return super.onTouchEvent(event);
    }

    @Override
    public void onItemClick(int position) {
        if (convenientBanner.getVisibility() == View.VISIBLE){
            convenientBanner.setVisibility(View.GONE);
            mPresenter.resetTipsTimer();
        }
    }
}
