package com.batchmates.android.wavesofwrath.injection.secondactivity;

import com.batchmates.android.wavesofwrath.view.secondactivity.SecondActivityPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Android on 7/17/2017.
 */
@Module
public class SecondActivityModule {

    @Provides
    public SecondActivityPresenter secondActivityPresenter()
    {
        return new SecondActivityPresenter();
    }
}
