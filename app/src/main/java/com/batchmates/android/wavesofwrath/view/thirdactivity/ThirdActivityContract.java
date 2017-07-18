package com.batchmates.android.wavesofwrath.view.thirdactivity;

import com.batchmates.android.wavesofwrath.BasePresenter;
import com.batchmates.android.wavesofwrath.BaseView;
import com.batchmates.android.wavesofwrath.view.secondactivity.SecondActivityContract;

/**
 * Created by Android on 7/18/2017.
 */

public interface ThirdActivityContract {

    interface View extends BaseView
    {

    }

    interface Presenter extends BasePresenter<ThirdActivityContract.View>
    {

    }
}
