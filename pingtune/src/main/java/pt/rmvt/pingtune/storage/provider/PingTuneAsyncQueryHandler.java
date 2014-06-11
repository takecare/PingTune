/**
 * @date Jun 11, 2014
 * @author Rui Teixeira, rui@vazteixeira.org
 * @copyright Copyright 2014 Rui Teixeira. All rights reserved.
 * PingTune - pt.rmvt.pingtune.storage.provider
 */
package pt.rmvt.pingtune.storage.provider;

import android.content.AsyncQueryHandler;
import android.content.ContentResolver;

import java.util.HashMap;

import pt.rmvt.pingtune.dao.IDataAccessObject;

public class PingTuneAsyncQueryHandler extends AsyncQueryHandler {

    public static final String LOG_TAG = "PingTuneAsyncQueryHandler";

    private HashMap<Object,IDataAccessObject.IDAOListener> mDAOListeners;

    public PingTuneAsyncQueryHandler(ContentResolver cr) {
        super(cr);
    }
}