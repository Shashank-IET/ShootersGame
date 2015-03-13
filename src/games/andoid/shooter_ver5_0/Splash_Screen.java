package games.andoid.shooter_ver5_0;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import games.andoid.shooter_ver6_0.R;

public class Splash_Screen extends Activity {

	Button PROCEED;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		ActionBar bar = getActionBar();
		bar.hide();
		setContentView(R.layout.splash_screen);

		Thread thread = new Thread() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					Intent intent = new Intent(getApplicationContext(),
							GameHomeScreen.class);
					startActivity(intent);
					finish();
				}
			}
		};
		thread.start();

	}

}
