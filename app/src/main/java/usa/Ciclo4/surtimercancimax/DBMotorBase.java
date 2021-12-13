package usa.Ciclo4.surtimercancimax;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBMotorBase extends SQLiteOpenHelper {

    public DBMotorBase(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE favoritos (id INT, titulo TEXT,descripcion TEXT)");
        // favoritos


        /*db.execSQL("INSERT INTO favoritos VALUES (0, 'Carpa2x2','No')");
        db.execSQL("INSERT INTO favoritos VALUES (1, 'Carpa3x2','Se')");
        db.execSQL("INSERT INTO favoritos VALUES (2, 'Carpa3x3','Que')");
        db.execSQL("INSERT INTO favoritos VALUES (3, 'MesaBanquete','Escribir')");
        db.execSQL("INSERT INTO favoritos VALUES (4, 'SillaNoBrazos','En')");
        db.execSQL("INSERT INTO favoritos VALUES (5, 'LucesLaser','Estos')");
        db.execSQL("INSERT INTO favoritos VALUES (6, 'LucesDisco','Campos')");
        db.execSQL("INSERT INTO favoritos VALUES (7, 'LucesCadena',':3')");*/
        //productos

        db.execSQL("CREATE TABLE productos_carpas(imagen INT, titulo TEXT,descripcion TEXT)");

        db.execSQL("INSERT INTO productos_carpas VALUES (0, 'Carpa 2x2','No')");
        db.execSQL("INSERT INTO productos_carpas VALUES (1, 'Carpa 3x2','Se')");
        db.execSQL("INSERT INTO productos_carpas VALUES (2, 'Carpa 3x3','Que')");

        db.execSQL("CREATE TABLE productos_Muebles(imagen INT, titulo TEXT,descripcion TEXT)");

        db.execSQL("INSERT INTO productos_Muebles VALUES (3, 'Mesa Banquete','Escribir')");
        db.execSQL("INSERT INTO productos_Muebles VALUES (4, 'Silla No Brazos','En')");

        db.execSQL("CREATE TABLE productos_luces(imagen INT, titulo TEXT,descripcion TEXT)");
        db.execSQL("INSERT INTO productos_luces VALUES (5, 'Luces Laser','Estos')");
        db.execSQL("INSERT INTO productos_luces VALUES (6, 'Luces Disco','Campos')");
        db.execSQL("INSERT INTO productos_luces VALUES (7, 'Luces Cadena',':3')");


        //servicios

        db.execSQL("CREATE TABLE servicios (titulo TEXT,descripcion TEXT)");
        //---- Registros
        db.execSQL("INSERT INTO servicios VALUES ('Cumpleaños','aaa')");
        db.execSQL("INSERT INTO servicios VALUES ('primeras comuniones','bbb')");
        db.execSQL("INSERT INTO servicios VALUES ('bodas','ccc')");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE favoritos");
        db.execSQL("DROP TABLE productos_carpas");
        db.execSQL("DROP TABLE productos_Muebles");
        db.execSQL("DROP TABLE productos_luces");
        db.execSQL("DROP TABLE servicios");
        onCreate(db);
    }

}
