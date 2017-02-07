package com.example.akav.qrcode;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;





public class MainActivity extends AppCompatActivity {
    private Button buttonid;
    private Button ipid;
    private TextView textid,tid,tempid;

    private EditText editid;
    public int a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





        buttonid=(Button)findViewById(R.id.buttonid);

        final Activity activity = this;




        buttonid.setOnClickListener(new View.OnClickListener() {
            @Override
             public void onClick(View view){


                IntentIntegrator integrator = new IntentIntegrator(activity);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                integrator.setPrompt("SCan");
                integrator.setCameraId(0);
                integrator.setBarcodeImageEnabled(false);
                integrator.initiateScan();


            }

        });
    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {


        EditText edit=(EditText)findViewById(R.id.editid);

        TextView finalid=(TextView)findViewById(R.id.finalid);
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if(result!=null){
            if(result.getContents()==null){
                Toast.makeText(this,"you cancelled scanning",Toast.LENGTH_LONG).show();
            }
            else{
                //Toast.makeText(this,result.getContents(),Toast.LENGTH_LONG).show();
                String fulldata=result.getContents().toString();

                String fdlower=fulldata.toLowerCase();
               // Toast.makeText(this,fdlower,Toast.LENGTH_LONG).show();

                //tempid.setText(edit.getText());
                String s=edit.getText().toString();
                String name=s.toLowerCase();

                if(fdlower.contains(name)&&(name.length()>7)&&(name.length()<30)){
                   // finalid.setText("Success");
                    Toast.makeText(this,"successfully verified your name",Toast.LENGTH_LONG).show();

                }
                else{
                    Toast.makeText(this,"Incorrect Credentials, U MAY BE TRACED!!!",Toast.LENGTH_LONG).show();
                }



                }

        }else{
            super.onActivityResult(requestCode,resultCode,data);
        }
    }


}
