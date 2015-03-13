package games.andoid.shooter_ver5_0;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import games.andoid.shooter_ver6_0.R;

public class LevelUpgradeFragment extends DialogFragment {

	View LevelProgressDialog;
	AlertDialog.Builder LevelProgressDialogBuilder;

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		Log.d("Msg", "Came to create dialog !!");
		LevelProgressDialogBuilder = new Builder(getActivity(),
				android.R.style.Theme_DeviceDefault_Light_Panel);
		LevelProgressDialog = getActivity().getLayoutInflater().inflate(
				R.layout.level_upgrade_fragment, null);

		// LevelProgressDialogBuilder.setTitle("Level Upgraded");
		LevelProgressDialogBuilder.setMessage("\t\t\t\t\t\t\tLEVEL "
				+ GameController.LEVEL + "\nLives Awarded : "
				+ GameController.LIVES_BOOST + "\t\tTotal : "
				+ GameDataManager.Lives);

		LevelProgressDialogBuilder.setView(LevelProgressDialog);
//		LevelProgressDialogBuilder.setNeutralButton("OK",
//				new Dialog.OnClickListener() {
//
//					@Override
//					public void onClick(DialogInterface dialog, int which) {
//						// TODO Auto-generated method stub
//
//					}
//				});

		// switch (GameController.LEVEL) {
		// case 2:
		// MainActivity.LevelImg.setImageResource(R.drawable.level2_tag);
		// break;
		// case 3:
		// MainActivity.LevelImg.setImageResource(R.drawable.level3_tag);
		// break;
		// case 4:
		// MainActivity.LevelImg.setImageResource(R.drawable.level4_tag);
		// break;
		// case 5:
		// MainActivity.LevelImg.setImageResource(R.drawable.level5_tag);
		// break;
		// case 6:
		// MainActivity.LevelImg.setImageResource(R.drawable.level6_tag);
		// break;
		// case 7:
		// MainActivity.LevelImg.setImageResource(R.drawable.level7_tag);
		// break;
		// case 8:
		// MainActivity.LevelImg.setImageResource(R.drawable.level8_tag);
		// break;
		// case 9:
		// MainActivity.LevelImg.setImageResource(R.drawable.level9_tag);
		// break;
		// case 10:
		// MainActivity.LevelImg.setImageResource(R.drawable.level10_tag);
		// break;
		// case 11:
		// MainActivity.LevelImg.setImageResource(R.drawable.level11_tag);
		// break;
		// case 12:
		// MainActivity.LevelImg.setImageResource(R.drawable.level12_tag);
		// break;
		// case 13:
		// MainActivity.LevelImg.setImageResource(R.drawable.level13_tag);
		// break;
		// case 14:
		// MainActivity.LevelImg.setImageResource(R.drawable.level14_tag);
		// break;
		// case 15:
		// MainActivity.LevelImg.setImageResource(R.drawable.level15_tag);
		// break;
		//
		// }

		return LevelProgressDialogBuilder.create();

	}
}
