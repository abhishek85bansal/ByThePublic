package com.photohack.bythepeople;

import com.photohack.bythepeople.R;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;

import android.widget.Button;
import android.widget.Toast;


public class LaunchActivity extends Activity
{
    public static final int VIDEO = 0;
    public static final int PHOTO = 1;
    public static final int NOTES = 2;
    public static final String TAG = "ByThePublic";
    private Button captureButton;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_launch);
      captureButton = (Button)findViewById(R.id.record_btn);
      captureButton.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			TakeVideo();
			
		}
	});
//      GridView gridview = (GridView) findViewById(R.id.gridview);
//
//      final ImageAdapter imageAdapter = new ImageAdapter(this);
//      gridview.setAdapter(imageAdapter);
//      gridview.setOnItemClickListener(new OnItemClickListener() {
//          public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
//            int action = imageAdapter.getAction(position);
//            switch (action) {
//              case VIDEO: TakeVideo(); break;
//              case PHOTO: TakePhoto(); break;
//              case NOTES: TakeNotes(); break;
//            }
//          }
//      });
    }

    @Override
    protected void onActivityResult (int requestCode, int resultCode, Intent data) {
      if (resultCode != Activity.RESULT_CANCELED) {
        if (requestCode == VIDEO) {
          Uri videoUri = data.getData();
          Log.d(TAG, "videoUri is " + videoUri);
        	TakeNotes(videoUri.toString());
        }  else {
          Log.e(TAG, "Unknown request code: " + requestCode);
        }
      } else {
        Toast.makeText(LaunchActivity.this, "Action canceled", Toast.LENGTH_SHORT).show();
      }
    }

    private void TakeVideo() {
      if (!Utils.isIntentAvailable(this, MediaStore.ACTION_VIDEO_CAPTURE)) {
        Toast.makeText(LaunchActivity.this, "Video recording is not available on this device.",
            Toast.LENGTH_SHORT).show();
      } else {
        Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        startActivityForResult(takeVideoIntent, VIDEO);
      }
    }

    private void TakePhoto() {
      if (!Utils.isIntentAvailable(this, MediaStore.ACTION_IMAGE_CAPTURE)) {
          Toast.makeText(LaunchActivity.this, "Photo capturing is not available on this device",
            Toast.LENGTH_SHORT).show();
      } else {
        // Reference: http://developer.android.com/training/camera/photobasics.html
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(takePictureIntent, PHOTO);
      }
    }

    private void TakeNotes(String videoUri) {
      Intent i = new Intent(this, NotesActivity.class);
      i.putExtra("" + VIDEO, videoUri);
      startActivity(i);
      finish();
    }
}
