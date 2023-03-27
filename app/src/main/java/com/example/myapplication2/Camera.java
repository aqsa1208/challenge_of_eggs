package com.example.myapplication2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class Camera extends AppCompatActivity {

    private static final int MY_CAMERA_REQUEST_CODE =7171;//Any number you want
    @BindView(R.id.btn_camera)
    ImageView btn_camera;
    @BindView(R.id.btn_camera_ok)
    ImageView btn_camera_ok;
    @BindView(R.id.img_preview )
    ImageView img_preview;

    byte[] bytes;
    int challenge_num;

    private Uri imageUri;

    @OnClick(R.id.btn_camera)
    void onCaptureImageClick(){
        Dexter.withContext(this)
                .withPermissions(Arrays.asList(
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.CAMERA
                ))
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport multiplePermissionsReport) {
                        if(multiplePermissionsReport.areAllPermissionsGranted())
                        {
                            ContentValues contentValues = new ContentValues();
                            contentValues.put(MediaStore.Images.Media.TITLE,"NewPicture");
                            contentValues.put(MediaStore.Images.Media.DESCRIPTION,"From your camera");
                            imageUri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,contentValues);
                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
                            startActivityForResult(intent,MY_CAMERA_REQUEST_CODE);
                        }
                        else
                            Toast.makeText(Camera.this, "You must accept all permission", Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> list, PermissionToken permissionToken){}
                }).check();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==MY_CAMERA_REQUEST_CODE)
        {
            if(resultCode == Activity.RESULT_OK)
            {
                try{
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),imageUri);
                    img_preview.setImageBitmap(bitmap);
                    btn_camera.setVisibility(View.INVISIBLE);
                    btn_camera_ok.setVisibility(View.VISIBLE);
                }catch (FileNotFoundException e) {
                    e.printStackTrace();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }

    @OnClick(R.id.btn_camera_ok)
    void onOKClick(){
        SharedPreferences sharedPreferences2 = getSharedPreferences("challenge", Context.MODE_PRIVATE);
        challenge_num = sharedPreferences2.getInt("num",0);
        ClientService.sendMessage("s"+ String.valueOf(challenge_num));
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        setBytes(Camera.this, getBytes(img_preview));

        String str = Arrays.toString(getBytes(img_preview));

        ClientService.sendMessage(str);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ClientService.sendMessage("zEOF");

        //byte[] img_byte = getBytes(img_preview);
        //String str = new String(img_byte, StandardCharsets.UTF_8);

        //ClientService.sendMessage(str);

        //Log.d("imagestringlength", String.valueOf(str.length()));
        //Log.d("imagebytelength", String.valueOf(img_byte.length));
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Intent intent = new Intent(Camera.this, MainActivity.class);
        intent.putExtra("index",1);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        ButterKnife.bind(this);
    }

    public byte[] getBytes(ImageView imageView) {
        try {
            Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 60, stream);
            byte[] bytesData = stream.toByteArray();
            stream.close();
            return bytesData;
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void setBytes(Context ctx, byte[] bytes) {
        String str = Data.get_posttotalnum();
        SharedPreferences prefs = ctx.getSharedPreferences("ShareImage", Context.MODE_PRIVATE);
        SharedPreferences.Editor ee = prefs.edit();
        ee.putString(str, Arrays.toString(bytes));
        ee.commit();
    }
}