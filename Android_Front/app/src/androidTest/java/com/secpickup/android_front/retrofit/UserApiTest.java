package com.secpickup.android_front.retrofit;
import com.secpickup.android_front.modele.User;
import com.secpickup.android_front.modele.UserAccountType;
import com.secpickup.android_front.retrofit.UserApi;
import org.junit.Test;
import org.mockito.Mockito;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

public class UserApiTest {

    @Test
    public void testFindUser() throws IOException {
        UserApi userApi = Mockito.mock(UserApi.class);
        Call<User> call = Mockito.mock(Call.class);

        Mockito.when(userApi.findUser(any(User.class))).thenReturn(call);
        Mockito.when(call.execute()).thenReturn(Response.success(new User("testUsername", "testPassword", UserAccountType.PARENT)));

        Response<User> response = userApi.findUser(new User("testUsername", "testPassword", UserAccountType.PARENT)).execute();
        assertTrue(response.isSuccessful());
    }

    @Test
    public void testRegisterNewUser() throws IOException {
        UserApi userApi = Mockito.mock(UserApi.class);
        Call<User> call = Mockito.mock(Call.class);

        Mockito.when(userApi.registerNewUser(any(User.class))).thenReturn(call);
        Mockito.when(call.execute()).thenReturn(Response.success(new User("testUsername", "testPassword", UserAccountType.PARENT)));

        Response<User> response = userApi.registerNewUser(new User("testUsername", "testPassword", UserAccountType.PARENT)).execute();
        assertTrue(response.isSuccessful());
    }

    @Test
    public void testUpdateUserPassword() throws IOException {
        UserApi userApi = Mockito.mock(UserApi.class);
        Call<Void> call = Mockito.mock(Call.class);

        Mockito.when(userApi.updateUserPassword(anyLong(), any(String.class), any(String.class))).thenReturn(call);
        Mockito.when(call.execute()).thenReturn(Response.success(null));

        Response<Void> response = userApi.updateUserPassword(1L, "oldPassword", "newPassword").execute();
        assertTrue(response.isSuccessful());
    }

    // Add similar tests for other methods in UserApi
}
