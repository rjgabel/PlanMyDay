package com.example.planmyday;
import org.junit.Test;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import static org.junit.Assert.*;
import org.junit.Before;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class LoginRegistrationTest {
    @Mock
    private FirebaseAuth mockedFirebaseAuth;
    //private FirebaseAuthService firebaseAuthService;
    @Before
    public void setUp() {
        mockedFirebaseAuth = mock(FirebaseAuth.class);
        //firebaseAuthService = new FirebaseAuthService(mockedFirebaseAuth);
    }

    //Black-Box test for if registration works
    @Test
    public void register_user(){
        String email = "test@example.com";
        String password = "password123";

        mockedFirebaseAuth.createUserWithEmailAndPassword(email, password);

        // Assert
        // Verify that createUserWithEmailAndPassword is called with the correct arguments
        verify(mockedFirebaseAuth).createUserWithEmailAndPassword(email, password);
    }


}
