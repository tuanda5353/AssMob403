package com.example.boylc.assmob403.database;

import static android.support.constraint.Constraints.TAG;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.example.boylc.assmob403.model.Gifs;
import com.example.boylc.assmob403.model.HDWALLPAPER;
import java.util.ArrayList;
import java.util.List;


public class HdwallPaperDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "HdWallPaperDatabase";

    private static final int DATABASE_VERSION = 1;

    // Table Names
    private static final String TABLE_WALLPAPERS = "wallpapers";

    private static final String TABLE_GIFTS = "gifts";

    // wallpapers Table Columns
    private static final String KEY_WALLPAPER_ID = "id";

    private static final String KEY_WALLPAPER__CAT_ID = "cat_id";

    private static final String KEY_WALLPAPER_IMAGE = "image";

    private static final String KEY_WALLPAPER_IMAGE_THUMB = "image_thumb";

    private static final String KEY_WALLPAPER_TOTAL_VIEW = "total_views";

    private static final String KEY_WALLPAPER_CID = "cid";

    private static final String KEY_WALLPAPER_CATEGORY_NAME = "category_name";

    private static final String KEY_WALLPAPER_CATEGORY_IMAGE = "category_image";

    private static final String KEY_WALLPAPER_CATEGORY_IMAGE_THUMB = "category_image_thumb";

    // gift Table Columns
    private static final String KEY_GIFT_ID = "id";

    private static final String KEY_GIFT_IMAGE = "gif_image";

    private static final String KEY_GIFT_TOTAL_VIEW = "total_views";

    private static HdwallPaperDatabaseHelper sInstance;

    public static synchronized HdwallPaperDatabaseHelper getInstance(Context context) {
        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (sInstance == null) {
            sInstance = new HdwallPaperDatabaseHelper(context.getApplicationContext());
        }
        return sInstance;
    }

    public HdwallPaperDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_WALLPAPER_TABLE = "CREATE TABLE " + TABLE_WALLPAPERS +
                "(" +
                KEY_WALLPAPER_ID + " INTEGER PRIMARY KEY," + // Define a primary key
                KEY_WALLPAPER__CAT_ID + " TEXT," +
                KEY_WALLPAPER_IMAGE + " TEXT," +
                KEY_WALLPAPER_IMAGE_THUMB + " TEXT," +
                KEY_WALLPAPER_TOTAL_VIEW + " TEXT," +
                KEY_WALLPAPER_CID + " TEXT," +
                KEY_WALLPAPER_CATEGORY_NAME + " TEXT," +
                KEY_WALLPAPER_CATEGORY_IMAGE + " TEXT," +
                KEY_WALLPAPER_CATEGORY_IMAGE_THUMB + " TEXT" +
                ")";

        String CREATE_GIFT_TABLE = "CREATE TABLE " + TABLE_GIFTS +
                "(" +
                KEY_GIFT_ID + " INTEGER PRIMARY KEY," + // Define a primary key
                KEY_GIFT_IMAGE + " TEXT," +
                KEY_GIFT_TOTAL_VIEW + " TEXT" +
                ")";
        db.execSQL(CREATE_WALLPAPER_TABLE);
        db.execSQL(CREATE_GIFT_TABLE);
    }

    public void addOrUpdateGift(Gifs gifs) {
        // The database connection is cached so it's not expensive to call getWriteableDatabase() multiple times.
        SQLiteDatabase db = getWritableDatabase();
        long giftId = -1;

        db.beginTransaction();
        try {
            // The user might already exist in the database (i.e. the same user created multiple posts).
            ContentValues values = new ContentValues();
            values.put(KEY_GIFT_ID, gifs.getId());
            values.put(KEY_GIFT_IMAGE, gifs.getGifImage());
            values.put(KEY_GIFT_TOTAL_VIEW, gifs.getTotalViews());

            // First try to update the user in case the user already exists in the database
            // This assumes userNames are unique
            int rows = db.update(TABLE_GIFTS, values, KEY_GIFT_ID + "= ?", new String[]{gifs.getId()});

            // Check if update succeeded
            if (rows == 1) {
                // Get the primary key of the user we just updated
                String usersSelectQuery = String.format("SELECT %s FROM %s WHERE %s = ?",
                        KEY_GIFT_ID, TABLE_GIFTS, KEY_GIFT_ID);
                Cursor cursor = db.rawQuery(usersSelectQuery, new String[]{String.valueOf(gifs.getId())});
                try {
                    if (cursor.moveToFirst()) {
                        giftId = cursor.getInt(0);
                        db.setTransactionSuccessful();
                    }
                } finally {
                    if (cursor != null && !cursor.isClosed()) {
                        cursor.close();
                    }
                }
            } else {
                // user with this userName did not already exist, so insert new user
                giftId = db.insertOrThrow(TABLE_GIFTS, null, values);
                db.setTransactionSuccessful();
            }
            Log.d("databaseWallpaper", "add HDWallPaper to database successful");
        } catch (Exception e) {
            Log.d("databaseWallpaper", "Error while trying to add or update HDWallPaper" + e);
        } finally {
            db.endTransaction();

        }
    }

    public void addOrUpdateWallPaper(HDWALLPAPER hdwallpaper) {
        // The database connection is cached so it's not expensive to call getWriteableDatabase() multiple times.
        SQLiteDatabase db = getWritableDatabase();
        long hdwallpaperId = -1;

        db.beginTransaction();
        try {
            // The user might already exist in the database (i.e. the same user created multiple posts).
            ContentValues values = new ContentValues();
            values.put(KEY_WALLPAPER__CAT_ID, hdwallpaper.getCatId());
            values.put(KEY_WALLPAPER_IMAGE, hdwallpaper.getWallpaperImage());
            values.put(KEY_WALLPAPER_IMAGE_THUMB, hdwallpaper.getWallpaperImageThumb());
            values.put(KEY_WALLPAPER_TOTAL_VIEW, hdwallpaper.getTotalViews());
            values.put(KEY_WALLPAPER_CID, hdwallpaper.getCid());
            values.put(KEY_WALLPAPER_CATEGORY_NAME, hdwallpaper.getCategoryName());
            values.put(KEY_WALLPAPER_CATEGORY_IMAGE, hdwallpaper.getCategoryImage());
            values.put(KEY_WALLPAPER_CATEGORY_IMAGE_THUMB, hdwallpaper.getCategoryImageThumb());

            // First try to update the user in case the user already exists in the database
            // This assumes userNames are unique
            int rows = db
                    .update(TABLE_WALLPAPERS, values, KEY_WALLPAPER_ID + "= ?", new String[]{hdwallpaper.getId()});

            // Check if update succeeded
            if (rows == 1) {
                // Get the primary key of the user we just updated
                String usersSelectQuery = String.format("SELECT %s FROM %s WHERE %s = ?",
                        KEY_WALLPAPER_ID, TABLE_WALLPAPERS, KEY_WALLPAPER_ID);
                Cursor cursor = db.rawQuery(usersSelectQuery, new String[]{String.valueOf(hdwallpaper.getId())});
                try {
                    if (cursor.moveToFirst()) {
                        hdwallpaperId = cursor.getInt(0);
                        db.setTransactionSuccessful();
                    }
                } finally {
                    if (cursor != null && !cursor.isClosed()) {
                        cursor.close();
                    }
                }
            } else {
                // user with this userName did not already exist, so insert new user
                hdwallpaperId = db.insertOrThrow(TABLE_WALLPAPERS, null, values);
                db.setTransactionSuccessful();
            }
        } catch (Exception e) {
            Log.d("databaseWallpaper", "Error while trying to add or update HDWallPaper");
        } finally {
            db.endTransaction();
            Log.d("databaseWallpaper", "add HDWallPaper to database successful");
        }
    }

    public List<Gifs> getAllGifs() {
        Log.d("databseasdas", "getAllGifs: ");
        List<Gifs> mGifs = new ArrayList<>();

        // SELECT * FROM POSTS
        // LEFT OUTER JOIN USERS
        // ON POSTS.KEY_POST_USER_ID_FK = USERS.KEY_USER_ID
        String GIFS_SELECT_QUERY =
                String.format("SELECT * FROM %s ",
                        TABLE_GIFTS
                );

        // "getReadableDatabase()" and "getWriteableDatabase()" return the same object (except under low
        // disk space scenarios)
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(GIFS_SELECT_QUERY, null);
        try {
            if (cursor.moveToFirst()) {
                do {
//
                    Log.d("databseasdas", "getAllGifs: " + cursor.getString(cursor.getColumnIndex(KEY_GIFT_ID)));
                    Gifs gifs = new Gifs();
                    gifs.setId(cursor.getString(cursor.getColumnIndex(KEY_GIFT_ID)));
                    gifs.setGifImage(cursor.getString(cursor.getColumnIndex(KEY_GIFT_IMAGE)));
                    gifs.setTotalViews(cursor.getString(cursor.getColumnIndex(KEY_GIFT_TOTAL_VIEW)));
                    mGifs.add(gifs);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.d("databseasdas", "Error while trying to get HdWallPaper from database" + e);
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        Log.d("databseasdas", "getAllGifs: " + mGifs.size());
        return mGifs;
    }

    public List<HDWALLPAPER> getAllHdWallPaper() {

        List<HDWALLPAPER> hdwallpapers = new ArrayList<>();

        // SELECT * FROM POSTS
        // LEFT OUTER JOIN USERS
        // ON POSTS.KEY_POST_USER_ID_FK = USERS.KEY_USER_ID
        String HDWALLPAPER_SELECT_QUERY =
                String.format("SELECT * FROM %s ",
                        TABLE_WALLPAPERS
                );

        // "getReadableDatabase()" and "getWriteableDatabase()" return the same object (except under low
        // disk space scenarios)
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(HDWALLPAPER_SELECT_QUERY, null);
        try {
            if (cursor.moveToFirst()) {
                do {
//                    values.put(KEY_WALLPAPER__CAT_ID, hdwallpaper.getCatId());
//                    values.put(KEY_WALLPAPER_IMAGE, hdwallpaper.getWallpaperImage());
//                    values.put(KEY_WALLPAPER_IMAGE_THUMB, hdwallpaper.getWallpaperImageThumb());
//                    values.put(KEY_WALLPAPER_TOTAL_VIEW, hdwallpaper.getTotalViews());
//                    values.put(KEY_WALLPAPER_CID, hdwallpaper.getCid());
//                    values.put(KEY_WALLPAPER_CATEGORY_NAME, hdwallpaper.getCategoryName());
//                    values.put(KEY_WALLPAPER_CATEGORY_IMAGE, hdwallpaper.getCategoryImage());
//                    values.put(KEY_WALLPAPER_CATEGORY_IMAGE_THUMB, hdwallpaper.getCategoryImageThumb());
                    HDWALLPAPER hdwallpaper = new HDWALLPAPER();
                    hdwallpaper.setId(cursor.getString(cursor.getColumnIndex(KEY_WALLPAPER_ID)));
                    hdwallpaper.setWallpaperImage(cursor.getString(cursor.getColumnIndex(KEY_WALLPAPER_IMAGE)));
                    hdwallpaper.setWallpaperImageThumb(
                            cursor.getString(cursor.getColumnIndex(KEY_WALLPAPER_IMAGE_THUMB)));
                    hdwallpaper.setTotalViews(cursor.getString(cursor.getColumnIndex(KEY_WALLPAPER_TOTAL_VIEW)));
                    hdwallpaper.setCid(cursor.getString(cursor.getColumnIndex(KEY_WALLPAPER_CID)));
                    hdwallpaper.setCategoryName(cursor.getString(cursor.getColumnIndex(KEY_WALLPAPER_CATEGORY_NAME)));
                    hdwallpaper
                            .setCategoryImage(cursor.getString(cursor.getColumnIndex(KEY_WALLPAPER_CATEGORY_IMAGE)));
                    hdwallpaper.setCategoryImageThumb(
                            cursor.getString(cursor.getColumnIndex(KEY_WALLPAPER_CATEGORY_IMAGE_THUMB)));
                    hdwallpapers.add(hdwallpaper);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.d(TAG, "Error while trying to get HdWallPaper from database");
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return hdwallpapers;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            // Simplest implementation is to drop all old tables and recreate them
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_WALLPAPERS);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_GIFTS);
            onCreate(db);
        }
    }
}
