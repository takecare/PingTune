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
import android.net.Uri;

import java.util.HashMap;

import pt.rmvt.pingtune.dao.IDataAccessObject;

public class PingTuneAsyncQueryHandler extends AsyncQueryHandler {

    public static final String LOG_TAG = "PingTuneAsyncQueryHandler";

    // stores all of our own dao listeners so when an operation finishes and we're notified here
    // (through our own async query listeners) we can relay that message to the specific dao listener
    private HashMap<Object,IDataAccessObject.IDAOListener> mDAOListeners;

    public PingTuneAsyncQueryHandler(ContentResolver cr) {
        super(cr);
        mDAOListeners = new HashMap<Object, IDataAccessObject.IDAOListener>();
    }

    public void startQuery (Uri uri, String[] projection, String selection, String[] selectionArgs,
                            String orderBy) {

        // TODO create our own: int token, Object cookie

    }


}