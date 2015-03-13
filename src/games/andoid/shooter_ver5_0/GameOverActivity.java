package games.andoid.shooter_ver5_0;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import games.andoid.shooter_ver6_0.R;

public class GameOverActivity extends Activity {

	Button HOME;
	TextView ScoreView, LivesView;
	final String SCORE_STATEMENT = "SCORE: ";
	Handler handle = new Handler();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.score_card_dialog_base);

		getActionBar().hide();

		ScoreView = (TextView) findViewById(R.id.Score_);
		LivesView = (TextView) findViewById(R.id.Lives_);

		Typeface typeface = Typeface.createFromAsset(getAssets(),
				"fonts/AMERSN_.TTF");
		ScoreView.setTypeface(typeface);
		LivesView.setTypeface(typeface);

		ScoreView.setText(SCORE_STATEMENT + GameDataManager.PlayerScore);
		LivesView.setText("LEVEL: " + GameController.LEVEL);

		HOME = (Button) findViewById(R.id.home_button_scoreCard);
		HOME.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(),
						GameHomeScreen.class);
				GameDataManager.ResetNecessary();
				startActivity(intent);
				finish();
			}

		});
	}
}
