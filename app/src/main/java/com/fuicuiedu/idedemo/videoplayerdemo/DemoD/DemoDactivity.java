package com.fuicuiedu.idedemo.videoplayerdemo.DemoD;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.fuicuiedu.idedemo.videoplayerdemo.R;
import com.fuicuiedu.idedemo.videoplayerdemo.VideoUrlRes;

import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;

public class DemoDactivity extends AppCompatActivity {

    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_d);

        videoView = (VideoView) findViewById(R.id.maind_vv);
        videoView.setVideoPath(VideoUrlRes.getTestVideo1());
        videoView.start();

    }
}
