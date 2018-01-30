package retrofit.chenna.com.retrofitcheck.Activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import retrofit.chenna.com.retrofitcheck.R;
import retrofit.chenna.com.retrofitcheck.model.Post;
import retrofit.chenna.com.retrofitcheck.newtork.RetrofitClient;
import retrofit.chenna.com.retrofitcheck.newtork.ApiUtils;
import retrofit.chenna.com.retrofitcheck.newtork.APIServices;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class POSTActivity extends AppCompatActivity {

    private TextView mResponseTv;
    private APIServices mAPIService;
    public static ProgressDialog progressDialog;

    private Toolbar get_toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        final EditText titleEt = (EditText) findViewById(R.id.et_title);
        final EditText bodyEt = (EditText) findViewById(R.id.et_body);
        Button submitBtn = (Button) findViewById(R.id.btn_submit);
        mResponseTv = (TextView) findViewById(R.id.tv_response);

        get_toolbar = (Toolbar) findViewById(R.id.get_toolbar);
        setSupportActionBar(get_toolbar);
        setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = titleEt.getText().toString().trim();
                String body = bodyEt.getText().toString().trim();
                if(!TextUtils.isEmpty(title) && !TextUtils.isEmpty(body)) {
                    sendPost(title, body);
                }
            }
        });


    }

    private void sendPost(String title, String body) {

        Post post = new Post();
        post.setTitle(title);
        post.setBody(body);

        mAPIService = RetrofitClient.getClientdata().create(APIServices.class);
        progressDialog = new ProgressDialog(POSTActivity.this,R.style.MyAlertDialogStyle);
        progressDialog.setMessage(ApiUtils.LOADING);
        progressDialog.show();
        Gson gson = new Gson();
        String json = gson.toJson(post);

        Call<Post> call1 = mAPIService.savePost(title,body,1);
//        Call<Post> call1 = mAPIService.savePost(json);
        Log.wtf(TAG, call1.request() + "");
        call1.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {

                progressDialog.dismiss();
//                showResponse(response.body().toString());

                if(mResponseTv.getVisibility() == View.GONE) {
                    mResponseTv.setVisibility(View.VISIBLE);
                }
//                mResponseTv.setText((CharSequence) response.body());
                Toast.makeText(POSTActivity.this," Login Success "+response.body().toString(), Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(POSTActivity.this, "Not SUbmittes", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void showResponse(String s) {

        if(mResponseTv.getVisibility() == View.GONE) {
            mResponseTv.setVisibility(View.VISIBLE);
        }
        mResponseTv.setText(s);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
