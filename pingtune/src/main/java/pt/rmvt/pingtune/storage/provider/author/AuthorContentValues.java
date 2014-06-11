package pt.rmvt.pingtune.storage.provider.author;

import java.util.Date;

import android.content.ContentResolver;
import android.net.Uri;

import pt.rmvt.pingtune.storage.provider.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code author} table.
 */
public class AuthorContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return AuthorColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     * 
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver, AuthorSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    public AuthorContentValues putName(String value) {
        mContentValues.put(AuthorColumns.NAME, value);
        return this;
    }

    public AuthorContentValues putNameNull() {
        mContentValues.putNull(AuthorColumns.NAME);
        return this;
    }


    public AuthorContentValues putEmail(String value) {
        mContentValues.put(AuthorColumns.EMAIL, value);
        return this;
    }

    public AuthorContentValues putEmailNull() {
        mContentValues.putNull(AuthorColumns.EMAIL);
        return this;
    }


    public AuthorContentValues putDate(String value) {
        mContentValues.put(AuthorColumns.DATE, value);
        return this;
    }

    public AuthorContentValues putDateNull() {
        mContentValues.putNull(AuthorColumns.DATE);
        return this;
    }

}
