package com.dicoding.plantbot.Fragment;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.dicoding.plantbot.FeedActivity;
import com.dicoding.plantbot.Model.AddPhotosModel;
import com.dicoding.plantbot.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import static android.app.Activity.RESULT_OK;

public class AddPhotosFragment extends Fragment implements View.OnClickListener {

    private static final int PICK_IMAGE_REQUEST = 1;

    private Button chooseImage, uploadImage, showUploads;
    private EditText editName;
    private ImageView showImage;
    private ProgressBar progressBar;

    private Uri imageUri;

    private StorageReference storageReference;
    private DatabaseReference databaseReference;

    private StorageTask uploadTask;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_add_photos, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        chooseImage = view.findViewById(R.id.button_add_file);
        chooseImage.setOnClickListener(this);
        uploadImage = view.findViewById(R.id.button_upload_file);
        uploadImage.setOnClickListener(this);
        showUploads = view.findViewById(R.id.button_show_upload_file);
        showUploads.setOnClickListener(this);
        editName = view.findViewById(R.id.edit_name_file);
        showImage = view.findViewById(R.id.image_upload);
        progressBar = view.findViewById(R.id.progress_bar_upload);

        storageReference = FirebaseStorage.getInstance().getReference("uploads");
        databaseReference = FirebaseDatabase.getInstance().getReference("uploads");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button_add_file:
                openFileChooser();
                break;
            case R.id.button_upload_file:
                if (uploadTask != null && uploadTask.isInProgress()){
                    Toast.makeText(AddPhotosFragment.this.getActivity(), "Proses mengupload", Toast.LENGTH_LONG).show();
                }else {
                    uploadFile(imageUri);
                    break;
                }
            case R.id.button_show_upload_file:
                openFeedFragment();
                break;
        }
    }

    private void openFileChooser(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null){
            imageUri = data.getData();
            Picasso.get().load(imageUri).into(showImage);
        }
    }

    private String getFileExtension(Uri uri){
        ContentResolver cR = getContext().getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    private void uploadFile(Uri uri){
        if(uri != null){
            StorageReference fileReference = storageReference.child(System.currentTimeMillis()
            + "." + getFileExtension(uri));

            fileReference.putFile(uri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    progressBar.setProgress(0);
                                }
                            }, 5000);
                            Toast.makeText(AddPhotosFragment.this.getActivity(), "Upload foto berhasil", Toast.LENGTH_SHORT).show();
                            AddPhotosModel addPhotosModel = new AddPhotosModel(editName.getText().toString().trim(),
                                    taskSnapshot.getMetadata().getReference().getDownloadUrl().toString());
                            String uploadId = databaseReference.push().getKey();
                            databaseReference.child(uploadId).setValue(addPhotosModel);

                            Task<Uri> downloadUri = taskSnapshot.getStorage().getDownloadUrl();
                            if(downloadUri.isSuccessful()) {
                                String generatedFilePath = downloadUri.getResult().toString();
                                System.out.println("## Stored path is " + generatedFilePath);
                            }
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(AddPhotosFragment.this.getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                            progressBar.setProgress((int)progress);
                        }
                    });

        } else {
            Toast.makeText(AddPhotosFragment.this.getActivity(), "Tidak ada file yang dipilih", Toast.LENGTH_SHORT).show();
        }
    }

    private void openFeedFragment(){
        Intent intent = new Intent(AddPhotosFragment.this.getActivity(), FeedActivity.class);
        startActivity(intent);
    }

}