package com.example.boylc.assmob403.uis.activitys;

import android.content.res.Configuration;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.boylc.assmob403.R;
import com.example.boylc.assmob403.uis.fragments.AboutUsFragment;
import com.example.boylc.assmob403.uis.fragments.CategoryFragment;
import com.example.boylc.assmob403.uis.fragments.FavoritesFragment;
import com.example.boylc.assmob403.uis.fragments.GifsFragment;
import com.example.boylc.assmob403.uis.fragments.LastestFragment;
import com.example.boylc.assmob403.uis.fragments.SettingFragment;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout mDrawer;
    private android.support.v7.widget.Toolbar toolbar;
    private NavigationView nvDrawer;

    // Make sure to be using android.support.v7.app.ActionBarDrawerToggle version.
// The android.support.v4.app.ActionBarDrawerToggle has been deprecated.
    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set a Toolbar to replace the ActionBar.
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            toolbar.setNavigationIcon(R.drawable.ic_humburger);
        }

        // Find our drawer view
        mDrawer = findViewById(R.id.drawer_layout);
        drawerToggle = setupDrawerToggle();

        // Tie DrawerLayout events to the ActionBarToggle
        mDrawer.addDrawerListener(drawerToggle);

        nvDrawer = findViewById(R.id.nvView);
        // Setup drawer view
        setupDrawerContent(nvDrawer);
        // add lastest fragment when first time
        selectDrawerItem(nvDrawer.getMenu().getItem(0));

    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }

    public void selectDrawerItem(MenuItem menuItem) {
        // Create a new fragment and specify the fragment to show based on nav item clicked
        //todo
        Fragment fragment = null;
        Class fragmentClass = null;
        switch (menuItem.getItemId()) {
            case R.id.nav_home_fragment:
                fragmentClass = LastestFragment.class;
                break;
            case R.id.nav_category_fragment:
                fragmentClass = CategoryFragment.class;
                break;
            case R.id.nav_gift_fragment:
                fragmentClass = GifsFragment.class;
                break;
            case R.id.nav_favorite_fragment:
                fragmentClass = FavoritesFragment.class;
                break;
            case R.id.nav_about_us_fragment:
                fragmentClass = AboutUsFragment.class;
                break;
            case R.id.nav_setting_fragment:
                fragmentClass = SettingFragment.class;
                break;
            case R.id.nav_rate_app:
                Toast.makeText(this, "Rate App", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_more_app:
                Toast.makeText(this, "More App", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_privacy:
                Toast.makeText(this, "Privacy ", Toast.LENGTH_SHORT).show();
                break;
            default:
                fragmentClass = LastestFragment.class;
        }
        if (fragmentClass != null) {
            try {
                fragment = (Fragment) fragmentClass.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
            // Insert the fragment by replacing any existing fragment
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
        }

        // Highlight the selected item has been done by NavigationView
        menuItem.setChecked(true);
        // Set action bar title
        setTitle(menuItem.getTitle());
        // Close the navigation drawer
        mDrawer.closeDrawers();
    }

    private ActionBarDrawerToggle setupDrawerToggle() {
        // NOTE: Make sure you pass in a valid toolbar reference.  ActionBarDrawToggle() does not require it
        // and will not render the hamburger icon without it.
        return new ActionBarDrawerToggle(this, mDrawer, toolbar, R.string.drawer_open, R.string.drawer_close);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return drawerToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

}
