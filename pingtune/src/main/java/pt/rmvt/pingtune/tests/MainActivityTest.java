/**
 * @date Jun 13, 2014
 * @author Rui Teixeira, rui@vazteixeira.org
 * @copyright Copyright 2014 Rui Teixeira. All rights reserved.
 * PingTune - pt.rmvt.pingtune.tests
 */
package pt.rmvt.pingtune.tests;

import android.support.v4.view.ViewPager;
import android.test.ActivityInstrumentationTestCase2;

import pt.rmvt.pingtune.R;
import pt.rmvt.pingtune.activity.MainActivity;
import pt.rmvt.pingtune.fragment.PingTuneFragmentPagerAdapter;

public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {

    private MainActivity mMainActivity;
    public ViewPager mViewPager;

    public MainActivityTest(Class<MainActivity> activityClass) {
        super(activityClass);
    }

    public MainActivityTest() {
        this(MainActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mMainActivity = getActivity();
        mViewPager = (ViewPager) mMainActivity.findViewById(R.id.pager);
    }

    public void testPreconditions() {
        assertNotNull("mMainActivity is null", mMainActivity);
        assertNotNull("mViewPager is null", mViewPager);
    }

    public void testViewPagerNumberOfViews() throws Exception {
        final int expected = PingTuneFragmentPagerAdapter.NUM_FRAGMENTS;
        final int actual = mViewPager.getAdapter().getCount();
        assertEquals(expected, actual);
    }

}