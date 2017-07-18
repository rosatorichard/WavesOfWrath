package com.batchmates.android.wavesofwrath.view.thirdactivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.batchmates.android.wavesofwrath.R;
import com.batchmates.android.wavesofwrath.injection.thirdactivity.DaggerThirdActivityComponent;

import javax.inject.Inject;

public class BattleActivity extends AppCompatActivity implements ThirdActivityContract.View{


    @Inject ThirdActivityPresenter presenter;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle);

        DaggerThirdActivityComponent.create().inject(this);
    }

    @Override
    public void showError(String error) {

    }
}
