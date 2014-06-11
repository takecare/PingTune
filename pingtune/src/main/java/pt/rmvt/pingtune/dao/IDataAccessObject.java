/**
 * @date Jun 11, 2014
 * @author Rui Teixeira, rui@vazteixeira.org
 * @copyright Copyright 2014 Rui Teixeira. All rights reserved.
 * PingTune - pt.rmvt.pingtune.dao
 */
package pt.rmvt.pingtune.dao;

import android.content.ContentResolver;
import android.content.ContentValues;

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


    public static interface ICreateListener<PK> {
        // PK since we intend to return the PK of the recently persisted object
    }

    public static interface IReadListener<T> {
        // T since we intend to have the listener pass the recently read object
    }

    public static interface IUpdateListener<Integer> {
        // Integer since we'll return the number of affected rows
    }

    public static interface IDeleteListener<Integer> {
        // Integer since we'll return the number of affected rows
    }
}