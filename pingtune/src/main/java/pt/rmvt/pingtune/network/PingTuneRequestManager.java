/**
 * @date Jun 11, 2014
 * @author Rui Teixeira, rui@vazteixeira.org
 * @copyright Copyright 2014 Rui Teixeira. All rights reserved.
 * PingTune - pt.rmvt.pingtune.network
 */
package pt.rmvt.pingtune.network;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import pt.rmvt.pingtune.network.requests.PingTuneRequest;

public class PingTuneRequestManager {

    private static RequestQueue sRequestQueue;
    private boolean mIsStarted = false;

    public PingTuneRequestManager() {

    }

    public void setup(Context context) {
        if (!mIsStarted) {
            sRequestQueue = Volley.newRequestQueue(context);
        }
        mIsStarted = true;
    }

    public void executeRequest(PingTuneRequest request) {
        assert mIsStarted;
        sRequestQueue.add(request.getRequest());
    }

    public void executeRequest(PingTuneRequest request,
                               PingTuneRequest.PingTuneResponseListener responseListener,
                               PingTuneRequest.PingTuneErrorListener errorListener) {
        request.setResposeListener(responseListener);
        request.setErrorListener(errorListener);
        executeRequest(request);
    }

}