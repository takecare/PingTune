/**
 * @date Jun 11, 2014
 * @author Rui Teixeira, rui@vazteixeira.org
 * @copyright Copyright 2014 Rui Teixeira. All rights reserved.
 * PingTune - pt.rmvt.pingtune.model
 */
package pt.rmvt.pingtune.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class Author implements Parcelable {

    public static final String AUTHOR_PARCELABLE_KEY = "AUTHOR_PARCELABLE_KEY";

    private String mName;
    private String mEmail;
    private String mAvatar;
    private String mTextDate;

    private String mFollowersUrl;
    private String mFollowingUrl;
    private String mStarredUrl;

    // CONSTRUCTORS
    public Author() {}

    public Author(String name, String email, String avatar, String textDate) {
        mName = name;
        mEmail = email;
        mAvatar = avatar;
        mTextDate = textDate;
    }

    protected Author(Parcel in) {
        mName = in.readString();
        mEmail = in.readString();
        mAvatar = in.readString();
        mTextDate = in.readString();
        mFollowersUrl = in.readString();
        mFollowingUrl = in.readString();
        mStarredUrl = in.readString();
    }


    // GETTERS & SETTERS
    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public String getAvatarUrl() {
        return mAvatar;
    }

    public void setAvatarUrl(String avatar) {
        mAvatar = avatar;
    }

    public String getTextDate() {
        return mTextDate;
    }

    public void setTextDate(String textDate) {
        mTextDate = textDate;
    }

    public String getFollowersUrl() {
        return mFollowersUrl;
    }

    public void setFollowersUrl(String followersUrl) {
        this.mFollowersUrl = followersUrl;
    }

    public String getFollowingUrl() {
        return mFollowingUrl;
    }

    public void setFollowingUrl(String followingUrl) {
        mFollowingUrl = followingUrl;
    }

    public String getStarredUrl() {
        return mStarredUrl;
    }

    public void setStarredUrl(String starredUrl) {
        mStarredUrl = starredUrl;
    }


    // HASHCODE & EQUALS
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Author author = (Author) o;

        if (mEmail != null ? !mEmail.equals(author.mEmail) : author.mEmail != null) return false;
        if (mName != null ? !mName.equals(author.mName) : author.mName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = mName != null ? mName.hashCode() : 0;
        result = 31 * result + (mEmail != null ? mEmail.hashCode() : 0);
        result = 31 * result + (mAvatar != null ? mAvatar.hashCode() : 0);
        result = 31 * result + (mTextDate != null ? mTextDate.hashCode() : 0);
        result = 31 * result + (mFollowersUrl != null ? mFollowersUrl.hashCode() : 0);
        result = 31 * result + (mFollowingUrl != null ? mFollowingUrl.hashCode() : 0);
        result = 31 * result + (mStarredUrl != null ? mStarredUrl.hashCode() : 0);
        return result;
    }


    // PARCELABLE
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mName);
        dest.writeString(mEmail);
        dest.writeString(mAvatar);
        dest.writeString(mTextDate);
        dest.writeString(mFollowersUrl);
        dest.writeString(mFollowingUrl);
        dest.writeString(mStarredUrl);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Author> CREATOR = new Parcelable.Creator<Author>() {
        @Override
        public Author createFromParcel(Parcel in) {
            return new Author(in);
        }

        @Override
        public Author[] newArray(int size) {
            return new Author[size];
        }
    };
}