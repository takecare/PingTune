package pt.rmvt.pingtune.storage.provider.commit;

import java.util.Date;

import android.content.ContentResolver;
import android.net.Uri;

import pt.rmvt.pingtune.storage.provider.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code commit} table.
 */
public class CommitContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return CommitColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     * 
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver, CommitSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    public CommitContentValues putSha(String value) {
        mContentValues.put(CommitColumns.SHA, value);
        return this;
    }

    public CommitContentValues putShaNull() {
        mContentValues.putNull(CommitColumns.SHA);
        return this;
    }


    public CommitContentValues putUrl(String value) {
        mContentValues.put(CommitColumns.URL, value);
        return this;
    }

    public CommitContentValues putUrlNull() {
        mContentValues.putNull(CommitColumns.URL);
        return this;
    }


    public CommitContentValues putHtmlurl(String value) {
        mContentValues.put(CommitColumns.HTMLURL, value);
        return this;
    }

    public CommitContentValues putHtmlurlNull() {
        mContentValues.putNull(CommitColumns.HTMLURL);
        return this;
    }


    public CommitContentValues putParentsha(String value) {
        mContentValues.put(CommitColumns.PARENTSHA, value);
        return this;
    }

    public CommitContentValues putParentshaNull() {
        mContentValues.putNull(CommitColumns.PARENTSHA);
        return this;
    }


    public CommitContentValues putAuthorid(long value) {
        mContentValues.put(CommitColumns.AUTHORID, value);
        return this;
    }

}
