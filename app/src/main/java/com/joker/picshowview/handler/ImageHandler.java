package com.joker.picshowview.handler;

import com.yanzhenjie.andserver.SimpleRequestHandler;
import com.yanzhenjie.andserver.view.View;

import org.apache.httpcore.HttpException;
import org.apache.httpcore.HttpRequest;

import java.io.IOException;

/**
 * Created by aa on 2018/3/21.
 */

public class ImageHandler extends SimpleRequestHandler{
    @Override
    protected View handle(HttpRequest request) throws HttpException, IOException {
        return super.handle(request);
    }
}
