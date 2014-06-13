/**
 * @date Jun 11, 2014
 * @author Rui Teixeira, rui@vazteixeira.org
 * @copyright Copyright 2014 Rui Teixeira. All rights reserved.
 * PingTune - pt.rmvt.pingtune.dao
 */
package pt.rmvt.pingtune.dao;

import android.content.ContentResolver;
import android.content.ContentValues;

import pt.rmvt.pingtune.BuildConfig;
import pt.rmvt.pingtune.model.Commit;
import pt.rmvt.pingtune.storage.provider.PingTuneAsyncQueryHandler;
import pt.rmvt.pingtune.storage.provider.commit.CommitColumns;

public class CommitDAO implements IDataAccessObject<Commit,Long> {

    public static final String LOG_TAG = "CommitDAO";

    private PingTuneAsyncQueryHandler mAsyncQueryHandler;

    public CommitDAO(ContentResolver resolver) {
        mAsyncQueryHandler = new PingTuneAsyncQueryHandler(resolver);
    }

    @Override @Deprecated
    public Long create(ContentResolver resolver, Commit obj) {
        // TODO sync. method not implemented due to time constraint
        return null;
    }

    @Override
    public void create(Commit obj, ICreateListener<Long> createListener) {
        if (BuildConfig.DEBUG && (obj == null || mAsyncQueryHandler == null))
            throw new RuntimeException();
        mAsyncQueryHandler.startInsert(
                CommitColumns.CONTENT_URI,
                getContentValues(obj),
                createListener);
    }

    @Override @Deprecated
    public Commit read(ContentResolver resolver, Long key) {
        // TODO sync. method not implemented due to time constraint
        return null;
    }

    @Override
    public void read(Long key, IReadListener<Commit> readListener) {
        if (BuildConfig.DEBUG && (mAsyncQueryHandler == null || readListener == null))
            throw new RuntimeException();
        mAsyncQueryHandler.startQuery(
                CommitColumns.CONTENT_URI,
                null,
                key != null ? CommitColumns._ID + "=?" : null,
                key != null ? new String[]{Long.toString(key)} : null,
                null,
                readListener);
    }

    public void readByAuthorName(String name, IReadListener<Commit> readListener) {
        if (BuildConfig.DEBUG && (mAsyncQueryHandler == null || name == null))
            throw new RuntimeException();
        mAsyncQueryHandler.startQuery(
                CommitColumns.CONTENT_URI,
                null,
                CommitColumns.AUTHORNAME + "=?",
                new String[]{name},
                null,
                readListener);
    }

    public void readBySha(String sha, IReadListener<Commit> readListener) {
        if (BuildConfig.DEBUG && (mAsyncQueryHandler == null || sha == null || readListener == null))
            throw new RuntimeException();
        mAsyncQueryHandler.startQuery(
                CommitColumns.CONTENT_URI,
                null,
                CommitColumns.AUTHORNAME + "=?",
                new String[]{sha},
                null,
                readListener);
    }

    public void readAll(IReadListener<Commit> readListener) {
        if (BuildConfig.DEBUG && (mAsyncQueryHandler == null || readListener == null))
            throw new RuntimeException();
        mAsyncQueryHandler.startQuery(
                CommitColumns.CONTENT_URI,
                null,
                null,
                null,
                null,
                readListener);
    }

    @Override @Deprecated
    public int update(ContentResolver resolver, Commit obj) {
        // TODO sync. method not implemented due to time constraint
        return 0;
    }

    @Override
    public void update(Commit obj, IUpdateListener updateListener) {
        if (BuildConfig.DEBUG && (mAsyncQueryHandler == null || obj == null))
            throw new RuntimeException();
        mAsyncQueryHandler.startUpdate(
                CommitColumns.CONTENT_URI,
                getContentValues(obj),
                null,
                null,
                updateListener);
    }

    @Override @Deprecated
    public int delete(ContentResolver resolver, Long key) {
        // TODO sync. method not implemented due to time constraint
        return 0;
    }

    @Override
    public void delete(Long key, IDeleteListener deleteListener) {
        if (BuildConfig.DEBUG && mAsyncQueryHandler== null) throw new RuntimeException();
        mAsyncQueryHandler.startDelete(
                CommitColumns.CONTENT_URI,
                key != null ? CommitColumns._ID + "=?" : null,
                key != null ? new String[] {String.valueOf(key)} : null,
                deleteListener);
    }

    @Override
    public ContentValues getContentValues(Commit obj) {
        ContentValues values = new ContentValues();
        values.put(CommitColumns.SHA,obj.getSha());
        values.put(CommitColumns.URL,obj.getUrl());
        values.put(CommitColumns.HTMLURL,obj.getHtmlUrl());
        values.put(CommitColumns.PARENTSHA,obj.getParentSha());
        values.put(CommitColumns.AUTHORNAME,obj.getAuthor().getName());
        return values;
    }

    // DAO LISTENERS

    public static interface IReadCommitListener extends IReadListener<Commit> {
        // see IDataAccessObject.IReadListener<T>
        //@Override public void onReadFinished(Commit commit);
    }

    public static interface ICreateCommitListener extends ICreateListener<Long> {
        @Override public void onCreateFinished(Long pk);
    }

    public static interface IUpdateCommitListener extends IUpdateListener {
        public void onUpdateFinished(Integer rows);
    }

    public static interface IDeleteCommitListener extends IDeleteListener {
        public void onDeleteFinished(Integer rows);
    }

}