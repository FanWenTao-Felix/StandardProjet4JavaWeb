package com.yihaomen.dao;

import java.util.List;

import com.yihaomen.model.Article;

public interface UserDAO {
    public List<Article> getUserArticles(int userid);
}
