package games.andoid.shooter_ver5_0;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import games.andoid.shooter_ver6_0.R;

public class OnIngameBackPressDialogFragment extends DialogFragment {

	View LevelProgressDialog;
	AlertDialog.Builder LevelProgressDialogBuilder;

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		Log.d("Msg", "Came to create dialog !!");
		LevelProgressDialogBuilder = new Builder(getActivity(),
				android.R.style.Theme_DeviceDefault_Dialog);
		LevelProgressDialog = getActivity().getLayoutInflater().inflate(
				R.layout.blank_base, null);

		LevelProgressDialogBuilder.setIcon(R.drawable.ic_menu_help_holo_light);
		LevelProgressDialogBuilder.setTitle("End Current Session ?");
		LevelProgressDialogBuilder
				.setMessage("LEVEL: "
						+ GameController.LEVEL
						+ "\nPOINTS : "
						+ GameDataManager.PlayerScore
						+ "\nLIVES: "
						+ GameDataManager.Lives
						+ "\n\nScore will be saved.\nGame can't be continued from this point !");

		LevelProgressDialogBuilder.setNeutralButton("Resume",
				new Dialog.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub

					}
				});
		LevelProgressDialogBuilder.setPositiveButton("Yes",
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface arg0, int arg1) {

						Intent intent = new Intent(getActivity(),
								GameHomeScreen.class);
						startActivity(intent);

						if (MainActivity.TouchedOnce) {
							GameDataManager.PlayerScore += GameDataManager.Lives * 5;
							if (GameDataManager.SaveScore(getActivity()))
								Toast.makeText(getActivity(),
										"Score Saved Successfully ! :)",
										Toast.LENGTH_LONG).show();
							else
								Toast.makeText(getActivity(),
										"Score couldn't be Saved ! :(",
										Toast.LENGTH_LONG).show();
						}
						GameDataManager.ResetNecessary();
						getActivity().finish();
					}
				});

		return LevelProgressDialogBuilder.create();

	}
}
