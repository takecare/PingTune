/**
 * @date Jun 12, 2014
 * @author Rui Teixeira, rui@vazteixeira.org
 * @copyright Copyright 2014 Rui Teixeira. All rights reserved.
 * PingTune - pt.rmvt.pingtune.crouton
 */
package pt.rmvt.pingtune.crouton;

import de.keyboardsurfer.android.widget.crouton.Style;

public class PingTuneCrouton {

    private Style mCroutonStyle;
    private String mMessage;

    private PingTuneCrouton() {}

    public PingTuneCrouton(Style style, String message) {
        mCroutonStyle = style;
        mMessage = message;
    }

    public PingTuneCrouton(String error) {
        mCroutonStyle = Style.ALERT;
        mMessage = error;
    }

    // GETTERS
    public Style getStyle() {
        return mCroutonStyle;
    }

    public String getMessage() {
        return mMessage;
    }

}