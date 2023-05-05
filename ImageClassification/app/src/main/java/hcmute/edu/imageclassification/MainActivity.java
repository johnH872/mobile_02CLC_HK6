package hcmute.edu.imageclassification;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.tensorflow.lite.DataType;
import org.tensorflow.lite.support.image.TensorImage;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import hcmute.edu.imageclassification.ml.MobilenetV110224Quant;

public class MainActivity extends AppCompatActivity {
    Button selectBtn, predictBtn, captureBtn;
    TextView textResult;
    ImageView imageView;
    Bitmap bitmap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //
        getPermission();
        String labels[] = new String[1001];
        int cnt = 0;
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(getAssets().open("labels.txt")));
            String line = bufferedReader.readLine();
            while (line!=null) {
                labels[cnt] = line;
                cnt++;
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        selectBtn = findViewById(R.id.selectButton);
        predictBtn = findViewById(R.id.predictButton);
        captureBtn = findViewById(R.id.captureButton);
        textResult = findViewById(R.id.result);
        imageView = findViewById(R.id.imageView);

        ActivityResultLauncher getImageLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Intent data = result.getData();
                            Uri uri = data.getData();
                            try {
                                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
                                imageView.setImageBitmap(bitmap);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                });

        ActivityResultLauncher captureImageLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Intent data = result.getData();
                            Uri uri = data.getData();
                            bitmap = (Bitmap) data.getExtras().get("data");
                            imageView.setImageBitmap(bitmap);
                        }
                    }
                });

        selectBtn.setOnClickListener(view -> {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
            getImageLauncher.launch(intent);
        });

        captureBtn.setOnClickListener(view -> {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            captureImageLauncher.launch(intent);
        });

        predictBtn.setOnClickListener(view -> {
            try {
                MobilenetV110224Quant model = MobilenetV110224Quant.newInstance(MainActivity.this);

                // Creates inputs for reference.
                TensorBuffer inputFeature0 = TensorBuffer.createFixedSize(new int[]{1, 224, 224, 3}, DataType.UINT8);
                bitmap = Bitmap.createScaledBitmap(bitmap, 224, 224, true);
                inputFeature0.loadBuffer(TensorImage.fromBitmap(bitmap).getBuffer());

                // Runs model inference and gets result.
                MobilenetV110224Quant.Outputs outputs = model.process(inputFeature0);
                TensorBuffer outputFeature0 = outputs.getOutputFeature0AsTensorBuffer();
                textResult.setText(labels[getMax(outputFeature0.getFloatArray())] + " ");

                // Releases model resources if no longer used.
                model.close();
            } catch (IOException e) {
                // TODO Handle the exception
            }
        });

    }

    private int getMax(float[] arr) {
        int max = 0;
        for (int i = 0; i<arr.length;i++)
            if (arr[i]>arr[max]) max=i;
        return max;
    }

    private void getPermission() {
        if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CAMERA},1);

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 11) {
            if(grantResults.length > 0) {
                if (grantResults[0] != PackageManager.PERMISSION_GRANTED) this.getPermission();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}