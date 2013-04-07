package com.photohack.bythepeople;

import com.photohack.bythepeople.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NotesActivity extends Activity {

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      Intent intent = getIntent();
      final String videoUri;
      if (!intent.hasExtra("" + LaunchActivity.VIDEO)) {
        videoUri = null;
        Log.e(LaunchActivity.TAG, "Something is wrong, videoUri was not " +
              "passed to NotesActivity");
      } else {
        videoUri = intent.getStringExtra("" + LaunchActivity.VIDEO);
      }
      // TODO: pass a Report object which contains a note and a video.
      setContentView(R.layout.notes_activity);
      Button doneBtn  = (Button)findViewById(R.id.done);
      doneBtn.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
           EditText notes_edittext = (EditText)(findViewById(R.id.notes_edittext));
           String note = notes_edittext.getText().toString();
           submitNoteAndVideo(videoUri, note);
           startThankYouAction();
         }});
      Button cancelBtn  = (Button)findViewById(R.id.cancel);
      cancelBtn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            finish();
          }});
    }

    public void submitNoteAndVideo(String videoUri, String note) {
      // In weird case, videoUri might be null.
      Log.d(LaunchActivity.TAG, "videoUri is " + videoUri
            + " and note is " + note);
    }

    public void startThankYouAction(){
    	Intent doneIntent = new Intent(this,ThankYouActivity.class);
    	startActivity(doneIntent);
    }
}
