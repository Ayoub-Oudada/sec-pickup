package com.secpickup.android_front.retrofit;


import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.mockito.ArgumentMatchers.any;

import com.secpickup.android_front.modele.Eleve;
import com.secpickup.android_front.modele.UserAccountType;

public class EleveApiTest {

//    @Test
//    public void testSearchParentByUsernameType() throws IOException {
//        EleveApi eleveApi = Mockito.mock(EleveApi.class);
//        Call<List<Eleve>> call = Mockito.mock(Call.class);
//
//        Mockito.when(eleveApi.searchParentByUsernameType(any(String.class), any(UserAccountType.class))).thenReturn(call);
//        Mockito.when(call.execute()).thenReturn(Response.success(/* Mocked List<Eleve> data */));
//
//        Response<List<Eleve>> response = eleveApi.searchParentByUsernameType("username", UserAccountType.PARENT).execute();
//        assertTrue(response.isSuccessful());
//        // Ajouter des assertions supplémentaires ici selon vos besoins
//    }
//
//    @Test
//    public void testSearchParentByUsernameTypeFailure() throws IOException {
//        EleveApi eleveApi = Mockito.mock(EleveApi.class);
//        Call<List<Eleve>> call = Mockito.mock(Call.class);
//
//        Mockito.when(eleveApi.searchParentByUsernameType(any(String.class), any(UserAccountType.class))).thenReturn(call);
//        Mockito.when(call.execute()).thenReturn(Response.error(404, Mockito.mock(okhttp3.ResponseBody.class)));
//
//        Response<List<Eleve>> response = eleveApi.searchParentByUsernameType("username", UserAccountType.PARENT).execute();
//        assertFalse(response.isSuccessful());
//        // Ajouter des assertions supplémentaires ici selon vos besoins pour gérer les cas d'échec
//    }
//
//    // Ajouter des tests similaires pour les autres méthodes de EleveApi
}
