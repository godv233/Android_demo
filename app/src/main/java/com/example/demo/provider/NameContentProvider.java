package com.example.demo.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class NameContentProvider extends ContentProvider {

    //初始化一些常量
    private static UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
    private DBOpenHelper dbOpenHelper;

    //为了方便直接使用UriMatcher,这里addURI,下面再调用Matcher进行匹配
    static {
        matcher.addURI("com.example.demo.myProvider", "test", 1);
    }

    @Override
    public boolean onCreate() {
        dbOpenHelper = new DBOpenHelper(this.getContext(), "test.db", null, 1);
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        return null;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        switch (matcher.match(uri)) {
            //把数据库打开放到里面是想证明uri匹配完成
            case 1:
                SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
                long rowId = db.insert("test", null, values);
                if (rowId > 0) {
                    //在前面已有的Uri后面追加ID
                    Uri nameUri = ContentUris.withAppendedId(uri, rowId);
                    //通知数据已经发生改变
                    getContext().getContentResolver().notifyChange(nameUri, null);
                    return nameUri;
                }
        }
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        return 0;
    }

}