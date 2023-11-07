package com.example.tareaunidad2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class daoUsuario {
    SQLiteDatabase bd;//Instancia de la base de datos
    ArrayList<Usuario> lista = new ArrayList<Usuario>();//Almacena una lista de  objetos de tipo contacto
    Usuario c;//Instancia de la clase contacto, utilizada para operaciones de busqueda
    Context ct;//contexto de la aplicacion
    String nombreBD= "BDtarea";//nombre de la Base de datos

    //cadena de sentencia sql que crea la tabla contacto si no existe, define las columnas de las tablas
    String tabla = "create table if not exists usuario(id integer primary key autoincrement, correo text, password text)";


    public daoUsuario(Context c){
        this.ct=c;
        bd=c.openOrCreateDatabase(nombreBD, Context.MODE_PRIVATE, null);
        bd.execSQL(tabla);
    }

    public boolean insertar(Usuario usuario){
        ContentValues contenedor = new ContentValues();
        contenedor.put("correo", usuario.getCorreo());
        contenedor.put("password", usuario.getPassword());
        return (bd.insert("usuario", null, contenedor))>0;
    }

    public Usuario getUsuarioByNombre(String correo){
        String consulta = "SELECT * FROM usuario WHERE correo = ? LIMIT 1";
        Usuario usuario = null;
        try{
            Cursor cursor = bd.rawQuery(consulta, new String[]{correo});
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    int id = cursor.getInt(0);
                    String correoR = cursor.getString(1);
                    String password = cursor.getString(2);
                    usuario = new Usuario(id, correoR, password);
                }
                cursor.close();
            }
        }catch (Exception e){
            return null;
        }
        return usuario;
    }


    public boolean eliminar(int id){
        //metodo para eliminar el contacto de la BD segun el id proporcionado,
        return (bd.delete("usuario","id="+id,null ))>0;
    }

    public boolean editar(Usuario usuario){
        ContentValues contenedor = new ContentValues();
        contenedor.put("correo", usuario.getCorreo());
        contenedor.put("password", usuario.getPassword());
        return (bd.update("usuario", contenedor, "id="+usuario.getId(),null))>0;
    }

    public ArrayList<Usuario>verTodo(){
        lista.clear();
        Cursor cursor = bd.rawQuery("select * from usuario", null);
        if (cursor!=null && cursor.getCount()>0){
            cursor.moveToFirst();
            do{
                lista.add(new Usuario(cursor.getInt(0),
                        cursor.getString(1), cursor.getString(2)));
            }while (cursor.moveToNext());
        }
        return lista;
    }

    public Usuario verUno(int posicion){
        Cursor cursor = bd.rawQuery("select * from usuario", null);
        cursor.moveToPosition(posicion);
        c=new Usuario(cursor.getInt(0),
                cursor.getString(1), cursor.getString(2));
        return c;
    }
}