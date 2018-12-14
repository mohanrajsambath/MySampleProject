package com.innobot.sample;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

/**
 * Created by innobot-linux-7 on 13/6/17.
 */

public class Search_activity extends AppCompatActivity {


    String[] myBooks = {"English", "Tamil", "Maths", "Chemistry"};
    public static Button btn_videorecord;

    VideoView vVideo_view;

    private Uri fileUri;
    public static final int MEDIA_TYPE_VIDEO = 2;
    private static final int CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE = 200;
    public static Search_activity ActivityContext = null;
    private Button btn_playIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        ActivityContext = this;


        initView();
        //handleIntent(getIntent());


    }

    private void initView() {
        btn_videorecord = (Button) findViewById(R.id.btn_videorecord);
        vVideo_view = (VideoView) this.findViewById(R.id.vVideo_view);
        btn_playIcon = (Button) findViewById(R.id.btn_playIcon);

        btn_videorecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent captureVideoIntent = new Intent(android.provider.MediaStore.ACTION_VIDEO_CAPTURE);
                startActivityForResult(captureVideoIntent, CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE);


            }
        });

        btn_playIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vVideo_view.setVideoURI(fileUri);
                vVideo_view.start();
            }
        });


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE) {
            fileUri = data.getData();
            // playVideoButton.setEnabled(true);
        }

    }


    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.menu_search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));

        return true;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
    }


    void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            //use the query to search
            System.out.println("Search String" + query);
        }
    }
}
