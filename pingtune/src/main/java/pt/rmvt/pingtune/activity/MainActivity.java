/**
 * @date Jun 11, 2014
 * @author Rui Teixeira, rui@vazteixeira.org
 * @copyright Copyright 2014 Rui Teixeira. All rights reserved.
 * PingTune - pt.rmvt.pingtune.activity
 */
package pt.rmvt.pingtune.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.squareup.otto.Subscribe;

import butterknife.ButterKnife;
import butterknife.InjectView;
import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;
import pt.rmvt.pingtune.R;
import pt.rmvt.pingtune.bus.PingTuneBus;
import pt.rmvt.pingtune.crouton.PingTuneCrouton;
import pt.rmvt.pingtune.dao.AuthorDAO;
import pt.rmvt.pingtune.dao.IDataAccessObject;
import pt.rmvt.pingtune.datamanager.PingTuneDataManager;
import pt.rmvt.pingtune.fragment.BaseFragment;
import pt.rmvt.pingtune.fragment.PingTuneFragmentPagerAdapter;
import pt.rmvt.pingtune.model.Author;
import pt.rmvt.pingtune.model.Commit;
import pt.rmvt.pingtune.network.PingTuneRequestManager;
import pt.rmvt.pingtune.network.requests.CardinalityRequest;
import pt.rmvt.pingtune.network.requests.CommitRequest;
import pt.rmvt.pingtune.network.requests.PingTuneRequest;
import pt.rmvt.pingtune.storage.provider.PingTuneAsyncQueryHandler;
import pt.rmvt.pingtune.storage.provider.author.AuthorColumns;
import pt.rmvt.pingtune.storage.provider.author.AuthorContentValues;
import pt.rmvt.pingtune.storage.provider.author.AuthorSelection;
import pt.rmvt.pingtune.storage.provider.commit.CommitColumns;
import pt.rmvt.pingtune.storage.provider.commit.CommitContentValues;
import pt.rmvt.pingtune.storage.provider.commit.CommitCursor;
import pt.rmvt.pingtune.storage.provider.commit.CommitSelection;

public class MainActivity extends ActionBarActivity implements ActionBar.TabListener, ViewPager.OnPageChangeListener {

    public static final String LOG_TAG = "MainActivity";

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    @InjectView(R.id.pager)
    public ViewPager mViewPager;
    private PingTuneFragmentPagerAdapter mFragmentPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        supportRequestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);

        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        // actionbar config
        getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(false);
        //setSupportProgressBarVisibility(true);

        mViewPager.setOnPageChangeListener(this);

        mFragmentPagerAdapter = new PingTuneFragmentPagerAdapter(
                getSupportFragmentManager(),
                getResources());
        mViewPager.setAdapter(mFragmentPagerAdapter);

        // add tabs...
        for (int i=0; i<PingTuneFragmentPagerAdapter.NUM_FRAGMENTS; i++) {

            getSupportActionBar().addTab(
                    getSupportActionBar().newTab()
                    .setText(((BaseFragment)mFragmentPagerAdapter.getItem(i)).getTitle())
                    .setTabListener(this));
        }

        PingTuneBus.getBusInstance().register(this);

        PingTuneDataManager.getInstance().setup(this);
        PingTuneDataManager.getInstance().update(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        Crouton.cancelAllCroutons();
    }

    // actionbar tab listener
    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {}

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {}

    // viewpager change listener
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

    @Override
    public void onPageSelected(int position) {
        getSupportActionBar().setSelectedNavigationItem(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {}

    @Subscribe
    public void onPingTuneCrouton(PingTuneCrouton crouton) {
        Crouton.makeText(this, crouton.getMessage(), crouton.getStyle())
                .show();
        setSupportProgressBarIndeterminateVisibility(false);
    }
}
