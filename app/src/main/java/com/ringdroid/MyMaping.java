package com.ringdroid;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class MyMaping extends Activity {

    String LOG_TAG = "sometag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        doMap();

    }

    void doMap(){

        RandomAccessFile randomAccessFile = null;
        try {
            randomAccessFile = new RandomAccessFile("/storage/emulated/0/In Need of Wisdom - Nouman Ali Khan - Gulf Tour 2015.mp3", "r");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Log.e(LOG_TAG, "File not found");
        }
        MappedByteBuffer mappedByteBuffer = null;
        try {
            mappedByteBuffer = randomAccessFile.getChannel().map(FileChannel.MapMode.READ_ONLY, 0, randomAccessFile.length());
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] data = new byte[100];  // Needs to be Byte Array only as MappedBuffer play only with Byte[]
        while (mappedByteBuffer.hasRemaining()) {
            int remaining = data.length;
            if(mappedByteBuffer.remaining() < remaining)
                remaining = mappedByteBuffer.remaining();
            mappedByteBuffer.get(data, 0, remaining);


            // do somthing with data
            Log.e(LOG_TAG, "inside whileloop : " +data[0]+ " remaining ");

        }

    }
}
