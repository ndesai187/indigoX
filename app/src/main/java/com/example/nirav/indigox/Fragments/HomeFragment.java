package com.example.nirav.indigox.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.nirav.indigox.R;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class HomeFragment extends Fragment {

    private static final int REQUEST_CAMERA = 1;
    private ZXingScannerView scannerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.content_home_page, container, false);

        /*Find the +1 button
        Button addSamplerButton = (Button) view.findViewById(R.id.add_sampler);
        addSamplerButton.setClickable(true);
        addSamplerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = getActivity();
                CharSequence msg = "Button Pressed...";
                int duration = Toast.LENGTH_LONG;

                Toast toast = Toast.makeText(context, msg, duration);
                toast.show();
                //addSamplerScanner();
            }
        });*/


        return view;
    }

     //addSamplerScanner();


    /*
    implements ZXingScannerView.ResultHandler

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_home_page);
        //setContentView(R.layout.content_home_page);

        Button addSamplerButton = (Button) findViewById(R.id.add_sampler);
        addSamplerButton.setClickable(true);
        addSamplerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = getApplicationContext();
                CharSequence msg = "Button Pressed...";
                int duration = Toast.LENGTH_LONG;

                Toast toast = Toast.makeText(context, msg, duration);
                toast.show();
                //addSamplerScanner();
            }
        });

        Button viewSamplerButton = (Button) findViewById(R.id.view_sampler);
        addSamplerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewSamplerInfo();
            }
        });

    }


    private void addSamplerScanner(){
        scannerView = new ZXingScannerView(this);
        setContentView(scannerView);
        scannerView.setResultHandler(this);
        scannerView.startCamera();
    }

    private void viewSamplerInfo() {
    }

    @Override
    public void handleResult(Result result) {
        String scanResult = result.getText();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Scan Result");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                scannerView.resumeCameraPreview(HomeFragment.this);
            }
        });
        builder.setMessage(scanResult);
        AlertDialog alert = builder.create();
        alert.show();
    }
    */
}
