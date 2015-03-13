package games.andoid.shooter_ver5_0;

import android.graphics.Point;
import android.util.Log;

public class Bullets {

	int XCoordArray[], YCoordArray[];
	double xC, yC, x, y;
	double reflect_x1, reflect_y1, reflect_x2, reflect_y2;
	double slopeM = 0, lineConst_C = 0;
	double initialSlope;
	static final int Rough_IncidentLineY_CoordDifferencing = 30;
	private static final double TANGENT_89 = 5729.577893;
	Point pos;
	int coordReader = 0, _K_ = 0;

	public Bullets(int fx, int fy) {

		xC = GameDrawer.domeCenterX;
		yC = GameDrawer.domeCenterY;
		x = fx;
		y = fy;

		pos = new Point((int) xC, (int) yC); // Dummy Statement
												// not much of use
		initialSlope = getSlope(xC, -yC, x, -y);
		XCoordArray = new int[100000];
		YCoordArray = new int[100000];

		try {
			ProcessThePoints(xC, -yC, x, -y);
		} catch (StackOverflowError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void ProcessThePoints(double x1, double y1, double x2, double y2) {

		double X, Y;

		slopeM = getSlope(x1, y1, x2, y2);
		lineConst_C = y1 - (slopeM * x1); // Much like c = y - m.x line equation

		if (slopeM == TANGENT_89)
			return;

		if (x2 > x1)
			X = GameDrawer.Rbr_right - GameDrawer.BULLET_RADIUS - 5; // This is
																		// actually
																		// the
																		// Canvas
																		// Width
		// or say limiting X - axis
		// coordinate
		// on Right side of screen
		else
			X = GameDrawer.BULLET_RADIUS + 5; // i.e. the initial XCoordArray -
												// axis
												// value or say limiting
		// XCoordArray - axis
		// coordinate on Left side of screen

		Y = slopeM * X + lineConst_C;

		// Log.d("Msg", "For that Infinity Case: slope: " + slopeM + ", C= "
		// + lineConst_C + ", Extra Polated X: " + X + ", Y: " + Y);

		if (Y > 0) { // This is the terminating condition for our recursion

			Y = 0;
			if (slopeM != 0)
				X = (Y - lineConst_C) / slopeM;
			else
				X = x1;

			try {
				MakePath(x1, y1, X, Y);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;

		} else if (Y <= 0) {

			try {
				MakePath(x1, y1, X, Y);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ComputeReflectedCoordinates(X, Y);
			ProcessThePoints(reflect_x1, reflect_y1, reflect_x2, reflect_y2);
		}

	}

	private double getSlope(double x1, double y1, double x2, double y2) {

		double M;

		if ((x2 - x1) != 0)
			M = (y2 - y1) / (x2 - x1);
		else {
			M = TANGENT_89;
			Log.d("Msg", "Got Infinity case !!!");
		}
		return M;
	}

	private void ComputeReflectedCoordinates(double X, double Y) {

		double yP = (-1) * YCoordArray[Rough_IncidentLineY_CoordDifferencing];
		double xP = XCoordArray[Rough_IncidentLineY_CoordDifferencing];

		reflect_x1 = X;
		reflect_y1 = Y;

		double diff = Y - yP;

		reflect_y2 = Y + diff;
		reflect_x2 = xP;

		// Log.d("Msg", "refX1:" + reflect_x1 + " & refY1:" + reflect_y1
		// + "\nrefX2:" + reflect_x2 + " & refY2:" + reflect_y2);

	}

	public void MakePath(double x1, double y1, double x2, double y2) {

		double xDiff = 0.0, yDiff = 0.0;
		double yPix, xPix;
		int smaller, bigger;

		xDiff = x2 - x1;
		yDiff = y2 - y1;

		if (xDiff > yDiff) {

			smaller = (int) (x2 > x1 ? x1 : x2);
			bigger = (int) (x2 > x1 ? x2 : x1);

			if (smaller == (int) x1)
				for (int i = smaller; i <= bigger; i++) {

					yPix = slopeM * i + lineConst_C;
					XCoordArray[coordReader] = Math.abs(i);
					YCoordArray[coordReader] = (int) Math.abs(yPix);
					// Log.d("Msg", "X:" + XCoordArray[coordReader] + " & Y:"
					// + YCoordArray[coordReader]);
					coordReader++;
				}
			else if (bigger == (int) x1)
				for (int i = bigger; i >= smaller; i--) {

					yPix = slopeM * i + lineConst_C;
					XCoordArray[coordReader] = Math.abs(i);
					YCoordArray[coordReader] = (int) Math.abs(yPix);
					// Log.d("Msg", "X:" + XCoordArray[coordReader] + " & Y:"
					// + YCoordArray[coordReader]);
					coordReader++;
				}

		} else if (xDiff <= yDiff) {
			smaller = (int) (y2 > y1 ? y1 : y2);
			bigger = (int) (y2 > y1 ? y2 : y1);

			if (smaller == (int) y1)
				for (int i = smaller; i <= bigger; i++) {

					xPix = (i - lineConst_C) / slopeM;
					XCoordArray[coordReader] = (int) Math.abs(xPix);
					YCoordArray[coordReader] = Math.abs(i);
					coordReader++;
				}
			else if (bigger == (int) y1)
				for (int i = bigger; i >= smaller; i--) {

					xPix = (i - lineConst_C) / slopeM;
					XCoordArray[coordReader] = (int) Math.abs(xPix);
					YCoordArray[coordReader] = Math.abs(i);
					coordReader++;
				}
		}

	}

	public int getCoorReader() {
		// TODO Auto-generated method stub
		return coordReader;
	}

	public int nextX() {

		if (_K_ <= coordReader)
			return XCoordArray[_K_];
		return -127;
	}

	public int nextY() {

		if ((_K_ += 10) <= coordReader)
			return YCoordArray[_K_];
		else
			_K_ = 0;
		return -127;
	}

	public void moveBullet() {

		pos.x = nextX();
		pos.y = nextY();

	}

	public int getX() {
		return pos.x;
	}

	public int getY() {
		return pos.y;
	}
}
