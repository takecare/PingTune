/**
 * @date Jun 13, 2014
 * @author Rui Teixeira, rui@vazteixeira.org
 * @copyright Copyright 2014 Rui Teixeira. All rights reserved.
 * PingTune - pt.rmvt.pingtune.tests
 */
package pt.rmvt.pingtune.tests;

import android.content.Intent;
import android.test.ActivityUnitTestCase;
import android.test.suitebuilder.annotation.MediumTest;
import android.widget.TextView;

import pt.rmvt.pingtune.activity.DetailActivity;
import pt.rmvt.pingtune.model.Author;

public class LaunchDetailActivityTestCase extends ActivityUnitTestCase<DetailActivity> {

    private Intent mLaunchIntent;
    private DetailActivity mDetailActivity;
    private TextView mNameTextView;
    private Author mAuthor;

    public LaunchDetailActivityTestCase(Class<DetailActivity> activityClass) {
        super(activityClass);
    }

    public LaunchDetailActivityTestCase() {
        this(DetailActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mAuthor = new Author("name","email","avatarurl","date");
        mLaunchIntent = new Intent(getInstrumentation().getTargetContext(), DetailActivity.class);
        mLaunchIntent.putExtra(Author.AUTHOR_PARCELABLE_KEY, mAuthor);
        startActivity(mLaunchIntent, null, null);
    }

    @MediumTest
    public void testNextActivityWasLaunchedWithIntent() {
        startActivity(mLaunchIntent, null, null);

        final Intent launchIntent = getStartedActivityIntent();
        assertNotNull("Intent was null", launchIntent);
        assertTrue(isFinishCalled());

        Author author = launchIntent.getParcelableExtra(Author.AUTHOR_PARCELABLE_KEY);
        assertEquals("testNextActivityWasLaunchedWithIntent",mAuthor,author);
    }

}