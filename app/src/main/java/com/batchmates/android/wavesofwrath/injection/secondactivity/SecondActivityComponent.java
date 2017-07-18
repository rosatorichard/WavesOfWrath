package com.batchmates.android.wavesofwrath.injection.secondactivity;

import com.batchmates.android.wavesofwrath.view.secondactivity.MapsActivity;

import dagger.Component;

/**
 * Created by Android on 7/17/2017.
 */
@Component(modules = SecondActivityModule.class)
public interface SecondActivityComponent {
    void inject(MapsActivity mapsActivity);
}
