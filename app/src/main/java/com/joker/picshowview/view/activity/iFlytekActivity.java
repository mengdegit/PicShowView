package com.joker.picshowview.view.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.SpeechSynthesizer;
import com.joker.picshowview.R;
import com.joker.picshowview.entity.User;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class iFlytekActivity extends AppCompatActivity {

    @BindView(R.id.read_text)
    Button readText;
    String name;
    @BindView(R.id.read_cache)
    Button readCache;

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_i_flytek);
        ButterKnife.bind(this);
//        initSDK();
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
                Observable.create(new ObservableOnSubscribe<User>() {
                    @Override
                    public void subscribe(ObservableEmitter<User> e) throws Exception {
                        User user = new User();
                        user.setUserId((long) 1);
                        user.setName("小明");
                        user.setId((long) 1);
                        e.onNext(null);
                    }
                })
                        .map(new Function<User, String>() {
                            @Override
                            public String apply(@NonNull User user) throws Exception {
                                return user.getName();
                            }
                        })
                        .doOnNext(new Consumer<String>() {
                            @Override
                            public void accept(@NonNull String s) throws Exception {
                                name = s;
                            }
                        })
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<String>() {
                            @Override
                            public void accept(@NonNull String s) throws Exception {
                                readText.setText(name);
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(@NonNull Throwable throwable) throws Exception {
                                readText.setText("接口失败");
                            }
                        });
            }
        });

        readCache.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Observable<String> cache = Observable.create(new ObservableOnSubscribe<String>() {
                    @Override
                    public void subscribe(ObservableEmitter<String> e) throws Exception {
                        e.onNext("this is cache text");
                        e.onComplete();


                    }
                });

                Observable<String> netWork = Observable.create(new ObservableOnSubscribe<User>() {
                    @Override
                    public void subscribe(ObservableEmitter<User> e) throws Exception {
                        User user = new User();
                        user.setId((long) 2);
                        user.setUserId((long) 2);
                        user.setName("小王");
                        e.onNext(user);
                        e.onComplete();
                    }
                }).map(new Function<User, String>() {
                    @Override
                    public String apply(@NonNull User user) throws Exception {
                        return user.getName();
                    }
                });

            Observable.concat(cache,netWork)
                    .subscribeOn(Schedulers.io())
                    .subscribe(new Consumer<String>() {
                        @Override
                        public void accept(@NonNull final String s) throws Exception {
                            Log.i("ButtonText",readCache.getText().toString());
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    readCache.setText(s);
                                }
                            });

                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(@NonNull Throwable throwable) throws Exception {
                            Log.i("ButtonText",Log.getStackTraceString(throwable));
//                            readCache.setText("readError");
                        }
                    });
            }
        });
    }
}
