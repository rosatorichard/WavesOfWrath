package com.batchmates.android.wavesofwrath;

/**
 * Created by Android on 7/17/2017.
 */

public interface BasePresenter <V extends BaseView>{
    void addView(V view);

    void removeView();
}
