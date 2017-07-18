package com.batchmates.android.wavesofwrath.injection.thirdactivity;

import com.batchmates.android.wavesofwrath.view.thirdactivity.BattleActivity;

import dagger.Component;

/**
 * Created by Android on 7/18/2017.
 */
@Component(modules = ThirdActivityModule.class)
public interface ThirdActivityComponent {
    void inject(BattleActivity battleActivity);
}
