package com.shreyaschand.bart.activity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.shreyaschand.bart.R;
import com.shreyaschand.bart.fragment.ManualRequestFragment;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity {

    private static final long DRAWER_CLOSE_DELAY_MS = 250;
    private static final String NAV_ITEM_ID = "navItemId";

    private final Handler drawerActionHandler = new Handler();
    private ActionBarDrawerToggle drawerToggle;
    private int navItemId;

    NavigationView.OnNavigationItemSelectedListener navListener =
            new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(final MenuItem menuItem) {
                    menuItem.setChecked(true);
                    navItemId = menuItem.getItemId();

                    drawerLayout.closeDrawer(GravityCompat.START);
                    drawerActionHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            navigate(menuItem.getItemId());
                        }
                    }, DRAWER_CLOSE_DELAY_MS);
                    return true;
                }
            };

    @InjectView(R.id.drawer_layout) DrawerLayout drawerLayout;
    @InjectView(R.id.navigation_drawer) NavigationView navView;
    @InjectView(R.id.toolbar) Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        setSupportActionBar(toolbar);

        if (null == savedInstanceState) {
            navItemId = R.id.nav_item_debug;
        } else {
            navItemId = savedInstanceState.getInt(NAV_ITEM_ID);
        }
        // select the correct nav menu item
        navView.getMenu().findItem(navItemId).setChecked(true);

        // set up the hamburger icon to open and close the drawer
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.setDrawerListener(drawerToggle);
        drawerToggle.syncState();
        navigate(navItemId);
        navView.setNavigationItemSelectedListener(navListener);

    }

    private void navigate(int menuItem) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            int titleRes = 0;
            switch (menuItem) {
                case R.id.nav_item_favorites:
                    titleRes = R.string.favorites;
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.content_fragment_container, null)
                            .commit();
                    break;
                case R.id.nav_item_departures:
                    titleRes = R.string.real_time_departures;
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.content_fragment_container, null)
                            .commit();
                    break;
                case R.id.nav_item_debug:
                    titleRes = R.string.debug;
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.content_fragment_container, new ManualRequestFragment())
                            .addToBackStack(null)
                            .commit();
                    break;
            }
            actionBar.setTitle(getResources().getString(titleRes));
        }
    }

    @Override
    public void onConfigurationChanged(final Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.support.v7.appcompat.R.id.home) {
            return drawerToggle.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onSaveInstanceState(final Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(NAV_ITEM_ID, navItemId);
    }
}
