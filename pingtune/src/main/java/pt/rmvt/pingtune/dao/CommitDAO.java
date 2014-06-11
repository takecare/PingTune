/**
 * @date Jun 11, 2014
 * @author Rui Teixeira, rui@vazteixeira.org
 * @copyright Copyright 2014 Rui Teixeira. All rights reserved.
 * PingTune - pt.rmvt.pingtune.dao
 */
package pt.rmvt.pingtune.dao;

import android.content.ContentResolver;
import android.content.ContentValues;

import pt.rmvt.pingtune.model.Commit;

public class CommitDAO implements IDataAccessObject<Commit,Long> {

    public static final String LOG_TAG = "CommitDAO";


    @Override @Deprecated
    public Long create(ContentResolver resolver, Commit obj) {
        return null;
    }

    @Override
    public void create(Commit obj, ICreateListener<Long> createListener) {

    }

    @Override @Deprecated
    public Commit read(ContentResolver resolver, Long key) {
        return null;
    }

    @Override
    public void read(Long key, IReadListener<Commit> readListener) {

    }

    @Override @Deprecated
    public int update(ContentResolver resolver, Commit obj) {
        return 0;
    }

    @Override
    public void update(Commit obj, IUpdateListener updateListener) {

    }

    @Override @Deprecated
    public int delete(ContentResolver resolver, Long key) {
        return 0;
    }

    @Override
    public void delete(Long key, IDeleteListener deleteListener) {

    }

    @Override
    public ContentValues getContentValues(Commit obj) {
        return null;
    }

    // DAO LISTENERS

    public static interface IReadCommitListener extends IReadListener<Commit> {
        public void onReadFinished(Commit commit);
    }

    public static interface ICreateCommitListener extends ICreateListener<Long> {
        public void onCreateFinished(Long pk);
    }

    public static interface IUpdateCommitListener extends IUpdateListener {
        public void onUpdateFinished(Integer rows);
    }

    public static interface IDeleteCommitListener extends IDeleteListener {
        public void onDeleteFinished(Integer rows);
    }

}