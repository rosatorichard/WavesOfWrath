package com.batchmates.android.wavesofwrath.view.secondactivity;

import com.batchmates.android.wavesofwrath.BasePresenter;
import com.batchmates.android.wavesofwrath.BaseView;
import com.batchmates.android.wavesofwrath.model.PlaceInformation;
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
        void placesCloseby();
        void checkEncounter(Marker myself);
    }

}
