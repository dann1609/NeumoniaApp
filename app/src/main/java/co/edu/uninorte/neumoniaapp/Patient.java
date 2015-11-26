package co.edu.uninorte.neumoniaapp;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Lucia on 18/11/2015.
 */
public class Patient implements Parcelable {
    private Boolean epoc=false,iC=false,dM=false,eRC=false,abAl=false,inmuno=false,neoplas=false,anPrev=false,alPen=false,inMac=false,inAut=false,alCog=false,inIngOr=false,abPsi=false,malSprt=false,tolOr=false,conf=false; //17
    private Integer frecRes=0,edad=0,tensArtSist=0,tensArtDiast=0,temp=0,urea=0,so2=0;  //7
    private Boolean efPle=false;  //1
    private Integer exam=0,examType=0,risk=0;  //3
    private Boolean betalac=false,expoMen=false,resdHog=false,disfag=false,respMec=false,sopVas=false,infMult=false,hipotens=false;  //8
    private Integer pafio2=0,leucocitos=0,plaquetas=0;  //3
    private Boolean terCor=false,terAnt7=false,malnutr=false,fumador=false,vihTard=false,eRCH=false,infInf=false,neuNecro=false,infPielConc=false,proStruPul=false,obsEnd=false; //11
    private String idPaciente="paciente1"; //1

    private final static int numprop=51;
    public final static Integer CURB65=1,CRB65=0,BAJO=0,MODERADO=1,GRAVE=2,HISTORIAL=3;

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

    public void modify(ArrayList a0, ArrayList a1,ArrayList a2){

        this.tolOr=(Boolean)a0.get(2);
        this.temp=(Integer)a0.get(3);
        this.frecRes=(Integer)a0.get(5);
        this.tensArtSist=(Integer)a0.get(6);
        this.tensArtDiast=(Integer)a0.get(7);
        this.so2=(Integer)a0.get(8);

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

    }

