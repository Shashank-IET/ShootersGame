package games.andoid.shooter_ver5_0;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import games.andoid.shooter_ver6_0.R;

public class Help_Tab03 extends Fragment {

	static final int TAB_ID = 2;
	private static final String INSTRUCTIONS = "\nCONTROLS\n\nTouch or drag ur finger on the screen to generate 'bullets' in desired directions.\n"
			+ "The hurdles sole purpose there is to save hive by destroying the bullets"
			+ "\n\n\nSCORING\n\nTouching the hurdle decreases ur score by 2per bullet touched "
			+ "\nTouching hurdle also decreases 1 live per bullet touched. "
			+ "\n\nFinal score is ur achieved score summed up with five times the lives u possess at the time of exiting."
			+ "\n\nIf The bullet successfully makes upto hive u receive a 'SCOREBOOST'. The boost varies with ur current game level as "
			+ "\n20, 22, 25, 30, 40, 55, 75, 100, 130, 165 and then further constant."
			+ "\n\nAlso u r awarded 'BONUS LIVES' for clearing every level."
			+ "\n\nGame ends only if\n1.U run out of lives\n2.U press the back button"
			+ "\n\n HAPPY GAMING ;)\n\n\n";

	TextView HelpSpace;
	Button home;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		initialize();
	}

	private void initialize() {
		// TODO Auto-generated method stub

		HelpSpace = (TextView) getActivity().findViewById(R.id.HelpDescription);
		home = (Button) getActivity().findViewById(R.id.Home_button_helptab);

		Typeface helpContentFont = Typeface.createFromAsset(getActivity()
				.getAssets(), "fonts/FRABK.TTF");
		HelpSpace.setTypeface(helpContentFont);
		HelpSpace.setText(INSTRUCTIONS);

		home.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				GameHomeScreen.pager.setCurrentItem(Home_Tab01.TAB_ID);

			}
		});
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.help_tab, container, false);
	}
}
