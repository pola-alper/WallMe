package com.alper.pola.andoid.wallme.Dagger;

import com.alper.pola.andoid.wallme.Activity.Main.MainActivity;
import com.alper.pola.andoid.wallme.Activity.Main.MainPresenter;
import com.alper.pola.andoid.wallme.Activity.Preview.PreviewActivity;
import com.alper.pola.andoid.wallme.Activity.Preview.PreviewPresenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by shashank on 01-Dec-17.
 */

@Singleton
@Component(modules = {AppModule.class, PresenterModule.class})
public interface AppComponent {

    void inject(PreviewPresenter target);
    void inject(PreviewActivity target);
    void inject(MainActivity target);

    void inject(MainPresenter target);

}