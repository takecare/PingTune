/**
 * @date Jun 11, 2014
 * @author Rui Teixeira, rui@vazteixeira.org
 * @copyright Copyright 2014 Rui Teixeira. All rights reserved.
 * PingTune - pt.rmvt.pingtune.fragment
 */
package pt.rmvt.pingtune.fragment;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class PingTuneFragmentPagerAdapter extends FragmentPagerAdapter {

    public static final String LOG_TAG = "PingTuneFragmentPagerAdapter";

    public static final int NUM_FRAGMENTS = 2;
    public static final int COMMIT_LIST_FRAGMENT_POS = 0;
    public static final int ABOUT_FRAGMENT_POS = 1;

    private CommitListFragment mCommitListFragment;
    private AboutFragment mAboutFragment;

    public PingTuneFragmentPagerAdapter(FragmentManager fm, Resources resources) {
        super(fm);
        mCommitListFragment = CommitListFragment.newInstance(resources);
        mAboutFragment = AboutFragment.newInstance(resources);
    }

    public PingTuneFragmentPagerAdapter(Bundle savedInstance, FragmentManager fm, Resources resources) {
        super(fm);
        mCommitListFragment = CommitListFragment.newInstance(resources);
        mAboutFragment = AboutFragment.newInstance(resources);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case COMMIT_LIST_FRAGMENT_POS:
                return mCommitListFragment;
            case ABOUT_FRAGMENT_POS:
                return mAboutFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return NUM_FRAGMENTS;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case COMMIT_LIST_FRAGMENT_POS:
                return null; // TODO
            case ABOUT_FRAGMENT_POS:
                return null; // TODO;
            default:
                return null;
        }
    }


}