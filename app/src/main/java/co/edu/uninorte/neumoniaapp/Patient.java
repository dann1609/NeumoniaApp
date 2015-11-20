package co.edu.uninorte.neumoniaapp;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Lucia on 18/11/2015.
 */
public class Patient implements Parcelable {
    private Boolean epoc,iC,dM,eRC,abAl,inmuno,neoplas,anPrev,alPen,inMac,inAut,alCog,inIngOr,abPsi,malSprt,tolOr,conf; //17
    private Integer frecRes,edad,tensArtSist,tensArtDiast,temp,urea,so2;  //7
    private Boolean efPle;
    private Integer exam=0,examType=0,risk=0;
    private final static int numprop=28;
    public final static Integer CURB65=1,CRB65=0,BAJO=0,MEDIO=1,ALTO=2;

    public Patient(){}

    public Patient(ArrayList a1,ArrayList a2,ArrayList a3,ArrayList a4){
        this.epoc=(Boolean)a1.get(1);
        this.iC=(Boolean)a1.get(2);
        this.dM=(Boolean)a1.get(3);
        this.eRC=(Boolean)a1.get(4);
        this.abAl=(Boolean)a1.get(5);
        this.inmuno=(Boolean)a1.get(6);
        this.neoplas=(Boolean)a1.get(7);
        this.anPrev=(Boolean)a1.get(8);
        this.alPen=(Boolean)a1.get(9);
        this.inMac=(Boolean)a1.get(10);
        this.inAut=(Boolean)a2.get(1);
        this.alCog=(Boolean)a2.get(2);
        this.inIngOr=(Boolean)a2.get(3);
        this.abPsi=(Boolean)a2.get(4);
        this.malSprt=(Boolean)a2.get(5);
        this.tolOr=(Boolean)a3.get(0);
        this.conf=(Boolean)a3.get(1);
        this.frecRes= (Integer) a3.get(2);
        this.edad=(int) a3.get(3);
        this.tensArtSist=(int)a3.get(4);
        this.tensArtDiast=(int)a3.get(5);
        this.temp=(int)a3.get(6);
        this.urea=(Integer)a4.get(0);
        this.so2=(Integer)a4.get(1);
        this.efPle=(Boolean)a4.get(2);

    }



    public Boolean getRiesgoSocial(){
        if(inAut||alCog||inIngOr||abPsi||malSprt){return true;}
        else {return false;}
    }
    public Boolean getTolOr(){return this.tolOr;}
    public Integer getSo2(){return this.so2;}
    public Boolean getEfPle(){return this.efPle;}

    public Boolean getComorbilidadesNoPMac(){
        if(epoc||iC||dM||eRC||abAl||inmuno||neoplas){return true;}
        else{return false;}
    }
    public Boolean getComorbilidades(){
        if(getComorbilidadesNoPMac()||alPen||inMac)
        {
            return true;
        }
        else{return false;}
    }
    public Boolean getAlPen(){return this.alPen;}
    public Boolean getInMac(){return this.inMac;}

    public  Boolean getConf(){return this.conf;}
    public Integer getUrea(){return this.urea;}
    public Integer getFrecRes(){return this.frecRes;}
    public Integer getTensArtSist(){return this.tensArtSist;}
    public Integer getTensArtDiast(){return this.tensArtDiast;}
    public Integer getEdad(){return this.edad;}
    public void setExam(Integer n){this.exam=n;}
    public void setExamType(Integer n){this.examType=n;}
    public Integer getExam(){return this.exam;}
    public String getExamTypeToString(){
        String txt="";
        if(this.examType==CURB65){txt="CURB65";}
        else {txt="CRB65";}
        return txt;
    }
    public Integer getRisk(){return this.risk;}
    public String[] toStringArray(){
        String[] data= new String[numprop];
        data[0]=Boolean.toString(this.epoc);
        data[1]=Boolean.toString(this.iC);
        data[2]=Boolean.toString(this.dM);
        data[3]=Boolean.toString(this.eRC);
        data[4]=Boolean.toString(this.abAl);
        data[5]=Boolean.toString(this.inmuno);
        data[6]=Boolean.toString(this.neoplas);
        data[7]=Boolean.toString(this.anPrev);
        data[8]=Boolean.toString(this.alPen);
        data[9]=Boolean.toString(this.inMac);
        data[10]=Boolean.toString(this.inAut);
        data[11]=Boolean.toString(this.alCog);
        data[12]=Boolean.toString(this.inIngOr);
        data[13]=Boolean.toString(this.abPsi);
        data[14]=Boolean.toString(this.malSprt);
        data[15]=Boolean.toString(this.tolOr);
        data[16]=Boolean.toString(this.conf);
        data[17]=Integer.toString(this.frecRes);
        data[18]=Integer.toString(this.edad);
        data[19]=Integer.toString(this.tensArtSist);
        data[20]=Integer.toString(this.tensArtDiast);
        data[21]=Integer.toString(this.temp);
        data[22]=Integer.toString(this.urea);
        data[23]=Integer.toString(this.so2);
        data[24]=Boolean.toString(this.efPle);
        data[25]=Integer.toString(this.exam);
        data[26]=Integer.toString(this.examType);
        data[27]=Integer.toString(this.risk);

        return data;
    }
    public Patient(Parcel in){
        String[] data= new String[numprop];

        in.readStringArray(data);
        this.epoc= Boolean.valueOf(data[0]);
        this.iC= Boolean.valueOf(data[1]);
        this.dM= Boolean.valueOf(data[2]);
        this.eRC= Boolean.valueOf(data[3]);
        this.abAl= Boolean.valueOf(data[4]);
        this.inmuno= Boolean.valueOf(data[5]);
        this.neoplas= Boolean.valueOf(data[6]);
        this.anPrev=Boolean.valueOf(data[7]);
        this.alPen= Boolean.valueOf(data[8]);
        this.inMac= Boolean.valueOf(data[9]);
        this.inAut= Boolean.valueOf(data[10]);
        this.alCog= Boolean.valueOf(data[11]);
        this.inIngOr= Boolean.valueOf(data[12]);
        this.abPsi=Boolean.valueOf(data[13]);
        this.malSprt=Boolean.valueOf(data[14]);
        this.tolOr= Boolean.valueOf(data[15]);
        this.conf= Boolean.valueOf(data[16]);
        this.frecRes= Integer.parseInt(data[17]);
        this.edad=Integer.parseInt(data[18]);
        this.tensArtSist= Integer.parseInt(data[19]);
        this.tensArtDiast=Integer.parseInt(data[20]);
        this.temp= Integer.parseInt(data[21]);
        this.urea= Integer.parseInt(data[22]);
        this.so2= Integer.parseInt(data[23]);
        this.efPle= Boolean.valueOf(data[24]);
        this.exam=Integer.parseInt(data[25]);
        this.examType=Integer.parseInt(data[26]);
        this.risk=Integer.parseInt(data[27]);
    }
    @Override
    public int describeContents() {
// TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
// TODO Auto-generated method stub

        dest.writeStringArray(toStringArray());
    }

    public static final Parcelable.Creator<Patient> CREATOR= new Parcelable.Creator<Patient>() {

        @Override
        public Patient createFromParcel(Parcel source) {
// TODO Auto-generated method stub
            return new Patient(source);  //using parcelable constructor
        }

        @Override
        public Patient[] newArray(int size) {
// TODO Auto-generated method stub
            return new Patient[size];
        }
    };
}
