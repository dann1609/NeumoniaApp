package co.edu.uninorte.neumoniaapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class ModificarDatosActivity extends AppCompatActivity {

    static Context context;
    static boolean active = false;
    static Patient p1;
    TextView mtxt1,mtxt2,units1,units2;
    EditText num1,num2,num4,num5,num6,num7_1,num7_2,num8;
    ArrayList values= new ArrayList<String>();
    public static ArrayList editValues=new ArrayList<Boolean>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_datos);
        context=getApplicationContext();
        active = true;
        Intent intent = getIntent();
        p1 = getIntent().getParcelableExtra("userTag");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        mtxt1=(TextView)findViewById(R.id.mtxt1);
        mtxt2=(TextView)findViewById(R.id.mtxt2);
        units1=(TextView)findViewById(R.id.units1);
        units2=(TextView)findViewById(R.id.units2);
        num1=(EditText) findViewById(R.id.num1);
        num2=(EditText) findViewById(R.id.num2);
        num4=(EditText) findViewById(R.id.num4);
        num5=(EditText) findViewById(R.id.num5);
        num6=(EditText) findViewById(R.id.num6);
        num7_1=(EditText) findViewById(R.id.num7_1);
        num7_2=(EditText) findViewById(R.id.num7_2);
        num8=(EditText) findViewById(R.id.num8);

        num4.setText(Integer.toString(p1.getTemp()));
        num5.setText("80");
        num6.setText(Integer.toString(p1.getFrecRes()));
        num7_1.setText(Integer.toString(p1.getTensArtSist()));
        num7_2.setText(Integer.toString(p1.getTensArtDiast()));
        num8.setText(Integer.toString(p1.getSo2()));

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        Boolean Procal = sp.getBoolean(getResources().getStringArray(R.array.configuracion)[2], false);
        Boolean PRC = sp.getBoolean(getResources().getStringArray(R.array.configuracion)[3], false);

        if(!PRC){
            mtxt1.setTextColor(Color.LTGRAY);
            num1.setEnabled(false);
            num1.setTextColor(Color.LTGRAY);
            num1.setText("0");
            units1.setTextColor(Color.LTGRAY);
        }
        if(!Procal){
            mtxt2.setTextColor(Color.LTGRAY);
            num2.setEnabled(false);
            num2.setTextColor(Color.LTGRAY);
            num2.setText("0");
            units2.setTextColor(Color.LTGRAY);
        }




        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String in1=((EditText) findViewById(R.id.num1)).getText().toString();
                String in2=((EditText) findViewById(R.id.num2)).getText().toString();
                String in4=((EditText) findViewById(R.id.num4)).getText().toString();
                String in5=((EditText) findViewById(R.id.num5)).getText().toString();
                String in6=((EditText) findViewById(R.id.num6)).getText().toString();
                String in71=((EditText) findViewById(R.id.num7_1)).getText().toString();
                String in72=((EditText) findViewById(R.id.num7_2)).getText().toString();
                String in8=((EditText) findViewById(R.id.num8)).getText().toString();
                if(in1.equals("")||in2.equals("")||in4.equals("")||in5.equals("")||in6.equals("")||in71.equals("")||in72.equals("")||in8.equals("")){
                    Snackbar.make(view, "Los campos no estan completos", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }else {
                    editValues = new ArrayList();
                    editValues.add(Integer.parseInt(in1));
                    editValues.add(Integer.parseInt(in2));
                    editValues.add(((CheckBox) findViewById(R.id.check3)).isChecked());
                    editValues.add(Integer.parseInt(in4));
                    editValues.add(Integer.parseInt(in5));
                    editValues.add(Integer.parseInt(in6));
                    editValues.add(Integer.parseInt(in71));
                    editValues.add(Integer.parseInt(in72));
                    editValues.add(Integer.parseInt(in8));
                    Intent intent = new Intent(context, ComorbilidadesActivity.class);
                    Log.d("Desarrollo", "Se abrio comorbilidades");
                    startActivity(intent);
                    overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
                }
            }
        });
    }

    public static void editar(){
        p1.modify(editValues,ComorbilidadesActivity.comValues,RiesgoSocialActivity.risSocValues);
        if(p1.getSalida()){
            p1.setRisk(3);
        }
        MySQLiteHelper db = new MySQLiteHelper(context);
        db.addPatient(p1);
        db.updatePatient(p1);
    }
}
