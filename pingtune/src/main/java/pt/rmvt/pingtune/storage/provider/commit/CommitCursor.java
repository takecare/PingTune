package pt.rmvt.pingtune.storage.provider.commit;

import java.util.Date;

import android.database.Cursor;

import pt.rmvt.pingtune.storage.provider.base.AbstractCursor;

/**
 * Cursor wrapper for the {@code commit} table.
 */
public class CommitCursor extends AbstractCursor {
    public CommitCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Get the {@code sha} value.
     * Can be {@code null}.
     */
    public String getSha() {
        Integer index = getCachedColumnIndexOrThrow(CommitColumns.SHA);
        return getString(index);
    }

    /**
     * Get the {@code url} value.
     * Can be {@code null}.
     */
    public String getUrl() {
        Integer index = getCachedColumnIndexOrThrow(CommitColumns.URL);
        return getString(index);
    }

    /**
     * Get the {@code htmlurl} value.
     * Can be {@code null}.
     */
    public String getHtmlurl() {
        Integer index = getCachedColumnIndexOrThrow(CommitColumns.HTMLURL);
        return getString(index);
    }

    /**
     * Get the {@code parentsha} value.
     * Can be {@code null}.
     */
    public String getParentsha() {
        Integer index = getCachedColumnIndexOrThrow(CommitColumns.PARENTSHA);
        return getString(index);
    }

    /**
     * Get the {@code authorid} value.
     */
    public long getAuthorid() {
        return getLongOrNull(CommitColumns.AUTHORID);
    }
}
