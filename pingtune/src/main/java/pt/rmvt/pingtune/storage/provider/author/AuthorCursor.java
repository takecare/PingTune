package pt.rmvt.pingtune.storage.provider.author;

import java.util.Date;

import android.database.Cursor;

import pt.rmvt.pingtune.storage.provider.base.AbstractCursor;

/**
 * Cursor wrapper for the {@code author} table.
 */
public class AuthorCursor extends AbstractCursor {
    public AuthorCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Get the {@code name} value.
     * Can be {@code null}.
     */
    public String getName() {
        Integer index = getCachedColumnIndexOrThrow(AuthorColumns.NAME);
        return getString(index);
    }

    /**
     * Get the {@code email} value.
     * Can be {@code null}.
     */
    public String getEmail() {
        Integer index = getCachedColumnIndexOrThrow(AuthorColumns.EMAIL);
        return getString(index);
    }

    /**
     * Get the {@code avatarurl} value.
     * Can be {@code null}.
     */
    public String getAvatarurl() {
        Integer index = getCachedColumnIndexOrThrow(AuthorColumns.AVATARURL);
        return getString(index);
    }

    /**
     * Get the {@code followersurl} value.
     * Can be {@code null}.
     */
    public String getFollowersurl() {
        Integer index = getCachedColumnIndexOrThrow(AuthorColumns.FOLLOWERSURL);
        return getString(index);
    }

    /**
     * Get the {@code followingurl} value.
     * Can be {@code null}.
     */
    public String getFollowingurl() {
        Integer index = getCachedColumnIndexOrThrow(AuthorColumns.FOLLOWINGURL);
        return getString(index);
    }

    /**
     * Get the {@code starredurl} value.
     * Can be {@code null}.
     */
    public String getStarredurl() {
        Integer index = getCachedColumnIndexOrThrow(AuthorColumns.STARREDURL);
        return getString(index);
    }

    /**
     * Get the {@code date} value.
     * Can be {@code null}.
     */
    public String getDate() {
        Integer index = getCachedColumnIndexOrThrow(AuthorColumns.DATE);
        return getString(index);
    }
}
