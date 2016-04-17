package com.yihaomen.test;

import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.yihaomen.inter.IUserOperation;
import com.yihaomen.model.Article;
import com.yihaomen.model.User;

public class Test {
    private static SqlSessionFactory sqlSessionFactory;
    private static Reader reader; 

    static{
        try{
            reader    = Resources.getResourceAsReader("Configuration.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static SqlSessionFactory getSession(){
        return sqlSessionFactory;
    }
    
    public User selectUserByID(int id){
    	   SqlSession session = sqlSessionFactory.openSession();
           try {
               IUserOperation userOperation=session.getMapper(IUserOperation.class);
               User user = userOperation.selectUserByID(id);
              
               return user;
           } finally {
               session.close();
           }
    }
    
    
    
    public void getUserList(String userName){
        SqlSession session = sqlSessionFactory.openSession();
        try {
            IUserOperation userOperation=session.getMapper(IUserOperation.class);           
            List<User> users = userOperation.selectUsers(userName);
            for(User user:users){
                System.out.println(user.getId()+":"+user.getUserName()+":"+user.getUserAddress());
            }
            
        } finally {
            session.close();
        }
    }
    
    /**
     * 测试增加,增加后，必须提交事务，否则不会写入到数据库.
     */
    public void addUser(User user){
      
        SqlSession session = sqlSessionFactory.openSession();
        try {
            IUserOperation userOperation=session.getMapper(IUserOperation.class);
            userOperation.addUser(user);
            session.commit();
            System.out.println("当前增加的用户 id为:"+user.getId());
        } finally {
            session.close();
        }
    }
    
    public void updateUser(User user){
        //先得到用户,然后修改，提交。
        SqlSession session = sqlSessionFactory.openSession();
        try {
            IUserOperation userOperation=session.getMapper(IUserOperation.class);
            
            userOperation.updateUser(user);
            session.commit();
            
        } finally {
            session.close();
        }
    }
    
    /**
     * 删除数据，删除一定要 commit.
     * @param id
     */
    public void deleteUser(int id){
        SqlSession session = sqlSessionFactory.openSession();
        try {
            IUserOperation userOperation=session.getMapper(IUserOperation.class);           
            userOperation.deleteUser(id);
            session.commit();            
        } finally {
            session.close();
        }
    }
    
    public void getUserArticles(int userid){
        SqlSession session = sqlSessionFactory.openSession();
      
        try {
            IUserOperation userOperation=session.getMapper(IUserOperation.class);           
            List<Article> articles = userOperation.getUserArticles(userid);
            //System.out.println(articles);
            for(Article article:articles){
                System.out.println(article.getTitle()+":"+article.getContent()+
                        ":作者是:"+article.getUser().getUserName()+":地址:"+
                         article.getUser().getUserAddress());
            }
        } finally {
            session.close();
        }
    }
    
    public static void main(String[] args) {
    	 Test testUser=new Test();
    	 //list
         testUser.getUserList("%");
         //add
       /*  User user=new User();
         user.setUserAddress("Chelsea");
         user.setUserName("Torres");
         user.setUserAge(30);
         testUser.addUser(user);*/
         //update
        /* User user = testUser.selectUserByID(2);            
         testUser.updateUser(user);
         testUser.getUserList("%");*/
         //delete
        /* testUser.deleteUser(1);
         testUser.getUserList("%");*/
         testUser.getUserArticles(3);
         
        
    }
}