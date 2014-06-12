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
}