package com.batchmates.android.wavesofwrath.view.mainactivity;

import com.batchmates.android.wavesofwrath.model.YourShip;

/**
 * Created by Android on 7/17/2017.
 */

public class MainActivityPresenter implements MainActivityContract.Presenter {

    MainActivityContract.View view;
    public void addView(MainActivityContract.View view) {
        this.view=view;
    }

    @Override
    public void removeView() {
        this.view=null;
    }

    @Override
    public void setUpCaptain(String shipName, String captainName) {
        YourShip yourShip= YourShip.getInstance(shipName,captainName,"Wood plank",0,1);
        view.readyToSetSail(yourShip);
    }

    @Override
    public void killcaptain() {
        YourShip.setYourShip(null);
    }
}
