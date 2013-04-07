package com.photohack.bythepeople;

import com.photohack.bythepeople.R;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ThankYouActivity extends Activity {
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	      super.onCreate(savedInstanceState);
	      setContentView(R.layout.thankyou);
	      
	      Button doneBtn = (Button)findViewById(R.id.done_btn);
	      doneBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				startLaunchAct();
			}
		});
	      Button updatesButton = (Button)findViewById(R.id.thu_thanks_button);
	      updatesButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/detectivebond007"));
				startActivity(browserIntent);
			}
		});
	      
	 }
	 
	 public void startLaunchAct(){
		 Intent i = new Intent(this, LaunchActivity.class);
		 startActivity(i);
		 
	 }
}
