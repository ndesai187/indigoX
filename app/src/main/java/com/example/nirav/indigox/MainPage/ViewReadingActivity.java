package com.example.nirav.indigox.MainPage;

import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import com.example.nirav.indigox.R;
import com.nirav.indigox.database.IndigoDBHandler;
import com.nirav.indigox.database.IndigoDBManager;

public class ViewReadingActivity extends AppCompatActivity {

    private IndigoDBManager dbManager;
    private ListView listView;
    private SimpleCursorAdapter cursorAdapter;
    final String[] from = new String[] { IndigoDBHandler.COLUMN_ID,
            IndigoDBHandler.COLUMN_BARCODE_TEXT };

    final int[] to = new int[] { R.id.id, R.id.code };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_reading);

        dbManager = new IndigoDBManager(this);
        dbManager.open();
        Cursor cursor = dbManager.geAllScanCodes();

        listView = (ListView) findViewById(R.id.list_view_sampler);
        listView.setEmptyView(findViewById(R.id.empty));

        cursorAdapter = new SimpleCursorAdapter(this, R.layout.content_view_reading
                ,cursor,from,to, 0);
        cursorAdapter.notifyDataSetChanged();

        listView.setAdapter(cursorAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView idTextView = (TextView) view.findViewById(R.id.id);
                TextView codeTextView = (TextView) view.findViewById(R.id.code);

                //String codeId = idTextView.getText().toString();
                //String codeText = codeTextView.getText().toString();
            }
        });

        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
