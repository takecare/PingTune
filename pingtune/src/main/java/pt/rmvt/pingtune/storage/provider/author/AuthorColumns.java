package pt.rmvt.pingtune.storage.provider.author;

import android.net.Uri;
import android.provider.BaseColumns;

import pt.rmvt.pingtune.storage.provider.PingTuneProvider;

/**
 * Columns for the {@code author} table.
 */
public interface AuthorColumns extends BaseColumns {
    String TABLE_NAME = "authorTable";
    Uri CONTENT_URI = Uri.parse(PingTuneProvider.CONTENT_URI_BASE + "/" + TABLE_NAME);

    String _ID = BaseColumns._ID;
    String NAME = "name";
    String EMAIL = "email";
    String DATE = "date";

    String DEFAULT_ORDER = _ID;

	// @formatter:off
    String[] FULL_PROJECTION = new String[] {
            _ID,
            NAME,
            EMAIL,
            DATE
    };
    // @formatter:on
}