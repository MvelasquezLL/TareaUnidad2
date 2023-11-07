package com.example.tareaunidad2;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Adaptador extends BaseAdapter {
    ArrayList<mascota> lista;
    daoMascota dao;
    mascota m;
    Activity a;//Una referencia a la actividad que utiliza este adaptador.
    int id=0;//Una variable que se utiliza para almacenar temporalmente el ID del contacto seleccionado.
    public Adaptador(Activity a, ArrayList<mascota> lista, daoMascota dao ){
        //El constructor de la clase recibe tres parámetros:
        this.lista = lista;
        this.a= a;
        this.dao= dao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    //Devuelve la cantidad de elementos en la lista
    @Override
    public int getCount() {
        return lista.size();
    }
    //No se utiliza en este contexto, devuelve null
    @Override
    public Object getItem(int i) {
        m=lista.get(i);
        return null;
    }
    //Devuelve el ID de un contacto en la posición i.
    @Override
    public long getItemId(int i) {
        m=lista.get(i);
        return m.getId();
    }
    //Este método se utiliza para mostrar cada elemento de la lista en la vista de la actividad.
    @Override
    public View getView(int posicion, View view, ViewGroup viewGroup) {
        //Se comprueba si la vista v es nula. Si es nula, se infla la vista desde el archivo de diseño R.layout.item.
        View v = view;
        if (v == null) {
            LayoutInflater li = (LayoutInflater) a.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = li.inflate(R.layout.item, null);
        }
        //Se obtiene el contacto en la posición posicion de la lista lista.
        m=lista.get(posicion);
        //Se obtienen referencias a vistas (TextViews) y botones en la vista v para mostrar los detalles del contacto.
        TextView nombre=v.findViewById(R.id.nombre);
        TextView raza=v.findViewById(R.id.raza);
        TextView tamano=v.findViewById(R.id.tamano);
        TextView peso=v.findViewById(R.id.peso);
        TextView enfermedad=v.findViewById(R.id.enfermedad);
        Button editar= v.findViewById(R.id.btn_editar);
        Button eliminar= v.findViewById(R.id.btn_eliminar);
        nombre.setText(m.getNombre());
        raza.setText(m.getRaza());
        tamano.setText(m.getTamano());
        peso.setText(m.getPeso());
        enfermedad.setText(m.getEnfermedad());
        editar.setTag(posicion);
        eliminar.setTag(posicion);
        //Al hacer clic en "Editar", se muestra un cuadro de diálogo que permite al usuario editar los detalles del contacto. Los cambios se aplican a la base de datos.
        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //dialogo de editar
                int pos = Integer.parseInt(view.getTag().toString());
                final Dialog dialog = new Dialog(a);
                dialog.setTitle("Editar Registro");
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.dialogo);
                dialog.show();
                final EditText nombre = dialog.findViewById(R.id.et_nombre);
                final EditText raza = dialog.findViewById(R.id.et_raza);
                final EditText tamano = dialog.findViewById(R.id.et_tamano);
                final EditText peso = dialog.findViewById(R.id.et_peso);
                final EditText emfermedad = dialog.findViewById(R.id.et_enfermedad);
                Button guardar = dialog.findViewById(R.id.btn_agregar);
                Button cancelar = dialog.findViewById(R.id.btn_cancelar);
                m=lista.get(pos);
                setId(m.getId());
                nombre.setText(m.getNombre());
                raza.setText(m.getRaza());
                tamano.setText(m.getTamano());
                peso.setText(m.getPeso());
                emfermedad.setText(m.getEnfermedad());
                guardar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try {
                            m = new mascota(getId(), nombre.getText().toString(),
                                    raza.getText().toString(),
                                    tamano.getText().toString(),
                                    peso.getText().toString(),
                                    emfermedad.getText().toString());
                            dao.editar(m);
                            lista=dao.verTodo();
                            notifyDataSetChanged();
                            dialog.dismiss();
                        }catch (Exception e){
                            Toast.makeText(a, "Error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                cancelar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
            }
        });
        //Al hacer clic en "Eliminar", se muestra un cuadro de diálogo de confirmación para eliminar el contacto. Si el usuario confirma, el contacto se elimina de la base de datos.
        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //dialogo para confirmar si o no se elimine
                int pos=Integer.parseInt(view.getTag().toString());
                m=lista.get(posicion);
                setId(m.getId());
                AlertDialog.Builder del = new AlertDialog.Builder(a);
                del.setMessage("Estas seguro de eliminar");
                del.setCancelable(false);
                del.setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dao.eliminar(getId());
                        lista=dao.verTodo();
                        notifyDataSetChanged();
                    }
                });
                del.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                del.show();
            }
        });
        return v;
    }
}
