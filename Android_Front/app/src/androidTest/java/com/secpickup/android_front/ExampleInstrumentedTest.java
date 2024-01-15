package com.secpickup.android_front;

import android.content.Context;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.test.core.app.ApplicationProvider;

import com.secpickup.android_front.activity_login;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.shadows.ShadowToast;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(RobolectricTestRunner.class)
public class ExampleInstrumentedTest {

    @Test
    public void testAuthenticationFailure() {
        // Utilisez un contexte d'instrumentation
        Context appContext = ApplicationProvider.getApplicationContext();

        // Créez une instance de votre activité en utilisant Robolectric.setupActivity
        activity_login activity = Robolectric.setupActivity(activity_login.class);

        // Définissez des valeurs incorrectes pour le test
        EditText usernameEditText = activity.findViewById(R.id.login_username);
        usernameEditText.setText("invalidUser");
        EditText passwordEditText = activity.findViewById(R.id.login_password);
        passwordEditText.setText("invalidPassword");
        Spinner spinner = activity.findViewById(R.id.spinner);
        spinner.setSelection(0); // Sélectionnez la première option dans le spinner

        // Obtenez une référence vers le bouton
        Button button = activity.findViewById(R.id.login_button);

        // Simulez le clic sur le bouton
        button.performClick();

        // Vérifiez que le message d'échec d'authentification approprié est affiché
        String toastText = ShadowToast.getTextOfLatestToast();
        assertNotNull("Aucun message d'erreur d'authentification n'a été affiché", toastText);
        assertEquals("L'authentification a échoué", toastText);
    }
}
