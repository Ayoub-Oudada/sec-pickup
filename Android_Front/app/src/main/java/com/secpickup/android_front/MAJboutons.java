package com.secpickup.android_front;

import android.content.Context;
import android.widget.Button;
import androidx.core.content.ContextCompat;

public class MAJboutons {

    private final Context context;

    public MAJboutons(Context context) {
        this.context = context;
    }

    public void updateButtonColors(Button btnRecuperer, Button btnDeposer, Button btnAbsent, String situation) {
        // Réinitialisez toutes les couleurs à leur état d'origine
        resetButtonColors(btnRecuperer, btnDeposer, btnAbsent);

        // Changez la couleur et l'état activé des boutons en fonction de la situation
        if ("DEPOSER".equals(situation)) {
            setButtonDisabledAndGrey(btnDeposer);
            setButtonEnabledAndGreen(btnRecuperer, btnAbsent);
        } if ("RECUPPERER".equals(situation)) {
            setButtonDisabledAndGrey(btnRecuperer);
            setButtonEnabledAndGreen(btnDeposer, btnAbsent);
        } if ("ABSENT".equals(situation)) {
            setButtonDisabledAndGrey(btnAbsent);
            setButtonEnabledAndGreen(btnDeposer, btnRecuperer);
        }
    }

    private void resetButtonColors(Button... buttons) {
        for (Button button : buttons) {
            button.setEnabled(true);
            button.setBackgroundColor(ContextCompat.getColor(context, R.color.teal_200));
        }
    }

    private void setButtonEnabledAndGreen(Button... buttons) {
        for (Button button : buttons) {
            button.setEnabled(true);
            button.setBackgroundColor(ContextCompat.getColor(context, R.color.teal_200));
        }
    }

    private void setButtonDisabledAndGrey(Button... buttons) {
        for (Button button : buttons) {
            button.setEnabled(false);
            button.setBackgroundColor(ContextCompat.getColor(context, R.color.grey));
        }
    }
}
