/**
 * @date Jun 12, 2014
 * @author Rui Teixeira, rui@vazteixeira.org
 * @copyright Copyright 2014 Rui Teixeira. All rights reserved.
 * PingTune - pt.rmvt.pingtune.bus
 */
package pt.rmvt.pingtune.bus;

import com.squareup.otto.Bus;

public class PingTuneBus {

    public static final String LOG_TAG = "PingTuneBus";

    public static final int UPDATE_TYPE_DB = 0;
    public static final int UPDATE_TYPE_NETWORK = 1;

    public static enum UpdateType {
        DB_UPDATE (UPDATE_TYPE_DB),
        NETWORK_UPDATE (UPDATE_TYPE_NETWORK);

        private final int mUpdateType;
        UpdateType(int updateType) {
            mUpdateType = updateType;
        }

    }

    private static Bus sBus;

    private PingTuneBus() {
    }

    public static Bus getBusInstance() {
        if (sBus == null) {
            sBus = new Bus(LOG_TAG);
        }
        return sBus;
    }

}