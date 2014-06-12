/**
 * @date Jun 11, 2014
 * @author Rui Teixeira, rui@vazteixeira.org
 * @copyright Copyright 2014 Rui Teixeira. All rights reserved.
 * PingTune - pt.rmvt.pingtune.fragment
 */
package pt.rmvt.pingtune.fragment;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import pt.rmvt.pingtune.R;
import pt.rmvt.pingtune.activity.DetailActivity;
import pt.rmvt.pingtune.adapter.AuthorAdapter;
import pt.rmvt.pingtune.bus.PingTuneBus;
import pt.rmvt.pingtune.model.Author;
import pt.rmvt.pingtune.model.Commit;

public class CommitListFragment extends BaseFragment implements AdapterView.OnItemClickListener {

    public static final String LOG_TAG = "CommitListFragment";

    private static final String ARRAY_LIST_AUTHOR_PARCELABLE_KEY = "ARRAY_LIST_AUTHOR_PARCELABLE_KEY";

    @InjectView(R.id.fragmentCommitListListView)
    public ListView mListView;
    private AuthorAdapter mAuthorAdapter;

    public CommitListFragment() {}

    public static CommitListFragment newInstance(Resources resources) {

        CommitListFragment fragment = new CommitListFragment();

        String title = resources.getString(R.string.commit_list_fragment_title);

        Bundle arguments = new Bundle();
        arguments.putString(KEY_ARGUMENT_FRAGMENT_TITLE,title);
        fragment.setArguments(arguments);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        PingTuneBus.getBusInstance().register(this);

        View view = inflater.inflate(R.layout.fragment_commit_list, container, false);
        ButterKnife.inject(this, view);

        mAuthorAdapter = new AuthorAdapter(getActivity(),new ArrayList<Author>());
        mListView.setAdapter(mAuthorAdapter);
        mListView.setOnItemClickListener(this);

        return view;
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);

        if (savedInstanceState != null) {

            ArrayList<Author> authors = savedInstanceState.getParcelableArrayList(
                    ARRAY_LIST_AUTHOR_PARCELABLE_KEY);

            if (authors != null) {
                update(authors);
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        ArrayList<Author> authors = new ArrayList<Author>(mAuthorAdapter.getCount());
        for (int i=0; i<mAuthorAdapter.getCount(); i++) {
            authors.add(mAuthorAdapter.getItem(i));
        }
        outState.putParcelableArrayList(ARRAY_LIST_AUTHOR_PARCELABLE_KEY,authors);
    }

    @Override
    public CharSequence getTitle() {
        return getArguments().getString(KEY_ARGUMENT_FRAGMENT_TITLE);
    }

    @Subscribe
    public void update(HashMap<Author, List<Commit>> commitsByAuthor) {
        update(new ArrayList<Author>(commitsByAuthor.keySet()));
    }

    private void update(List<Author> authors) {
        mAuthorAdapter.clear();
        mAuthorAdapter.addAll(authors); // FIXME
        mAuthorAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Author clickedAuthor = mAuthorAdapter.getItem(position);
        Intent activityIntent = new Intent(getActivity(), DetailActivity.class);
        activityIntent.putExtra(Author.AUTHOR_PARCELABLE_KEY,clickedAuthor);
        startActivity(activityIntent);
    }
}