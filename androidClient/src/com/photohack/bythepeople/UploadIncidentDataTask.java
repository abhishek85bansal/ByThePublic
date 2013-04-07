package com.photohack.bythepeople;

import android.os.AsyncTask;
import android.util.Log;

class UploadIncidentDataTask extends AsyncTask<IncidentData, Boolean, Boolean> {
   protected Boolean doInBackground(IncidentData... incidentDatas) {
     // We will have only one path.
     int count = incidentDatas.length;
     boolean success = false;
     if (count != 1) {
       Log.wtf(LaunchActivity.TAG, "count should have been 1");
     }
     for (int i = 0; i < count; i++) {
       success = Utils.uploadReport(incidentDatas[i]);
       publishProgress(true);
     }
     return success;
   }
 }
 
