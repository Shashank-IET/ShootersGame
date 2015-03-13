package games.andoid.shooter_ver5_0;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;
import games.andoid.shooter_ver6_0.R;

public class Scores_Tab04 extends Fragment {

	static final int TAB_ID = 3;
	static final int RANKS = 20;

	TextView NameHeading, SCoreHeading;
	static TextView[] rows = new TextView[RANKS];

	// t_11, t_12, t_21, t_22, t_31, t_32, t_41, t_42, t_51, t_52,
	// t_61, t_62, t_71, t_72, t_81, t_82, t_91, t_92, t_01, t_02;
	TableLayout scoreCard;
	Button home;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		initialize();
		GameDataManager.SetScores(getActivity());
	}

	private void initialize() {
		// TODO Auto-generated method stub
		scoreCard = (TableLayout) getActivity().findViewById(R.id.SoreCard_);

		home = (Button) getActivity().findViewById(R.id.home_button_scoretab);
		NameHeading = (TextView) getActivity().findViewById(
				R.id.name_title_scoretab);
		SCoreHeading = (TextView) getActivity().findViewById(
				R.id.score_title_scoretab);
		setTextViews();

		Typeface contentFont = Typeface.createFromAsset(getActivity()
				.getAssets(), "fonts/CORBELB.TTF");

		for (int n = 0; n < RANKS; n++)
			rows[n].setTypeface(contentFont);

		Typeface titleFont = Typeface.createFromAsset(
				getActivity().getAssets(), "fonts/AMERSN_.TTF");

		NameHeading.setTypeface(titleFont);
		SCoreHeading.setTypeface(titleFont);
		home.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				GameHomeScreen.pager.setCurrentItem(Home_Tab01.TAB_ID);
			}
		});
	}

	private void setTextViews() {
		// TODO Auto-generated method stub

		rows[18] = (TextView) getActivity().findViewById(R.id.textView19);
		rows[19] = (TextView) getActivity().findViewById(R.id.textView20);
		rows[0] = (TextView) getActivity().findViewById(R.id.textView1);
		rows[1] = (TextView) getActivity().findViewById(R.id.textView2);
		rows[2] = (TextView) getActivity().findViewById(R.id.textView3);
		rows[3] = (TextView) getActivity().findViewById(R.id.textView4);
		rows[4] = (TextView) getActivity().findViewById(R.id.textView5);
		rows[5] = (TextView) getActivity().findViewById(R.id.textView6);
		rows[6] = (TextView) getActivity().findViewById(R.id.textView7);
		rows[7] = (TextView) getActivity().findViewById(R.id.textView8);
		rows[8] = (TextView) getActivity().findViewById(R.id.textView9);
		rows[9] = (TextView) getActivity().findViewById(R.id.textView10);
		rows[10] = (TextView) getActivity().findViewById(R.id.textView11);
		rows[11] = (TextView) getActivity().findViewById(R.id.textView12);
		rows[12] = (TextView) getActivity().findViewById(R.id.textView13);
		rows[13] = (TextView) getActivity().findViewById(R.id.textView14);
		rows[14] = (TextView) getActivity().findViewById(R.id.textView15);
		rows[15] = (TextView) getActivity().findViewById(R.id.textView16);
		rows[16] = (TextView) getActivity().findViewById(R.id.textView17);
		rows[17] = (TextView) getActivity().findViewById(R.id.textView18);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.high_scores_tab, container, false);
	}
}
