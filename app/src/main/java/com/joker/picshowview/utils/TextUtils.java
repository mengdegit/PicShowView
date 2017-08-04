package com.joker.picshowview.utils;

import android.os.Environment;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by aa on 2017/8/4.
 */

public class TextUtils {
    public static void mCreatFile(String id) {
        try {
            //获取手机本身存储根目录Environment.getExternalStoragePublicDirectory("")
            //sd卡根目录Environment.getExternalStorageDirectory()
            File file = new File(Environment.getExternalStoragePublicDirectory("") + "/xinge/");
            if (!file.exists()) {
                file.mkdir();
            }
            FileWriter fw = new FileWriter(Environment.getExternalStoragePublicDirectory("")+"/xinge/print.xgo", false);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(id);// 往已有的文件上添加字符串
            bw.close();
            fw.close();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static String getId() throws IOException {
        File file = new File(Environment.getExternalStoragePublicDirectory("") + "/xinge/");
        if (!file.exists()) {
            return null;
        }else {
            FileReader reader = new FileReader(Environment.getExternalStoragePublicDirectory("")+"/xinge/print.xgo");
            BufferedReader br = new BufferedReader(reader);
            String id = br.readLine();
            return id;
        }
    }
}