package com.example.planmyday;
import org.junit.Test;

import com.example.planmyday.planning.LocationsActivity;
import com.example.planmyday.registration.RegistrationActivity;
import com.google.android.gms.maps.GoogleMap;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowToast;

import static org.mockito.Mockito.*;
import android.content.Context;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.lifecycle.ReportFragment;
import androidx.test.platform.app.InstrumentationRegistry;

import com.google.firebase.auth.FirebaseAuth;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

@RunWith(RobolectricTestRunner.class)
@Config(manifest=Config.NONE)
public class LoginRegistrationTest {
    @Mock
    private FirebaseAuth mockedFirebaseAuth;
    private RegistrationActivity mockRegistrationActivity;
    //private FirebaseAuthService firebaseAuthService;

    @Before
    public void setUp() {
        mockRegistrationActivity = mock(RegistrationActivity.class);
        mockedFirebaseAuth = mock(FirebaseAuth.class);
        //firebaseAuthService = new FirebaseAuthService(mockedFirebaseAuth);
    }

    //Unit tests for logging in
    @Test
    public void password_mismatch(){
        String name = "test";
        String email = "test@example.com";
        String password = "password";
        String confirmPassword = "password";
        mockRegistrationActivity.finishRegister(name, email, password, confirmPassword);
        //Toast is shown, not registered
        verify(mockRegistrationActivity).finishRegister(name, email, password, confirmPassword);
        assertFalse(ShadowToast.showedToast("Successfully Registered"));
    }


    @Test
    public void register_user(){
        String email = "test@example.com";
        String password = "password123";

        mockedFirebaseAuth.createUserWithEmailAndPassword(email, password);
        // Assert
        // Verify that createUserWithEmailAndPassword is called with the correct arguments
        verify(mockedFirebaseAuth).createUserWithEmailAndPassword(email, password);
    }

    @Test
    public void login_user(){
        String email = "test@example.com";
        String password = "password123";

        mockedFirebaseAuth.signInWithEmailAndPassword(email, password);

        verify(mockedFirebaseAuth).signInWithEmailAndPassword(email, password);
    }

}
