package co.edu.uninorte.neumoniaapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

public class DefinirGravedadActivity extends AppCompatActivity {

    Context context;
    Patient p1;
    Integer exam,examType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_definir_gravedad);
        context=getApplicationContext();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        Boolean UREA = sp.getBoolean(getResources().getStringArray(R.array.configuracion)[1], false);
        p1=new Patient(ComorbilidadesActivity.comValues,RiesgoSocialActivity.risSocValues,DatosGeneralesActivity.datGenValues,ParaclinicosAActivity.paraAValues);

        if(UREA){
          exam=CURB65();
            examType=p1.CURB65;
            p1.setExam(exam);
            p1.setExamType(examType);
            if(exam<=1){goToBajo();}
            if(exam==2){goToModerado();}
            if(exam>=3){goToGrave();}
        }else{
            exam=CRB65();
            examType=p1.CRB65;
            p1.setExam(exam);
            p1.setExamType(examType);
            if(exam<=0){goToBajo();}
            else{goToModerado();}
        }

    }

    private Integer CURB65(){
        Integer result=0;
        if(p1.getConf()){result++;}
        if(p1.getUrea()>44){result++;}
        if(p1.getFrecRes()>=30){result++;}
        if(p1.getTensArtSist()<90){result++;}
        if(p1.getTensArtDiast()<=60){result++;}
        if(p1.getEdad()>=65){result++;}
        return result;
    }

    private Integer CRB65(){
        Integer result=0;
        if(p1.getConf()){result++;}
        if(p1.getFrecRes()>=30){result++;}
        if(p1.getTensArtSist()<90){result++;}
        if(p1.getTensArtDiast()<=60){result++;}
        if(p1.getEdad()>=65){result++;}
        return result;
    }

    private void goToBajo(){
        Intent intent = new Intent(context, RecomendacionesActivity.class);
        intent.putExtra("userTag",p1);
        Log.d("Desarrollo", "Se abrio la recomendacion");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
    private void goToModerado(){
        Intent intent = new Intent(context, RiesgoGermenMedActivity.class);
        intent.putExtra("userTag",p1);
        Log.d("Desarrollo", "Se abrio la recomendacion");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
    private void goToGrave(){}

}
