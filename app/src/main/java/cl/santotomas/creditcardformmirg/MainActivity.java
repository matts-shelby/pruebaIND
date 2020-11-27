package cl.santotomas.creditcardformmirg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText   NumeroT,nombre,apellido,Mes,Ano,CVC,CalleYnumero,Estado,CPostal;
    String n = NumeroT.getText().toString();
    String nm = nombre.getText().toString();
    String a = apellido.getText().toString();
    String M = Mes.getText().toString();
    String A = Ano.getText().toString();
    String CV = CVC.getText().toString();
    String CyN = CalleYnumero.getText().toString();
    String E = Estado.getText().toString();
    String P = CPostal.getText().toString();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NumeroT = (EditText) findViewById(R.id.NumeroT);
        nombre = (EditText) findViewById(R.id.nombre);
        apellido = (EditText) findViewById(R.id.apellido);
        Mes = (EditText) findViewById(R.id.Mes);
        Ano = (EditText) findViewById(R.id.Ano);
        CVC = (EditText) findViewById(R.id.CVC);
        CalleYnumero = (EditText) findViewById(R.id.CalleYnumero);
        Estado = (EditText) findViewById(R.id.Estado);
        CPostal = (EditText) findViewById(R.id.CPostal);
    }
     public void Registrar(View view){
         SQLiteOpenHelper admin = new SQLiteOpenHelper(this, "bdtarjeta", null, 1);
         SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

         String n = NumeroT.getText().toString();
         String nm = nombre.getText().toString();
         String a = apellido.getText().toString();
         String M = Mes.getText().toString();
         String A = Ano.getText().toString();
         String CV = CVC.getText().toString();
         String CyN = CalleYnumero.getText().toString();
         String E = Estado.getText().toString();
         String P = CPostal.getText().toString();

         if(!n.isEmpty() && !a.isEmpty() && !t.isEmpty() && !mes.isEmpty() && !anio.isEmpty() && !codig.isEmpty() &&
                 !d.isEmpty() && !c.isEmpty() && !e.isEmpty() && !codp.isEmpty()){
             ContentValues registro = new ContentValues();

             registro.put("NumeroT", n);
             registro.put("nombre", nm);
             registro.put("apellido", a);
             registro.put("Mes", M);
             registro.put("Ano", A);
             registro.put("CVC", CV);
             registro.put("CalleYnumero", CyN);
             registro.put("Estado", E);
             registro.put("CPostal", P);

             BaseDeDatos.insert("tarjeta", null, registro);

             BaseDeDatos.close();
             NumeroT.setText("");
             nombre.setText("");
             apellido.setText("");
             Mes.setText("");
             Ano.setText("");
             CVC.setText("");
             CalleYnumero.setText("");
             Estado.setText("");
             CPostal.setText("");

             Toast.makeText(this,"Registro exitoso", Toast.LENGTH_SHORT).show();
    }else{
             Toast.makeText(this, "Debes llenar todos los campos", Toast.LENGTH_SHORT).show();
         }
    }
    public void Buscar(View view) {
        SQLiteOpenHelper admin = new SQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase BaseDeDatabase = admin.getWritableDatabase();

        String NumTarjeta = NumeroT.getText().toString();

        if (!NumTarjeta.isEmpty()) {
            Cursor fila = BaseDeDatabase.rawQuery
                    ("select * from tarjeta where num_tar =" + NumTarjeta, null);
            if(fila.moveToFirst()){
                NumeroT.setText(fila.getString(0));
                nombre.setText(fila.getString(1));
                apellido.setText(fila.getString(2));
                Mes.setText(fila.getString(3));
                Ano.setText(fila.getString(4));
                CVC.setText(fila.getString(5));
                CalleYnumero.setText(fila.getString(6));
                Estado.setText(fila.getString(7));
                CPostal.setText(fila.getString(8));
                BaseDeDatabase.close();
            } else {
                Toast.makeText(this,"No existe la tarjeta", Toast.LENGTH_SHORT).show();
                BaseDeDatabase.close();
            }

        } else {
            Toast.makeText(this, "Debes introducir el numero de la tarjeta", Toast.LENGTH_SHORT).show();
        }

    }
  }