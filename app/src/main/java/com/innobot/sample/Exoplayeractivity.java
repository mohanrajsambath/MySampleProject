package com.innobot.sample;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import java.io.File;

/**
 * Created by innobot-linux-7 on 19/6/17.
 */

public class Exoplayeractivity extends AppCompatActivity {

    private DefaultBandwidthMeter bandwidthMeter;
    private DefaultTrackSelector trackSelector;
    private SimpleExoPlayer player;
    private DefaultExtractorsFactory extractorsFactory;
    private SimpleExoPlayerView videoview;
    TextView txtV_fileStatus;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview_exoplayer);
        initialiseExo();
        File dir = Environment.getExternalStorageDirectory();
        //Get the text file
        File file = new File(dir,"/Cyranofflinevideos/sample.mp4");
        // i have kept text.txt in the sd-card

        if(file.exists())   // check if file exist
        {
            txtV_fileStatus.setVisibility(View.GONE);


            //Read text from file
            StringBuilder text = new StringBuilder();

            try {
              //  BufferedReader br = new BufferedReader(new FileReader(file));
            //    String line;

                String urimp4 = "/Clips/Videoos/big_buck_bunny.mp4";
                Uri mp4VideoUri = Uri.parse(Environment.getExternalStorageDirectory().getAbsolutePath()+urimp4);

                DefaultBandwidthMeter bandwidthMeterA = new DefaultBandwidthMeter();
                DefaultDataSourceFactory dataSourceFactory = new DefaultDataSourceFactory(this, Util.getUserAgent(this, "Exoplayeractivity"), bandwidthMeterA);


                String videoResource = file.getPath();
                System.out.println("File name"+ mp4VideoUri);
                MediaSource videoSource = new ExtractorMediaSource(mp4VideoUri, dataSourceFactory, extractorsFactory, null, null);
               // final LoopingMediaSource loopingSource = new LoopingMediaSource(videoSource);
                player.prepare(videoSource);
                videoview.setPlayer(player);






              //  while ((line = br.readLine()) != null) {
                //    text.append(line);
                    text.append('n');
               // }
            }
            catch (Exception e) {
                //You'll need to add proper error handling here
            }
            //Set the text
            txtV_fileStatus.setText(text);
        }
        else
        {
            txtV_fileStatus.setText("Sorry file doesn't exist!!");
        }

    }



    private void initialiseExo() {


        txtV_fileStatus = (TextView)findViewById(R.id.txtV_fileStatus);
        bandwidthMeter = new DefaultBandwidthMeter();
      //  mediaDataSourceFactory = new DefaultDataSourceFactory(this, Util.getUserAgent(this, "mediaPlayerSample"), (TransferListener<? super DataSource>) bandwidthMeter);
      //  window = new Timeline.Window();
        videoview = (SimpleExoPlayerView) findViewById(R.id.videoview);
        videoview.setVisibility(View.VISIBLE);
        TrackSelection.Factory videoTrackSelectionFactory =
                new AdaptiveTrackSelection.Factory(bandwidthMeter);
        trackSelector = new DefaultTrackSelector(videoTrackSelectionFactory);
        player = ExoPlayerFactory.newSimpleInstance(this, trackSelector, new DefaultLoadControl());
        videoview.setPlayer(player);
        player.setPlayWhenReady(true);
        extractorsFactory = new DefaultExtractorsFactory();
        player.addListener(new ExoPlayer.EventListener() {
            @Override
            public void onTimelineChanged(Timeline timeline, Object manifest) {


            }

            @Override
            public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {

            }

            @Override
            public void onLoadingChanged(boolean isLoading) {

            }

            @Override
            public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
//                System.out.println("Play ended " + playbackState);
                if (playbackState == ExoPlayer.STATE_ENDED) {
                    if (player != null) {
                        player.seekTo(0);
                        player.setPlayWhenReady(false);
                    }
                }
            }

            @Override
            public void onPlayerError(ExoPlaybackException error) {

            }

            @Override
            public void onPositionDiscontinuity() {

            }

        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        if (player != null) {
            player.setPlayWhenReady(true);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (player != null) {
            player.setPlayWhenReady(false);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        releasePlayer();
        finish();
    }

    private void releasePlayer() {
        if (player != null) {
            player.release();
        }
    }

}
