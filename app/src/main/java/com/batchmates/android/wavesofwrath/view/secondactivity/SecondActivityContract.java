package com.batchmates.android.wavesofwrath.view.secondactivity;

import android.graphics.drawable.BitmapDrawable;

import com.batchmates.android.wavesofwrath.BasePresenter;
import com.batchmates.android.wavesofwrath.BaseView;
import com.batchmates.android.wavesofwrath.model.PlaceInformation;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

import java.util.List;

/**
 * Created by Android on 7/17/2017.
 */

public interface SecondActivityContract {

    interface View extends BaseView
    {
        void returnedPlaces(List<PlaceInformation> placeInformations);
    }

    interface  Presenter extends BasePresenter<View>
    {
        void placesCloseby(LatLng latLng);
        void checkEncounter(Marker myself);
        void setMarkersOnMap(GoogleMap mMap,List<PlaceInformation> placeInformations,BitmapDrawable bitmapdraw);
    }

}
