package games.andoid.shooter_ver5_0;

import android.graphics.Point;

public class Hurdels {

	static int HurdelWidth = 150, HurdelHeight = 15;

	int pixelSpeed = 0;
	int Direction = 1;
	int saveY_Position = 0;

	public Point hurdelCentroid = null;

	private boolean ChangeDir = false;

	public Hurdels(int speed, int xCenter, int yCenter) {

		hurdelCentroid = new Point(xCenter, yCenter);
		pixelSpeed = speed;

		saveY_Position = yCenter;
	}

	public void HURDEL_MOVER() {

		hurdelCentroid.x = getCentroid_X();
	}

	private int getCentroid_X() {

		if (hurdelCentroid.x + HurdelWidth / 2 > GameDrawer.Rbr_left) {

			ChangeDir = true;
		} else if (hurdelCentroid.x - HurdelWidth / 2 < GameDrawer.Lbr_right) {

			ChangeDir = false;
		}

		if (ChangeDir)
			Direction = -1;
		else
			Direction = 1;

		hurdelCentroid.x += Direction * pixelSpeed;
		return hurdelCentroid.x;
	}

	public int getTOP() {

		int top = hurdelCentroid.y - HurdelHeight / 2;
		return top;
	}

	public int getLEFT() {

		int left = hurdelCentroid.x - HurdelWidth / 2;
		return left;
	}

	public int getRIGHT() {

		int right = hurdelCentroid.x + HurdelWidth / 2;
		return right;
	}

	public int getBOTTOM() {

		int bottom = hurdelCentroid.y + HurdelHeight / 2;
		return bottom;
	}
}
