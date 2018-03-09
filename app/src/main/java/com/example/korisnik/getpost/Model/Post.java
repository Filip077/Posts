package com.example.korisnik.getpost.Model;

import android.content.Intent;

/**
 * Created by Korisnik on 09.03.2018.
 */

public class Post {
    private Integer id;
    private String title;
    private String body;

    public Post(String title, String post) {
        this.title = title;
        this.body = post;
    }

    public Integer getId() {
        return id;
    }

}
