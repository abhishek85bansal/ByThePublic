package com.photohack.bythepeople;

import com.photohack.bythepeople.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NotesActivity extends Activity {

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      // TODO: pass a Report object which might contain Photo/Video.
      setContentView(R.layout.notes_activity);
      Button doneBtn  = (Button)findViewById(R.id.done);
      doneBtn.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			startThankYouAct();
			
		}
	});
      Button cancelBtn  = (Button)findViewById(R.id.cancel);
      cancelBtn.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			finish();
			
		}
	});
    
    }
    public void startThankYouAct(){
    	Intent doneIntent = new Intent(this,ThankYouActivity.class);
    	startActivity(doneIntent);
    }
}