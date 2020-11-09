package com.example.signatureapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.ContentResolver;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.kyanogen.signatureview.SignatureView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    SignatureView signatureView;
    Button save, clear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        /**
         * below 2 line for change signatureView line color
         */
        //
        //        int colorPrimary = ContextCompat.getColor(this, R.color.ColorPrimary);
        //        signatureView.setPenColor(colorPrimary);

        save.setOnClickListener(v -> {
            if (signatureView.isBitmapEmpty()) {
                Toast.makeText(this, "Signature is empty", Toast.LENGTH_LONG).show();
                return;
            }
            Bitmap bitmap = signatureView.getSignatureBitmap();

            String bitmap1 = MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, "yourTitle", "yourDescription");

            if (bitmap1 != null) {
                Toast.makeText(this, "Image Saved", Toast.LENGTH_LONG).show();
            }

        });
        clear.setOnClickListener(v -> {
            if (signatureView.isBitmapEmpty()) {
                Toast.makeText(this, "Already empty", Toast.LENGTH_LONG).show();
                return;
            }
            signatureView.clearCanvas();
        });
    }

    private void init() {
        signatureView = findViewById(R.id.signature_view);
        save = findViewById(R.id.save);
        clear = findViewById(R.id.clear);
    }
}