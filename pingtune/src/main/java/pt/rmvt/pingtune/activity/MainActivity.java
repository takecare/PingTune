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

import butterknife.ButterKnife;
import butterknife.InjectView;
import pt.rmvt.pingtune.R;
import pt.rmvt.pingtune.dao.AuthorDAO;
import pt.rmvt.pingtune.dao.IDataAccessObject;
import pt.rmvt.pingtune.fragment.BaseFragment;
import pt.rmvt.pingtune.fragment.PingTuneFragmentPagerAdapter;
import pt.rmvt.pingtune.model.Author;
import pt.rmvt.pingtune.model.Commit;
import pt.rmvt.pingtune.network.PingTuneRequestManager;
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

        //testProvider();
        //testAsyncQuery();
        testRequest();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
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

    // ***** **** *** ** *

    private void testProvider() {
        AuthorContentValues values = new AuthorContentValues();
        values.putEmail("rui@vazteixeira.org");
        values.putName("Rui Teixeira");
        values.putDateNull();
        getContentResolver().insert(values.uri(),values.values());

        AuthorSelection where = new AuthorSelection();
        where.email("rui@vazteixeira.org");
        Cursor cursor = getContentResolver().query(
                AuthorColumns.CONTENT_URI,
                AuthorColumns.FULL_PROJECTION,
                where.sel(),
                where.args(),
                null);

        String name = null;

        if (cursor != null && cursor.getCount()>0) {
            cursor.moveToFirst();
            name = cursor.getString(cursor.getColumnIndex(AuthorColumns.NAME));
            Log.d(LOG_TAG,"name="+name);
        } else {
            Log.d(LOG_TAG,"author query returned no results");
        }

        //

        CommitContentValues commitContentValues = new CommitContentValues();
        commitContentValues.putAuthorname(name);
        commitContentValues.putHtmlurl("html url");
        commitContentValues.putUrl("url");
        commitContentValues.putSha("super rad sha");
        commitContentValues.putParentsha("parent sha");
        getContentResolver().insert(commitContentValues.uri(), commitContentValues.values());

        CommitSelection commitSelection = new CommitSelection();
        commitSelection.authorname(name);
        Cursor commitCursor = getContentResolver().query(
                CommitColumns.CONTENT_URI,
                CommitColumns.FULL_PROJECTION,
                commitSelection.sel(),
                commitSelection.args(),
                null);

        if (commitCursor != null && commitCursor.getCount()>0) {
            commitCursor.moveToFirst();
            Log.d(LOG_TAG,"sha="+commitCursor.getString(commitCursor.getColumnIndex(CommitColumns.SHA)));
        } else {
            Log.d(LOG_TAG,"commit query returned no results");
        }
    }

    private void testAsyncQuery() {
        AuthorDAO authorDAO = new AuthorDAO(getContentResolver());
        authorDAO.readByName(
                "Rui Teixeira",
                new IDataAccessObject.IReadListener<Author>() {
                    @Override
                    public void onReadFinished(Cursor cursor) {
                        if (cursor != null && cursor.getCount()>0) {
                            cursor.moveToFirst();
                            Log.d(LOG_TAG,"async name="+cursor.getString(cursor.getColumnIndex(AuthorColumns.NAME)));
                        } else {
                            Log.d(LOG_TAG,"async author query returned no results");
                        }
                    }
                });
    }

    private void testRequest() {

        PingTuneRequestManager requestManager = new PingTuneRequestManager();
        requestManager.setup(this);

        CommitRequest request = new CommitRequest();
        requestManager.executeRequest(
                request,
                new PingTuneRequest.PingTuneResponseListener<List<Commit>>() {
                    @Override
                    public void onResponse(List<Commit> list) {
                        Log.d(LOG_TAG,"RECEIVED RESPONSE: "+list.size());
                        HashMap<Author,List<Commit>> commitsByAuthor = group(list);
                        for (Author author : commitsByAuthor.keySet()) {
                            Log.d(LOG_TAG,author.getName());
                        }
                    }
                },
                new PingTuneRequest.PingTuneErrorListener() {
                    @Override
                    public void onError(String errorMessage, int statusCode) {
                        Log.d(LOG_TAG,"ERROR");
                    }
                }
        );
    }

    public HashMap<Author,List<Commit>> group(List<Commit> commits) {

        HashMap<Author,List<Commit>> commitsByAuthor = new HashMap<Author, List<Commit>>();

        for (Commit commit : commits) {

            if (!commitsByAuthor.containsKey(commit.getAuthor())) {
                commitsByAuthor.put(commit.getAuthor(),new ArrayList<Commit>());
            } else {
                commitsByAuthor.get(commit.getAuthor()).add(commit);
            }
        }

        return commitsByAuthor;
    }
}
