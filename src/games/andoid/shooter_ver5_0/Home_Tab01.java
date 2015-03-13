package games.andoid.shooter_ver5_0;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import games.andoid.shooter_ver6_0.R;

public class Home_Tab01 extends Fragment {

	static final int TAB_ID = 0;
	Button PLAY_, SCORE_, HELP_, EXIT_;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		initialize();
	}

	private void initialize() {
		// TODO Auto-generated method stub

		PLAY_ = (Button) getActivity().findViewById(R.id.PlayButton_);
		PLAY_.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				GameHomeScreen.pager
						.setCurrentItem(Play_Customisation_Tab02.TAB_ID);
			}
		});

		SCORE_ = (Button) getActivity().findViewById(R.id.ScoreButton_);
		SCORE_.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				GameHomeScreen.pager.setCurrentItem(Scores_Tab04.TAB_ID);
			}
		});

		HELP_ = (Button) getActivity().findViewById(R.id.HelpButton_);
		HELP_.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				GameHomeScreen.pager.setCurrentItem(Help_Tab03.TAB_ID);
			}
		});

		EXIT_ = (Button) getActivity().findViewById(R.id.ExitButton_);
		EXIT_.setOnClickListener(new View.OnClickListener() {

			@SuppressWarnings("deprecation")
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				getActivity().showDialog(0);
			}
		});
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.home_options_tab, container, false);
	}

}
