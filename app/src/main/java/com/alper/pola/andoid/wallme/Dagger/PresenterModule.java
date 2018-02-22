package com.alper.pola.andoid.wallme.Dagger;

import android.content.Context;

import com.alper.pola.andoid.wallme.Activity.Main.MainPresenter;
import com.alper.pola.andoid.wallme.Activity.Preview.PreviewPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by shashank on 01-Dec-17.
 */

@Module
public class PresenterModule {
    @Provides
    @Singleton
    PreviewPresenter previewPresenter(Context context) {
        return  new PreviewPresenter(context);
    }
    @Provides
    @Singleton
    MainPresenter MainPresenter(Context context) {
        return  new MainPresenter(context);
    }

}
