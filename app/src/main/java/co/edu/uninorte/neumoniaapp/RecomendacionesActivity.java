package co.edu.uninorte.neumoniaapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class RecomendacionesActivity extends AppCompatActivity {

    Context context;
    Patient p1;
    TextView puntaje,lugar,germen,tratamiento,recFinales;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recomendaciones);
        context=getApplicationContext();
        Intent intent = getIntent();
        p1 = getIntent().getParcelableExtra("userTag");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        puntaje=(TextView) findViewById(R.id.puntaje);
        lugar=(TextView) findViewById(R.id.lugar);
        germen=(TextView) findViewById(R.id.germen);
        tratamiento=(TextView) findViewById(R.id.tratamiento);
        recFinales=(TextView) findViewById(R.id.recFinales);

        if(p1.getRisk()==p1.BAJO){

            puntaje.setText(getResources().getString(R.string.puntaje)+" "+p1.getExamTypeToString()+": "+p1.getExam().toString());
            if(p1.getRiesgoSocial()||!(p1.getTolOr())||p1.getSo2()<90||p1.getEfPle()){
                lugar.setText(getResources().getString(R.string.lugar)+" "+"Hospitalizacion");
            }
            else{
                lugar.setText(getResources().getString(R.string.lugar)+" "+"Ambulatorio");
            }

            germen.setText(getResources().getString(R.string.germen)+"\nNo tiene factores de riesgo para germen específico.");

            if(p1.getAlPen()||p1.getInMac()){
                tratamiento.setText(getResources().getString(R.string.tratamiento)+" \nMoxifloxacina 400mg VO cada 24 horas por 5 dias o levofloxacina 750 mg VO cada 24h");
            }else if (p1.getComorbilidadesNoPMac()){
                tratamiento.setText(getResources().getString(R.string.tratamiento)+" \n1era linea: Amoxicilina/Clavulonato 1g VO, cada 12h con Claritromicina 500mg VO cada 12h por 5 dias\n2da linea: cefuroxima, 500 mg VO cada 12h, con claritromicina 500mg VO, cada 12h por cinco dias" );
            }else{tratamiento.setText(getResources().getString(R.string.tratamiento)+" \n1era linea: Amoxicilina 1 g pVO, cada 8h por 5 dias\n2da linea: claritromicina:500 mg VO, cada 12h por 5 dias");}

        }

        if(p1.getRisk()==p1.MODERADO){

            puntaje.setText(getResources().getString(R.string.puntaje)+" "+p1.getExamTypeToString()+": "+p1.getExam().toString());
            String germentxt="";
            String tratamientotxt="";
            if(p1.getSPneumoniae()){
                germentxt=germentxt+"sPneumoniae";
                tratamientotxt=tratamientotxt+"\nCeftriaxona, 2g, IV cada 24h mas Claritromicina, 500mg IV cada 12h de 7-10 días";
            }
            if(p1.getGramMen()&&p1.getSPneumoniae()){germentxt=germentxt+", ";}
            if(!p1.getSPneumoniae()&&!p1.getGramMen()){
                germentxt=germentxt+" No tiene factores de riesgo para germen específico";
                tratamientotxt=tratamientotxt+"\n1era linea: Ampicilina 2g intravenosa cada h(o penicilina cristalina 2 millones de unidades, intravenosa cada 4h)" +
                        "+ Claritromicina, 500mg IV cada 12h" +
                        "\n2da linea: moxifloxacina,400mg IV cada 24h o levofloxacina, 500mg IV cada 24h.";
            }
            if(p1.getGramMen()){
                germentxt = germentxt + "Gramn-.";
                tratamientotxt=tratamientotxt+"\n1era linea: AMpicilina-sulbactam, 3g IV cada 6h mas Calitromicina, 500 mg IV cada 12h por 7-10 días."+
                        "\n2da linea: Cefuroxima, 750mg IV cada 8 horas mas claritromicina 500mg IV cada 12h por 7-10 días.";
            }else {germentxt=germentxt+".";}
            if(p1.getAlPen()||p1.getInMac()){
                tratamientotxt="\nDoxiciclina 100mg por VO cada 12h.";
            }
            lugar.setText(getResources().getString(R.string.lugar)+" "+"Hospitalizacion");
            germen.setText(getResources().getString(R.string.germen)+" "+germentxt);
            tratamiento.setText(getResources().getString(R.string.tratamiento)+tratamientotxt);

        }

        if(p1.getRisk()==p1.GRAVE){


            puntaje.setText(getResources().getString(R.string.puntaje)+" "+p1.getExamTypeToString()+": "+p1.getExam().toString());
            String germentxt="";
            String tratamientotxt="";
            if(p1.getCriteriosMayores()||p1.getCriteriosMenores()){
                lugar.setText(getResources().getString(R.string.lugar)+" "+"UCI");
            }else{lugar.setText(getResources().getString(R.string.lugar)+" "+"Hospitalizacion");}

            if(p1.getPAeruginosa()){
                germentxt=germentxt+"P. aeruginosa";
                tratamientotxt=tratamientotxt+"\n1era linea:piperacilina-tazobactam 4,5g IV cada 6h mas Claritromicina 500mg IV cada 12 h." +
                        "\n 2da linea: Cefepima 2g IV cada 8h mas Claritromicina 500mg IV cada 12h.";
            }
            if(p1.getSAureus()&&p1.getPAeruginosa()){germentxt=germentxt+", ";}
            if(!p1.getPAeruginosa()&&!p1.getSAureus()){
                germentxt=germentxt+" No tiene factores de riesgo para germen específico";
                tratamientotxt=tratamientotxt+"\nBlactamico(Cefotaxime, ceftriaxona Ampicilina-sulbactam 3g IV cada 6h) mas Claritromicina 500mg IV cada 12h mas Oseltamivir." +
                        "\n2da linea: Moxifloxacina 400mg IV cada 24h o Levofloacina 500mg IV cada 24h.";
            }
            if(p1.getSAureus()){
                germentxt = germentxt + "S. aureus";
                tratamientotxt=tratamientotxt+"\n1era linea: Betalactamico(Cefotaxime 500mg cada 12h o ceftriaxona 1 g IV cada 24h o Ampicilina-sulbactam 3 g IV cada 6h mas claritromicina 500mg IV cada 12h mas " +
                        "Vancomicina(dosis de carga: 25mg/kg IV y luego 15mg/kg 12h IV) o linezolid 600mg IV cada 12h)" +
                        "2da linea: Blactamico  (Cefotaxime( 500 mg IV cada 12H ) o ceftriaxona 1 g IV cada 24h   o Ampicilina-sulbactam, 3 g  IV cada 6h) + claritromicina, 500 mg IV cada 12h + TMP-SMX.";
            }else {germentxt=germentxt+".";}
            if(p1.getAlPen()||p1.getInMac()){
                tratamientotxt="\n1era linea: Quinolona ( levofloxacina 750 mg IV cada 24h  o moxifloxacina 400 mg IV cada 24h + Aztreonam 2 gm IV cada 6h" +
                        "\n2da linea: b.\tCefalosporina de 2da(  Cefuroxima, 750 mg IV, cada 8h) o 3era ( ceftriaxona 1gm IV cada 24h)+ Macrolido (claritromicina, 500 mg IV cada 1 h)";
            }
            germen.setText(getResources().getString(R.string.germen)+" "+germentxt);
            tratamiento.setText(getResources().getString(R.string.tratamiento)+tratamientotxt);
        }

        recFinales.setText(getRecomendacionesFinales());


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
    public String getRecomendacionesFinales(){
        String recomendaciones=getResources().getString(R.string.recomendacion);

        if(p1.getRisk()==p1.BAJO&&!p1.getRiesgoSocial()&&(p1.getTolOr())||p1.getSo2()<90&&!p1.getEfPle()) {
            recomendaciones = recomendaciones + "\nAconsejar que visite a su medico si sienten que su condicion no mejora como esperaban." +
                    "\nSi no encuentran mejoría después de 72h de iniciado el tratamiento antibiotico, si la fiebre ha durado mas de " +
                    "1 semana, si la disnea empeora, si el pasciente deja de beber, o su nivel de conciencia disminuye." +
                    "\nEvolucion del tratamiento:" +
                    "\n1 semana:Cesó la fiebre." +
                    "\n4 semanas: el dolor en el pecho y producción de esputo han disminuido." +
                    "\n6 semanas: la tos y falta de aire han disminuido." +
                    "\n3 meses: la mayoria de los sintomas han cesado, pero puede persistir la fatiga." +
                    "\n6 meses: la mayoria esta totalmente recuperada.";
        }

        if(p1.getVacunaInfluenza()){recomendaciones=recomendaciones+"\nSe recomenienda aplicar la Vacuna de la Influenza";}
        if(p1.getVacunaNeumococo()){recomendaciones=recomendaciones+"\nSe recomenienda aplicar la Vacuna del Neumococo";}
        if(p1.getFumador()){recomendaciones=recomendaciones+"\nSe recomienda abandonar el hábito de fumar";}
        recomendaciones=recomendaciones+"Se recomienda a TODOS los pacientes los hábitos de higiene como:" +
                "\nLavado de manos adecuado." +
                "\nUso de tapabocas del paciente como de personas que lo rodean." +
                "\nUso de trapos o pañuelos personales y su lavado rutinario.";

        return recomendaciones;
    }

}
