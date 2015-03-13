package games.andoid.shooter_ver5_0;

import java.util.Random;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import games.andoid.shooter_ver6_0.R;

public class Play_Customisation_Tab02 extends Fragment {

	static final int TAB_ID = 1;
	private static final int TOTAL_TIPS = 3;
	Button home, GameStarter;
	EditText PlayerName, Level;
	TextView playerLabel, levelLabel, Tip;
	private String[] Tips = {
			"Try targetting using side-walls, they can reflect the bullets",
			"Dragging your finger on the screen will create continuous bullets in same direction. ",
			"Each life accounts for 5 points in the final sore, try saving more lives." };

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		initialize();
	}

	private void initialize() {
		// TODO Auto-generated method stub

		home = (Button) getActivity().findViewById(R.id.home_button_setuptab);
		playerLabel = (TextView) getActivity().findViewById(R.id.player_tag);
		levelLabel = (TextView) getActivity().findViewById(R.id.level_tag);
		PlayerName = (EditText) getActivity().findViewById(R.id.Pname);
		Level = (EditText) getActivity().findViewById(R.id.gameLevel);
		Tip = (TextView) getActivity().findViewById(R.id.Hint_setup);

		GameStarter = (Button) getActivity().findViewById(
				R.id.startBUTTON_setup_);

		home.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				GameHomeScreen.pager.setCurrentItem(Home_Tab01.TAB_ID);
			}
		});

		GameStarter.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				int l;
				if (!Level.getText().toString().isEmpty()) {
					l = Integer.parseInt(Level.getText().toString());
					Log.d("Msg", "non null!! " + l);
				} else
					l = 1;

				String name;
				if (!PlayerName.getText().toString().isEmpty()) {

					name = PlayerName.getText().toString();
				} else {
					name = "Player 1";

				}

				GameDataManager.PlayerName = name;
				GameController.LEVEL = l;

				Intent intent = new Intent(getActivity(), MainActivity.class);
				startActivity(intent);
				getActivity().finish();
			}
		});

		Typeface labelFont = Typeface.createFromAsset(
				getActivity().getAssets(), "fonts/AMERSN_.TTF");
		playerLabel.setTypeface(labelFont);
		levelLabel.setTypeface(labelFont);

		Typeface contentFont = Typeface.createFromAsset(getActivity()
				.getAssets(), "fonts/CORBEL.TTF");
		Level.setTypeface(contentFont);
		PlayerName.setTypeface(contentFont);

		Random r = new Random();
		int k = r.nextInt(3);
		Log.d("Msg", "k:" + k);
		Tip.setText("Tip: " + Tips[k]);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.play_customisation_tab, container,
				false);
	}
}
