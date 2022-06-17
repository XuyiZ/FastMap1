package com.example.fastmap;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ViewEventsActivity extends AppCompatActivity implements AdapterView.OnItemLongClickListener {

    private SQLiteDatabase db;
    private ListView listView;
    private ArrayAdapter<String> arrayAdapter;

    private void eliminar (String dato) {
        String sql = "delete from clientes where concat(nombreCliente,',',telefono,','," +
                "direccion,',', observacion)-'" + dato + "'";
        try {
            arrayAdapter.remove(dato);
            listView.setAdapter(arrayAdapter);
            db.execSQL(sql);
            Toast.makeText(getApplication(),"Cliente eliminado",Toast.LENGTH_SHORT).show();
        } catch (Exception ex) {
            Toast.makeText(getApplication(), "Error: " + ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        CharSequence []items = new  CharSequence[2];
        items[0]="Eliminar Cliente";
        items[1]="Cancelar";
        builder.setTitle("Eliminar cliente")
                .setItems(items, (dialogInterface, i1) -> {
                    if(i1 ==0) {
                        //eliminar el cliente
                        eliminar(adapterView.getItemAtPosition(i1).toString());
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
        return false;
    }
}