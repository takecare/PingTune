/**
 * @date Jun 11, 2014
 * @author Rui Teixeira, rui@vazteixeira.org
 * @copyright Copyright 2014 Rui Teixeira. All rights reserved.
 * PingTune - pt.rmvt.pingtune.network.requests
 */
package pt.rmvt.pingtune.network.requests;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import pt.rmvt.pingtune.model.Commit;
import pt.rmvt.pingtune.network.NetworkInfo;
import pt.rmvt.pingtune.network.parser.CommitParser;
import pt.rmvt.pingtune.network.parser.PingTuneParser;

public class CommitRequest extends PingTuneRequest<Commit,JSONArray> {

    private static final String COMMIT_REQUEST_API_URL =
            NetworkInfo.GITHUB_API_ENDPOINT + String.format(
                    NetworkInfo.GITHUB_API_COMMITS_REQUEST_FORMAT,
                    NetworkInfo.GITHUB_RUBY_USER,
                    NetworkInfo.GITHUB_RUBY_REPO);

    public CommitRequest() {
        this(new CommitParser());
    }

    public CommitRequest(PingTuneParser<Commit,JSONArray> parser) {
        super(parser);
        mJsonRequest = new JsonArrayRequest(
                COMMIT_REQUEST_API_URL,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray jsonObject) {
                        mResposeListener.onResponse(mParser.parse(jsonObject));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                        if (volleyError.networkResponse != null) {
                            mErrorListener.onError(
                                    volleyError.getMessage(),
                                    volleyError.networkResponse.statusCode);
                        } else {
                            mErrorListener.onError(volleyError.getMessage(),-1);
                        }
                    }
                });
    }



}