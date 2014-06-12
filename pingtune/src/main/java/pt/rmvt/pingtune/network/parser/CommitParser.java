/**
 * @date Jun 11, 2014
 * @author Rui Teixeira, rui@vazteixeira.org
 * @copyright Copyright 2014 Rui Teixeira. All rights reserved.
 * PingTune - pt.rmvt.pingtune.network.parser
 */
package pt.rmvt.pingtune.network.parser;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import pt.rmvt.pingtune.model.Author;
import pt.rmvt.pingtune.model.Commit;

public class CommitParser extends PingTuneParser<List<Commit>,JSONArray> {

    public static final String LOG_TAG = "CommitParser";

    private static final String SHA_JSON_KEY = "sha";
    private static final String COMMIT_JSON_KEY = "commit";
    private static final String COMMITTER_JSON_KEY = "committer";
    private static final String AUTHOR_JSON_KEY = "author";
    private static final String NAME_JSON_KEY = "name";
    private static final String DATE_JSON_KEY = "date";
    private static final String URL_JSON_KEY = "url";
    private static final String HTML_URL_JSON_KEY = "html_url";
    private static final String AVATAR_URL_JSON_KEY = "avatar_url";
    private static final String LOGIN_JSON_KEY = "login";
    private static final String FOLLOWERS_URL_JSON_KEY = "followers_url";
    private static final String FOLLOWING_URL_JSON_KEY = "following_url";
    private static final String STARRED_URL_JSON_KEY = "starred_url";

    public CommitParser() {}

    @Override
    public List<Commit> parse(JSONArray response) {

        ArrayList<Commit> commits = new ArrayList<Commit>();

        try {
            for (int i=0; i<response.length(); i++)
                commits.add(parseCommit(response.getJSONObject(i)));
        } catch (JSONException e) {
            Log.e(LOG_TAG, "#parse(JSONObject): " + e.getMessage());
            //e.printStackTrace();
        }

        return commits;
    }

    private Commit parseCommit(JSONObject obj) {
        Commit commit = new Commit();
        Author author = parseAuthor(obj);

        commit.setAuthor(author);
        commit.setSha(getSha(obj));

        return commit;
    }

    private Author parseAuthor(JSONObject obj) {
        Author author = new Author();

        author.setName(getName(obj));
        author.setAvatarUrl(getAvatarUrl(obj));
        author.setFollowersUrl(getFollowersUrl(obj));
        author.setFollowingUrl(getFollowingUrl(obj));
        author.setStarredUrl(getStarredUrl(obj));

        return author;
    }

    // --

    private String getName(JSONObject obj) {
        String name = null;

        // 1st attempt -- go to author object ("commit" -> "author")
        if (obj.has(COMMIT_JSON_KEY)) {
            try {
                name = getNameFromCommit(obj.getJSONObject(COMMIT_JSON_KEY));
            } catch (JSONException e) {
                //e.printStackTrace();
            }
        }
        else if (obj.has(AUTHOR_JSON_KEY)) {
            try {
                name = getLoginFromAuthor(obj.getJSONObject(AUTHOR_JSON_KEY));
            } catch (JSONException e) {
                //e.printStackTrace();
            }
        }

        return name;
    }

    private String getNameFromCommit(JSONObject obj) {
        String name = null;

        try {
            if (obj.has(AUTHOR_JSON_KEY)) {
                name = getNameFromCommitAuthor(obj.getJSONObject(AUTHOR_JSON_KEY));
            } else if (obj.has(COMMITTER_JSON_KEY)) {
                name = getNameFromCommitCommitter(obj.getJSONObject(COMMITTER_JSON_KEY));
            }
        } catch (JSONException e) {
            //e.printStackTrace();
        }

        return name;
    }

    private String getNameFromCommitAuthor(JSONObject obj) {
        String name = null;
        try {
            name = obj.getString(NAME_JSON_KEY);
        } catch (JSONException e) {
            //e.printStackTrace();
        }
        return name;
    }

    private String getNameFromCommitCommitter(JSONObject obj) {
        String name = null;
        try {
            name = obj.getString(NAME_JSON_KEY);
        } catch (JSONException e) {
            //e.printStackTrace();
        }
        return name;
    }

    private String getLoginFromAuthor(JSONObject obj) {
        String login = null;
        try {
            login = obj.getString(LOGIN_JSON_KEY);
        } catch (JSONException e) {
            //e.printStackTrace();
        }
        return login;
    }

    private String getAvatarUrl(JSONObject obj) {
        String url = null;
        if (obj.has(AUTHOR_JSON_KEY)) {
            try {
                url = getAvatarUrlFromAuthor(obj.getJSONObject(AUTHOR_JSON_KEY));
            } catch (JSONException e) {
                //e.printStackTrace();
            }
        }
        return url;
    }

    private String getAvatarUrlFromAuthor(JSONObject obj) {
        String url = null;
        if (obj.has(AVATAR_URL_JSON_KEY)) {
            try {
                url = obj.getString(AVATAR_URL_JSON_KEY);
            } catch (JSONException e) {
                //e.printStackTrace();
            }
        }
        return url;
    }

    private String getFollowersUrl(JSONObject obj) {
        String url = null;
        if (obj.has(AUTHOR_JSON_KEY)) {
            try {
                url = getFollowersUrlFromAuthor(obj.getJSONObject(AUTHOR_JSON_KEY));
            } catch (JSONException e) {
                //e.printStackTrace();
            }
        }
        return url;
    }

    private String getFollowersUrlFromAuthor(JSONObject obj) {
        String url = null;
        if (obj.has(FOLLOWERS_URL_JSON_KEY)) {
            try {
                url = obj.getString(FOLLOWERS_URL_JSON_KEY);
            } catch (JSONException e) {
                //e.printStackTrace();
            }
        }
        return url;
    }

    private String getFollowingUrl(JSONObject obj) {
        String url = null;
        if (obj.has(AUTHOR_JSON_KEY)) {
            try {
                url = getFollowingUrlFromAuthor(obj.getJSONObject(AUTHOR_JSON_KEY));
            } catch (JSONException e) {
                //e.printStackTrace();
            }
        }
        return url;
    }

    private String getFollowingUrlFromAuthor(JSONObject obj) {
        String url = null;
        if (obj.has(FOLLOWING_URL_JSON_KEY)) {
            try {
                url = obj.getString(FOLLOWING_URL_JSON_KEY);
            } catch (JSONException e) {
                //e.printStackTrace();
            }
        }
        return url;
    }

    private String getStarredUrl(JSONObject obj) {
        String url = null;
        if (obj.has(AUTHOR_JSON_KEY)) {
            try {
                url = getStarredUrlFromAuthor(obj.getJSONObject(AUTHOR_JSON_KEY));
            } catch (JSONException e) {
                //e.printStackTrace();
            }
        }
        return url;
    }

    private String getStarredUrlFromAuthor(JSONObject obj) {
        String url = null;
        if (obj.has(STARRED_URL_JSON_KEY)) {
            try {
                url = obj.getString(STARRED_URL_JSON_KEY);
            } catch (JSONException e) {
                //e.printStackTrace();
            }
        }
        return url;
    }

    // --

    private String getSha(JSONObject obj) {
        String sha = null;
        try {
            sha = obj.getString(SHA_JSON_KEY);
        } catch (JSONException e) {
            //e.printStackTrace();
        }
        return sha;
    }
}