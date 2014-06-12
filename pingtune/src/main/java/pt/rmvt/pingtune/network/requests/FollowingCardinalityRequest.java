/**
 * @date Jun 12, 2014
 * @author Rui Teixeira, rui@vazteixeira.org
 * @copyright Copyright 2014 Rui Teixeira. All rights reserved.
 * PingTune - pt.rmvt.pingtune.network.requests
 */
package pt.rmvt.pingtune.network.requests;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;

import pt.rmvt.pingtune.network.parser.PingTuneParser;

public class FollowingCardinalityRequest extends PingTuneRequest<Integer,JSONArray> {

    public static final String LOG_TAG = "FollowingRequest";

    private static String sFOLLOWING_REQUEST_API_URL;

    public FollowingCardinalityRequest(String url) {
        sFOLLOWING_REQUEST_API_URL = cleanUpUrl(url);
    }

    private FollowingCardinalityRequest(PingTuneParser<Integer,JSONArray> parser) {
        super(parser);
        mJsonRequest = new JsonArrayRequest(
                sFOLLOWING_REQUEST_API_URL,
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

    private String cleanUpUrl(String url) {
        // TODO: remove everything enclosed in {}
        return url;
    }

}