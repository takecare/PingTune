/**
 * @date Jun 11, 2014
 * @author Rui Teixeira, rui@vazteixeira.org
 * @copyright Copyright 2014 Rui Teixeira. All rights reserved.
 * PingTune - pt.rmvt.pingtune.fragment
 */
package pt.rmvt.pingtune.fragment;

import android.content.res.Resources;
import android.os.Bundle;

public class CommitListFragment extends BaseFragment {

    public static final String LOG_TAG = "CommitListFragment";

    public CommitListFragment() {}

    public static CommitListFragment newInstance(Resources resources) {

        CommitListFragment fragment = new CommitListFragment();
/*
        String title = resources.getString(R.string.commitListFragmentTitle);

        Bundle arguments = new Bundle();
        arguments.putString(KEY_ARGUMENT_FRAGMENT_TITLE,title);
        fragment.setArguments(arguments);
*/
        return fragment;
    }

    @Override
    public CharSequence getTitle() {
        return null;
    }
}