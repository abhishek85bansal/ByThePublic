package com.photohack.bythepeople;

import android.os.AsyncTask;
import android.util.Log;

class UploadVideoTask extends AsyncTask<String, Boolean, String> {
   protected String doInBackground(String... paths) {
     // We will have only one path.
     int count = paths.length;
     Log.d(LaunchActivity.TAG, "ASHISH");
     String uri = null;
     if (count != 1) {
       Log.wtf(LaunchActivity.TAG, "count should have been 1");
     }
     for (int i = 0; i < count; i++) {
       uri = Utils.uploadVideo(paths[i]);
       publishProgress(true);
     }
     return uri;
   }
 }
 
