package hcmute.edu.cropimagedemo_week7;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AliasActivity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {
    private Button btnSave, btnCrop;
    private ImageView imageView;
    private Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSave = findViewById(R.id.btnSave);
        btnCrop = findViewById(R.id.btnCrop);
        imageView = findViewById(R.id.iv);

        int bitmapResourceID = R.drawable.image;

        imageView.setImageBitmap(BitmapFactory.decodeResource(getResources(), bitmapResourceID));
        bitmap = BitmapFactory.decodeResource(getResources(), bitmapResourceID);

        btnCrop.setOnClickListener(view -> {
            bitmap = getCircularBitmap(bitmap);
            imageView.setImageBitmap(bitmap);
        });

        btnSave.setOnClickListener(view -> {
            try {
                saveMediaToStorage(bitmap);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private Bitmap getCircularBitmap(Bitmap srcBitmap) {
        int squareBitmapWidth = Math.min(srcBitmap.getWidth(), srcBitmap.getHeight());
        Bitmap dstBitmap = Bitmap.createBitmap(squareBitmapWidth, squareBitmapWidth, Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(dstBitmap);
        Paint paint = new Paint();
        paint.isAntiAlias();

        Rect rect = new Rect(0,0, squareBitmapWidth, squareBitmapWidth);
        RectF rectF = new RectF(rect);

        canvas.drawOval(rectF, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        float left = ((squareBitmapWidth - srcBitmap.getWidth()) / 2);
        float top = ((squareBitmapWidth - srcBitmap.getHeight()) / 2);
        canvas.drawBitmap(srcBitmap, left, top, paint);
        srcBitmap.recycle();
        return dstBitmap;
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