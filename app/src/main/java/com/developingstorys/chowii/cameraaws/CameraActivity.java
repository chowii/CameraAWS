package com.developingstorys.chowii.cameraaws;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.developingstorys.chowii.cameraaws.dscustomview.DSToolbar;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.provider.MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE;
import static android.provider.MediaStore.Files.FileColumns.MEDIA_TYPE_VIDEO;


public class CameraActivity extends AppCompatActivity implements View.OnClickListener {

    private String TAG = this.getClass().getSimpleName();
    public final int CAMERA_REQUEST_CODE = 1234;
    public final int VIDEO_REQUEST_CODE = 4321;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Nullable
    @BindView(R.id.open_camera)
    TextView openCamera;

    @Nullable
    @BindView(R.id.imageView)
    ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        ButterKnife.bind(this);

        inflateToolbar();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

    }

    private int inflateToolbar() {
        return getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.ds_toolbar_container,new DSToolbar())
                .commit();
    }

    @OnClick(R.id.open_camera)
    public void onClick(View v) {

        int cameraPermissionCheck = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA);
        int audioPermissionCheck = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.RECORD_AUDIO);
        int granted = PackageManager.PERMISSION_GRANTED;
        if (!(cameraPermissionCheck == granted
                &&
                audioPermissionCheck == granted
        ))
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.CAMERA,
                    Manifest.permission.RECORD_AUDIO}, CAMERA_REQUEST_CODE);
        else sendCameraIntent();


    }

    private void sendCameraIntent(){
        new AlertDialog.Builder(this)
                .setTitle("Open Camera").setMessage("Please select either still or  video camera")
                .setPositiveButton("Still", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startCameraIntent(MediaStore.INTENT_ACTION_STILL_IMAGE_CAMERA, CAMERA_REQUEST_CODE);
                    }
                }).setNegativeButton("Video", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startCameraIntent(MediaStore.INTENT_ACTION_VIDEO_CAMERA, VIDEO_REQUEST_CODE);
            }
        }).show();
    }

    private void startCameraIntent(String camera, int code) {
        Intent cameraIntent = new Intent(camera);
        Uri photoUri = Uri.fromFile(getOutputMediaFile(MEDIA_TYPE_IMAGE, getApplicationContext()));
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
        if (cameraIntent.resolveActivity(getPackageManager()) != null)
            startActivityForResult(cameraIntent, code);
    }


    @Nullable
    private File getOutputMediaFile(int type, Context context) {
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES),
                context.getResources().getString(R.string.app_name));
        if (!mediaStorageDir.exists()) if (!mediaStorageDir.mkdirs()) {
            Log.d("MyCameraApp", "failed to create directory");
            return null;
        }
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        if (type == MEDIA_TYPE_IMAGE)
            return new File(mediaStorageDir.getPath() + File.separator +
                    "IMG_" + timeStamp + ".jpg");
        else if (type == MEDIA_TYPE_VIDEO)
            return new File(mediaStorageDir.getPath() + File.separator +
                    "VID_" + timeStamp + ".mp4");
        else return null;
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Camera Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}//end class

