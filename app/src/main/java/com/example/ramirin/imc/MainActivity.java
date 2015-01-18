package com.example.ramirin.imc;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;








public class MainActivity extends ActionBarActivity {
   // private static final String TAG = "MyActivity";

    private void hideKeyboard() {
        // Check if no view has focus:
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager inputManager = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }



    public double IMC( double peso, double altura)
     {
         double IMC= peso/(altura*altura);

         return Math.round(IMC);
     }

    public String tipo_imc(double imc)
    {
       if(imc<=18)
         {
          return "Peso bajo. \n Necesario valorar signos de desnutrición";

         }
       else if(imc>=18 && imc<=24.9)
        {
        return " Normal";
        }
        else if(imc>=25 && imc <=26.9)
        {
            return " Sobrepeso";
        }

        else if(imc>=27 && imc <=29.9)
        {
            return " Obesidad grado I. \n Riesgo relativo alto para desarrollar enfermedades cardiovasculares";
        }
        else if(imc>=30 && imc <=39.9)
        {
            return " Obesidad grado II. \n Riesgo relativo muy alto para el desarrollo de enfermedades cardiovasculares";
        }
        else if(imc>=40)
        {
            return "Obesidad grado III Extrema o Mórbida. \n Riesgo relativo extremadamente alto para el desarrollo de enfermedades cardiovasculares";
        }

         return"";

    }
    public void limpiar_resultado(){
        TextView txt_resultado=(TextView)findViewById(R.id.textView_resultado);
        txt_resultado.setText("");
      }

    public void calcular(View view)
    {


        try {

            EditText txt_peso = (EditText)findViewById(R.id.editText_peso);
            EditText txt_estatura=(EditText)findViewById(R.id.editText_estatura);
            double peso = Double.parseDouble(txt_peso.getText().toString());
            double estatura = Double.parseDouble(txt_estatura.getText().toString());

            if(peso<=0 || estatura<=0){


                Exception ex = new Exception();
                throw ex;
            }

            TextView txt_resultado=(TextView)findViewById(R.id.textView_resultado);
            Double indice = IMC(peso,estatura);
            String resultado =Double.toString(indice);
            String tipoimc = tipo_imc(indice);

            limpiar_resultado();

             //REDONDEAR RESULTADO A 2 DECIMALES
            //DecimalFormat df=new DecimalFormat("0.00");
           // String formate = df.format(resultado);

            txt_resultado.setText("Tu IMC es: "+resultado+"\n"+tipoimc);
            //Toast.makeText( this,"Tu IMC es: " );
             hideKeyboard();//hide keyboard

        }catch(Exception e)
        {
            Context context = getApplicationContext();
            CharSequence text = "Debe ingresar su peso y su altura para poder calcular su IMC";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

            limpiar_resultado();
            //hideKeyboard();//hide keyboard
        }



    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
