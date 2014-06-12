/**
 * @date Jun 11, 2014
 * @author Rui Teixeira, rui@vazteixeira.org
 * @copyright Copyright 2014 Rui Teixeira. All rights reserved.
 * PingTune - pt.rmvt.pingtune.dao
 */
package pt.rmvt.pingtune.dao;

import android.content.ContentResolver;
import android.content.ContentValues;

import pt.rmvt.pingtune.model.Author;
import pt.rmvt.pingtune.storage.provider.PingTuneAsyncQueryHandler;
import pt.rmvt.pingtune.storage.provider.author.AuthorColumns;

public class AuthorDAO implements IDataAccessObject<Author,Long> {

    public static final String LOG_TAG = "AuthorDAO";

    private PingTuneAsyncQueryHandler mAsyncQueryHandler;

    public AuthorDAO(ContentResolver resolver) {
        mAsyncQueryHandler = new PingTuneAsyncQueryHandler(resolver);
    }

    @Override @Deprecated
    public Long create(ContentResolver resolver, Author obj) {
        return null; // TODO
    }

    @Override
    public void create(Author obj, ICreateListener<Long> createListener) {
        assert obj != null && mAsyncQueryHandler != null;
        mAsyncQueryHandler.startInsert(
                AuthorColumns.CONTENT_URI,
                getContentValues(obj),
                createListener);
    }

    @Override @Deprecated
    public Author read(ContentResolver resolver, Long key) {
        return null; // TODO
    }

    @Override
    public void read(Long key, IReadListener<Author> readListener) {
        assert mAsyncQueryHandler != null && key != null;
        mAsyncQueryHandler.startQuery(
                AuthorColumns.CONTENT_URI,
                null,
                AuthorColumns._ID + "=?",
                new String[]{Long.toString(key)},
                null,
                readListener);
    }

    public void readByName(String name, IReadListener<Author> readListener) {
        assert mAsyncQueryHandler != null;
        mAsyncQueryHandler.startQuery(
                AuthorColumns.CONTENT_URI,
                null,
                name != null ? AuthorColumns.NAME + "=?" : null,
                name != null ? new String[]{name} : null,
                null,
                readListener);
    }

    public void readAll(IReadListener<Author> readListener) {
        assert mAsyncQueryHandler != null && readListener != null;
        mAsyncQueryHandler.startQuery(
                AuthorColumns.CONTENT_URI,
                null,
                null,
                null,
                null,
                readListener);
    }

    @Override @Deprecated
    public int update(ContentResolver resolver, Author obj) {
        return 0; // TODO
    }

    @Override
    public void update(Author obj, IUpdateListener updateListener) {
        assert mAsyncQueryHandler != null && obj != null;
        mAsyncQueryHandler.startUpdate(
                AuthorColumns.CONTENT_URI,
                getContentValues(obj),
                null,
                null,
                updateListener);
    }

    @Override @Deprecated
    public int delete(ContentResolver resolver, Long key) {
        return 0; // TODO
    }

    @Override
    public void delete(Long key, IDeleteListener deleteListener) {
        assert mAsyncQueryHandler != null;
        mAsyncQueryHandler.startDelete(
                AuthorColumns.CONTENT_URI,
                key != null ? AuthorColumns._ID + "=?" : null,
                key != null ? new String[] {String.valueOf(key)} : null,
                deleteListener);
    }


    @Override
    public ContentValues getContentValues(Author obj) {
        ContentValues values = new ContentValues();
        values.put(AuthorColumns.NAME,obj.getName());
        values.put(AuthorColumns.EMAIL,obj.getEmail());
        values.put(AuthorColumns.DATE,obj.getTextDate());
        values.put(AuthorColumns.AVATARURL,obj.getAvatarUrl());
        values.put(AuthorColumns.STARREDURL,obj.getStarredUrl());
        values.put(AuthorColumns.FOLLOWINGURL,obj.getFollowingUrl());
        values.put(AuthorColumns.FOLLOWERSURL,obj.getFollowersUrl());
        return values;
    }

    // DAO LISTENERS

    public static interface IReadAuthorListener extends IReadListener<Author> {
        // see IDataAccessObject.IReadListener<T>
        //@Override public void onReadFinished(Author author);
    }

    public static interface ICreateAuthorListener extends ICreateListener<Long> {
        @Override public void onCreateFinished(Long pk);
    }

    public static interface IUpdateAuthorListener extends IUpdateListener {
        //public void onUpdateFinished(Integer rows);
    }

    public static interface IDeleteAuthorListener extends IDeleteListener {
        //public void onDeleteFinished(Integer rows);
    }
}