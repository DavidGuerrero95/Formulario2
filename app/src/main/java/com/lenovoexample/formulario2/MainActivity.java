package com.lenovoexample.formulario2;
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.time.Year;
import java.util.Calendar;
import java.util.Vector;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private EditText eNombre, eContrasena, eVerificacionContra, eCorreo;
    private TextView tFechaN, tDatos;
    private Button bGuardar, bBuscar,bEliminar, bContacto;
    private CheckBox cFutbol, cGuitarra, cPS4, cCine;
    private Spinner sNacimiento;
    private String sexo = "masculino";
    private String date = "";
    Vector<String> name = new Vector<String>();
    Vector<String> password = new Vector<String>();
    Vector<String> mail = new Vector<String>();
    Vector<String> sex = new Vector<String>();
    Vector<String> birth = new Vector<String>();
    Vector<String> birthplace = new Vector<String>();
    Vector<String> hobby = new Vector<String>();
    int cont = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        eNombre = findViewById(R.id.eNombre);
        eContrasena = findViewById(R.id.eContraseña);
        eVerificacionContra = findViewById(R.id.eVerificacionContra);
        eCorreo = findViewById(R.id.eCorreo);
        tFechaN = findViewById(R.id.tFechaN);
        tDatos = findViewById(R.id.tDatos);
        bGuardar = findViewById(R.id.bGuardar);
        bBuscar = findViewById(R.id.bBuscar);
        bEliminar = findViewById(R.id.bEliminar);
        bContacto = findViewById(R.id.bContacto);
        cFutbol = findViewById(R.id.cFutbol);
        cGuitarra = findViewById(R.id.cGuitarra);
        cPS4 = findViewById(R.id.cPS4);
        cCine = findViewById(R.id.cCine);
        sNacimiento = (Spinner) findViewById(R.id.sNacimiento);
        sNacimiento.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.sP1, R.layout.spinner);
        sNacimiento.setAdapter(adapter1);

        bGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre, pass, repPass, correo, nacimiento, hobbies="";
                int indice1 = sNacimiento.getSelectedItemPosition();
                nombre = eNombre.getText().toString();
                pass = eContrasena.getText().toString();
                repPass = eVerificacionContra.getText().toString();
                correo = eCorreo.getText().toString();
                switch (indice1){
                    case 1:
                        nacimiento = "Pasto";
                        break;
                    case 2:
                        nacimiento = "Medellin";
                        break;
                    case 3:
                        nacimiento = "Cali";
                        break;
                    case 4:
                        nacimiento = "Bogota";
                        break;
                    case 5:
                        nacimiento = "Cartagena";
                        break;
                    default:
                        nacimiento = "";
                }
                if(cCine.isChecked()) hobbies = hobbies +" Cine";
                if(cGuitarra.isChecked()) hobbies = hobbies + " Dormir";
                if(cFutbol.isChecked()) hobbies = hobbies + " Futbol";
                if(cPS4.isChecked()) hobbies = hobbies +" Ps4";
                if (nombre.equals("") || pass.equals("") || repPass.equals("")||
                        correo.equals("")||hobbies.equals("")||date.equals("")||nacimiento.equals("")
                        ){
                    Toast.makeText(getApplicationContext(),"Digite todos los datos", Toast.LENGTH_SHORT).show();
                    tDatos.setText("");
                }else{
                    if(pass.equals(repPass)){
                        name.add(cont,nombre);
                        mail.add(cont,correo);
                        sex.add(cont,sexo);
                        birth.add(cont,date);
                        birthplace.add(cont,nacimiento);
                        hobby.add(cont,hobbies);
                        password.add(cont,pass);
                        cont++;
                        tDatos.setText("Nombre: "+nombre+"\nCorreo: "+correo+"\nSexo: "+sexo+"\nFecha de nacimiento: "+date
                                +"\nLugar de nacimiento: "+nacimiento+"\nhobbie:"+hobbies);
                    }else{
                        Toast.makeText(getApplicationContext(),"Contraseñas no son iguales", Toast.LENGTH_SHORT).show();
                        tDatos.setText("");
                    }
                }
            }
        });

        bBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre, correo;
                boolean bandera = false;
                nombre = eNombre.getText().toString();
                correo = eCorreo.getText().toString();
                for(int i=0; i<cont ;i++){
                    if(name.elementAt(i).equals(nombre) || mail.elementAt(i).equals(correo)){
                        tDatos.setText("Nombre: "+name.elementAt(i)+"\nCorreo: "+mail.elementAt(i)+"\nSexo: "+sex.elementAt(i)+"\nFecha de nacimiento: "+birth.elementAt(i)
                                +"\nLugar de nacimiento: "+birthplace.elementAt(i)+"\nhobbie:"+hobby.elementAt(i));
                        bandera = true;
                    }
                }
                if(bandera == false){Toast.makeText(getApplicationContext(),"Contacto no se encuentra", Toast.LENGTH_SHORT).show();}
            }
        });

        bEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre, pass, repPass, correo;
                boolean bandera = false;
                nombre = eNombre.getText().toString();
                pass = eContrasena.getText().toString();
                repPass = eVerificacionContra.getText().toString();
                correo = eCorreo.getText().toString();

                for(int i=0; i<cont ;i++){
                    if(nombre.equals(name.elementAt(i)) || correo.equals(mail.elementAt(i)) || (pass.equals(repPass) && pass.equals(password.elementAt(i)))){
                        for(int j=i ;j<cont ;j++){
                            name.add(j,name.elementAt(j+1));
                            password.add(j,password.elementAt(j+1));
                            mail.add(j,mail.elementAt(j+1));
                            sex.add(j,sex.elementAt(j+1));
                            birth.add(j,birth.elementAt(j+1));
                            birthplace.add(j,birthplace.elementAt(j+1));
                            hobby.add(j,hobby.elementAt(j+1));
                            bandera = true;
                        }
                        cont--;
                    }
                }
                if(bandera == false){Toast.makeText(getApplicationContext(),"Contacto no se encuentra", Toast.LENGTH_SHORT).show();}
            }
        });

        bContacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String salida = "";
                for(int i=0; i<cont; i++){
                    salida = salida +"\n \nConctacto "+i+" Nombre: "+name.elementAt(i)+"\nCorreo: "+mail.elementAt(i)+"\nSexo: "
                            +sex.elementAt(i)+"\nFecha de nacimiento: "+birth.elementAt(i)
                            +"\nLugar de nacimiento: "+birthplace.elementAt(i)+"\nhobbie:"+hobby.elementAt(i);
                    tDatos.setText(salida);
                }
            }
        });
    }

    public void setDate(View view) {

        @SuppressLint("ValidFragment")
        class DatePickerFragment extends DialogFragment
                implements DatePickerDialog.OnDateSetListener {

            @Override
            public Dialog onCreateDialog(Bundle savedInstanceState) {
                // Use the current date as the default date in the picker
                final Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);
                // Create a new instance of DatePickerDialog and return it
                return new DatePickerDialog(getActivity(), this, year, month, day);
            }

            public void onDateSet(DatePicker view, int year, int month, int day) {
                tFechaN.setText(String.valueOf(day + " / " +  (month+1) + " / " + year));
                date = day + " / " +  (month+1) + " / " + year;
                // Do something with the date chosen by the user
            }
        }
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    public void radioButtonClicked(View view) {
        int id = view.getId();
        if (id == R.id.rMasculino){
            sexo = "masculino";
        }else{
            sexo = "femenino";
        }
    }
}