package pt.rmvt.pingtune.storage.provider.author;

import java.util.Date;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import pt.rmvt.pingtune.storage.provider.base.AbstractSelection;

/**
 * Selection for the {@code author} table.
 */
public class AuthorSelection extends AbstractSelection<AuthorSelection> {
    @Override
    public Uri uri() {
        return AuthorColumns.CONTENT_URI;
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @param sortOrder How to order the rows, formatted as an SQL ORDER BY clause (excluding the ORDER BY itself). Passing null will use the default sort
     *            order, which may be unordered.
     * @return A {@code AuthorCursor} object, which is positioned before the first entry, or null.
     */
    public AuthorCursor query(ContentResolver contentResolver, String[] projection, String sortOrder) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), sortOrder);
        if (cursor == null) return null;
        return new AuthorCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, projection, null}.
     */
    public AuthorCursor query(ContentResolver contentResolver, String[] projection) {
        return query(contentResolver, projection, null);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, projection, null, null}.
     */
    public AuthorCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null, null);
    }


    public AuthorSelection id(long... value) {
        addEquals(AuthorColumns._ID, toObjectArray(value));
        return this;
    }


    public AuthorSelection name(String... value) {
        addEquals(AuthorColumns.NAME, value);
        return this;
    }

    public AuthorSelection nameNot(String... value) {
        addNotEquals(AuthorColumns.NAME, value);
        return this;
    }

    public AuthorSelection nameLike(String... value) {
        addLike(AuthorColumns.NAME, value);
        return this;
    }

    public AuthorSelection email(String... value) {
        addEquals(AuthorColumns.EMAIL, value);
        return this;
    }

    public AuthorSelection emailNot(String... value) {
        addNotEquals(AuthorColumns.EMAIL, value);
        return this;
    }

    public AuthorSelection emailLike(String... value) {
        addLike(AuthorColumns.EMAIL, value);
        return this;
    }

    public AuthorSelection date(String... value) {
        addEquals(AuthorColumns.DATE, value);
        return this;
    }

    public AuthorSelection dateNot(String... value) {
        addNotEquals(AuthorColumns.DATE, value);
        return this;
    }

    public AuthorSelection dateLike(String... value) {
        addLike(AuthorColumns.DATE, value);
        return this;
    }
}
