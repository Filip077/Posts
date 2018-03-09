package com.example.korisnik.getpost;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.korisnik.getpost.API.GETandPOST;
import com.example.korisnik.getpost.Model.Post;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostActivity extends AppCompatActivity {
    private static final String TAG = PostActivity.class.getSimpleName();
        private static final String BASE_URL = "https://jsonplaceholder.typicode.com/";
        private EditText title , body;
        private Button post_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        title=findViewById(R.id.title_edit_text);
        body=findViewById(R.id.post_edit_text);
        post_button = findViewById(R.id.post_button);

        post_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Post post1 = new Post(title.getText().toString(),
                        body.getText().toString());


                OkHttpClient.Builder client = new OkHttpClient.Builder();
                HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor()
                        .setLevel(HttpLoggingInterceptor.Level.BODY);
                if(BuildConfig.DEBUG){
                    client.addInterceptor(interceptor);
                }

                Retrofit.Builder builder = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(client.build());

                Retrofit retrofit = builder.build();

                GETandPOST post = retrofit.create(GETandPOST.class);
                Call<Post> call = post.post(post1);

                call.enqueue(new Callback<Post>() {
                    @Override
                    public void onResponse(Call<Post> call, Response<Post> response) {
                        if (response.body() != null) {
                            Toast.makeText(PostActivity.this, "Success :)" + response.body().getId(), Toast.LENGTH_SHORT).show();
                            Log.d(TAG, "onResponse: " + response.body().toString());
                        } else{
                            Toast.makeText(PostActivity.this, "Epmty body :(", Toast.LENGTH_SHORT).show();

                        }

                    }

                    @Override
                    public void onFailure(Call<Post> call, Throwable t) {
                        Toast.makeText(PostActivity.this, "Failed to connect", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });


        
    }



}
