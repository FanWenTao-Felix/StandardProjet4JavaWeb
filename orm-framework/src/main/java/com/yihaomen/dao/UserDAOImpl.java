package com.yihaomen.dao;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.yihaomen.model.Article;

@Repository
public class UserDAOImpl extends SqlSessionDaoSupport implements UserDAO {

	@Override
	public List<Article> getUserArticles(int userid) {		
		return this.getSqlSession().selectList("com.yihaomen.inter.IUserOperation.getUserArticles",userid);
	}

}
