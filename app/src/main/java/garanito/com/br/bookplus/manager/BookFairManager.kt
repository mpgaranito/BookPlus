package garanito.com.br.bookplus.manager

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import garanito.com.br.bookplus.model.Fair

class BookFairManager(context: Context?) : SQLiteOpenHelper(context, DATABASE_NAME, null, 1) {

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

        db.execSQL("CREATE TABLE IF NOT EXISTS $TABLE_NAME(ID INTEGER PRIMARY KEY AUTOINCREMENT , NAME  TEXT , DESCRIPTION TEXT , " +
                "ADDRESS TEXT, INITIAL_DATE TEXT, FINAL_DATE TEXT,INITIAL_HOUR TEXT, FINAL_HOUR TEXT)")

    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)

    }


    fun insert(name: String, description: String, address: String, initialDate: String, finalDate: String, initialHour: String, finalHour: String): Boolean? {
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(COL_2, name)
        cv.put(COL_3, description)
        cv.put(COL_4, address)
        cv.put(COL_5, initialDate)
        cv.put(COL_6, finalDate)
        cv.put(COL_7, initialHour)
        cv.put(COL_8, finalHour)
        val res = db.insert(TABLE_NAME, null, cv)
        return !res.equals(-1)
    }


    fun select(): ArrayList<Fair> {
        val db = this.writableDatabase
        var fairs = arrayListOf<Fair>()
        var cursor = db.rawQuery("SELECT * FROM $TABLE_NAME", null)
        while (cursor.moveToNext()) {
            val id = cursor.getInt(cursor.getColumnIndex("ID"))
            val nome = cursor.getString(cursor.getColumnIndex("NAME"))
            val descricao = cursor.getString(cursor.getColumnIndex("DESCRIPTION"))
            val endereco = cursor.getString(cursor.getColumnIndex("ADDRESS"))
            val finaldate = cursor.getString(cursor.getColumnIndex("FINAL_DATE"))
            val initialdate = cursor.getString(cursor.getColumnIndex("INITIAL_DATE"))
            val finalhour = cursor.getString(cursor.getColumnIndex("FINAL_HOUR"))
            val initialhour = cursor.getString(cursor.getColumnIndex("INITIAL_HOUR"))
            fairs.add(Fair(id, nome, descricao, initialdate, finaldate, initialhour, finalhour, ""))
        }
        cursor.close()
        return fairs
    }


    fun selectById(id: String): Cursor {
        val db = this.writableDatabase
        return db.rawQuery("SELECT * FROM $TABLE_NAME WHERE ID=? ", arrayOf(id), null)
    }

    fun update(id: String, name: String, description: String, address: String, initialDate: String, finalDate: String, initialHour: String, finalHour: String): Boolean? {
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(COL_1, id)
        cv.put(COL_2, name)
        cv.put(COL_3, description)
        cv.put(COL_4, address)
        cv.put(COL_5, initialDate)
        cv.put(COL_6, finalDate)
        cv.put(COL_7, initialHour)
        cv.put(COL_8, finalHour)

        db.update(TABLE_NAME, cv, "ID=?", arrayOf(id))
        return true
    }

    fun delete(id: String): Int? {
        val db = this.writableDatabase
        return db.delete(TABLE_NAME, "ID =? ", arrayOf(id))
    }


}
