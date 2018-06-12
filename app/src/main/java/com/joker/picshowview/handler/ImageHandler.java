package com.joker.picshowview.handler;

import android.util.Log;

import com.yanzhenjie.andserver.RequestHandler;
import com.yanzhenjie.andserver.RequestMethod;
import com.yanzhenjie.andserver.SimpleRequestHandler;
import com.yanzhenjie.andserver.annotation.RequestMapping;
import com.yanzhenjie.andserver.util.HttpRequestParser;
import com.yanzhenjie.andserver.view.View;

import org.apache.httpcore.HttpException;
import org.apache.httpcore.HttpRequest;
import org.apache.httpcore.HttpResponse;
import org.apache.httpcore.entity.StringEntity;
import org.apache.httpcore.protocol.HttpContext;

import java.io.IOException;

/**
 * Created by aa on 2018/3/21.
 */

public class ImageHandler implements RequestHandler{

    @RequestMapping(method = {RequestMethod.POST})
    @Override
    public void handle(HttpRequest request, HttpResponse response, HttpContext context) throws HttpException, IOException {
        String requestBody = HttpRequestParser.getContentFromBody(request);
        Log.i("andserver",requestBody);
        StringEntity stringEntity = new StringEntity("请求成功", "utf-8");
        response.setStatusCode(200);
        response.setEntity(stringEntity);
    }
}
