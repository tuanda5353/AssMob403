package com.example.boylc.assmob403.uis.activitys;

import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.net.Uri;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.boylc.assmob403.R;
import com.example.boylc.assmob403.common.Constant;
import com.example.boylc.assmob403.model.AboutUs;
import com.example.boylc.assmob403.model.AboutUsResponse;
import com.example.boylc.assmob403.rest.ApiClient;
import com.example.boylc.assmob403.rest.ApiInterface;
import com.example.boylc.assmob403.uis.fragments.AboutUsFragment;
import com.example.boylc.assmob403.uis.fragments.CategoryFragment;
import com.example.boylc.assmob403.uis.fragments.FavoritesFragment;
import com.example.boylc.assmob403.uis.fragments.GifsFragment;
import com.example.boylc.assmob403.uis.fragments.LastestFragment;
import com.example.boylc.assmob403.uis.fragments.SettingFragment;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
//            case R.id.nav_setting_fragment:
//                fragmentClass = SettingFragment.class;
//                break;
//            case R.id.nav_rate_app:
//                rateApp();
//                break;
//            case R.id.nav_more_app:
//                openAppRating(this);
//                break;
            case R.id.nav_privacy:
                showDialogPrivacy();
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
            fragmentManager.beginTransaction().setCustomAnimations(R.anim.slide_in_left,R.anim.exit_from_right).replace(R.id.flContent, fragment).commit();
        }

        // Highlight the selected item has been done by NavigationView
        menuItem.setChecked(true);
        // Set action bar title
        toolbar.setTitle(menuItem.getTitle());
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

    void showDialogPrivacy() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_privacy);
//        dialog.setTitle("Privacy Policy");
        final TextView txtPrivacy = dialog.findViewById(R.id.txt_privacy);
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<AboutUsResponse> call = apiService.getAboutUs();
        call.enqueue(new Callback<AboutUsResponse>() {
            @Override
            public void onResponse(Call<AboutUsResponse> call, Response<AboutUsResponse> response) {
                if (Constant.isDebug)
                    Log.d("getAboutUs", "onResponse: " + response.body());

                AboutUs aboutUs = response.body().getHDWALLPAPER().get(0);
                txtPrivacy.setText(Html.fromHtml(aboutUs.getAppPrivacyPolicy()));

            }

            @Override
            public void onFailure(Call<AboutUsResponse> call, Throwable t) {

            }
        });
        dialog.show();
    }

    public void rateApp() {
        final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
        } catch (android.content.ActivityNotFoundException anfe) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
        }
    }

    public static void openAppRating(Context context) {
        // you can also use BuildConfig.APPLICATION_ID
        String appId = context.getPackageName();
        Intent rateIntent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("market://details?id=" + appId));
        boolean marketFound = false;

        // find all applications able to handle our rateIntent
        final List<ResolveInfo> otherApps = context.getPackageManager()
                .queryIntentActivities(rateIntent, 0);
        for (ResolveInfo otherApp : otherApps) {
            // look for Google Play application
            if (otherApp.activityInfo.applicationInfo.packageName
                    .equals("com.android.vending")) {

                ActivityInfo otherAppActivity = otherApp.activityInfo;
                ComponentName componentName = new ComponentName(
                        otherAppActivity.applicationInfo.packageName,
                        otherAppActivity.name
                );
                // make sure it does NOT open in the stack of your activity
                rateIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                // task reparenting if needed
                rateIntent.addFlags(Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
                // if the Google Play was already open in a search result
                //  this make sure it still go to the app page you requested
                rateIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                // this make sure only the Google Play app is allowed to
                // intercept the intent
                rateIntent.setComponent(componentName);
                context.startActivity(rateIntent);
                marketFound = true;
                break;

            }
        }
    }

}