    public Boolean getSalida(){
        Integer result=0;
        if(this.frecRes>24){result++;}
        if(this.frecRes>24){result++;}
        if(this.tensArtSist<90){result++;}
        if(this.so2<90){result++;}
        if(!this.tolOr){result++;}
        if(conf){result++;}
        if(this.temp>37.8){result++;}
        if(getComorbilidades()){result++;}
        if(getRiesgoSocial()){result++;}


        if(result<2&&!(this.temp>37.8)){
            return true;
        }else{
            return false;
        }
    }
    public String getIdPaciente() {
        return this.idPaciente;
    }
    public void setIdPaciente(String id){
        this.idPaciente=id;
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
    public Boolean get2Comorbilidades(){
        int sum=((epoc)? 1 : 0)+((iC)? 1 : 0)+((dM)? 1 : 0)+((eRC)? 1 : 0)+((abAl)? 1 : 0)+((inmuno)? 1 : 0)+((neoplas)? 1 : 0)+((alPen)? 1 : 0)+((inMac)? 1 : 0);
        if (sum>1){
            return true;
        }
        else{return false;}
    }
    public Boolean getSPneumoniae(){
        if(edad>65||betalac||abAl||get2Comorbilidades()||expoMen||resdHog){return true;}
        else{return false;}
    }
    public Boolean getGramMen(){
        if(disfag||abAl){return true; }
        else{return false;}
    }
    public Boolean getCriteriosMayores(){
        if(respMec||sopVas){return true;}
        else{return false;}
    }
    public Boolean getCriteriosMenores(){
        int sum=((infMult)? 1 : 0)+((hipotens)? 1 : 0)+((pafio2<250)? 1 : 0)+((leucocitos<4000)? 1 : 0)+((plaquetas<100000)? 1 : 0)+((infMult)? 1 : 0)+((urea>42.8)? 1 : 0)+((frecRes>30)? 1 : 0)+((temp<36)? 1 : 0);
        if(sum>=3){return true;}
        else{return false;}
    }
    public Boolean getPAeruginosa(){
        if(proStruPul||epoc||terCor||terAnt7||malnutr||fumador||vihTard){return true;}
        else{return false;}
    }
    public Boolean getSAureus(){
        if(eRCH||abPsi||infInf||anPrev||neuNecro||infPielConc||proStruPul||obsEnd){return true;}
        else{return false;}
    }
    public Boolean getVacunaInfluenza(){
        if(edad>65||iC||dM||eRC){return true;}
        else{return false;}
    }
    public Boolean getVacunaNeumococo(){
        if(edad>65||iC||epoc||dM||alCog){return true;}
        else{return false;}
    }
    public Boolean getAlPen(){return this.alPen;}
    public Boolean getInMac(){return this.inMac;}

    public Boolean getFumador(){return this.fumador;}
    public  Boolean getConf(){return this.conf;}
    public Integer getUrea(){return this.urea;}
    public Integer getFrecRes(){return this.frecRes;}
    public Integer getTensArtSist(){return this.tensArtSist;}
    public Integer getTensArtDiast(){return this.tensArtDiast;}
    public Integer getEdad(){return this.edad;}
    public Integer getTemp(){return this.temp;}
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
    public void setRisk(Integer risk){
        if(0<=risk&&risk<=3){
            this.risk=risk;
        }
    }
    public void setGermenMed(ArrayList list){
        this.betalac= (Boolean) list.get(0);
        this.expoMen=(Boolean) list.get(1);
        this.resdHog=(Boolean) list.get(2);
        this.disfag=(Boolean) list.get(3);
    }
    public void setGermenGrav(ArrayList list){
        this.terCor= (Boolean) list.get(1);
        this.terAnt7= (Boolean) list.get(2);
        this.malnutr= (Boolean) list.get(3);
        this.fumador= (Boolean) list.get(4);
        this.vihTard= (Boolean) list.get(5);
        this.eRCH= (Boolean) list.get(6);
        this.infInf= (Boolean) list.get(7);
        this.neuNecro= (Boolean) list.get(8);
        this.infPielConc= (Boolean) list.get(9);
        this.proStruPul= (Boolean) list.get(10);
        this.obsEnd= (Boolean) list.get(11);

    }
    public void setCriteriosMayores(Boolean b1,Boolean b2){
        this.respMec=b1;
        this.sopVas=b2;
    }
    public void setCriteriosMenores(ArrayList list){
        this.infMult=(Boolean)list.get(0);
        this.hipotens=(Boolean)list.get(1);
        this.pafio2=(Integer)list.get(2);
        this.leucocitos=(Integer)list.get(3);
        this.plaquetas=(Integer)list.get(4);
    }

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
        data[28]=Boolean.toString(this.betalac);
        data[29]=Boolean.toString(this.expoMen);
        data[30]=Boolean.toString(this.resdHog);
        data[31]=Boolean.toString(this.disfag);
        data[32]=Boolean.toString(this.respMec);
        data[33]=Boolean.toString(this.sopVas);
        data[34]=Boolean.toString(this.infMult);
        data[35]=Boolean.toString(this.hipotens);
        data[36]=Integer.toString(this.pafio2);
        data[37]=Integer.toString(this.leucocitos);
        data[38]=Integer.toString(this.plaquetas);
        data[39]=Boolean.toString(this.terCor);
        data[40]=Boolean.toString(this.terAnt7);
        data[41]=Boolean.toString(this.malnutr);
        data[42]=Boolean.toString(this.fumador);
        data[43]=Boolean.toString(this.vihTard);
        data[44]=Boolean.toString(this.eRCH);
        data[45]=Boolean.toString(this.infInf);
        data[46]=Boolean.toString(this.neuNecro);
        data[47]=Boolean.toString(this.infPielConc);
        data[48]=Boolean.toString(this.proStruPul);
        data[49]=Boolean.toString(this.obsEnd);
        data[50]=this.idPaciente;

        return data;
    }
    public void fromStringArray(String[] data){
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
        this.betalac= Boolean.valueOf(data[28]);
        this.expoMen= Boolean.valueOf(data[29]);
        this.resdHog= Boolean.valueOf(data[30]);
        this.disfag= Boolean.valueOf(data[31]);
        this.respMec= Boolean.valueOf(data[32]);
        this.sopVas= Boolean.valueOf(data[33]);
        this.infMult= Boolean.valueOf(data[34]);
        this.hipotens= Boolean.valueOf(data[35]);
        this.pafio2=Integer.parseInt(data[36]);
        this.leucocitos=Integer.parseInt(data[37]);
        this.plaquetas=Integer.parseInt(data[38]);
        this.terCor= Boolean.valueOf(data[39]);
        this.terAnt7= Boolean.valueOf(data[40]);
        this.malnutr= Boolean.valueOf(data[41]);
        this.fumador= Boolean.valueOf(data[42]);
        this.vihTard= Boolean.valueOf(data[43]);
        this.eRCH= Boolean.valueOf(data[44]);
        this.infInf= Boolean.valueOf(data[45]);
        this.neuNecro= Boolean.valueOf(data[46]);
        this.infPielConc= Boolean.valueOf(data[47]);
        this.proStruPul= Boolean.valueOf(data[48]);
        this.obsEnd= Boolean.valueOf(data[49]);
        this.idPaciente=data[50];
    }
    public Patient(Parcel in){
        String[] data= new String[numprop];

        in.readStringArray(data);
        fromStringArray(data);

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
