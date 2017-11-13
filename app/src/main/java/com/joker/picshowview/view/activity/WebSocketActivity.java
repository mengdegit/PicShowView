package com.joker.picshowview.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.blankj.utilcode.util.SPUtils;
import com.joker.picshowview.R;
import com.joker.picshowview.utils.CountDownUtil;
import com.socks.library.KLog;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.ws.WebSocket;
import okhttp3.ws.WebSocketCall;
import okhttp3.ws.WebSocketListener;
import okio.Buffer;

public class WebSocketActivity extends AppCompatActivity {
    //使用MockWebServer构造一个mock server对象，顺便new一个线程池，用于write线程回写消息。
    private final MockWebServer mockWebServer = new MockWebServer();
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();
    @BindView(R.id.webserver)
    Button webserver;
    @BindView(R.id.webclient)
    Button webclient;
    @BindView(R.id.handler_timer)
    Button handlerTimer;
    @BindView(R.id.stop_handler)
    Button stopHandler;
    @BindView(R.id.restart_machine)
    Button restartMachine;

    private CountDownUtil countDownUtil = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_socket);
        ButterKnife.bind(this);
        initClick();

    }

    private void initClick() {
        restartMachine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_REBOOT);
                intent.putExtra("nowait", 1);
                intent.putExtra("interval", 1);
                intent.putExtra("window", 0);
                sendBroadcast(intent);
            }
        });
    }

    @OnClick(R.id.webserver)
    public void getWebserver(View view) {
        KLog.i("getwebserver");
        mockWebServer.enqueue(new MockResponse().withWebSocketUpgrade(new WebSocketListener() {
            WebSocket webSocket = null;

            @Override
            public void onOpen(WebSocket webSocket, Response response) {
                this.webSocket = webSocket;
                KLog.i("WebSocketActivity", "server onOpen");
                KLog.i("server request header:" + response.request().headers());
                KLog.i("server response header:" + response.headers());
                KLog.i("server response:" + response);

            }

            @Override
            public void onFailure(IOException e, Response response) {
                KLog.i("server onFailure");
                KLog.i("throwable:" + e);
                KLog.i("response:" + response);
            }

            @Override
            public void onMessage(ResponseBody message) throws IOException {
                String string = message.string();
                KLog.i("server onMessage");
                KLog.i("message:" + string);
                if ("command 1".equals(string)) {
                    executorService.execute(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                webSocket.sendMessage(RequestBody.create(WebSocket.TEXT, "replay command 1"));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                } else if ("command 2".equals(string)) {
                    //当收到客户端的command 2时，发送ping帧
                    //ping it
                    executorService.execute(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Buffer buffer = new Buffer();
                                buffer.writeUtf8("ping from server...");
                                webSocket.sendPing(buffer);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }

            @Override
            public void onPong(Buffer payload) {
                KLog.i("server onPong");
                //注意下面都是write线程回写给客户端
                //客户端收到ping帧后会回复pong帧，回调到这，收到pong帧后关闭连接
                //close it
                executorService.execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            webSocket.close(1000, "Normal Closure");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }

            @Override
            public void onClose(int code, String reason) {
                KLog.i("server onClose");
                KLog.i("code:" + code + "reason:" + reason);
            }
        }));

    }

    @OnClick(R.id.webclient)
    public void getClient(View view) {
        SPUtils.getInstance("test").clear();
        Log.i("testClear", "之后" + SPUtils.getInstance("test").getString("testclear"));
        new Thread(new Runnable() {
            @Override
            public void run() {
                String hostName = mockWebServer.getHostName();
                int port = mockWebServer.getPort();
                KLog.i("hostName:" + hostName);
                KLog.i("port:" + port);
                //新建client
                OkHttpClient client = new OkHttpClient.Builder().build();
                //构造request对象
                Request request = new Request.Builder()
                        .url("http://" + "192.168.18.55" + ":" + "8099" + "/")
                        .build();
                //new 一个websocket调用对象并建立连接
                WebSocketCall webSocketCall = WebSocketCall.create(client, request);
                webSocketCall.enqueue(new WebSocketListener() {
                    WebSocket webSocket = null;

                    @Override
                    public void onOpen(WebSocket webSocket, Response response) {
                        this.webSocket = webSocket;
                        KLog.i("client onOpen");
                        KLog.i("client request header:" + response.request().headers());
                        KLog.i("client response header:" + response.headers());
                        KLog.i("client response:" + response);
                        //注意下面都是write线程回写给客户端
                        //建立连接成功后，发生command 1给服务器端
                        try {
                            webSocket.sendMessage(RequestBody.create(WebSocket.TEXT, "command 1"));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(IOException e, Response response) {
                        KLog.i("client onFailure");
                        KLog.i("throwable:" + e);
                        KLog.i("response:" + response);

                    }

                    @Override
                    public void onMessage(ResponseBody message) throws IOException {
                        String string = message.string();
                        KLog.i("client onMessage");
                        KLog.i("message:" + string);
                        //注意下面都是write线程回写给客户端
                        if ("replay command 1".equals(string)) {
                            executorService.execute(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        webSocket.sendMessage(RequestBody.create(WebSocket.TEXT, "command 2"));
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                        }
                    }

                    @Override
                    public void onPong(Buffer payload) {
                        KLog.i("client onPong");
                        KLog.i("payload:" + payload);
                    }

                    @Override
                    public void onClose(int code, String reason) {
                        KLog.i("client onClose");
                        KLog.i("code:" + code + "reason:" + reason);
                    }
                });
            }
        }).start();
    }

    @OnClick(R.id.handler_timer)
    public void handlerTimer(View view) {
        mhandler.postDelayed(runnable, 0);
        countDownUtil = new CountDownUtil(30, new CountDownUtil.ICountDownListener() {
            @Override
            public void onDone() {
                Log.i("testhandlerTimer", "onDone");
            }

            @Override
            public void onProgress(int current, CountDownUtil util) {
                Log.i("testhandlerTimer", current + "");
                if (current == 20) {
                    util.stop();
                }
            }
        });
//        countDownUtil.start();

    }

    @OnClick(R.id.stop_handler)
    public void stopHandler(View view) {
//        countDownUtil.stop();
        mhandler.removeCallbacks(runnable);
    }

    Handler mhandler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            Log.i("testhandlerTimer", "testhandlerTimer");
            mhandler.postDelayed(this, 5000);
        }
    };
}
