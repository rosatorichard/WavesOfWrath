package com.batchmates.android.wavesofwrath.injection.thirdactivity;

import com.batchmates.android.wavesofwrath.view.thirdactivity.ThirdActivityPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Android on 7/18/2017.
 */
@Module
public class ThirdActivityModule {
    @Provides
    public ThirdActivityPresenter thirdActivityPresenter()
    {
        return new ThirdActivityPresenter();
    }

}
