/**
 * @date Jun 11, 2014
 * @author Rui Teixeira, rui@vazteixeira.org
 * @copyright Copyright 2014 Rui Teixeira. All rights reserved.
 * PingTune - pt.rmvt.pingtune.model
 */
package pt.rmvt.pingtune.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Commit implements Parcelable {

    private String mSha;
    private String mUrl;
    private String mHtmlUrl;
    private String mParentSha;
    private Author mAuthor;

    // CONSTRUCTORS
    public Commit() {}

    protected Commit(Parcel in) {
        mSha = in.readString();
        mUrl = in.readString();
        mHtmlUrl = in.readString();
        mParentSha = in.readString();
        mAuthor = in.readParcelable(Author.class.getClassLoader());
    }

    public Commit(String sha, String url, String htmlUrl, String parentSha, Author author) {
        mSha = sha;
        mUrl = url;
        mHtmlUrl = htmlUrl;
        mParentSha = parentSha;
        mAuthor = author;
    }

    // GETTERS & SETTERS
    public String getSha() {
        return mSha;
    }

    public void setSha(String sha) {
        mSha = sha;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    public String getHtmlUrl() {
        return mHtmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        mHtmlUrl = htmlUrl;
    }

    public String getParentSha() {
        return mParentSha;
    }

    public void setParentSha(String parentSha) {
        mParentSha = parentSha;
    }

    public Author getAuthor() {
        return mAuthor;
    }

    public void setAuthor(Author author) {
        mAuthor = author;
    }

    // PARCELABLE
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mSha);
        dest.writeString(mUrl);
        dest.writeString(mHtmlUrl);
        dest.writeString(mParentSha);
        dest.writeParcelable(mAuthor,flags);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Commit> CREATOR = new Parcelable.Creator<Commit>() {
        @Override
        public Commit createFromParcel(Parcel in) {
            return new Commit(in);
        }

        @Override
        public Commit[] newArray(int size) {
            return new Commit[size];
        }
    };
}