/**
 * @date Jun 12, 2014
 * @author Rui Teixeira, rui@vazteixeira.org
 * @copyright Copyright 2014 Rui Teixeira. All rights reserved.
 * PingTune - pt.rmvt.pingtune.network.parser
 */
package pt.rmvt.pingtune.network.parser;

import org.json.JSONArray;

public class FollowingCardinalityParser extends PingTuneParser<Integer,JSONArray> {

    public static final String LOG_TAG = "FollowingCardinalityParser";

    @Override
    public Integer parse(JSONArray response) {
        return response.length();
    }
}