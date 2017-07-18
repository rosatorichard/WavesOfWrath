package com.batchmates.android.wavesofwrath.view.mainactivity;

import android.content.Context;
import android.view.View;

import com.batchmates.android.wavesofwrath.BasePresenter;
import com.batchmates.android.wavesofwrath.BaseView;
import com.batchmates.android.wavesofwrath.model.YourShip;

/**
 * Created by Android on 7/17/2017.
 */

public interface MainActivityContract {

    interface View extends BaseView
    {
        void readyToSetSail(YourShip yourShip);
    }

    interface Presenter extends BasePresenter<View>
    {
        void setUpCaptain(String shipName, String captainName);
        void killcaptain();
    }

}
