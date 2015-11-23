package co.edu.uninorte.neumoniaapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Lucia on 23/11/2015.
 */
public class MySQLiteHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "PatientDB";

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create book table
        String CREATE_PATIENT_TABLE = "CREATE TABLE patients ( " +
                "epoc TEXT, " +
                "iC TEXT, " +
                "dM TEXT, " +
                "eRC TEXT, " +
                "abAl TEXT, " +
                "inmuno TEXT, " +
                "neoplas TEXT, " +
                "anPrev TEXT, " +
                "alPen TEXT, " +
                "inMac TEXT, " +
                "inAut TEXT, " +
                "alCog TEXT, " +
                "inIngOr TEXT, " +
                "abPsi TEXT, " +
                "malSprt TEXT, " +
                "tolOr TEXT, " +
                "conf TEXT, " +
                "frecRes TEXT, " +
                "edad TEXT, " +
                "tensArtSist TEXT, " +
                "tensArtDiast TEXT, " +
                "temp TEXT, " +
                "urea TEXT, " +
                "so2 TEXT, " +
                "efPle TEXT, " +
                "exam TEXT, " +
                "examType TEXT, " +
                "risk TEXT, " +
                "betalac TEXT, " +
                "expoMen TEXT, " +
                "resdHog TEXT, " +
                "disfag TEXT, " +
                "respMec TEXT, " +
                "sopVas TEXT, " +
                "infMult TEXT, " +
                "hipotens TEXT, " +
                "pafio2 TEXT, " +
                "leucocitos TEXT, " +
                "plaquetas TEXT, " +
                "terCor TEXT, " +
                "terAnt7 TEXT, " +
                "malnutr TEXT, " +
                "fumador TEXT, " +
                "vihTard TEXT, " +
                "eRCH TEXT, " +
                "infInf TEXT, " +
                "neuNecro TEXT, " +
                "infPielConc TEXT, " +
                "proStruPul TEXT, " +
                "obsEnd TEXT, " +
                "idPaciente TEXT PRIMARY KEY )";

        // create books table
        db.execSQL(CREATE_PATIENT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older books table if existed
        db.execSQL("DROP TABLE IF EXISTS patients");

        // create fresh books table
        this.onCreate(db);
    }
    //---------------------------------------------------------------------

    /**
     * CRUD operations (create "add", read "get", update, delete) book + get all books + delete all books
     */

    // Books table name
    private static final String TABLE_PATIENTS = "patients";

    private static final String KEY_ID = "idPaciente";


    // Patients Table Columns names

    private static final String[] COLUMNS = {"epoc",
            "iC",
            "dM",
            "eRC",
            "abAl",
            "inmuno",
            "neoplas",
            "anPrev",
            "alPen",
            "inMac",
            "inAut",
            "alCog",
            "inIngOr",
            "abPsi",
            "malSprt",
            "tolOr",
            "conf",
            "frecRes",
            "edad",
            "tensArtSist",
            "tensArtDiast",
            "temp",
            "urea",
            "so2",
            "efPle",
            "exam",
            "examType",
            "risk",
            "betalac",
            "expoMen",
            "resdHog",
            "disfag",
            "respMec",
            "sopVas",
            "infMult",
            "hipotens",
            "pafio2",
            "leucocitos",
            "plaquetas",
            "terCor",
            "terAnt7",
            "malnutr",
            "fumador",
            "vihTard",
            "eRCH",
            "infInf",
            "neuNecro",
            "infPielConc",
            "proStruPul",
            "obsEnd",
            "idPaciente"};

    public void addPatient(Patient p1){
        Log.d("addP1", String.valueOf(p1.toStringArray()));
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        ArrayList names=new ArrayList();
        names.addAll(Arrays.asList(COLUMNS));

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        String[] info = p1.toStringArray();
        for(int i=0;i<info.length;i++) {
            values.put(names.get(i).toString(), info[i]); // get title
        }


        // 3. insert
        db.insert(TABLE_PATIENTS, // table
                null, //nullColumnHack
                values); // key/value -> keys = column names/ values = column values

        // 4. close
        db.close();
    }

    public Patient getPatient(String id){

        // 1. get reference to readable DB
        SQLiteDatabase db = this.getReadableDatabase();

        // 2. build query
        Cursor cursor =
                db.query(TABLE_PATIENTS, // a. table
                        COLUMNS, // b. column names
                        " idPaciente = ?", // c. selections
                        new String[]{id}, // d. selections args
                        null, // e. group by
                        null, // f. having
                        null, // g. order by
                        null); // h. limit

        // 3. if we got results get the first one
        if (cursor != null)
            cursor.moveToFirst();

        // 4. build book object
        Patient p1 = new Patient();
        String[] values=new String[p1.toStringArray().length];
        for (int i=0;i<p1.toStringArray().length-1;i++) {
            Log.d("desarrollo base",cursor.getString(i));
            values[i]=cursor.getString(i);
        }

        p1.fromStringArray(values);

        Log.d("getBook(" + id + ")", String.valueOf(p1.toStringArray()));

        // 5. return book
        return p1;
    }

    // Get All Patients
    public List<Patient> getAllPatients() {
        List<Patient> patients = new LinkedList<Patient>();

        // 1. build the query
        String query = "SELECT  * FROM " + TABLE_PATIENTS;

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. go over each row, build book and add it to list
        Patient p1 = null;
        if (cursor.moveToFirst()) {
            do {
                p1 = new Patient();
                String[] values=new String[p1.toStringArray().length];
                for (int i=0;i<p1.toStringArray().length;i++) {
                    values[i]=cursor.getString(i);
                }
                p1.fromStringArray(values);

                // Add patient to patients
                patients.add(p1);
            } while (cursor.moveToNext());
        }

        Log.d("getAllBooks()", String.valueOf(p1.toStringArray()));

        // return books
        return patients;
    }

    // Updating single patient
    public int updatePatient(Patient p1) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        ArrayList names=new ArrayList();
        names.addAll(Arrays.asList(COLUMNS));
        String[] info = p1.toStringArray();
        for(int i=0;i<info.length;i++) {
            values.put(names.get(i).toString(), info[i]); // get title
        }

        // 3. updating row
        int i = db.update(TABLE_PATIENTS, //table
                values, // column/value
                KEY_ID+" = ?", // selections
                new String[] { p1.getIdPaciente() }); //selection args

        // 4. close
        db.close();

        return i;

    }

    // Deleting single patient
    public void deletePatient(Patient p1) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. delete
        db.delete(TABLE_PATIENTS,
                KEY_ID + " = ?",
                new String[]{p1.getIdPaciente()});

        // 3. close
        db.close();

        Log.d("deleteBook", String.valueOf(p1.toStringArray()));

    }
}