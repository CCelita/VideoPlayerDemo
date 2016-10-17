package com.fuicuiedu.idedemo.videoplayerdemo.DemoB;

import android.content.Context;
import android.graphics.PixelFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.TextView;

import com.fuicuiedu.idedemo.videoplayerdemo.R;
import com.fuicuiedu.idedemo.videoplayerdemo.VideoUrlRes;

import java.io.IOException;

import io.vov.vitamio.MediaPlayer;

public class DemoBactivity extends AppCompatActivity {
    private MediaPlayer mediaPlayer;
    private SurfaceView surfaceView;
    private SurfaceHolder surfaceHolder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_b);

        mediaPlayer = new MediaPlayer(getApplicationContext());

        surfaceView = (SurfaceView) findViewById(R.id.mainb_sv);
        surfaceHolder = surfaceView.getHolder();

        surfaceHolder.setFormat(PixelFormat.RGBA_8888);//不加则会花屏

        surfaceHolder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                try {
                    mediaPlayer.setDisplay(surfaceHolder);
                    mediaPlayer.setDataSource(VideoUrlRes.getTestVideo1());
                    mediaPlayer.prepareAsync();
                    mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mp) {
                            mediaPlayer.start();
                        }
                    });

                    mediaPlayer.setOnInfoListener(new MediaPlayer.OnInfoListener() {
                        @Override public boolean onInfo(MediaPlayer mp, int what, int extra) {
                            // vitamio5.0，要进行audio处理,才能对在线视频进行播放!!!!
                            if (what == MediaPlayer.MEDIA_INFO_FILE_OPEN_OK) {
                                mediaPlayer.audioInitedOk(mediaPlayer.audioTrackInit());
                                return true;
                            }
                            return false;
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                mediaPlayer.stop();
            }
        });

    }
}
