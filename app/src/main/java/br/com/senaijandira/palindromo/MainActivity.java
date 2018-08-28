package br.com.senaijandira.palindromo;

import android.app.Activity;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends Activity {

    TextView txtPalavra, txtResultado;
    Button btnVerifique;

    /*PARA FALAR O TEXTO*/
    TextToSpeech mulherGoogle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        txtPalavra = findViewById(R.id.txtPalavra);
        txtResultado = findViewById(R.id.txtResultado);
        btnVerifique = findViewById(R.id.btnVerifique);

        /*INICALIZAÇÃO DO OBJETO TextToSpeech*/
        mulherGoogle = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR){
                    mulherGoogle.setLanguage(new Locale("pt","br"));
                }
            }
        });
    }

    public void vericarPalindromo(View v){
        String palavra = txtPalavra.getText().toString();


        /*TRATAMENTO DE CAIXA VAZIA*/
        if (txtPalavra.getText().toString().isEmpty()){
            txtPalavra.setError("Digite uma palavra");
        }

        /*INVERTER PALAVRA*/
        StringBuffer invertidor = new StringBuffer(palavra);
        invertidor.reverse();

        /*RESGATANDO A PALAVAR NORMAL*/
        String palavraResult = invertidor.toString();

        /*LÓGICA PALÍNDROMO*/
        if(palavra.equals(palavraResult)){
            txtResultado.setText("A palavra é um palíndromo");

            mulherGoogle.speak("É Palíndromo",TextToSpeech.QUEUE_FLUSH,null);
        }
        else{
            txtResultado.setText("A palavra não é um palíndromo");

            mulherGoogle.speak("Não é Palíndromo",TextToSpeech.QUEUE_FLUSH,null);
        }

    }


}
