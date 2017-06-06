package com.callanna.rankmusic.service;

import android.media.MediaPlayer;
import android.util.Log;
import android.widget.SeekBar;

import com.callanna.rankmusic.App;

import java.io.IOException;
import java.util.TimerTask;

/**
 * Created by Callanna on 2017/6/5.
 */

public class TestApp {
    private TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {

        }
    };
    MediaPlayer mediaplayer = new MediaPlayer();
    public TestApp(){

    }
    public void play() throws IOException {
        mediaplayer.setDataSource("");
        mediaplayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                Log.d("duanyl","what:"+what);
                return false;
            }
        });
        mediaplayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {

            }
        });
        mediaplayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {

            }
        });

        SeekBar seekBar = new SeekBar(App.instance);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}