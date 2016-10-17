package com.fuicuiedu.idedemo.videoplayerdemo.DemoF;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.fuicuiedu.idedemo.videoplayerdemo.R;
import com.fuicuiedu.idedemo.videoplayerdemo.VideoUrlRes;

import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;

public class DemoFactivity extends AppCompatActivity {

    private VideoView videoView;
    private TextView tvDownloadRate;
    private TextView tvLoadRate;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_f);

        videoView = (VideoView) findViewById(R.id.videoView);
        tvDownloadRate = (TextView) findViewById(R.id.tvDownloadRate);
        tvLoadRate = (TextView) findViewById(R.id.tvLoadRate);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);


        videoView.setVideoPath(VideoUrlRes.getTestVideo2());
        videoView.setMediaController(new MediaController(this));

        //第一步：设置缓冲大小
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                videoView.setBufferSize(512 * 1024);
            }
        });

        //第二步，监听缓冲的开始，结束，速率变化（调整UI界面）
        videoView.setOnInfoListener(new MediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(MediaPlayer mp, int what, int extra) {
                switch (what){
                    case MediaPlayer.MEDIA_INFO_BUFFERING_START:
                        startBuffer();
                        break;
                    case MediaPlayer.MEDIA_INFO_BUFFERING_END:
                        endBuffer();
                        break;
                    case MediaPlayer.MEDIA_INFO_DOWNLOAD_RATE_CHANGED:
                        tvDownloadRate.setText(extra + "KB/S");
                        break;
                }
                return false;
            }
        });

        //第三步：监听缓冲进度更新当前缓冲的百分比
        videoView.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
            @Override
            public void onBufferingUpdate(MediaPlayer mp, int percent) {
                tvLoadRate.setText(percent + "%");
            }
        });

    }


    private void startBuffer(){
        if (videoView.isPlaying()){
            videoView.pause();
            progressBar.setVisibility(View.VISIBLE);
            tvDownloadRate.setVisibility(View.VISIBLE);
            tvLoadRate.setVisibility(View.VISIBLE);
            tvDownloadRate.setText("");
            tvLoadRate.setText("");
        }
    }

    private void endBuffer(){
        videoView.start();
        progressBar.setVisibility(View.GONE);
        tvDownloadRate.setVisibility(View.GONE);
        tvLoadRate.setVisibility(View.GONE);

    }
}
