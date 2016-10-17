package com.fuicuiedu.idedemo.videoplayerdemo.DemoA;

import android.media.MediaPlayer;
import android.media.session.MediaController;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.fuicuiedu.idedemo.videoplayerdemo.R;
import com.fuicuiedu.idedemo.videoplayerdemo.VideoUrlRes;

import java.io.IOException;

public class DemoAactivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private SurfaceView surfaceView;
    private SurfaceHolder surfaceHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demoa);

//        mediaPlayer = new MediaPlayer();
//        mediaPlayer.setDataSource(VideoUrlRes.getTestVideo1());
//        mediaPlayer.prepareAsync();
//        mediaPlayer.start();
        // 但是：MediaPlayer并不能显示视频
//        /mediaPlayer.setDisplay("SurfaceHolder");


        // SurfaceView
        // SurfaceView 上有一个Surface进行绘制处理,怎么样才能拿到这个Surface，怎么才能将内容显示到Surface上
        // 它提供了一个        SurfaceHodler
        // SurfaceView - getVolder();
        surfaceView = (SurfaceView) findViewById(R.id.maina_sf);
        surfaceHolder = surfaceView.getHolder();
        //        视频画面处理 --- 不用我们去做

        // 你只是放了一个SurfaceView的控件,但是，他内部是通过Surface去处理的
        // 你怎么知道这个Surface有没有创建好？有没有改变大小？有没有dest..???
        // 所以它提供了一个Callback

        surfaceHolder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                try {
                    mediaPlayer = new MediaPlayer();
                    mediaPlayer.setDisplay(surfaceHolder);
                    mediaPlayer.setDataSource(VideoUrlRes.getTestVideo1());
                    mediaPlayer.prepareAsync();
                    mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mp) {
                            mediaPlayer.start();
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
