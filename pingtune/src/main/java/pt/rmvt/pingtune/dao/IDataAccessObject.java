/**
 * @date Jun 11, 2014
 * @author Rui Teixeira, rui@vazteixeira.org
 * @copyright Copyright 2014 Rui Teixeira. All rights reserved.
 * PingTune - pt.rmvt.pingtune.dao
 */
package pt.rmvt.pingtune.dao;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;

// T: type/model -- eg. Author
// PK: model's PK (in the db) -- eg. String, Integer, etc.

public interface IDataAccessObject<T, PK> {

    public PK create(ContentResolver resolver, T obj);

    public void create(T obj, ICreateListener<PK> createListener);

    public T read(ContentResolver resolver, PK key);

    public void read(PK key, IReadListener<T> readListener);

    public int update(ContentResolver resolver, T obj);

    public void update(T obj, IUpdateListener updateListener);

    public int delete(ContentResolver resolver, PK key);

    public void delete(PK key, IDeleteListener deleteListener);

    public ContentValues getContentValues(T obj);


    public static interface IDAOListener {
        // enables us to "group" all listeners
    }

    public static interface ICreateListener<PK> extends IDAOListener {
        // PK since we intend to return the PK of the recently persisted object

        public void onCreateFinished(PK key);

        public void onCreateFinished(String uriSegment);
    }

    public static interface IReadListener<T> extends IDAOListener {
        // T since we intend to have the listener pass the recently read object

        // if we were to implement an asyncqueryhandler for each of our models this would be very
        // useful...
        public void onReadFinished(T obj);

        public void onReadFinished(Cursor cursor);
    }

    public static interface IUpdateListener<Integer> extends IDAOListener {
        // Integer since we'll return the number of affected rows

        public void onUpdateFinished(Integer rows);
    }

    public static interface IDeleteListener<Integer> extends IDAOListener {
        // Integer since we'll return the number of affected rows

        public void onDeleteFinished(Integer rows);
    }
}