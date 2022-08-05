package com.sanjeev.sanjeevapp.module_9;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class DBHandlerContacts extends SQLiteOpenHelper {

    // creating a constant variables for our database.
    // below variable is for our database name.
    private static final String DB_NAME = "contactdb";

    // below int is our database version
    private static final int DB_VERSION = 7;

    // below variable is for our table name.
    private static final String TABLE_NAME = "mycontacts";

    // below variable is for our id column.
    private static final String ID_COL = "id";

    // below variable is for our course name column
    private static final String NAME_COL = "name";
    private static final String IMAGE_COL = "image";

    // below variable id for our course duration column.
    private static final String tel_COl = "telphone";

    // below variable for our course description column.
    private static final String email_COL = "email";

    // below variable is for our course tracks column.
    private static final String address_COL = "address";

    // creating a constructor for our database handler.
    public DBHandlerContacts(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    // below method is for creating a database by running a sqlite query
    @Override
    public void onCreate(SQLiteDatabase db) {
        // on below line we are creating
        // an sqlite query and we are
        // setting our column names
        // along with their data types.
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME_COL + " TEXT,"
                + tel_COl + " TEXT,"
                + email_COL + " TEXT,"
                + address_COL + " TEXT,"
                + IMAGE_COL + " TEXT)";

        // at last we are calling a exec sql
        // method to execute above sql query
        db.execSQL(query);
    }


    /*FOr image*/
    public static byte[] getBitmapAsByteArray(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, outputStream);
        return outputStream.toByteArray();
    }

    // this method is use to add new course to our sqlite database.
    public void addNewContact(String mImageBitmap, String name, String tel, String email, String address) {

        // on below line we are creating a variable for
        // our sqlite database and calling writable method
        // as we are writing data in our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are creating a
        // variable for content values.
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(NAME_COL, name);
        values.put(tel_COl, tel);
        values.put(email_COL, email);
        values.put(address_COL, address);
        values.put(IMAGE_COL, mImageBitmap);

        // after adding all values we are passing
        // content values to our table.
        db.insert(TABLE_NAME, null, values);

        // at last we are closing our
        // database after adding database.
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // we have created a new method for reading all the courses.
    public ArrayList<ContactModel> readAllContacts() {
        // on below line we are creating a
        // database for reading our database.
        SQLiteDatabase db = this.getReadableDatabase();

        // on below line we are creating a cursor with query to read data from database.
        Cursor cursorCourses = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        // on below line we are creating a new array list.
        ArrayList<ContactModel> courseModalArrayList = new ArrayList<>();

        // moving our cursor to first position.
        if (cursorCourses.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                courseModalArrayList.add(new ContactModel(
                        cursorCourses.getString(5),//for Image
                        cursorCourses.getString(1),
                        cursorCourses.getString(2),
                        cursorCourses.getString(3),
                        cursorCourses.getString(4),
                        cursorCourses.getInt(0)));
            } while (cursorCourses.moveToNext());
            // moving our cursor to next.
        }
        // at last closing our cursor
        // and returning our array list.
        cursorCourses.close();
//        db.close();
        return courseModalArrayList;
    }

    public ArrayList<ContactModel> searchContact(String type,String searchText) {
        // on below line we are creating a
        // database for reading our database.
        SQLiteDatabase db = this.getReadableDatabase();

        if (type.equals("name")) {
            // on below line we are creating a cursor with query to read data from database.
            Cursor cursorCourses = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE "+NAME_COL +" LIKE  \"%"+searchText+"%\"", null);

            // on below line we are creating a new array list.
            ArrayList<ContactModel> courseModalArrayList = new ArrayList<>();

            // moving our cursor to first position.
            if (cursorCourses.moveToFirst()) {
                do {
                    // on below line we are adding the data from cursor to our array list.
                    courseModalArrayList.add(new ContactModel(cursorCourses.getString(5),
                            cursorCourses.getString(1),
                            cursorCourses.getString(2),
                            cursorCourses.getString(3),
                            cursorCourses.getString(4),
                            cursorCourses.getInt(0)));
                } while (cursorCourses.moveToNext());
                // moving our cursor to next.
            }
            // at last closing our cursor
            // and returning our array list.
            cursorCourses.close();
            return courseModalArrayList;
        }
        else{
            // on below line we are creating a cursor with query to read data from database.
            Cursor cursorCourses = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE "+email_COL +" LIKE  \"%"+searchText+"%\"", null);

            // on below line we are creating a new array list.
            ArrayList<ContactModel> courseModalArrayList = new ArrayList<>();

            // moving our cursor to first position.
            if (cursorCourses.moveToFirst()) {
                do {
                    // on below line we are adding the data from cursor to our array list.
                    courseModalArrayList.add(new ContactModel(cursorCourses.getString(5), cursorCourses.getString(1),
                            cursorCourses.getString(2),
                            cursorCourses.getString(3),
                            cursorCourses.getString(4),
                            cursorCourses.getInt(0)));
                } while (cursorCourses.moveToNext());
                // moving our cursor to next.
            }
            // at last closing our cursor
            // and returning our array list.
            cursorCourses.close();
            return courseModalArrayList;
        }
    }

    public void deleteContact(ContactModel modal) {
        SQLiteDatabase db = this.getReadableDatabase();
        db.delete(TABLE_NAME,ID_COL +" = "+ String.valueOf(modal.getId()),null);

    }

    public void updateContact(String stName, String stTel, String stEmail, String stAddress, String currentID) {
        SQLiteDatabase db = this.getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(NAME_COL, stName);
        values.put(tel_COl, stTel);
        values.put(email_COL, stEmail);
        values.put(address_COL, stAddress);

        // updating row
         db.update(TABLE_NAME, values, ID_COL + " = ?",
                new String[] { String.valueOf(currentID) });
    }
}
