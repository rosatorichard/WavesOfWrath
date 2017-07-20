package com.batchmates.android.wavesofwrath.view.secondactivity;

import android.util.Log;

import com.batchmates.android.wavesofwrath.model.PlaceInformation;
import com.batchmates.android.wavesofwrath.model.closeplaces.ClosePlacesPojo;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Android on 7/19/2017.
 */

public class Threader extends Thread {

    private static final String TAG = "Threader";
    List<PlaceInformation> placeList=new ArrayList<>();
    private LatLng latLng;
    private int pricer=0;
    private double rating=0;
    private LatLng latLngCurrent;
    private SecondActivityContract.View view;

    public Threader(LatLng latLngCurrent,SecondActivityContract.View view) {
        this.latLngCurrent = latLngCurrent;
        this.view=view;
    }

    @Override
    public void run() {
        super.run();
        retrofit2.Call<ClosePlacesPojo> myCall=RetroFitHelper.callPlaces(latLngCurrent);
        myCall.enqueue(new Callback<ClosePlacesPojo>() {
            @Override
            public void onResponse(Call<ClosePlacesPojo> call, Response<ClosePlacesPojo> response) {
                for (int i = 0; i < 20; i++) {
                    latLng=new LatLng(response.body().getResults().get(i).getGeometry().getLocation().getLat(),response.body().getResults().get(i).getGeometry().getLocation().getLng());

                    if(response.body().getResults().get(i).getPriceLevel()==null) {
                        pricer=0;
                    }
                    else {
                        pricer=response.body().getResults().get(i).getPriceLevel();
                    }
                    if(response.body().getResults().get(i).getRating()==null)
                    {
                        rating=0;
                    }
                    else
                    {
                        rating=response.body().getResults().get(i).getRating();
                    }
                    placeList.add(new PlaceInformation(response.body().getResults().get(i).getName(),
                            response.body().getResults().get(i).getVicinity(),
                            response.body().getResults().get(i).getIcon(),
                            pricer,
                            rating,
                            latLng));

                }
                Log.d(TAG, "onResponse: call return");
                view.returnedPlaces(placeList);
            }

            @Override
            public void onFailure(Call<ClosePlacesPojo> call, Throwable t) {

            }
        });
    }
}
