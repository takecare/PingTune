/**
 * @date Jun 11, 2014
 * @author Rui Teixeira, rui@vazteixeira.org
 * @copyright Copyright 2014 Rui Teixeira. All rights reserved.
 * PingTune - pt.rmvt.pingtune.network
 */
package pt.rmvt.pingtune.network;

import android.content.Intent;

import java.util.ArrayList;

public class NetworkInfo {

    public static final String GITHUB_API_ENDPOINT = "https://api.github.com/";
    public static final String GITHUB_API_COMMITS_REQUEST_FORMAT = "repos/%s/%s/commits";
    public static final String GITHUB_API_COMMITS_LAST_SHA_REQUEST_FORMAT = "repos/%s/%s/commits?last_sha=%s";

    public static final String GITHUB_RUBY_USER = "ruby";
    public static final String GITHUB_RUBY_REPO = "ruby";

    public static final String PLACEKITTEN_ENDPOINT = "http://placekitten.com/";
    public static final String PLACEKITTEN_REQUEST_FORMAT = "%d/%d";
    public static final Integer[] PLACEKITTEN_DIMENSIONS = {100,125,150,200,225,250,275};
}