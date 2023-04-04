package hcmute.edu.captureviewandsavetogallery_week7;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {

    CardView cardView;
    Button btn_capture;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);

        cardView = findViewById(R.id.cardView);
        btn_capture = findViewById(R.id.btn_capture);
        btn_capture.setOnClickListener(view -> {
            Bitmap bitmap = getScreenShotFromView(cardView);
            if (bitmap != null) {
                try {
                    saveMediaToStorage(bitmap);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private Bitmap getScreenShotFromView(CardView cardView) {
        Bitmap screenshot = null;
        try {
            screenshot = Bitmap.createBitmap(cardView.getMeasuredWidth(), cardView.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(screenshot);
            cardView.draw(canvas);
        }catch (Exception e) {
            Log.e("GFG", "Failed to capture screenshot because:" + e.getMessage());
        }
        return screenshot;
    }

    private void saveMediaToStorage(Bitmap bitmap) throws FileNotFoundException {
        String fileName = System.currentTimeMillis()+".jpg";

        OutputStream fos = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            ContentResolver resolver = this.getContentResolver();
            if (resolver != null) {
                ContentValues contentValues = new ContentValues();
                contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, fileName);
                contentValues.put(MediaStore.MediaColumns.MIME_TYPE, "image/jpg");
                contentValues.put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES);
                Uri imageUri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
                if (imageUri != null) {
                    resolver.openOutputStream(imageUri);
                }
            }
        } else {
            File imageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
            File image = new File(imageDir, fileName);
            Log.e("imageDir", imageDir.getAbsolutePath().toString());
            Log.e("Image", imageDir.getAbsolutePath().toString());
            fos = new FileOutputStream(image);
        }

        if (fos != null) {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            Toast.makeText(this, "Captured View and saved to Gallery", Toast.LENGTH_SHORT).show();
        }
    }
}