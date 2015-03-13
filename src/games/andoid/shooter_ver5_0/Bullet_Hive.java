package games.andoid.shooter_ver5_0;

public class Bullet_Hive {

	private static final int PLACEMENT_OFFSET = 50;
	private static final int HIVE_SPEED = 5;
	static int HIVE_OUTER_RADIUS = 130;
	static int HIVE_CENTER_X = 0;
	static int HIVE_CENTER_Y = HIVE_OUTER_RADIUS + PLACEMENT_OFFSET;
	private static boolean ChangeDirX = true, ChangeDirY = false;
	private static int Direction = 1;

	// - PLACEMENT_OFFSET;

	public Bullet_Hive() {
		// TODO Auto-generated constructor stub
	}

	public static int getCentX() {

		if (HIVE_CENTER_X + HIVE_OUTER_RADIUS / 2 + HIVE_SPEED > GameDrawer.Rbr_left) {

			ChangeDirX = true;
		} else if (HIVE_CENTER_X - HIVE_OUTER_RADIUS / 2 - HIVE_SPEED < GameDrawer.Lbr_right) {

			ChangeDirX = false;
		}

		if (ChangeDirX)
			Direction = -1;
		else
			Direction = 1;

		HIVE_CENTER_X += Direction * HIVE_SPEED;
		return HIVE_CENTER_X;
	}

	public static int getCentY() {

		if (HIVE_CENTER_Y + HIVE_OUTER_RADIUS / 2 + HIVE_SPEED > (GameDrawer.domeCenterY
				- GameController.INITIAL_HURDLE_OFFSET - PLACEMENT_OFFSET - 100)) {

			ChangeDirY = true;
		} else if (HIVE_CENTER_Y - HIVE_OUTER_RADIUS / 2 - HIVE_SPEED < GameDrawer.Tbr_bottom) {

			ChangeDirY = false;
		}

		if (ChangeDirY)
			Direction = -1;
		else
			Direction = 1;

		HIVE_CENTER_Y += Direction * HIVE_SPEED;
		return HIVE_CENTER_Y;
	}
}
