/**
 * @date Jun 11, 2014
 * @author Rui Teixeira, rui@vazteixeira.org
 * @copyright Copyright 2014 Rui Teixeira. All rights reserved.
 * PingTune - pt.rmvt.pingtune.storage.provider
 */
package pt.rmvt.pingtune.storage.provider;

import android.content.AsyncQueryHandler;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

import java.util.HashMap;
import java.util.Random;

import pt.rmvt.pingtune.dao.IDataAccessObject;

public class PingTuneAsyncQueryHandler extends AsyncQueryHandler {

    public static final String LOG_TAG = "PingTuneAsyncQueryHandler";

    private static final int QUERY_TOKEN = 0;
    private static final int INSERT_TOKEN = 1;
    private static final int DELETE_TOKEN = 2;
    private static final int UPDATE_TOKEN = 3;

    // stores all of our own dao listeners so when an operation finishes and we're notified here
    // (through our own async query listeners) we can relay that message to the specific dao listener
    private HashMap<Object,IDataAccessObject.IDAOListener> mDAOListeners;

    public PingTuneAsyncQueryHandler(ContentResolver cr) {
        super(cr);
        mDAOListeners = new HashMap<Object, IDataAccessObject.IDAOListener>();
    }

    public void startQuery (Uri uri, String[] projection, String selection, String[] selectionArgs,
                            String orderBy, IDataAccessObject.IReadListener readListener) {

        String cookie = getCookie();
        mDAOListeners.put(cookie,readListener);
        super.startQuery(
                QUERY_TOKEN,
                cookie,
                uri,
                projection,
                selection,
                selectionArgs,
                orderBy);
    }

    // TODO: startInsert

    // TODO: startUpdate

    // TODO: startDelete

    private String getCookie() {
        return (new Random()).nextLong() + String.valueOf(System.currentTimeMillis());
    }

    // LISTENERS

    @Override
    protected void onQueryComplete(int token, Object cookie, Cursor cursor) {
        IDataAccessObject.IReadListener listener = (IDataAccessObject.IReadListener) mDAOListeners.get(cookie);
        listener.onReadFinished(cursor);
        mDAOListeners.remove(cookie);
    }

    // TODO: insert listener

    // TODO: update listener

    // TODO: delete listener
}