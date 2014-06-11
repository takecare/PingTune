/**
 * @date Jun 11, 2014
 * @author Rui Teixeira, rui@vazteixeira.org
 * @copyright Copyright 2014 Betfair. All rights reserved.
 */
package pt.rmvt.pingtune.model;

import java.util.Date;

public class Author {

    private String mName;
    private String mEmail;
    private Date mDate;
    private String mTextDate;


    public Author(String name, String email, Date date) {
        mName = name;
        mEmail = email;
        mDate = date;
    }

    public Author(String name, String email, String textDate) {
        mName = name;
        mEmail = email;
        mTextDate = textDate;
    }

    // GETTERS & SETTERS
    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public String getTextDate() {
        return mTextDate;
    }

    public void setTextDate(String textDate) {
        mTextDate = textDate;
    }
}