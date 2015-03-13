package games.andoid.shooter_ver5_0;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Vibrator;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import games.andoid.shooter_ver6_0.R;

public class GameDrawer extends View {

	public static final float BULLET_RADIUS = 12;
	private static final int DOME_CENTER_Y_OFFSET = 80;

	Rect LBorder, RBorder, TBorder;
	RectF Intercepter;
	static int borderHeight;
	static final int borderThickness = 20;

	static final int HIVE_OUTER_COLOR = Color.DKGRAY;
	static final int HIVE_COLOR_INNER = Color.WHITE;
	static final int BULLET_COLOR = Color.LTGRAY;
	static final int HURDLE_COLOR = Color.RED;
	static final int DOME_COLOR = Color.GREEN;

	static int Lbr_top = 0, Lbr_left = 0, Lbr_right = 10, Lbr_bottom;
	static int Rbr_top = 0, Rbr_left, Rbr_right, Rbr_bottom;
	static int Tbr_top = 0, Tbr_left = 0, Tbr_right, Tbr_bottom = 10;
	static int domeCenterX, domeCenterY = 770, domeRad = 50;

	Bullets bullets[] = new Bullets[1000];
	int bulletCount = 0;
	int x_newBullet, y_newBullet;
	boolean KilledByHurdle = false;
	boolean CaughtByHive = false;
	boolean ActivityKilled = false;

	Hurdels hurdle[] = new Hurdels[20];

	Bullet_Hive hive;

	Paint paint = new Paint();

	public static Canvas gamePlot;

	Context obtainedContext;
	Handler handler = new Handler();
	private boolean addABall = false;
	private int alpha = 100;
	private boolean shouldAlphaIncrease = true;
	private int hurdlesInGame = 0;
	private boolean GAME_JUST_STARTED = true;
	private int hurdlesToPlace;

	Bitmap ball;
	static Bitmap rocket;
	private static boolean RotationDemandedFlag = false;
	private static double rotationAngle;

	int speedBump = 500;
	Vibrator vibrator = (Vibrator) getContext().getSystemService(
			Context.VIBRATOR_SERVICE);

	public GameDrawer(Context context, AttributeSet attrs) {
		super(context, attrs);

		borderHeight = GameController.SideBorderHeight;
		obtainedContext = context;
		ball = BitmapFactory.decodeResource(getResources(),
				R.drawable.ball_silver);
		rocket = BitmapFactory
				.decodeResource(getResources(), R.drawable.rocket);
	}

