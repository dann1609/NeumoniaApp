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
            String recomendaciones=getResources().getString(R.string.recomendacion);

            puntaje.setText(getResources().getString(R.string.puntaje)+" "+p1.getExamTypeToString()+": "+p1.getExam().toString());
            if(p1.getRiesgoSocial()||!(p1.getTolOr())||p1.getSo2()<90||p1.getEfPle()){
                lugar.setText(getResources().getString(R.string.lugar)+" "+"Hospitalizacion");
            }
            else{
                lugar.setText(getResources().getString(R.string.lugar)+" "+"Ambulatorio");
                recomendaciones=recomendaciones+"\nAconsejar que visite a su medico si sienten que su condicion no mejora como esperaban." +
                        "\nSi no encuentran mejoría después de 72h de iniciado el tratamiento antibiotico, si la fiebre ha durado mas de " +
                        "1 semana, si la disnea empeora, si el pasciente deja de beber, o su nivel de conciencia disminuye." +
                        "\nEvolucion del tratamiento:" +
                        "\n1 semana:Cesó la fiebre." +
                        "\n4 semanas: el dolor en el pecho y producción de esputo han disminuido." +
                        "\n6 semanas: la tos y falta de aire han disminuido." +
                        "\n3 meses: la mayoria de los sintomas han cesado, pero puede persistir la fatiga." +
                        "\n6 meses: la mayoria esta totalmente recuperada";
            }

            germen.setText(getResources().getString(R.string.lugar)+"\nNo tiene factores de riesgo para germen específico.");

            if(p1.getAlPen()||p1.getInMac()){
                tratamiento.setText(getResources().getString(R.string.tratamiento)+" \nMoxifloxacina 400mg VO cada 24 horas por 5 dias o levofloxacina 750 mg VO cada 24h");
            }else if (p1.getComorbilidadesNoPMac()){
                tratamiento.setText(getResources().getString(R.string.tratamiento)+" \n1era linea: Amoxicilina/Clavulonato 1g VO, cada 12h con Claritromicina 500mg VO cada 12h por 5 dias\n2da linea: cefuroxima, 500 mg VO cada 12h, con claritromicina 500mg VO, cada 12h por cinco dias" );
            }else{tratamiento.setText(getResources().getString(R.string.tratamiento)+" \n1era linea: Amoxicilina 1 g pVO, cada 8h por 5 dias\n2da linea: claritromicina:500 mg VO, cada 12h por 5 dias");}



        }



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }


}
