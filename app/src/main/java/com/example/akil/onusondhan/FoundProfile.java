package com.example.akil.onusondhan;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.content.MimeTypeFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.FileNotFoundException;
import java.io.IOException;

public class FoundProfile extends AppCompatActivity implements View.OnClickListener{

    private ImageView image;
    private EditText foundPersonName;
    private EditText weight;
    private EditText identifyingMark;
    private EditText description;
    private EditText height;
    private EditText skinColor;
    private EditText foundDate;
    private EditText foundPlace;
    private EditText contactNumber;
    private Spinner age;
    private Button confirm;
    private String imageLink;
    public String path;
    DatabaseReference databaseFound;
    StorageReference storageFound;
    private Uri filePath;
    private static final int PICK_IMAGE_REQUEST = 234;
    public static final String FB_STORAGE_PATH= "image/";
    public static final String FB_DATABASE_PATH= "image";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_found_profile);

        image = (ImageView) findViewById(R.id.imgview);
        foundPersonName = (EditText)findViewById(R.id.editName);
        weight = (EditText)findViewById(R.id.editWeight);
        identifyingMark = (EditText)findViewById(R.id.editMark);
        description = (EditText)findViewById(R.id.editDescrip);
        confirm = (Button) findViewById(R.id.confirmbtn);
        height = (EditText)findViewById(R.id.editheight);
        age = (Spinner)findViewById(R.id.age);
        skinColor = (EditText)findViewById(R.id.editSkinColo);
        foundDate = (EditText)findViewById(R.id.editFoundDat);
        foundPlace = (EditText)findViewById(R.id.editFoundPlac);
        contactNumber = (EditText)findViewById(R.id.contactNumb);
        int TYPE = Integer.parseInt(age.getSelectedItem().toString());

        databaseFound = FirebaseDatabase.getInstance().getReference("Found");
        storageFound = FirebaseStorage.getInstance().getReference("FoundImg");
        confirm.setOnClickListener(this);
        image.setOnClickListener(this);

    }

    private void showfileChooser(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select Image"),PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data !=null && data.getData() != null){
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                image.setImageBitmap(bitmap);
            }catch(FileNotFoundException e){
                    e.printStackTrace();
            } catch (IOException e) {

            }
        }
    }
    private String getImageExt(Uri uri){

        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    @Override
    public void onClick(View view) {
        if(view == confirm){
            if(filePath !=null) {
                final ProgressDialog progressDialog = new ProgressDialog(this);
                progressDialog.setTitle("Uploading...");
                progressDialog.show();
                path = FB_STORAGE_PATH + System.currentTimeMillis() + "." +getImageExt(filePath);

                StorageReference riversRef = storageFound.child(path);

                riversRef.putFile(filePath)
                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                progressDialog.dismiss();
                                Uri downloadUrl = taskSnapshot.getDownloadUrl();
                                imageLink = downloadUrl.toString();
                                Toast.makeText(getApplicationContext(), "Image Uploaded", Toast.LENGTH_SHORT).show();
                                registerFound();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception exception) {
                                progressDialog.dismiss();
                                Toast.makeText(getApplicationContext(), exception.getMessage(), Toast.LENGTH_SHORT).show();

                            }
                        })
                        .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred())/taskSnapshot.getTotalByteCount();
                            progressDialog.setMessage(((int) progress) + "% Uploaded");
                        }
                });
            }else{
                Toast.makeText(getApplicationContext(), "Please Select Image", Toast.LENGTH_SHORT).show();

            }


        }
        if(view == image){
            showfileChooser();

        }
    }

    private void registerFound() {
        String foundName = foundPersonName.getText().toString().trim();
        String wght = weight.getText().toString().trim();
        String mark = identifyingMark.getText().toString().trim();
        String desc = description.getText().toString();
        String hght = height.getText().toString();
        String skcol = skinColor.getText().toString();
        String fnddat = foundDate.getText().toString();
        String fndplc = foundPlace.getText().toString();
        String numb = contactNumber.getText().toString();
        int ag = Integer.parseInt(age.getSelectedItem().toString());
        String imgPath = imageLink;

        Found found = new Found(foundName,wght,mark,desc,hght,ag,imgPath,skcol,fnddat,fndplc,numb);

        databaseFound.child(foundName).setValue(found);
        Toast.makeText(getApplicationContext(), "Post Added", Toast.LENGTH_SHORT).show();
        finish();

    }
}