	@Override
	protected void onDraw(Canvas canvas) {

		gamePlot = canvas;

		if (GAME_JUST_STARTED) {
			Bullet_Hive.HIVE_CENTER_X = gamePlot.getWidth() / 2;
			GAME_JUST_STARTED = false;
			putAllValues();
			hurdlesToPlace = GameController.getHurdelCount();
		}

		GameController.GameScan();
		if (GameController.LEVEL_UPGRADED) {

			// MainActivity.Inform();
			Toast.makeText(
					getContext(),
					"Lives Awarded: " + GameController.LIVES_BOOST
							+ "\nScore Booster: " + GameController.SCORE_BOOST,
					Toast.LENGTH_LONG).show();
			vibrator.vibrate(200);
			hurdlesToPlace = GameController.getHurdelCount();
		}

		initialDrawings(canvas, 0);

		// Drawing BulletHIVE
		// ////////////////////////////////////////////////////////////
		paint.setColor(HIVE_OUTER_COLOR);
		paint.setAlpha(150);
		if (hive == null)
			hive = new Bullet_Hive();

		canvas.drawCircle(Bullet_Hive.getCentX(), Bullet_Hive.getCentY(),
				Bullet_Hive.HIVE_OUTER_RADIUS, paint);
		paint.setColor(HIVE_COLOR_INNER);
		paint.setAlpha(225);
		canvas.drawCircle(Bullet_Hive.HIVE_CENTER_X, Bullet_Hive.HIVE_CENTER_Y,
				GameController.HIVE_INNER_RADIUS, paint);

		// Drawing Bullets
		// ////////////////////////////////////////////////////////////
		if (addABall) {

			bullets[bulletCount++] = addBullet(x_newBullet, y_newBullet);
			addABall = false;
			if (bulletCount == 999)
				bulletCount = 0;
		}

		for (int i = 0; i < bulletCount; i++) {

			bullets[i].moveBullet();
			paint.setColor(BULLET_COLOR);

			if (bullets[i].getX() == -127 || bullets[i].getY() == -127
					|| isInsideHive(bullets[i].getX(), bullets[i].getY())
					|| IsASideCollision(bullets[i].getX(), bullets[i].getY())
					|| IsAMidCollision(bullets[i].getX(), bullets[i].getY())) {

				bullets[i] = null;
				for (int j = i; j < bulletCount - 1; j++)
					bullets[j] = bullets[j + 1];
				bulletCount--;

				if (KilledByHurdle) {
					KilledByHurdle = false;
					if (GameDataManager.Lives > 0)
						GameDataManager.Lives--;

					else if (GameDataManager.Lives == 0) {
						if (!ActivityKilled) {

							for (int k = 0; k < bulletCount; k++) {
								bullets[i] = null;
							}
							bulletCount = 0;

							GameDataManager.PlayerScore += GameDataManager.Lives * 5;
							boolean saveResult = GameDataManager
									.SaveScore(getContext());
							if (!saveResult) {
								Toast.makeText(
										getContext(),
										"ERROR OCCURED\nGame Score was not be saved",
										Toast.LENGTH_LONG).show();
								Log.d("Msg", "Not Saved");
							} else {
								Log.d("Msg", "Saved");
							}
							MainActivity homeScreen = (MainActivity) getContext();
							homeScreen.Kill();
							ActivityKilled = true;
						}

					}

					if (GameDataManager.PlayerScore >= 2)
						GameDataManager.PlayerScore -= 2;
					else
						GameDataManager.PlayerScore = 0;
				}

				if (CaughtByHive) {
					CaughtByHive = false;
					GameDataManager.PlayerScore += GameController.SCORE_BOOST;
				}
				putAllValues();

			} else if (bullets[i].getX() > 0
					&& bullets[i].getX() < canvas.getWidth()
					&& bullets[i].getY() > 0) {

				// Drawing Bullets
				// paint.setARGB(0xff, 00, 00, 00);
				paint.setColor(BULLET_COLOR);
				// paint.setAlpha(150);
				// canvas.drawCircle(bullets[i].getX(), bullets[i].getY(),
				// BULLET_RADIUS, paint);
				canvas.drawBitmap(ball,
						bullets[i].getX() - ball.getWidth() / 2,
						bullets[i].getY() - ball.getHeight() / 2, paint);
			}

			initialDrawings(canvas, i);
			// putAllValues();

		}

		// Drawing Hurdles
		// ////////////////////////////////////////////////////////////
		if (GameController.LEVEL_UPGRADED) {
			for (int i = 0; i < hurdlesInGame; i++) {
				hurdle[i] = null;

			}
			hurdlesInGame = 0;
			GameController.LEVEL_UPGRADED = false;
		}
		if (hurdlesInGame < hurdlesToPlace) {

			for (; hurdlesInGame < hurdlesToPlace; hurdlesInGame++) {

				hurdle[hurdlesInGame] = new Hurdels(
						GameController.getHurdleSpeeds(),
						GameController.getHurdelCenter_X(),
						GameController.getHurdelCenter_Y());

			}
		}

		for (int i = 0; i < hurdlesToPlace; i++) {

			hurdle[i].HURDEL_MOVER();
			paint.setColor(HURDLE_COLOR);
			hurdle[i].pixelSpeed = GameController.getHurdleSpeeds();
			if (Intercepter == null)
				Intercepter = new RectF(hurdle[i].getLEFT(),
						hurdle[i].getTOP(), hurdle[i].getRIGHT(),
						hurdle[i].getBOTTOM());
			else {
				Intercepter.bottom = hurdle[i].getBOTTOM();
				Intercepter.left = hurdle[i].getLEFT();
				Intercepter.right = hurdle[i].getRIGHT();
				Intercepter.top = hurdle[i].getTOP();
			}
			canvas.drawRoundRect(Intercepter, 10, 10, paint);// ect(Intercepter,
																// paint);

			initialDrawings(canvas, i);
		}

		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				invalidate();
			}
		};

		handler.postDelayed(runnable, 3);
	}

	private void putAllValues() {

		MainActivity.Level.setText("LEVEL " + GameController.LEVEL);
		MainActivity.Lives.setText("L : " + GameDataManager.Lives);
		MainActivity.Points.setText("PTS : " + GameDataManager.PlayerScore);

	}

	private boolean isInsideHive(int x, int y) {

		boolean result = false;
		if (Math.sqrt(Math.pow(x - Bullet_Hive.HIVE_CENTER_X, 2)
				+ Math.pow(y - Bullet_Hive.HIVE_CENTER_Y, 2)) < Bullet_Hive.HIVE_OUTER_RADIUS
				- BULLET_RADIUS) {

			result = true;
			CaughtByHive = true;

			if (GameController.HIVE_INNER_RADIUS < Bullet_Hive.HIVE_OUTER_RADIUS - 1)
				GameController.HIVE_INNER_RADIUS += 5;
		}

		return result;
	}

	private boolean IsASideCollision(int x, int y) {

		boolean result = false;
		for (int i = 0; i < hurdlesInGame; i++) {

			if ((hurdle[i].getLEFT() == (x + BULLET_RADIUS) && Math
					.abs(hurdle[i].getTOP() - y) < BULLET_RADIUS) // Collision
																	// on
																	// LeftSide
																	// of hurdle
					|| (hurdle[i].getRIGHT() == (x - BULLET_RADIUS) && Math
							.abs(hurdle[i].getTOP() - y) < BULLET_RADIUS)) { // Collision
																				// on
																				// RightSide
																				// of
																				// Hurdle
				result = true;
				KilledByHurdle = true;
				vibrator.vibrate(10);

			}
		}
		return result;
	}

	public boolean IsAMidCollision(int x, int y) {

		boolean ressult = false;

		for (int i = 0; i < hurdlesInGame; i++) {

			if (x > hurdle[i].getLEFT() && x < hurdle[i].getRIGHT()) {
				if (Math.abs(hurdle[i].hurdelCentroid.y - y) < BULLET_RADIUS - 6) {
					ressult = true;
					KilledByHurdle = true;
					vibrator.vibrate(10);
				}
			}
		}
		return ressult;

	}

	private Bullets addBullet(int x_newBullet2, int y_newBullet2) {
		// TODO Auto-generated method stub
		return new Bullets(x_newBullet2, y_newBullet2);
	}

	private void initialDrawings(Canvas canvas, int i) {

		setLengths(canvas);

		LBorder = new Rect(Lbr_left, Lbr_top, Lbr_right, Lbr_bottom);
		RBorder = new Rect(Rbr_left, Rbr_top, Rbr_right, Rbr_bottom);
		TBorder = new Rect(Tbr_left, Tbr_top, Tbr_right, Tbr_bottom);

		paint.setColor(Color.TRANSPARENT);
		canvas.drawRect(LBorder, paint);
		canvas.drawRect(RBorder, paint);
		// canvas.drawRect(TBorder, paint);

		paint.setColor(Color.GREEN);

		if (shouldAlphaIncrease) {
			alpha += 5;
			if (alpha < 255)
				shouldAlphaIncrease = true;
			else if (alpha == 255) {
				shouldAlphaIncrease = false;
			}
		} else if (!shouldAlphaIncrease) {
			alpha -= 5;
			if (alpha > 100)
				shouldAlphaIncrease = false;
			else if (alpha == 100) {
				shouldAlphaIncrease = true;
			}
		}
		paint.setAlpha(alpha);

		// speedBump--;
		// if (RotationDemandedFlag || speedBump != 0) {
		//
		// canvas.drawBitmap(StartRotate(rotationAngle), domeCenterX - 100,
		// domeCenterY - 100, paint);
		// RotationDemandedFlag = false;
		// speedBump = 5000;
		// } else if (speedBump == 0)
		// canvas.drawBitmap(StartRotate(0), domeCenterX - 100,
		// domeCenterY - 100, paint);

		canvas.drawBitmap(rocket, domeCenterX - 100, domeCenterY - 100, paint);
		// canvas.rotate(

		// double slopeAngle = Math.atan(bullets[i].initialSlope);
		// canvas.ro
		// canvas.drawCircle(domeCenterX, domeCenterY, domeRad, paint);
		// paint.setColor(Color.WHITE);
		// canvas.drawCircle(domeCenterX, domeCenterY + 500, domeRad + 450,
		// paint);

	}

	public static Bitmap StartRotate(double angle) {

		Matrix matrix = new Matrix();
		matrix.setRotate((float) angle, domeCenterX - 100, domeCenterY - 100);
		// matrix.postRotate((float) angle);

		return Bitmap.createBitmap(rocket, 0, 0, rocket.getWidth(),
				rocket.getHeight(), matrix, true);

	}

	public static void RotateTo(int x, int y) {

		int domeCx = domeCenterX, domeCy = domeCenterY;

		double slope;

		y *= -1;
		domeCy *= -1;

		if (x != domeCenterX) {

			slope = (y - domeCy) / (x - domeCx);
			Log.d("Msg", "slope: " + slope);
			rotationAngle = 180 * Math.atan(slope) / Math.PI;

		} else
			rotationAngle = 0;

		Log.d("Msg", "Came to RotateTo\nrotationangle got=" + rotationAngle);

		RotationDemandedFlag = true;
	}

	private void setLengths(Canvas canvas) {

		borderHeight = getBorderHeight();

		Lbr_left = 0;
		Lbr_top = 0;
		Lbr_right = Lbr_left + borderThickness;
		Lbr_bottom = Lbr_top + borderHeight;

		Rbr_left = canvas.getWidth() - borderThickness;
		Rbr_top = 0;
		Rbr_right = canvas.getWidth();
		Rbr_bottom = Rbr_top + borderHeight;

		Tbr_left = 0;
		Tbr_top = 0;
		Tbr_right = canvas.getWidth();
		Tbr_bottom = Tbr_top + borderThickness;

		domeCenterX = canvas.getWidth() / 2;
		domeCenterY = canvas.getHeight() - DOME_CENTER_Y_OFFSET;
		domeRad = 50;
	}

	private int getBorderHeight() {

		return GameController.SideBorderHeight;
	}

	public static void setBorderHeight(int h) {

		borderHeight = h;
	}

	public void myNewBulletTarget(int x, int y) {

		this.x_newBullet = x;
		this.y_newBullet = y;
		addABall = true;
	}

}
