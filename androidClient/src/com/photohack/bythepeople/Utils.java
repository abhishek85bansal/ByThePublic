package com.photohack.bythepeople;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.HttpResponse;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.json.JSONException;
import org.json.JSONObject;

public final class Utils {

  private static final String AWS_INCIDENT_UPLOAD_URL =
      "http://ec2-54-224-126-216.compute-1.amazonaws.com:8080/servicearchitecture/rest/incident/new";

  public static boolean isIntentAvailable(Context context, String action) {
    final PackageManager packageManager = context.getPackageManager();
    final Intent intent = new Intent(action);
    List<ResolveInfo> list = packageManager.queryIntentActivities(intent,
        PackageManager.MATCH_DEFAULT_ONLY);
    return list.size() > 0;
  }

  public static boolean uploadReport(IncidentData incidentData,
      String videoUri /* uri of video on file hosting service */) {
    /*
       A sample curl command which works.
       curl -H "Content-Type: application/json" -X POST -d '{"url": "google.com", "longitude": "1", "latitude": "2", "description": "testing"}' http://ec2-54-224-126-216.compute-1.amazonaws.com:8080/servicearchitecture/rest/incident/new
     */
    HttpClient httpClient = new DefaultHttpClient();
    HttpPost httppost = new HttpPost(AWS_INCIDENT_UPLOAD_URL);
    JSONObject json = new JSONObject();
    try {
      json.put("url", videoUri);
      json.put("longitude", incidentData.getLongitude());
      json.put("latitude", "" + incidentData.getLatitude());
      json.put("description", incidentData.getNote());
      StringEntity se = new StringEntity( json.toString());  
      se.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
      httppost.setEntity(se);
      HttpResponse response = httpClient.execute(httppost);
      /*Checking response */
      if(response==null){
        return false;
      }
      int statusCode = response.getStatusLine().getStatusCode();
      if (statusCode == 200) {
        return true;
      } else {
        Log.e(LaunchActivity.TAG, "Response code for incident upload is " + statusCode);
        return false;
      }
    } catch (ClientProtocolException e) {
      e.printStackTrace();
      return false;
    } catch (IOException e) {
      e.printStackTrace();
      return false;
    } catch (JSONException e) {
      e.printStackTrace();
      return false;
    }
  }

}
