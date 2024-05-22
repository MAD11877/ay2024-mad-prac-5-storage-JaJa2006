package sg.edu.np.mad.madpractical5;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;


public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
        private static final String DATABASE_NAME = "userDB.db";
        public static final String TABLE_USERS = "users";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_DESCRIPTION = "description";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_FOLLOWED = "followed";
        public DatabaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, DATABASE_NAME, factory, DATABASE_VERSION);
        }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PRODUCTS_TABLE = "CREATE TABLE " + TABLE_USERS +
                "(" + COLUMN_NAME + " TEXT," + COLUMN_DESCRIPTION + " TEXT,"
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_FOLLOWED + " TEXT" + ")";
        db.execSQL(CREATE_PRODUCTS_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }
    public void addUser(User user) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, user.getName());
        values.put(COLUMN_DESCRIPTION, user.getDescription());
        values.put(COLUMN_FOLLOWED, user.getFollowed());
        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(TABLE_USERS, null, values);
    }
    public ArrayList<User> getUsers() {
        ArrayList<User> users = new ArrayList<User>();
        String query = "SELECT * FROM " + TABLE_USERS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {
            User user = new User();
            user.name = cursor.getString(0);
            user.description = cursor.getString(1);
            user.id = Integer.parseInt(cursor.getString(2));
            user.followed = Boolean.parseBoolean(cursor.getString(3));
            users.add(user);
            cursor.moveToNext();
        }
        cursor.close();
        return users;
    }

    public void updateUsers(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();


        values.put(COLUMN_NAME, user.name);
        values.put(COLUMN_DESCRIPTION, user.description);
        values.put(COLUMN_ID, user.id);
        if (user.followed) {
            values.put(COLUMN_FOLLOWED, false);
        } else {
            values.put(COLUMN_FOLLOWED, true);
        }

        db.update(TABLE_USERS, values, "id="+user.id, null);
    }



}

