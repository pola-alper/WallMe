package com.alper.pola.andoid.wallme.App;

import android.app.Application;

import com.alper.pola.andoid.wallme.Dagger.AppComponent;
import com.alper.pola.andoid.wallme.Dagger.AppModule;
import com.alper.pola.andoid.wallme.Dagger.DaggerAppComponent;

/**
 * Created by pola alper on 18-Feb-18.
 */

public class WallMeApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = initDagger(this);
    }

    private AppComponent appComponent;

    public AppComponent getAppComponent() {
        return appComponent;
    }



    protected AppComponent initDagger(WallMeApplication application) {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(application))
                .build();
    }
}

