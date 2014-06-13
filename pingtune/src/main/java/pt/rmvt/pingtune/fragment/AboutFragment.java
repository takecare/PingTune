/**
 * @date Jun 11, 2014
 * @author Rui Teixeira, rui@vazteixeira.org
 * @copyright Copyright 2014 Rui Teixeira. All rights reserved.
 * PingTune - pt.rmvt.pingtune.fragment
 */
package pt.rmvt.pingtune.fragment;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pt.rmvt.pingtune.R;

public class AboutFragment extends BaseFragment {

    public static final String LOG_TAG = "AboutFragment";

    public AboutFragment() {}

    public static AboutFragment newInstance(Resources resources) {

        AboutFragment fragment = new AboutFragment();

        String title = resources.getString(R.string.about_fragment_title);

        Bundle arguments = new Bundle();
        arguments.putString(KEY_ARGUMENT_FRAGMENT_TITLE,title);
        fragment.setArguments(arguments);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_about, container, false);
        return view;
    }

    @Override
    public CharSequence getTitle() {
        return getArguments().getString(KEY_ARGUMENT_FRAGMENT_TITLE);
    }

}