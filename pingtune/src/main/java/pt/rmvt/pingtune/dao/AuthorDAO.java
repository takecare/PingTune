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

public class AuthorDAO implements IDataAccessObject<Author,Long> {

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
        return null; // TODO
    }

}