package pt.rmvt.pingtune.storage.provider;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.DefaultDatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import pt.rmvt.pingtune.BuildConfig;
import pt.rmvt.pingtune.storage.provider.author.AuthorColumns;
import pt.rmvt.pingtune.storage.provider.commit.CommitColumns;

public class DataBaseOpenHelper extends SQLiteOpenHelper {
    private static final String TAG = DataBaseOpenHelper.class.getSimpleName();

    public static final String DATABASE_FILE_NAME = "PingTune.db";
    private static final int DATABASE_VERSION = 1;
    private final Context mContext;
    private final DataBaseOpenHelperCallbacks mOpenHelperCallbacks;

    // @formatter:off
    private static final String SQL_CREATE_TABLE_AUTHOR = "CREATE TABLE IF NOT EXISTS "
            + AuthorColumns.TABLE_NAME + " ( "
            + AuthorColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + AuthorColumns.NAME + " TEXT, "
            + AuthorColumns.EMAIL + " TEXT, "
            + AuthorColumns.DATE + " TEXT, "
            + AuthorColumns.AVATARURL + " TEXT, "
            + AuthorColumns.FOLLOWERSURL + " TEXT, "
            + AuthorColumns.FOLLOWINGURL + " TEXT, "
            + AuthorColumns.STARREDURL + " TEXT "
            + " );";

    private static final String SQL_CREATE_TABLE_COMMIT = "CREATE TABLE IF NOT EXISTS "
            + CommitColumns.TABLE_NAME + " ( "
            + CommitColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + CommitColumns.SHA + " TEXT, "
            + CommitColumns.URL + " TEXT, "
            + CommitColumns.HTMLURL + " TEXT, "
            + CommitColumns.PARENTSHA + " TEXT, "
            + CommitColumns.AUTHORID + " INTEGER, "
            + CommitColumns.AUTHORNAME + " TEXT "
            + " );";

    // @formatter:on

    public static DataBaseOpenHelper newInstance(Context context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
            return newInstancePreHoneycomb(context);
        }
        return newInstancePostHoneycomb(context);
    }


    /*
     * Pre Honeycomb.
     */

    private static DataBaseOpenHelper newInstancePreHoneycomb(Context context) {
        return new DataBaseOpenHelper(context, DATABASE_FILE_NAME, null, DATABASE_VERSION);
    }

    private DataBaseOpenHelper(Context context, String name, CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
        mOpenHelperCallbacks = new DataBaseOpenHelperCallbacks();
    }


    /*
     * Post Honeycomb.
     */

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private static DataBaseOpenHelper newInstancePostHoneycomb(Context context) {
        return new DataBaseOpenHelper(context, DATABASE_FILE_NAME, null, DATABASE_VERSION, new DefaultDatabaseErrorHandler());
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private DataBaseOpenHelper(Context context, String name, CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
        mContext = context;
        mOpenHelperCallbacks = new DataBaseOpenHelperCallbacks();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        if (BuildConfig.DEBUG) Log.d(TAG, "onCreate");
        mOpenHelperCallbacks.onPreCreate(mContext, db);
        db.execSQL(SQL_CREATE_TABLE_AUTHOR);
        db.execSQL(SQL_CREATE_TABLE_COMMIT);
        mOpenHelperCallbacks.onPostCreate(mContext, db);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            db.execSQL("PRAGMA foreign_keys=ON;");
        }
        mOpenHelperCallbacks.onOpen(mContext, db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        mOpenHelperCallbacks.onUpgrade(mContext, db, oldVersion, newVersion);
    }
}
