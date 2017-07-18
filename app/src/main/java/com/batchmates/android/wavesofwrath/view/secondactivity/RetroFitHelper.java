package com.batchmates.android.wavesofwrath.view.secondactivity;

import com.batchmates.android.wavesofwrath.model.closeplaces.ClosePlacesPojo;
import com.google.android.gms.location.places.Place;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Android on 7/17/2017.
 */

public class RetroFitHelper {

    private static final String BASE_URL="https://maps.googleapis.com/maps/";
    private static final String QUERY_ZIP="500";
    private static final String APP_ID="AIzaSyCZbgGY4IMPsS20u_dVlhZUy-MTq0TWlss";
    private static final String QUERY_LOCAL = "33.9896094,-84.45333540000001";
    private static final int FOOD=Place.TYPE_FOOD;


    public static Call<ClosePlacesPojo> callPlaces()
    {
        Retrofit retrofit=Create();
        places places = retrofit.create(RetroFitHelper.places.class);
        return places.getNearbyLocations(QUERY_LOCAL,500);

    }


//    public static Call<ClosePlacesPojo> callPlacesFood()
//    {
//        Retrofit retrofit=Create();
//        places places = retrofit.create(RetroFitHelper.places.class);
//        return places.getNearbyLocations2(QUERY_LOCAL, Place.TYPE_FOOD,500);
//
//    }

    private static Retrofit Create() {
        Retrofit retro= new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retro;
    }

    public interface places{

//        &pagetoken
        @GET("api/place/nearbysearch/json?sensor=true&key=AIzaSyCZbgGY4IMPsS20u_dVlhZUy-MTq0TWlss")
        Call<ClosePlacesPojo> getNearbyLocations(@Query("location")String coord,@Query("radius")int radius);

//        @GET("api/place/nearbysearch/json?sensor=true&key=AIzaSyCZbgGY4IMPsS20u_dVlhZUy-MTq0TWlss")
//        Call<ClosePlacesPojo> getNearbyLocations2(@Query("location")String coord,@Query("type")int food,@Query("radius")int radius);


    }
}
