/**
 * @date Jun 11, 2014
 * @author Rui Teixeira, rui@vazteixeira.org
 * @copyright Copyright 2014 Rui Teixeira. All rights reserved.
 * PingTune - pt.rmvt.pingtune.model
 */
package pt.rmvt.pingtune.model;

import java.util.Date;

public class Author {

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

    // EQUALS

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
}