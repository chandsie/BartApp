package com.shreyaschand.bart.fragment;

import android.os.Bundle;

public class FavoritesFragment extends BaseFragment {

    public static FavoritesFragment newInstance() {
        Bundle args = new Bundle();

        FavoritesFragment fragment = new FavoritesFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
