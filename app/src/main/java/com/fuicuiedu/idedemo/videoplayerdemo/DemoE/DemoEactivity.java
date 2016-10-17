package com.fuicuiedu.idedemo.videoplayerdemo.DemoE;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.fuicuiedu.idedemo.videoplayerdemo.R;
import com.fuicuiedu.idedemo.videoplayerdemo.VideoUrlRes;

import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;

public class DemoEactivity extends AppCompatActivity {

    private VideoView videoView;
    private MediaController mediaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_e);


        videoView = (VideoView) findViewById(R.id.maine_vv);

        mediaController = new MediaController(this);
        videoView.setMediaController(mediaController);

        videoView.setVideoPath(VideoUrlRes.getTestVideo1());
        videoView.start();
    }
}
