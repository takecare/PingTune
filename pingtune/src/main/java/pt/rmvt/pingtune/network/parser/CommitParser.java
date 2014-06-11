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

import pt.rmvt.pingtune.model.Commit;

public class CommitParser extends PingTuneParser<Commit,JSONArray>{

    public static final String LOG_TAG = "CommitParser";

    private static final String SHA_JSON_KEY = "sha";
    private static final String COMMIT_JSON_KEY = "commit";
    private static final String AUTHOR_JSON_KEY = "author";
    private static final String DATE_JSON_KEY = "date";
    private static final String URL_JSON_KEY = "url";
    private static final String HTML_URL_JSON_KEY = "html_url";
    private static final String AVATAR_URL_JSON_KEY = "avatar_url";

    public CommitParser() {}

    @Override
    public Commit parse(JSONArray response) {

        try {
            for (int i=0; i<response.length(); i++) {
                Log.d(LOG_TAG,i+"\t"+response.getString(i));
            }
        } catch (JSONException e) {

            Log.e(LOG_TAG, "#parse(JSONObject): " + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }
}