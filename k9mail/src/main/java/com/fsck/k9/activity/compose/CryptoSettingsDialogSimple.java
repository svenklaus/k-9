package com.fsck.k9.activity.compose;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

import com.fsck.k9.R;

public class CryptoSettingsDialogSimple extends DialogFragment {
    private RecipientPresenter.CryptoMode currentMode;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        //builder.setView(view);
        builder.setMessage("Encrypt email content?");
        builder.setNegativeButton(R.string.crypto_settings_cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                changeCryptoSettingsDisable();
                dialog.dismiss();
            }
        });
        builder.setPositiveButton(R.string.crypto_settings_ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                changeCryptoSettingsPrivate();
                dialog.dismiss();
            }
        });
        // Create the AlertDialog object and return it
        return builder.create();
    }

    private void changeCryptoSettingsPrivate() {
        Activity activity = getActivity();
        if (activity == null) {
            // is this supposed to happen?
            return;
        }
        boolean activityIsCryptoModeChangedListener = activity instanceof CryptoSettingsDialog.OnCryptoModeChangedListener;
        if (!activityIsCryptoModeChangedListener) {
            throw new AssertionError("This dialog must be called by an OnCryptoModeChangedListener!");
        }
        currentMode = RecipientPresenter.CryptoMode.PRIVATE;
        ((CryptoSettingsDialog.OnCryptoModeChangedListener) activity).onCryptoModeChanged(currentMode);
    }

    private void changeCryptoSettingsDisable() {
        Activity activity = getActivity();
        if (activity == null) {
            // is this supposed to happen?
            return;
        }
        boolean activityIsCryptoModeChangedListener = activity instanceof CryptoSettingsDialog.OnCryptoModeChangedListener;
        if (!activityIsCryptoModeChangedListener) {
            throw new AssertionError("This dialog must be called by an OnCryptoModeChangedListener!");
        }
        currentMode = RecipientPresenter.CryptoMode.DISABLE;
        ((CryptoSettingsDialog.OnCryptoModeChangedListener) activity).onCryptoModeChanged(currentMode);
    }
}


