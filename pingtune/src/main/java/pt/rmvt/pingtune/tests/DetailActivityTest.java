/**
 * @date Jun 13, 2014
 * @author Rui Teixeira, rui@vazteixeira.org
 * @copyright Copyright 2014 Rui Teixeira. All rights reserved.
 * PingTune - pt.rmvt.pingtune.tests
 */
package pt.rmvt.pingtune.tests;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;

import pt.rmvt.pingtune.R;
import pt.rmvt.pingtune.activity.DetailActivity;

public class DetailActivityTest extends ActivityInstrumentationTestCase2<DetailActivity> {

    private DetailActivity mDetailActivity;

    private TextView mNameTextView;
    private TextView mDataTextView;

    private TextView mFollowersTitleTextView;
    private TextView mFollowersValueTextView;

    private TextView mFollowingTitleTextView;
    private TextView mFollowingValueTextView;

    private TextView mStarredTitleTextView;
    private TextView mStarredValueTextView;

    private NetworkImageView mAvatarNetworkImageView;

    public DetailActivityTest(Class<DetailActivity> activityClass) {
        super(activityClass);
    }

    public DetailActivityTest() {
        this(DetailActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mDetailActivity = getActivity();

        mNameTextView = (TextView) mDetailActivity.findViewById(R.id.activityDetailNameTextView);
        mDataTextView = (TextView) mDetailActivity.findViewById(R.id.activityDetailDataTextView);

        mFollowersTitleTextView = (TextView) mDetailActivity.findViewById(R.id.activityDetailFollowersLayout)
                .findViewById(R.id.cardinalityTitleTextView);
        mFollowersValueTextView = (TextView) mDetailActivity.findViewById(R.id.activityDetailFollowersLayout)
                .findViewById(R.id.cardinalityValueTextView);

        mFollowingTitleTextView = (TextView) mDetailActivity.findViewById(R.id.activityDetailFollowingLayout)
                .findViewById(R.id.cardinalityTitleTextView);
        mFollowingValueTextView = (TextView) mDetailActivity.findViewById(R.id.activityDetailFollowingLayout)
                .findViewById(R.id.cardinalityValueTextView);;

        mStarredTitleTextView = (TextView) mDetailActivity.findViewById(R.id.activityDetailStarredLayout)
                .findViewById(R.id.cardinalityTitleTextView);
        mStarredValueTextView = (TextView) mDetailActivity.findViewById(R.id.activityDetailStarredLayout)

                .findViewById(R.id.cardinalityValueTextView);
        mAvatarNetworkImageView = (NetworkImageView) mDetailActivity.findViewById(R.id.activityDetailAvatarImageView);
    }

    public void testPreconditions() {
        assertNotNull("mDetailActivity is null", mDetailActivity);
        assertNotNull("mNameTextView is null", mNameTextView);
        assertNotNull("mDataTextView is null", mDataTextView);

        assertNotNull("mFollowersTitleTextView is null", mFollowersTitleTextView);
        assertNotNull("mFollowersValueTextView is null", mFollowersValueTextView);

        assertNotNull("mFollowingTitleTextView is null", mFollowingTitleTextView);
        assertNotNull("mFollowingValueTextView is null", mFollowingValueTextView);

        assertNotNull("mStarredTitleTextView is null", mStarredTitleTextView);
        assertNotNull("mStarredValueTextView is null", mStarredValueTextView);

        assertNotNull("mAvatarNetworkImageView is null", mAvatarNetworkImageView);
    }

    public void testFollowersTitleTextView_labelText() throws Exception {
        final CharSequence expected = getActivity().getResources()
                .getString(R.string.cardinality_followers_title);
        final CharSequence actual = mFollowersTitleTextView.getText();
        assertEquals("testFollowersTitleTextView_labelText",expected,actual);
    }

    public void testFollowingTitleTextView_labelText() throws Exception {
        final CharSequence expected = getActivity().getResources()
                .getString(R.string.cardinality_following_title);
        final CharSequence actual = mFollowingTitleTextView.getText();
        assertEquals("testFollowingTitleTextView_labelText",expected,actual);
    }

    public void testStarredTitleTextView_labelText() throws Exception {
        final CharSequence expected = getActivity().getResources()
                .getString(R.string.cardinality_starred_title);
        final CharSequence actual = mStarredTitleTextView.getText();
        assertEquals("testStarredTitleTextView_labelText",expected,actual);
    }

}