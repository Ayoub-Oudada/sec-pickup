package com.secpickup.android_front;

import com.secpickup.android_front.modele.Positions;
import com.secpickup.android_front.retrofit.PositionApi;
import com.secpickup.android_front.retrofit.RetrofitService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoadTrajet {

    public interface LoadTrajetCallback {
        void onTrajetLoaded(List<Positions> positionList);
        void onFailedToLoadTrajet();
    }

    public interface LoadPositionCallback {
        void onPositionLoaded(Positions position);
        void onFailedToLoadPosition();
    }

    public interface LoadDistanceCallback {
        void onDistanceLoaded(Double distance);
        void onFailedToLoadDistance();
    }
    List<Positions> positionList=null;
    public List<Positions> loadTrajet(String username, LoadTrajetCallback loadTrajetCallback) {
        RetrofitService retrofitService = new RetrofitService();
        PositionApi positionApi = retrofitService.getRetrofit().create(PositionApi.class);
        positionApi.GetTrajet(username)
                .enqueue(new Callback<List<Positions>>() {
                    @Override
                    public void onResponse(Call<List<Positions>> call, Response<List<Positions>> response) {
                        if (response.isSuccessful()) {
                            positionList = response.body();
                            loadTrajetCallback.onTrajetLoaded(positionList);
                            System.err.println(positionList.size());
                        } else {
                            loadTrajetCallback.onFailedToLoadTrajet();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Positions>> call, Throwable t) {
                        loadTrajetCallback.onFailedToLoadTrajet();
                    }
                });
        return positionList;
    }

    public void loadPosition(String username, LoadPositionCallback loadPositionCallback) {
        RetrofitService retrofitService = new RetrofitService();
        PositionApi positionApi = retrofitService.getRetrofit().create(PositionApi.class);
        positionApi.GetPosition(username)
                .enqueue(new Callback<Positions>() {
                    @Override
                    public void onResponse(Call<Positions> call, Response<Positions> response) {
                        if (response.isSuccessful()) {
                            Positions position = response.body();
                            loadPositionCallback.onPositionLoaded(position);
                            System.err.println(position);
                        } else {
                            loadPositionCallback.onFailedToLoadPosition();
                        }
                    }

                    @Override
                    public void onFailure(Call<Positions> call, Throwable t) {
                        loadPositionCallback.onFailedToLoadPosition();
                    }
                });
    }

    public void loadDistance(String username1,String username2, LoadDistanceCallback loadDistanceCallback) {
        RetrofitService retrofitService = new RetrofitService();
        PositionApi positionApi = retrofitService.getRetrofit().create(PositionApi.class);
        positionApi.GetDistance(username1,username2)
                .enqueue(new Callback<Double>() {
                    @Override
                    public void onResponse(Call<Double> call, Response<Double> response) {
                        if (response.isSuccessful()) {
                            Double distance = response.body();
                            loadDistanceCallback.onDistanceLoaded(distance);
                            System.err.println(distance);
                        } else {
                            loadDistanceCallback.onFailedToLoadDistance();
                        }
                    }

                    @Override
                    public void onFailure(Call<Double> call, Throwable t) {
                        loadDistanceCallback.onFailedToLoadDistance();
                    }
                });
    }
}
