package com.example.helen;

import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

public class PdfOpener extends AppCompatActivity {
    PDFView myPDFViewer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_opener);
        myPDFViewer=(PDFView)findViewById(R.id.pdfviewer);
        String getitem=getIntent().getStringExtra("pdffilename");
        if(getitem.equals("Advanced Computer Architecture"))
        {
            myPDFViewer.fromAsset("Ada.pdf").load();
        }
        if(getitem.equals("Big Data Analytics"))
        {
            myPDFViewer.fromAsset("BDA.pdf").load();
        }
        if(getitem.equals("Mobile Computing"))
        {
            myPDFViewer.fromAsset("MCC.pdf").load();
        }
        if(getitem.equals("Advanced DBMS"))
        {
            myPDFViewer.fromAsset("adbms.pdf").load();
        }


    }
}