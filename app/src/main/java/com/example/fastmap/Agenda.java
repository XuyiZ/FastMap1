package com.example.fastmap;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.CalendarView;
import androidx.appcompat.app.AlertDialog;

class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda);

        CalendarView calendarView = (CalendarView) findViewById(R.id.CalendarView);
        calendarView.setOnDateChangeListener((CalendarView.OnDateChangeListener) this);
    }

    public void onSelectedDayChange(CalendarView calendarView, int i, int i1, int i2) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        CharSequence []items = new CharSequence[3];
        items[0]="Agregar Cliente";
        items[1]="Ver Clientes";
        items[2]="Cancelar";

        final int nombre;
        nombre = i;


        builder.setTitle("Seleciona un cliente")
                .setItems(items, (dialogInterface, i3) -> {
                    if (i3 ==0) {
                        //actividad agregar CLiente
                        Intent intent = new Intent(getApplication(), AddActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putInt("nombre",nombre);


                        intent.putExtras(bundle);
                        startActivity(intent);

                    }else if(i3 ==1){
                        //Ver clientes
                        Intent intent = new Intent(getApplication(), ViewEventsActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putInt("nombre",nombre);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }

                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}