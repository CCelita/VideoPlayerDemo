package com.fuicuiedu.idedemo.videoplayerdemo.DemoC;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import com.fuicuiedu.idedemo.videoplayerdemo.R;
import com.fuicuiedu.idedemo.videoplayerdemo.VideoUrlRes;

public class DemoCactivity extends AppCompatActivity {
    private VideoView videoView;
    private MediaController mediaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_c);

        videoView = (VideoView) findViewById(R.id.mainc_vv);

        videoView.setVideoPath(VideoUrlRes.getTestVideo1());

        mediaController = new MediaController(this);
        videoView.setMediaController(mediaController);


        videoView.start();
    }
}
