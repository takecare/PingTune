/**
 * @date Jun 12, 2014
 * @author Rui Teixeira, rui@vazteixeira.org
 * @copyright Copyright 2014 Rui Teixeira. All rights reserved.
 * PingTune - pt.rmvt.pingtune.activity
 */
package pt.rmvt.pingtune.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.Window;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import pt.rmvt.pingtune.R;
import pt.rmvt.pingtune.model.Author;

public class DetailActivity extends ActionBarActivity {

    public static final String LOG_TAG = "DetailActivity";

    private Author mAuthor;

    private NetworkImageView mAvatarNetworkImageView;
    //@InjectView(R.id.)
    public TextView mNameTextView;
    public TextView mDataTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        supportRequestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);

        setContentView(R.layout.activity_detail);
        ButterKnife.inject(this);

        // actionbar config
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        if (getIntent() != null) {
            mAuthor = getIntent().getParcelableExtra(Author.AUTHOR_PARCELABLE_KEY);
        } else {
            // TODO ERROR!
        }

    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onPause() {
        super.onPause();

    }

    //
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}