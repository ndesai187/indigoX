package com.example.nirav.indigox;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
import com.google.zxing.Result;
import com.nirav.indigox.database.BarCodeScan;
import com.nirav.indigox.database.IndigoDBHandler;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class StartScannerActivity extends AppCompatActivity
implements ZXingScannerView.ResultHandler{

    private static final int REQUEST_CAMERA = 0;
    private ZXingScannerView scannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_home_page);
        startSamplerScanner();
    }

    @Override
    public void handleResult(final Result result) {
        String scanResult = result.getText();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Scan Result");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                addScanCodetoDB(result.getText());
                scannerView.resumeCameraPreview(StartScannerActivity.this);
            }
        });
        builder.setMessage(scanResult);
        AlertDialog alert = builder.create();
        alert.show();
    }

    private void addScanCodetoDB(String scanText) {
        IndigoDBHandler dbHandler = new IndigoDBHandler(this,null,null,1);

        BarCodeScan codeScan = new BarCodeScan(scanText.toString());
        dbHandler.addSamplerCode(codeScan);
    }


    private void startSamplerScanner() {
        scannerView = new ZXingScannerView(this);
        setContentView(scannerView);
        scannerView.setResultHandler(this);
        scannerView.startCamera();
    }

    @Override
    public void onResume (){
        super.onResume();
        if(scannerView == null){
            scannerView = new ZXingScannerView(this);
            setContentView(scannerView);
        }
        scannerView.setResultHandler(this);
        scannerView.startCamera();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        scannerView.stopCamera();
        IndigoDBHandler dbHandler = new IndigoDBHandler(this,null,null,1);
        Context context = getApplicationContext();
        CharSequence msg = "fetching from DB : " + dbHandler.getAllCodes().toString();
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(context, msg, duration);
        toast.show();
    }

    private void viewSamplerInfo() {
    }

}
