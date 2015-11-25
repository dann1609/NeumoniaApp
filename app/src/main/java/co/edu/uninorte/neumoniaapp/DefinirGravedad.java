package co.edu.uninorte.neumoniaapp;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

public class DefinirGravedad {

    private Context mContext;
    Patient p1;
    Integer exam,examType;

    public DefinirGravedad(Context mContext, Patient p1) {
        this.mContext = mContext;
        this.p1 = p1;
    }

    public DefinirGravedad() {

    }

    public void calcular(Context mContext){

        this.mContext = mContext;
        this.p1 = p1;

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(mContext);
        Boolean UREA = sp.getBoolean(mContext.getResources().getStringArray(R.array.configuracion)[1], false);
        Patient p1 = new Patient(ComorbilidadesActivity.comValues,RiesgoSocialActivity.risSocValues,DatosGeneralesActivity.datGenValues,ParaclinicosAActivity.paraAValues);

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
        Intent intent = new Intent(mContext, RecomendacionesActivity.class);
        p1.setRisk(p1.BAJO);
        intent.putExtra("userTag",p1);
        Log.d("Desarrollo", "Se abrio la recomendacion");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(intent);
    }
    private void goToModerado(){
        Intent intent = new Intent(mContext, RiesgoGermenMedActivity.class);
        p1.setRisk(p1.MODERADO);
        intent.putExtra("userTag",p1);
        Log.d("Desarrollo", "Se abrio la recomendacion");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(intent);
    }
    private void goToGrave(){
        Intent intent = new Intent(mContext, CriteriosMayoresActivity.class);
        p1.setRisk(p1.GRAVE);
        intent.putExtra("userTag",p1);
        Log.d("Desarrollo", "Se abrio la recomendacion");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(intent);
    }


}
