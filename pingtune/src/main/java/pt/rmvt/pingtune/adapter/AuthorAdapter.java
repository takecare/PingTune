/**
 * @date Jun 12, 2014
 * @author Rui Teixeira, rui@vazteixeira.org
 * @copyright Copyright 2014 Rui Teixeira. All rights reserved.
 * PingTune - pt.rmvt.pingtune.adapter
 */
package pt.rmvt.pingtune.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import pt.rmvt.pingtune.R;
import pt.rmvt.pingtune.model.Author;

public class AuthorAdapter extends ArrayAdapter<Author> {

    public static final String LOG_TAG = "AuthorAdapter";

    private int mResourceId; // need to have our own copy

    public AuthorAdapter(Context context, List<Author> objects) {
        this(context, R.layout.row_author, objects);
    }

    public AuthorAdapter(Context context, int resource, List<Author> objects) {
        super(context, resource, objects);
        mResourceId = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View row = convertView;
        AuthorHolder holder = null;

        if (row == null) {
            row = inflater.inflate(mResourceId,parent,false);
            holder = new AuthorHolder(row);
            row.setTag(holder);
        } else {
            holder = (AuthorHolder) row.getTag();
        }

        holder.mNameTextView.setText(getItem(position).getName());
        holder.mFirstCommitMessageTextView.setText(getItem(position).getEmail());
        holder.mFirstCommitDateTextView.setText(getItem(position).getTextDate());

        return row;
    }

    //

    public static final class AuthorHolder {

        public NetworkImageView mImageView;
        @InjectView(R.id.rowAuthorNameTextView) public TextView mNameTextView;
        @InjectView(R.id.rowAuthorCommitMessage) public TextView mFirstCommitMessageTextView;
        @InjectView(R.id.rowAuthorCommitDate) public TextView mFirstCommitDateTextView;

        public AuthorHolder(View view) {
            ButterKnife.inject(this, view);
            mImageView = (NetworkImageView) view.findViewById(R.id.rowAuthorAvatarImageView);
        }

    }

}