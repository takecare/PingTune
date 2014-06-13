/**
 * @date Jun 11, 2014
 * @author Rui Teixeira, rui@vazteixeira.org
 * @copyright Copyright 2014 Rui Teixeira. All rights reserved.
 * PingTune - pt.rmvt.pingtune.network.requests
 */
package pt.rmvt.pingtune.network.requests;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;

import pt.rmvt.pingtune.network.parser.PingTuneParser;

// T: object/model expected on response
// R: object received from network layer (in our case, volley)

public abstract class PingTuneRequest<T,R> {

    public static final String LOG_TAG = "PingTuneRequest";

    protected JsonArrayRequest mJsonRequest;
    protected PingTuneResponseListener<T> mResposeListener;
    protected PingTuneErrorListener mErrorListener;
    protected PingTuneParser<T,R> mParser;

    // CONSTRUCTORS
    protected PingTuneRequest(PingTuneResponseListener<T> resposeListener,
                              PingTuneErrorListener errorListener,
                              PingTuneParser<T, R> parser) {
        mResposeListener = resposeListener;
        mErrorListener = errorListener;
        mParser = parser;
    }

    protected PingTuneRequest() {}

    public PingTuneRequest(PingTuneParser<T,R> parser) {
        mParser = parser;
    }

    public Request getRequest() {
        return mJsonRequest;
    }

    // GETTERS & SETTERS
    public PingTuneResponseListener<T> getResposeListener() {
        return mResposeListener;
    }

    public void setResposeListener(PingTuneResponseListener<T> resposeListener) {
        mResposeListener = resposeListener;
    }

    public PingTuneErrorListener getErrorListener() {
        return mErrorListener;
    }

    public void setErrorListener(PingTuneErrorListener errorListener) {
        mErrorListener = errorListener;
    }

    public PingTuneParser<T, R> getParser() {
        return mParser;
    }

    public void setParser(PingTuneParser<T, R> parser) {
        mParser = parser;
    }

    public String getTag() {
        return LOG_TAG + "_" + hashCode();
    }

    // HASHCODE
    @Override
    public int hashCode() {
        int result = mJsonRequest != null ? mJsonRequest.hashCode() : 0;
        result = 31 * result + (mResposeListener != null ? mResposeListener.hashCode() : 0);
        result = 31 * result + (mErrorListener != null ? mErrorListener.hashCode() : 0);
        result = 31 * result + (mParser != null ? mParser.hashCode() : 0);
        return result;
    }

    // LISTENERS
    public static interface PingTuneResponseListener<T> {
        public void onResponse(T obj);
    }

    public static interface PingTuneErrorListener {
        public void onError(String errorMessage, int statusCode);
    }

}