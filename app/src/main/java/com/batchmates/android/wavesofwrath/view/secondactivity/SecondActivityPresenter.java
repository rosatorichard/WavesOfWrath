package com.batchmates.android.wavesofwrath.view.secondactivity;

import android.util.Log;

import com.batchmates.android.wavesofwrath.model.PlaceInformation;
import com.batchmates.android.wavesofwrath.model.closeplaces.ClosePlacesPojo;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Android on 7/17/2017.
 */

public class SecondActivityPresenter implements SecondActivityContract.Presenter {

    List<PlaceInformation> placeList=new ArrayList<>();
    private static final String TAG = "SecondPresenter";
    private static final String BASEURL = "https://maps.googleapis.com/maps/api/place";
    SecondActivityContract.View view;
    private LatLng latLng;
    private int pricer=0;
    private double rating=0;

    @Override
    public void addView(SecondActivityContract.View view) {
        this.view=view;

    }

    @Override
    public void removeView() {
        this.view=null;
    }

    @Override
    public void placesCloseby() {
        retrofit2.Call<ClosePlacesPojo> myCall=RetroFitHelper.callPlaces();
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

                    view.returnedPlaces(placeList);
                }
            }

            @Override
            public void onFailure(Call<ClosePlacesPojo> call, Throwable t) {

            }
        });

    }

    @Override
    public void checkEncounter(Marker myself) {

        LatLng lat;
        for (int i = 0; i <20 ; i++) {
            lat=new LatLng(placeList.get(i).getLocation().latitude,placeList.get(i).getLocation().longitude);
            if(myself.getPosition()==lat)
            {
                Log.d(TAG, "checkEncounter: You are on top of it");
            }
            
            if (myself.getPosition().latitude>placeList.get(i).getLocation().latitude)
            {
                Log.d(TAG, "checkEncounter: SillyCheckWork "+ i);
            }

        }

    }
}
