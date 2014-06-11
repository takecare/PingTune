/**
 * @date Jun 11, 2014
 * @author Rui Teixeira, rui@vazteixeira.org
 * @copyright Copyright 2014 Rui Teixeira. All rights reserved.
 * PingTune - pt.rmvt.pingtune.model
 */
package pt.rmvt.pingtune.model;

public class Commit {

    private String mSha;
    private String mUrl;
    private String mHtmlUrl;
    private String mParentSha;
    private Author mAuthor;

    // CONSTRUCTORS
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
}