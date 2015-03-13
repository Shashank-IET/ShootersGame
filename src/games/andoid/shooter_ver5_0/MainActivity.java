package games.andoid.shooter_ver5_0;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import games.andoid.shooter_ver6_0.R;

public class MainActivity extends Activity implements OnTouchListener {

	static int lives, level, score;
	FrameLayout GameScreen;
	private GameDrawer drawer;
	static int touchCounter = 0;
	static ImageView LevelImg;
	static TextView Level, Points, Lives;
	private static boolean LevelUpdateRequested = false;
	public static boolean TouchedOnce = false;

	private long currentTime;
	private long pastTime = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.game_pad);

		initialize();
		getActionBar().hide();

	}

	private void initialize() {

		drawer = new GameDrawer(getApplicationContext(), null);
		drawer = (GameDrawer) findViewById(R.id.gameView);

		Typeface typeface_For_Level = Typeface.createFromAsset(getAssets(),
				"fonts/BRLNSR.TTF");
		Typeface typeface_For_LivesNPoints = Typeface.createFromAsset(
				getAssets(), "fonts/CORBEL.TTF");

		GameScreen = (FrameLayout) findViewById(R.id.container);
		Level = (TextView) findViewById(R.id.LevelTAG_);
		Level.setTypeface(typeface_For_Level);

		Points = (TextView) findViewById(R.id.PointsLABEL_);
		Points.setTypeface(typeface_For_LivesNPoints);

		Lives = (TextView) findViewById(R.id.LivesLABEL_);
		Lives.setTypeface(typeface_For_LivesNPoints);

		// LevelImg = (ImageView) findViewById(R.id.LevelImage);

		GameScreen.setOnTouchListener(this);
	}

	public void Kill() {
		Intent intent = new Intent(getApplicationContext(),
				GameOverActivity.class);
		startActivity(intent);
		finish();
	}

	public void CallLevelDialog() {
		LevelUpgradeFragment fragment = new LevelUpgradeFragment();
		fragment.show(getFragmentManager(), "Upgrade");
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub

		OnIngameBackPressDialogFragment dialogFragment = new OnIngameBackPressDialogFragment();
		dialogFragment.show(getFragmentManager(), "GameExit");

	}

	@Override
	public boolean onTouch(View arg0, MotionEvent touch) {

		if (LevelUpdateRequested) {

			CallLevelDialog();
			LevelUpdateRequested = false;

		}

		try {
			int x = (int) touch.getX();
			int y = (int) touch.getY();

			currentTime = System.currentTimeMillis();
			if (currentTime - pastTime > 50)
				if (y < GameController.SideBorderHeight - 10) {

					TouchedOnce = true;

					if (touch.getAction() == MotionEvent.ACTION_DOWN
							|| touch.getAction() == MotionEvent.ACTION_MOVE) {

						drawer.myNewBulletTarget(x, y);
						GameDrawer.RotateTo(x, y);
						pastTime = currentTime;

					}
				} else
					Log.d("Msg", "Blocked !!");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;
	}

	public static void Inform() {

		LevelUpdateRequested = true;
	}
}
