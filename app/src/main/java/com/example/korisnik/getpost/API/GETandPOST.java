package com.example.korisnik.getpost.API;

import com.example.korisnik.getpost.Model.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Korisnik on 09.03.2018.
 */

public interface GETandPOST {

    @POST("posts")
    Call<Post> post(@Body Post post);

    @GET("posts")
    Call <List<Post>> getAllPosts();
}
