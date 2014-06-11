package pt.rmvt.pingtune.storage.provider.commit;

import android.net.Uri;
import android.provider.BaseColumns;

import pt.rmvt.pingtune.storage.provider.PingTuneProvider;

/**
 * Columns for the {@code commit} table.
 */
public interface CommitColumns extends BaseColumns {
    String TABLE_NAME = "commitTable";
    Uri CONTENT_URI = Uri.parse(PingTuneProvider.CONTENT_URI_BASE + "/" + TABLE_NAME);

    String _ID = BaseColumns._ID;
    String SHA = "sha";
    String URL = "url";
    String HTMLURL = "htmlurl";
    String PARENTSHA = "parentsha";
    String AUTHORID = "authorid";
    String AUTHORNAME = "authorname";

    String DEFAULT_ORDER = _ID;

	// @formatter:off
    String[] FULL_PROJECTION = new String[] {
            _ID,
            SHA,
            URL,
            HTMLURL,
            PARENTSHA,
            AUTHORID,
            AUTHORNAME
    };
    // @formatter:on
}