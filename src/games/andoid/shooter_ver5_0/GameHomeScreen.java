package games.andoid.shooter_ver5_0;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.Toast;
import games.andoid.shooter_ver6_0.R;

/**
 * @author Shashank Mishra
 * 
 */
public class GameHomeScreen extends FragmentActivity {

	static ViewPager pager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home_screen);

		getActionBar().hide();

		pager = (ViewPager) findViewById(R.id.homepage);
		FragmentManager mgr = getSupportFragmentManager();
		pager.setAdapter(new MAdapter(mgr, getApplicationContext()));
	}

	@SuppressWarnings("deprecation")
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub

		if (pager.getCurrentItem() != 0)
			pager.setCurrentItem(Home_Tab01.TAB_ID);
		else
			showDialog(0);
	}

	protected Dialog onCreateDialog(int id) {
		// TODO Auto-generated method stub
		if (id == 0) {
			return new AlertDialog.Builder(this,
					android.R.style.Theme_DeviceDefault_Dialog)
					.setTitle("Exit ")
					.setMessage("Sure to Exit ?")
					.setPositiveButton("Sure",
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface arg0,
										int arg1) {
									// TODO Auto-generated method stub
									System.exit(1001);
								}
							})
					.setNegativeButton("Cancel",
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface arg0,
										int arg1) {
									// TODO Auto-generated method stub
									Toast.makeText(getApplicationContext(),
											"Cancelled !", Toast.LENGTH_SHORT)
											.show();
								}
							}).create();
		}
		return null;
	}

}

class MAdapter extends FragmentPagerAdapter {

	Context context;

	public MAdapter(FragmentManager fm, Context context) {

		super(fm);
		this.context = context;
	}

	@Override
	public Fragment getItem(int pos) {

		Fragment displayPage = null;

		switch (pos) {

		case Home_Tab01.TAB_ID:
			displayPage = new Home_Tab01();
			break;

		case Play_Customisation_Tab02.TAB_ID:
			displayPage = new Play_Customisation_Tab02();
			break;

		case Help_Tab03.TAB_ID:
			displayPage = new Help_Tab03();
			break;

		case Scores_Tab04.TAB_ID:
			displayPage = new Scores_Tab04();
			break;

		default:
			displayPage = new Home_Tab01();
		}
		return displayPage;
	}

	@Override
	public int getCount() {

		return 4;
	}

}
