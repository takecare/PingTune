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

import pt.rmvt.pingtune.BuildConfig;
import pt.rmvt.pingtune.network.parser.CardinalityParser;
import pt.rmvt.pingtune.network.parser.PingTuneParser;

public class CardinalityRequest extends PingTuneRequest<Integer,JSONArray> {

    public static final String LOG_TAG = "FollowingRequest";

    private static final String CLEAN_UP_URL_REGEX = "(\\{[^\\}]*\\})";

    private static String sCARDINALITY_REQUEST_API_URL;

    public CardinalityRequest(String url) {
        super(new CardinalityParser());

        sCARDINALITY_REQUEST_API_URL = cleanUpUrl(url);

        mJsonRequest = new JsonArrayRequest(
                sCARDINALITY_REQUEST_API_URL,
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
        if (BuildConfig.DEBUG && url == null) throw new RuntimeException();
        return url.replaceAll(CLEAN_UP_URL_REGEX, "");
    }

}