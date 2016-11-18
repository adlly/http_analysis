package com.example.administrator.http_analysis;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Administrator on 2016/11/18.
 */

public class Utils {

    public Utils() {
    }

    public static String getTextFromStream(InputStream inputStream) {
        byte[] b = new byte[1024];
        int index = 0;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();


        try {
            while ((index = inputStream.read(b)) != -1) {
                byteArrayOutputStream.write(b, 0, index);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String text = new String(byteArrayOutputStream.toByteArray());
        return text;


    }

}
