/**
 * @date Jun 12, 2014
 * @author Rui Teixeira, rui@vazteixeira.org
 * @copyright Copyright 2014 Rui Teixeira. All rights reserved.
 * PingTune - pt.rmvt.pingtune.activity
 */
package pt.rmvt.pingtune.activity;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.Window;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.squareup.otto.Subscribe;

import butterknife.ButterKnife;
import butterknife.InjectView;
import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;
import pt.rmvt.pingtune.R;
import pt.rmvt.pingtune.bus.PingTuneBus;
import pt.rmvt.pingtune.crouton.PingTuneCrouton;
import pt.rmvt.pingtune.model.Author;
import pt.rmvt.pingtune.network.PingTuneRequestManager;
import pt.rmvt.pingtune.network.requests.CardinalityRequest;
import pt.rmvt.pingtune.network.requests.PingTuneRequest;

public class DetailActivity extends ActionBarActivity implements PingTuneRequest.PingTuneErrorListener {

    public static final String LOG_TAG = "DetailActivity";

    private Author mAuthor;

    private PingTuneRequestManager mRequestManager;
    private CardinalityRequest mFollowersRequest;
    private boolean mFinishedFollowersRequest;
    private CardinalityRequest mFollowingRequest;
    private boolean mFinishedFollowingRequest;
    private CardinalityRequest mStarredRequest;
    private boolean mFinishedStarredRequest;

    @InjectView(R.id.activityDetailNameTextView)
    public TextView mNameTextView;
    @InjectView(R.id.activityDetailDataTextView)
    public TextView mDataTextView;
    public TextView mFollowersTitleTextView;
    public TextView mFollowersValueTextView;
    public TextView mFollowingTitleTextView;
    public TextView mFollowingValueTextView;
    public TextView mStarredTitleTextView;
    public TextView mStarredValueTextView;
    private NetworkImageView mAvatarNetworkImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mRequestManager = new PingTuneRequestManager();
        mRequestManager.setup(this);

        supportRequestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);

        setContentView(R.layout.activity_detail);
        ButterKnife.inject(this);

        mAvatarNetworkImageView = ButterKnife.findById(this,R.id.activityDetailAvatarImageView);
        mFollowersTitleTextView = ButterKnife.findById(
                ButterKnife.findById(this,R.id.activityDetailFollowersLayout),
                R.id.cardinalityTitleTextView);
        mFollowersValueTextView = ButterKnife.findById(
                ButterKnife.findById(this,R.id.activityDetailFollowersLayout),
                R.id.cardinalityValueTextView);
        mFollowingTitleTextView = ButterKnife.findById(
                ButterKnife.findById(this,R.id.activityDetailFollowingLayout),
                R.id.cardinalityTitleTextView);
        mFollowingValueTextView = ButterKnife.findById(
                ButterKnife.findById(this,R.id.activityDetailFollowingLayout),
                R.id.cardinalityValueTextView);
        mStarredTitleTextView = ButterKnife.findById(
                ButterKnife.findById(this,R.id.activityDetailStarredLayout),
                R.id.cardinalityTitleTextView);
        mStarredValueTextView = ButterKnife.findById(
                ButterKnife.findById(this,R.id.activityDetailStarredLayout),
                R.id.cardinalityValueTextView);

        // actionbar config
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        PingTuneBus.getBusInstance().register(this);

        if (getIntent() != null &&
                (mAuthor=getIntent().getParcelableExtra(Author.AUTHOR_PARCELABLE_KEY)) != null) {

            mNameTextView.setText(mAuthor.getName());
            mDataTextView.setText(mAuthor.getEmail());
            mAvatarNetworkImageView.setImageUrl(
                    mAuthor.getAvatarUrl(),
                    PingTuneRequestManager.getImageLoaderInstance());

            mFollowersTitleTextView.setText(getResources().getString(R.string.cardinality_followers_title));
            mFollowingTitleTextView.setText(getResources().getString(R.string.cardinality_following_title));
            mStarredTitleTextView.setText(getResources().getString(R.string.cardinality_starred_title));

            if (mAuthor.getFollowersUrl() != null) {
                setSupportProgressBarIndeterminateVisibility(true);
                mFollowersRequest = new CardinalityRequest(mAuthor.getFollowersUrl());
                mFollowersRequest.setErrorListener(this);
                mFollowersRequest.setResposeListener(new PingTuneRequest.PingTuneResponseListener<Integer>() {
                    @Override
                    public void onResponse(Integer obj) {
                        mFinishedFollowersRequest = true;
                        setSupportProgressBarIndeterminateVisibility(!(mFinishedFollowingRequest
                                && mFinishedStarredRequest));
                        if (mFollowersValueTextView != null)
                            mFollowersValueTextView.setText(String.valueOf(obj));
                    }
                });
                mRequestManager.executeRequest(mFollowersRequest);
            }

            if (mAuthor.getFollowingUrl() != null) {
                setSupportProgressBarIndeterminateVisibility(true);
                mFollowingRequest = new CardinalityRequest(mAuthor.getFollowingUrl());
                mFollowingRequest.setErrorListener(this);
                mFollowingRequest.setResposeListener(new PingTuneRequest.PingTuneResponseListener<Integer>() {
                    @Override
                    public void onResponse(Integer obj) {
                        mFinishedFollowingRequest = true;
                        setSupportProgressBarIndeterminateVisibility(!(mFinishedFollowersRequest
                                && mFinishedStarredRequest));
                        if (mFollowingValueTextView != null)
                            mFollowingValueTextView.setText(String.valueOf(obj));
                    }
                });
                mRequestManager.executeRequest(mFollowingRequest);
            }

            if (mAuthor.getStarredUrl() != null) {
                setSupportProgressBarIndeterminateVisibility(true);
                mStarredRequest = new CardinalityRequest(mAuthor.getStarredUrl());
                mStarredRequest.setErrorListener(this);
                mStarredRequest.setResposeListener(new PingTuneRequest.PingTuneResponseListener<Integer>() {
                    @Override
                    public void onResponse(Integer obj) {
                        mFinishedStarredRequest = true;
                        setSupportProgressBarIndeterminateVisibility(!(mFinishedFollowersRequest
                                && mFinishedFollowingRequest));
                        if (mStarredValueTextView != null)
                            mStarredValueTextView.setText(String.valueOf(obj));
                    }
                });
                mRequestManager.executeRequest(mStarredRequest);
            }

        } else {
            PingTuneBus.getBusInstance().post(new PingTuneCrouton(
                    Style.ALERT,
                    getResources().getString(R.string.detail_activity_bundle_error)));
            setSupportProgressBarVisibility(false);
        }

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();

        Crouton.cancelAllCroutons();

        if (mFollowersRequest != null)
            mRequestManager.cancelRequest(mFollowersRequest);

        if (mFollowingRequest != null)
            mRequestManager.cancelRequest(mFollowingRequest);

        if (mStarredRequest != null)
            mRequestManager.cancelRequest(mStarredRequest);
    }

    // MENU
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onError(String errorMessage, int statusCode) {
        PingTuneBus.getBusInstance().post(new PingTuneCrouton(
                Style.ALERT,
                getResources().getString(R.string.detail_activity_cardinality_error)));
        Log.e(LOG_TAG, statusCode+": "+errorMessage);
        setSupportProgressBarIndeterminateVisibility(false);
    }


    @Subscribe
    public void onPingTuneCrouton(PingTuneCrouton crouton) {
        Crouton.makeText(this, crouton.getMessage(), crouton.getStyle())
                .show();
    }
}