package garanito.com.br.bookplus.Manager

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class BookFairManager(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, 1) {

    companion object {

        val DATABASE_NAME = "Fairs.db"
        val TABLE_NAME = "fair_table"
        val COL_1 = "ID"
        val COL_2 = "NAME"
        val COL_3 = "DESCRIPTION"
        val COL_4 = "ADDRESS"
        val COL_5 = "INITIAL_DATE"
        val COL_6 = "FINAL_DATE"
        val COL_7 = "INITIAL_HOUR"
        val COL_8 = "FINAL_HOUR"
    }

    override fun onCreate(db: SQLiteDatabase) {

        db.execSQL("CREATE TABLE IF NOT EXISTS $TABLE_NAME(ID INTEGER PRIMARY KEY AUTOINCREMENT , NAME  TEXT , DESCRIPTION TEXT , ADDRESS TEXT, INITIAL_DATE TEXT, FINAL_DATE TEXT,INITIAL_HOUR TEXT, INITIAL_HOUR TEXT)")

    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)

    }


    fun intertData(name: String, description: String, address: String): Boolean? {
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(COL_2, name)
        cv.put(COL_3, description)
        cv.put(COL_4, address)
        val res = db.insert(TABLE_NAME, null, cv)
        return !res.equals(-1)
    }


    fun getAllData(): Cursor {

        val db = this.writableDatabase
        return db.rawQuery("SELECT * FROM $TABLE_NAME", null)
    }


    fun getData(id: String): Cursor {
        val db = this.writableDatabase
        return db.rawQuery("SELECT * FROM $TABLE_NAME WHERE ID=? ", arrayOf(id), null)
    }

    fun updateData(id: String, name: String, profession: String, salary: String): Boolean? {
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(COL_1, id)
        cv.put(COL_2, name)
        cv.put(COL_3, profession)
        cv.put(COL_4, salary)
        db.update(TABLE_NAME, cv, "ID=?", arrayOf(id))
        return true
    }

    fun daleteData(id: String): Int? {
        val db = this.writableDatabase
        return db.delete(TABLE_NAME, "ID =? ", arrayOf(id))
    }


}
