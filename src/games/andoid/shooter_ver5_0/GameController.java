package games.andoid.shooter_ver5_0;

import java.util.Random;

import android.os.Vibrator;
import android.util.Log;

public class GameController {

	private static final int Y_PLACEMENT_OFFSET = 10;
	public static final int INITIAL_HURDLE_OFFSET = 300;
	public static final int LEVEL1_REWARD = 10;
	public static final int LEVEL2_REWARD = 10;
	public static final int LEVEL3_REWARD = 15;
	public static final int LEVEL4_REWARD = 15;
	public static final int LEVEL5_REWARD = 20;
	public static final int LEVEL6_REWARD = 20;
	public static final int LEVEL7_REWARD = 25;
	public static final int LEVEL8_REWARD = 25;
	public static final int LEVEL9_REWARD = 30;
	public static final int LEVEL10_REWARD = 30;
	public static final int LEVEL11_REWARD = 35;
	public static final int LEVEL12_REWARD = 35;
	public static final int LEVEL13_REWARD = 40;
	public static final int LEVEL14_REWARD = 50;

	public static final int MAX_HURDELS = 5;

	public static int SideBorderHeight = 750;
	public static int HIVE_INNER_RADIUS = 5;

	public static final int HURDLE_STARTING_COORDINATE = GameDrawer.domeCenterY
			- INITIAL_HURDLE_OFFSET;
	public static int previousHurdelcY = HURDLE_STARTING_COORDINATE;
	static int LEVEL = 1;
	static int HURDELS = 0;
	static int HurdelSpeed = 5;
	static boolean LEVEL_UPGRADED = false;

	private static int speedSeed = 5;
	public static int SCORE_BOOST = 20;
	public static int LIVES_BOOST = 10;

	public GameController() {

	}

	public static void GameScan() {

		if (HIVE_INNER_RADIUS >= Bullet_Hive.HIVE_OUTER_RADIUS) {
			LEVEL++;
			Log.d("Msg", "Level increased !");
			GameDataManager.Lives += LIVES_BOOST;
			HIVE_INNER_RADIUS = 5;
			LEVEL_UPGRADED = true;
			previousHurdelcY = HURDLE_STARTING_COORDINATE;

		} else
			LEVEL_UPGRADED = false;

	}

	public static int getHurdelCount() {

		switch (LEVEL) {

		case 1:
			HURDELS = 1;
			LIVES_BOOST = LEVEL1_REWARD;
			break;
		case 2:
			HURDELS = 1;
			SCORE_BOOST = SCORE_BOOST + 2;
			speedSeed = 10;
			LIVES_BOOST = LEVEL2_REWARD;
			break;
		case 3:
			HURDELS = 2;
			SCORE_BOOST = SCORE_BOOST + 3;
			speedSeed = 5;
			LIVES_BOOST = LEVEL3_REWARD;
			break;
		case 4:
			HURDELS = 2;
			SCORE_BOOST = SCORE_BOOST + 5;
			speedSeed = 10;
			LIVES_BOOST = LEVEL4_REWARD;
			break;
		case 5:
			HURDELS = 3;
			SCORE_BOOST = SCORE_BOOST + 10;
			speedSeed = 5;
			LIVES_BOOST = LEVEL5_REWARD;
			break;
		case 6:
			HURDELS = 3;
			SCORE_BOOST = SCORE_BOOST + 15;
			speedSeed = 10;
			LIVES_BOOST = LEVEL6_REWARD;
			break;
		case 7:
			HURDELS = 3;
			SCORE_BOOST = SCORE_BOOST + 20;
			speedSeed = 15;
			LIVES_BOOST = LEVEL7_REWARD;
			break;
		case 8:
			HURDELS = 4;
			SCORE_BOOST = SCORE_BOOST + 25;
			speedSeed = 5;
			LIVES_BOOST = LEVEL8_REWARD;
			break;
		case 9:
			HURDELS = 4;
			SCORE_BOOST = SCORE_BOOST + 30;
			speedSeed = 10;
			LIVES_BOOST = LEVEL9_REWARD;
			break;
		case 10:
			HURDELS = 4;
			SCORE_BOOST = SCORE_BOOST + 35;
			speedSeed = 15;
			LIVES_BOOST = LEVEL10_REWARD;
			break;
		case 11:
			HURDELS = 4;
			speedSeed = 20;
			LIVES_BOOST = LEVEL11_REWARD;
			break;
		case 12:
			HURDELS = MAX_HURDELS;
			speedSeed = 5;
			LIVES_BOOST = LEVEL12_REWARD;
			break;
		case 13:
			HURDELS = MAX_HURDELS;
			speedSeed = 10;
			LIVES_BOOST = LEVEL13_REWARD;
			break;
		case 14:
			HURDELS = MAX_HURDELS;
			speedSeed = 15;
			LIVES_BOOST = LEVEL14_REWARD;
			break;
		case 15:
			HURDELS = MAX_HURDELS;
			speedSeed = 20;
			break;
		default:
			HURDELS = MAX_HURDELS;
			speedSeed = 30;
		}

		return HURDELS;

	}

	public static int getHurdleSpeeds() {

		Random rand = new Random();
		int speed = HurdelSpeed + rand.nextInt(speedSeed);
		return speed;
	}

	public static int getHurdelCenter_X() {

		Random rand = new Random();
		int cX = Hurdels.HurdelWidth / 2
				+ rand.nextInt(GameDrawer.Rbr_left - Hurdels.HurdelWidth / 2);

		return cX;
	}

	public static int getHurdelCenter_Y() {

		int cY = (previousHurdelcY + Hurdels.HurdelHeight + Y_PLACEMENT_OFFSET);
		previousHurdelcY = cY;

		return cY;
	}
}
