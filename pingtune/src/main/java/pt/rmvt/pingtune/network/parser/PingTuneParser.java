/**
 * @date Jun 11, 2014
 * @author Rui Teixeira, rui@vazteixeira.org
 * @copyright Copyright 2014 Rui Teixeira. All rights reserved.
 * PingTune - pt.rmvt.pingtune.network.parser
 */
package pt.rmvt.pingtune.network.parser;

public abstract class PingTuneParser<T,R> {

    public abstract T parse(R response);

}