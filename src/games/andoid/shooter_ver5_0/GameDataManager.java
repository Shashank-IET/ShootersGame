package games.andoid.shooter_ver5_0;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

public class GameDataManager {

	public static final int INITIAL_LIVES = 50;
	public static String PlayerName;
	public static int PlayerScore = 0;
	public static int Lives = INITIAL_LIVES;

	static DatabaseAdapter adapter;

	public GameDataManager(Context c) {

		SetScores(c);
	}

	public static void ResetNecessary() {

		Lives = INITIAL_LIVES;
		PlayerScore = 0;
		GameController.HIVE_INNER_RADIUS = 5;
		GameController.LEVEL = 1;
		GameController.previousHurdelcY = GameController.HURDLE_STARTING_COORDINATE;
		GameController.SCORE_BOOST = 20;
	}

	public static void ResetValuesHere() {
		// TODO Auto-generated method stub
		MainActivity.level = GameController.LEVEL;
		MainActivity.lives = Lives;
		MainActivity.score = PlayerScore;
	}

	public static boolean SetScores(Context c) {

		adapter = new DatabaseAdapter(c);
		Cursor data = adapter.getAllData();

		int totalCount = data.getCount();
		int k = 2;
		boolean result = false;

		if (data != null) {

			result = true;
			while (data.moveToNext() && k < Scores_Tab04.RANKS) {

				Scores_Tab04.rows[k].setText(data.getString(1).toUpperCase());
				Scores_Tab04.rows[k + 1].setText((data.getInt(2)) + "");

				k += 2;
			}
		}

		// while (Scores_Tab04.RANKS - k - 1 >= 0) {
		//
		// Log.d("Msg", "k =" + k);
		// Scores_Tab04.rows[k++].setText(" ... ");
		// }

		return result;
	}

	public static boolean SaveScore(Context context) {

		adapter = new DatabaseAdapter(context);
		boolean result = adapter.addPayerScore(PlayerName, PlayerScore);

		return result;

	}
}
