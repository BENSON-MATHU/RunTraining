package com.runtraining;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;



public class BaseDeDatos extends SQLiteOpenHelper{
	
	

	
	String sqlCreate = "CREATE TABLE datos (id INTEGER PRIMARY KEY AUTOINCREMENT, distancia DOUBLE)";
	
    public BaseDeDatos (Context contexto, String nombre, CursorFactory factory, int version){
    
    	super(contexto,nombre,factory,version);
    
    }
    
    
    
    public void insertar(SQLiteDatabase db){ 
		
        db = getWritableDatabase();

        //Si hemos abierto correctamente la base de datos
        if(db != null)
        {
            //Insertamos 5 usuarios de ejemplo
            for(int i=1; i<=5; i++)
            {
                //Generamos los datos
               

                //Insertamos los datos en la tabla Usuarios
                db.execSQL("INSERT INTO datos (distancia) VALUES (50)");
                db.execSQL("INSERT INTO datos (distancia) VALUES (1)");
                db.execSQL("INSERT INTO datos (distancia) VALUES (4)");
                db.execSQL("INSERT INTO datos (distancia) VALUES (25)");
                db.execSQL("INSERT INTO datos (distancia) VALUES (65)");
                db.execSQL("INSERT INTO datos (distancia) VALUES (42)");
                db.execSQL("INSERT INTO datos (distancia) VALUES (100)");
                db.execSQL("INSERT INTO datos (distancia) VALUES (10)");
                db.execSQL("INSERT INTO datos (distancia) VALUES (35)");
                db.execSQL("INSERT INTO datos (distancia) VALUES (6)");
                db.execSQL("INSERT INTO datos (distancia) VALUES (9)");
                db.execSQL("INSERT INTO datos (distancia) VALUES (50)");
                db.execSQL("INSERT INTO datos (distancia) VALUES (50)");
            }

        }
           
        }
    
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(sqlCreate);
		insertar(db);
		
		
	}

	

	
	
	
	public double consultar(int id){
		
		SQLiteDatabase bd =getReadableDatabase();
		Cursor micursor = bd.rawQuery("SELECT distancia FROM datos WHERE id= "+id, null);
		double valorBaseDatos=micursor.getDouble(0);
		return valorBaseDatos;
		
		
		
		
	}
	
	
	
	

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		//db.execSQL("DROP TABLE IF EXISTS Usuarios");
		//db.execSQL(sqlCreate);
	}


}
