package com.batchmates.android.wavesofwrath.injection.mainactivity;

import com.batchmates.android.wavesofwrath.view.mainactivity.MainActivityPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Android on 7/17/2017.
 */
@Module
public class MainActivityModule {

    @Provides
    public MainActivityPresenter providesMainActivityPresenter()
    {
        return new MainActivityPresenter();
    }
}
