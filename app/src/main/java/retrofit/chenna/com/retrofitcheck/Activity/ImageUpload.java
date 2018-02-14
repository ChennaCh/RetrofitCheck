package retrofit.chenna.com.retrofitcheck.Activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.ProgressCallback;

import java.io.File;

import retrofit.chenna.com.retrofitcheck.R;

public class ImageUpload extends Activity {

    private ImageView uploadimg;
    private int IMG_REQUEST = 1;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_upload);

        uploadimg = (ImageView) findViewById(R.id.upload_image21);

        uploadimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectImage();
            }
        });
    }

    private void selectImage() {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Image From Gallery"), IMG_REQUEST);
    }

//    private void imageToString() {
//
//        ByteArrayOutputStream byteArrayOutputStreamObject ;
//
//        byteArrayOutputStreamObject = new ByteArrayOutputStream();
//
//        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStreamObject);
//
//        byte[] byteArrayVar = byteArrayOutputStreamObject.toByteArray();
//
//        final String ConvertImage = Base64.encodeToString(byteArrayVar, Base64.DEFAULT);
//
//        Ion.with(this)
//                .load("http://streaklabs.in:90//api/Streaker/PostUserImage")
//                .setStringBody(ConvertImage)
//                .asString()
//                .setCallback(new FutureCallback<String>() {
//                    @Override
//                    public void onCompleted(Exception e, String result) {
//                        String output = result;
//                        String in;
//                    }
//                });
//
//        String h;
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IMG_REQUEST && resultCode == RESULT_OK && data != null) {

            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            android.database.Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
            if (cursor == null)
                return;

            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String filePath = cursor.getString(columnIndex);
            cursor.close();

            File file = new File(filePath);

            Ion.with(this)
                    .load("POST","http://streaklabs.in:90//api/Streaker/PostUserImage")
                    .uploadProgressHandler(new ProgressCallback() {
                        @Override
                        public void onProgress(long downloaded, long total) {

                        }
                    })
                    .setMultipartFile("image","image/jpeg",file)
                    .asString()
                    .setCallback(new FutureCallback<String>() {
                        @Override
                        public void onCompleted(Exception e, String result) {
                            String re = result;
                            String h;
                        }
                    });
        }
    }
}
