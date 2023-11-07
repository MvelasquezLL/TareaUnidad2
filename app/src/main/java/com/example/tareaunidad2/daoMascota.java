package com.example.tareaunidad2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class daoMascota {
    SQLiteDatabase bd;//Instancia de la base de datos
    ArrayList<mascota> lista = new ArrayList<mascota>();//Almacena una lista de  objetos de tipo contacto
    mascota m;//Instancia de la clase contacto, utilizada para operaciones de busqueda
    Context ct;//contexto de la aplicacion
    String nombreBD= "BDtarea";//nombre de la Base de datos

    //cadena de sentencia sql que crea la tabla contacto si no existe, define las columnas de las tablas
    String tabla = "create table if not exists mascota(id integer primary key autoincrement, nombre text, raza text, tamano text, peso text, enfermedad text)";


    public daoMascota(Context c){
        //Constructor de la clase, acepta un parametro de tipo context y utiliza para inicializar la clase
        this.ct=c;
        bd=c.openOrCreateDatabase(nombreBD, Context.MODE_PRIVATE, null);
        bd.execSQL(tabla);
    }

    public boolean insertar(mascota m){
        //metodo para insertar un nuevo contacto en la base de datos
        ContentValues contenedor = new ContentValues();
        contenedor.put("nombre", m.getNombre());
        contenedor.put("raza", m.getRaza());
        contenedor.put("tamano", m.getTamano());
        contenedor.put("peso", m.getPeso());
        contenedor.put("enfermedad", m.getEnfermedad());
        return (bd.insert("mascota", null, contenedor))>0;
    }

    public boolean eliminar(int id){
        //metodo para eliminar el contacto de la BD segun el id proporcionado,
        return (bd.delete("mascota","id="+id,null ))>0;
    }

    public boolean editar(mascota m){
        //metodo para actualizar la informacion de un contacto en la base de datos
        ContentValues contenedor = new ContentValues();
        contenedor.put("nombre", m.getNombre());
        contenedor.put("raza", m.getRaza());
        contenedor.put("tamano", m.getTamano());
        contenedor.put("peso", m.getPeso());
        contenedor.put("enfermedad", m.getEnfermedad());
        return (bd.update("mascota", contenedor, "id="+m.getId(),null))>0;
    }

    public ArrayList<mascota>verTodo(){
        //Metodo para recuperar una lista de contacto desde la base de datos
        lista.clear();
        Cursor cursor = bd.rawQuery("select * from mascota", null);
        if (cursor!=null && cursor.getCount()>0){
            cursor.moveToFirst();
            do{
                lista.add(new mascota(cursor.getInt(0),
                        cursor.getString(1), cursor.getString(2),
                        cursor.getString(3), cursor.getString(4),
                        cursor.getString(5)));
            }while (cursor.moveToNext());
        }
        return lista;
    }

    public mascota verUno(int posicion){
        //Metodo para buscar el contacto segun su id en la base de datos y devolverlo como un objeto contacto.
        Cursor cursor = bd.rawQuery("select * from mascota", null);
        cursor.moveToPosition(posicion);
        m=new mascota(cursor.getInt(0),
                cursor.getString(1), cursor.getString(2),
                cursor.getString(3), cursor.getString(4),
                cursor.getString(5));
        return m;
    }
}
