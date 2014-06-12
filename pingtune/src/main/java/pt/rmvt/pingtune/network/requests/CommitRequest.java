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

import java.util.List;

import pt.rmvt.pingtune.model.Commit;
import pt.rmvt.pingtune.network.NetworkInfo;
import pt.rmvt.pingtune.network.parser.CommitParser;
import pt.rmvt.pingtune.network.parser.PingTuneParser;

public class CommitRequest extends PingTuneRequest<List<Commit>,JSONArray> {

    private static String sCOMMIT_REQUEST_API_URL;

    private String mUser = NetworkInfo.GITHUB_RUBY_USER;
    private String mRepo = NetworkInfo.GITHUB_RUBY_REPO;

    public CommitRequest() {
        this(null);
    }

    public CommitRequest(String lastSha) {
        super(new CommitParser());

        sCOMMIT_REQUEST_API_URL = lastSha == null ?
                NetworkInfo.GITHUB_API_ENDPOINT + String.format(
                        NetworkInfo.GITHUB_API_COMMITS_REQUEST_FORMAT,
                        mUser,
                        mRepo) :
                NetworkInfo.GITHUB_API_ENDPOINT + String.format(
                        NetworkInfo.GITHUB_API_COMMITS_LAST_SHA_REQUEST_FORMAT,
                        mUser,
                        mRepo,
                        lastSha);

        mJsonRequest = new JsonArrayRequest(
                sCOMMIT_REQUEST_API_URL,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray jsonArray) {
                        mResposeListener.onResponse(mParser.parse(jsonArray));
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