/**
 * @date Jun 12, 2014
 * @author Rui Teixeira, rui@vazteixeira.org
 * @copyright Copyright 2014 Rui Teixeira. All rights reserved.
 * PingTune - pt.rmvt.pingtune.network.requests
 */
package pt.rmvt.pingtune.network.requests;

import org.json.JSONArray;

import java.util.List;

import pt.rmvt.pingtune.model.Commit;
import pt.rmvt.pingtune.network.parser.PingTuneParser;

public class FollowingCardinalityRequest extends PingTuneRequest<List<Commit>,JSONArray> {

    public static final String LOG_TAG = "FollowingRequest";

    private static String sFOLLOWING_REQUEST_API_URL;


    private FollowingCardinalityRequest(PingTuneParser<Integer, JSONArray> parser) {

    }

}