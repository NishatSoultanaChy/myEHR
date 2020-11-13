package com.nishatsultana.myehr.Patient;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.nishatsultana.myehr.Model.Reports;
import com.nishatsultana.myehr.Prevalent.Prevalent;
import com.nishatsultana.myehr.R;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import io.paperdb.Paper;

public class AddReports_P extends AppCompatActivity {

    Uri filepath;
    EditText testName, testDate, testAssignedBy;
    Button btn_selectPdf, btn_upload;

    StorageReference mystorageReference;
    DatabaseReference myDbReference;
    FirebaseDatabase rootNode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reports__p);

        Paper.init(this);


        testName = findViewById(R.id.id_test_name);
        testDate = findViewById(R.id.id_test_date);
        testAssignedBy = findViewById(R.id.id_assignedBy);

        btn_selectPdf = findViewById(R.id.btn_Select_PDF);
        btn_upload = findViewById(R.id.btn_upload);

        mystorageReference = FirebaseStorage.getInstance().getReference();
        rootNode = FirebaseDatabase.getInstance();
        myDbReference = rootNode.getReference("Reports");

        btn_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectPdfFile();
            }
        });

    }

    private void selectPdfFile() {

        Intent intent = new Intent();
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select PDF File"),1);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==1 && resultCode== RESULT_OK && data!=null && data.getData()!=null){
            Log.e("Add Report", "File selected!"+ data.getData());

            //filepath = data.getData();
            uploadPDFFile(data.getData());
        }
        else{
            Log.e("Add Report", "File not selected!");

        }
    }

    private void uploadPDFFile(Uri data) {
        final String phone = Prevalent.CurrentOnlineUser.getPhone();
        final String report = "Lab Reports";

        Log.e("Add Report", "Upload Started...");

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Uploading Report...");
        progressDialog.show();
        final StorageReference reference = mystorageReference.child("Reports/"+ System.currentTimeMillis()+".pdf");

        reference.putFile(data).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Reports obj = new Reports(testName.getText().toString(),testDate.getText().toString(),testAssignedBy.getText().toString(), uri.toString());
                        myDbReference.child(phone).child(myDbReference.push().getKey()).setValue(obj);

                        Toast.makeText(AddReports_P.this,"File Uploaded!",Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();

                        Intent intent = new Intent(AddReports_P.this, PatientHomeActivity.class);
                        startActivity(intent);
                    }
                });

                /*Task<Uri> uri = taskSnapshot.getStorage().getDownloadUrl();
                if (uri.isComplete()){
                    Uri url = uri.getResult();

                    HashMap<String , Object> reportDataMap = new HashMap<>();

                    reportDataMap.put("testName", testName.getText().toString());
                    reportDataMap.put("testDate", testDate.getText().toString());
                    reportDataMap.put("testAssignedBy",testAssignedBy.getText().toString());
                    reportDataMap.put("reportURL",url.toString());

                    String id = myDbReference.push().getKey();


                    myDbReference.child("Reports").child(phone).updateChildren(reportDataMap) //addChildren(reportDataMap)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    Toast.makeText(AddReports_P.this,"File Uploaded!",Toast.LENGTH_SHORT).show();
                                    progressDialog.dismiss();
                                    AlertDialog.Builder alertdialog =  new AlertDialog.Builder(getApplicationContext());
                                    alertdialog.setTitle("Message");
                                    alertdialog.setMessage("File Uploaded");

                                    alertdialog.setPositiveButton("Go to home", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            Intent intent = new Intent(AddReports_P.this, PatientHomeActivity.class);
                                            startActivity(intent);
                                        }
                                    });

                                    alertdialog.show();

                                    }
                            });*/


                    //Reports reports = new Reports(testName.getText().toString(),testDate.getText().toString(),testAssignedBy.getText().toString(),url.toString());

                    //myDbReference.child(phone).child(myDbReference.push().getKey()).setValue(reports);

                    //Toast.makeText(AddReports_P.this,"File Uploaded!",Toast.LENGTH_SHORT).show();
                    //progressDialog.dismiss();

            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {

                double progress = (100.0*snapshot.getBytesTransferred())/snapshot.getTotalByteCount();
                progressDialog.setMessage("Uploaded: "+ (int)progress+"%");
            }
        });
    }
}