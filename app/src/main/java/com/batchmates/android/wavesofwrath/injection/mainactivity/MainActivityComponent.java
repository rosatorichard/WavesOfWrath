package com.batchmates.android.wavesofwrath.injection.mainactivity;

import com.batchmates.android.wavesofwrath.view.mainactivity.MainActivity;

import dagger.Component;
import dagger.Provides;

/**
 * Created by Android on 7/17/2017.
 */
@Component(modules = MainActivityModule.class)
public interface MainActivityComponent {
    void inject(MainActivity mainActivity);
}
