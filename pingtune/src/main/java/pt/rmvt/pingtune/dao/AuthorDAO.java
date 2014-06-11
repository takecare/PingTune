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
        return null;
    }

    @Override
    public void create(Author obj, ICreateListener<Long> createListener) {


    }

    @Override @Deprecated
    public Author read(ContentResolver resolver, Long key) {
        return null;
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

    @Override @Deprecated
    public int update(ContentResolver resolver, Author obj) {
        return 0;
    }

    @Override
    public void update(Author obj, IUpdateListener updateListener) {

    }

    @Override @Deprecated
    public int delete(ContentResolver resolver, Long key) {
        return 0;
    }

    @Override
    public void delete(Long key, IDeleteListener deleteListener) {

    }


    @Override
    public ContentValues getContentValues(Author obj) {
        ContentValues values = new ContentValues();
        values.put(AuthorColumns.NAME,obj.getName());
        values.put(AuthorColumns.EMAIL,obj.getEmail());
        if (obj.getTextDate() != null)
            values.put(AuthorColumns.DATE,obj.getTextDate());
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