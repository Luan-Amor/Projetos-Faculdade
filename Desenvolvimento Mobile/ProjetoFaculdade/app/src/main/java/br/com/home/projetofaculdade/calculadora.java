package br.com.home.projetofaculdade;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.NumberFormat;
import java.text.ParseException;

public class calculadora extends AppCompatActivity {

    private String operador = "";
    private String valorTexto = "";
    private String textoVisor = "";
    private double primeiroValor = 0;
    private double segundoValor = 0;
    private double resultado = 0;
    private TextView visor;
    private TextView visorAuxilio;
    private NumberFormat nf = NumberFormat.getNumberInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculadora);
        atualizarVisor();

        ActionBar bar = getSupportActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#05070D")));
    }

    private void atualizarVisor(){

    }
    /* Esse método atribui o valor do visor para a variável primeiroValor
    * e atribui o operador a variável operador */
    private void setOperador(String op) throws ParseException {
        if (this.operador.length() > 0) return;

        this.operador = op;

        if(primeiroValor == 0)
            primeiroValor = nf.parse(valorTexto).doubleValue();

        valorTexto = "";
        textoVisor += " " + op + " ";

        visorAuxilio = (TextView) findViewById(R.id.visorAuxilio);
        visorAuxilio.setText(textoVisor);

        visor = (TextView) findViewById(R.id.visor);
        visor.setText("0");
    }

    /**
     * Coloca os valores informados pelo o usuário no visor
     * @param caracter
     */
    private void setCaracter(char caracter){
        valorTexto += caracter;
        textoVisor = valorTexto;
        visor = (TextView) findViewById(R.id.visor);
        visor.setText(textoVisor);
    }

    //Números
    public void numberOne(View view){
        setCaracter('1');
    }
    public void numberTwo(View view){
        setCaracter('2');
    }
    public void numberThree(View view){
        setCaracter('3');
    }
    public void numberFour(View view){
        setCaracter('4');
    }
    public void numberFive(View view){
        setCaracter('5');
    }
    public void numberSix(View view){
        setCaracter('6');
    }
    public void numberSeven(View view){
        setCaracter('7');
    }
    public void numberEight(View view){
        setCaracter('8');
    }
    public void numberNine(View view){
        setCaracter('9');
    }
    public void numberZero(View view){
        setCaracter('0');
    }

    public void handleVirgula(View view){
        if(valorTexto.indexOf('.') < 0)
            setCaracter('.');

    }

    // Operadores
    public void handleSoma(View view) throws ParseException {
        setOperador("+");
    }
    public void handleSubtracao(View view) throws ParseException{
        setOperador("-");
    }
    public void handleMutiplicacao(View view) throws ParseException{
        setOperador("x");
    }
    public void handleDivisao(View view) throws ParseException{
        setOperador("/");
    }
    public void handlePorcentagem(View view)throws ParseException{
        setOperador("%");
    }

    public void handleResult(View view)throws ParseException{
        segundoValor = nf.parse(valorTexto).doubleValue();
        String res = calcularResultado();

        visorAuxilio = (TextView) findViewById(R.id.visorAuxilio);
        visorAuxilio.setText(textoVisor.concat(operador).concat(valorTexto).concat(" = ").concat(res));

        primeiroValor = nf.parse(res).doubleValue();
        operador = "";
        valorTexto = textoVisor = res;

        visor = (TextView) findViewById(R.id.visor);
        visor.setText(res);

    }

    public void clearVisor(View view){
        operador = "";
        valorTexto = "";
        textoVisor= "0";
        primeiroValor = 0;
        segundoValor = 0;
        resultado = 0;

        visorAuxilio = (TextView) findViewById(R.id.visorAuxilio);
        visorAuxilio.setText("");

        visor = (TextView) findViewById(R.id.visor);
        visor.setText("0");
    }

    public void delCatactere(View view){
        if(valorTexto.length() > 0){
            valorTexto = valorTexto.substring(0, valorTexto.length() -1);
            textoVisor = valorTexto;
            visor = (TextView) findViewById(R.id.visor);
            visor.setText(valorTexto);

        }
    }

    private String calcularResultado(){
        String res = "";

        if(operador.equals("+")){
            resultado = primeiroValor + segundoValor;
        }else if(operador.equals("-")){
            resultado = primeiroValor - segundoValor;
        }else if(operador.equals("x")){
            resultado = primeiroValor * segundoValor;
        }else if(operador.equals("/")){
            if(primeiroValor == 0){
                resultado = 0;
            }else{
                resultado = primeiroValor / segundoValor;
            }
        }else if(operador.equals("%")){
            resultado = primeiroValor * segundoValor / 100;
        }else{
            resultado = 0;
        }
        return res = nf.format(resultado);
    }


}
