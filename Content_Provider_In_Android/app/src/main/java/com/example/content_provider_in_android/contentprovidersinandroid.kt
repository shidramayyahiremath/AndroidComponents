package com.example.contentprovidersinandroid

import android.content.ContentProvider
import android.content.ContentUris
import android.content.ContentValues
import android.content.Context
import android.content.UriMatcher
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteQueryBuilder
import android.net.Uri

class MyContentProvider : ContentProvider() {

    companion object {
        const val PROVIDER_NAME = "com.demo.user.provider"
        const val URL = "content://$PROVIDER_NAME/users"
        val CONTENT_URI: Uri = Uri.parse(URL)
        const val ID = "id"
        const val NAME = "name"
        const val URI_CODE = 1

        private val uriMatcher: UriMatcher = UriMatcher(UriMatcher.NO_MATCH).apply {
            addURI(PROVIDER_NAME, "users", URI_CODE)
            addURI(PROVIDER_NAME, "users/*", URI_CODE)
        }

        const val DATABASE_NAME = "UserDB"
        const val TABLE_NAME = "Users"
        const val DATABASE_VERSION = 1
        const val CREATE_DB_TABLE = "CREATE TABLE $TABLE_NAME (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL);"
    }

    private var db: SQLiteDatabase? = null

    override fun onCreate(): Boolean {
        val dbHelper = DatabaseHelper(context)
        db = dbHelper.writableDatabase
        return db != null
    }

    override fun query(
        uri: Uri,
        projection: Array<String>?,
        selection: String?,
        selectionArgs: Array<String>?,
        sortOrder: String?
    ): Cursor? {
        val qb = SQLiteQueryBuilder().apply { tables = TABLE_NAME }
        if (uriMatcher.match(uri) != URI_CODE) {
            throw IllegalArgumentException("Unknown URI $uri")
        }

        val cursor = qb.query(db, projection, selection, selectionArgs, null, null, sortOrder ?: ID)
        cursor.setNotificationUri(context!!.contentResolver, uri)
        return cursor
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        val rowID = db!!.insert(TABLE_NAME, null, values)
        if (rowID > 0) {
            val _uri = ContentUris.withAppendedId(CONTENT_URI, rowID)
            context!!.contentResolver.notifyChange(_uri, null)
            return _uri
        }
        throw SQLiteException("Failed to add a record into $uri")
    }

    override fun update(uri: Uri, values: ContentValues?, selection: String?, selectionArgs: Array<String>?): Int {
        val count = db!!.update(TABLE_NAME, values, selection, selectionArgs)
        context!!.contentResolver.notifyChange(uri, null)
        return count
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        val count = db!!.delete(TABLE_NAME, selection, selectionArgs)
        context!!.contentResolver.notifyChange(uri, null)
        return count
    }

    override fun getType(uri: Uri): String? = when (uriMatcher.match(uri)) {
        URI_CODE -> "vnd.android.cursor.dir/users"
        else -> throw IllegalArgumentException("Unsupported URI: $uri")
    }

    private class DatabaseHelper(context: Context?) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
        override fun onCreate(db: SQLiteDatabase) {
            db.execSQL(CREATE_DB_TABLE)
        }

        override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
            db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
            onCreate(db)
        }
    }
}
