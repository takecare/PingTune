/**
 * @date Jun 11, 2014
 * @author Rui Teixeira, rui@vazteixeira.org
 * @copyright Copyright 2014 Rui Teixeira. All rights reserved.
 * PingTune - pt.rmvt.pingtune.fragment
 */
package pt.rmvt.pingtune.fragment;

import android.support.v4.app.Fragment;

public abstract class BaseFragment extends Fragment {

    public static final String LOG_TAG = "BaseFragment";

    public static final String KEY_ARGUMENT_FRAGMENT_TITLE = "KEY_ARGUMENT_FRAGMENT_TITLE";

    public abstract CharSequence getTitle();
}