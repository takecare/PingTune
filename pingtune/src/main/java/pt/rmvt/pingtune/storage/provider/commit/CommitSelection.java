package pt.rmvt.pingtune.storage.provider.commit;

import java.util.Date;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import pt.rmvt.pingtune.storage.provider.base.AbstractSelection;

/**
 * Selection for the {@code commit} table.
 */
public class CommitSelection extends AbstractSelection<CommitSelection> {
    @Override
    public Uri uri() {
        return CommitColumns.CONTENT_URI;
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @param sortOrder How to order the rows, formatted as an SQL ORDER BY clause (excluding the ORDER BY itself). Passing null will use the default sort
     *            order, which may be unordered.
     * @return A {@code CommitCursor} object, which is positioned before the first entry, or null.
     */
    public CommitCursor query(ContentResolver contentResolver, String[] projection, String sortOrder) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), sortOrder);
        if (cursor == null) return null;
        return new CommitCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, projection, null}.
     */
    public CommitCursor query(ContentResolver contentResolver, String[] projection) {
        return query(contentResolver, projection, null);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, projection, null, null}.
     */
    public CommitCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null, null);
    }


    public CommitSelection id(long... value) {
        addEquals(CommitColumns._ID, toObjectArray(value));
        return this;
    }


    public CommitSelection sha(String... value) {
        addEquals(CommitColumns.SHA, value);
        return this;
    }

    public CommitSelection shaNot(String... value) {
        addNotEquals(CommitColumns.SHA, value);
        return this;
    }

    public CommitSelection shaLike(String... value) {
        addLike(CommitColumns.SHA, value);
        return this;
    }

    public CommitSelection url(String... value) {
        addEquals(CommitColumns.URL, value);
        return this;
    }

    public CommitSelection urlNot(String... value) {
        addNotEquals(CommitColumns.URL, value);
        return this;
    }

    public CommitSelection urlLike(String... value) {
        addLike(CommitColumns.URL, value);
        return this;
    }

    public CommitSelection htmlurl(String... value) {
        addEquals(CommitColumns.HTMLURL, value);
        return this;
    }

    public CommitSelection htmlurlNot(String... value) {
        addNotEquals(CommitColumns.HTMLURL, value);
        return this;
    }

    public CommitSelection htmlurlLike(String... value) {
        addLike(CommitColumns.HTMLURL, value);
        return this;
    }

    public CommitSelection parentsha(String... value) {
        addEquals(CommitColumns.PARENTSHA, value);
        return this;
    }

    public CommitSelection parentshaNot(String... value) {
        addNotEquals(CommitColumns.PARENTSHA, value);
        return this;
    }

    public CommitSelection parentshaLike(String... value) {
        addLike(CommitColumns.PARENTSHA, value);
        return this;
    }

    public CommitSelection authorid(long... value) {
        addEquals(CommitColumns.AUTHORID, toObjectArray(value));
        return this;
    }

    public CommitSelection authoridNot(long... value) {
        addNotEquals(CommitColumns.AUTHORID, toObjectArray(value));
        return this;
    }

    public CommitSelection authoridGt(long value) {
        addGreaterThan(CommitColumns.AUTHORID, value);
        return this;
    }

    public CommitSelection authoridGtEq(long value) {
        addGreaterThanOrEquals(CommitColumns.AUTHORID, value);
        return this;
    }

    public CommitSelection authoridLt(long value) {
        addLessThan(CommitColumns.AUTHORID, value);
        return this;
    }

    public CommitSelection authoridLtEq(long value) {
        addLessThanOrEquals(CommitColumns.AUTHORID, value);
        return this;
    }

    public CommitSelection authorname(String... value) {
        addEquals(CommitColumns.AUTHORNAME, value);
        return this;
    }

    public CommitSelection authornameNot(String... value) {
        addNotEquals(CommitColumns.AUTHORNAME, value);
        return this;
    }

    public CommitSelection authornameLike(String... value) {
        addLike(CommitColumns.AUTHORNAME, value);
        return this;
    }
}
