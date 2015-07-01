package com.shreyaschand.bart;

import com.shreyaschand.bart.fragment.BaseFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    void inject(BaseFragment fragment);
}
