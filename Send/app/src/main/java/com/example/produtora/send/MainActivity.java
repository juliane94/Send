package com.example.produtora.send;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;


public class MainActivity extends Activity {
    EditText teacher;
    EditText body;
    EditText date;
    EditText students;
    String dateDel;
    String studentsDel;
    String bodyContent;
    String teacherDel;
    Button sendEmail;
    ImageButton photo;





    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE  = 100;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    protected void onResume() {
        super.onResume();
        body = (EditText) findViewById(R.id.body);
        sendEmail = (Button) findViewById(R.id.sendEmail);
        sendEmail.setOnClickListener(send);
        date = (EditText) findViewById(R.id.date);
        students = (EditText) findViewById(R.id.students);
        teacher = (EditText) findViewById(R.id.teacher);
        photo = (ImageButton) findViewById(R.id.photo);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                // Image captured and saved to fileUri specified in the Intent
                Toast.makeText(this, "Image saved to:\n" +
                        data.getData(), Toast.LENGTH_LONG).show();
            } else if (resultCode == RESULT_CANCELED) {
                // User cancelled the image capture
            } else {
                // Image capture failed, advise user
            }
        }

    }

    public void takePhoto(View v) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
    }

    private View.OnClickListener send = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            variablesIntoString();

            String message = bodyContent
                    + "\nData de Entrega" + dateDel
                    + "\nTurmas" + studentsDel
                    + "\nNome do professor" + teacherDel;

            Intent emailIntent = new Intent(Intent.ACTION_SEND);
            emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"tarefaspositivo@badbear.com.br"});
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Homework");
            emailIntent.setType("plain/text");
            emailIntent.putExtra(Intent.EXTRA_TEXT, message);
            startActivity(emailIntent);

        }
    };

    private void variablesIntoString() {

        bodyContent = body.getText().toString();
        dateDel = date.getText().toString();
        studentsDel = students.getText().toString();
        teacherDel = teacher.getText().toString();

    }



    protected void onPause() {
        super.onPause();
        finish();
    }


}