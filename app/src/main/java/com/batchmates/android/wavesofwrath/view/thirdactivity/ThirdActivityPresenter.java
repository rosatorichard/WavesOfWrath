package com.batchmates.android.wavesofwrath.view.thirdactivity;

import com.batchmates.android.wavesofwrath.view.secondactivity.SecondActivityContract;

/**
 * Created by Android on 7/18/2017.
 */

public class ThirdActivityPresenter implements ThirdActivityContract.Presenter{

    ThirdActivityContract.View view;
    public void addView(ThirdActivityContract.View view) {
        this.view=view;
    }

    @Override
    public void removeView() {
        this.view=null;
    }
}
