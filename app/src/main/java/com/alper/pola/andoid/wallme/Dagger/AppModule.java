package com.alper.pola.andoid.wallme.Dagger;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by shashank on 01-Dec-17.
 */


    @Module
    public class AppModule {
        private Application application;

        public AppModule(Application application) {
            this.application = application;
        }

        @Provides
        @Singleton
        public Context provideContext() {
            return application;
        }

}
