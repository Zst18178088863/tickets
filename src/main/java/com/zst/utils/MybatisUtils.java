package com.zst.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

// 应用单例模式
public class MybatisUtils {
    private static SqlSessionFactory sqlSessionFactory = null;
    private MybatisUtils(){};
    public static SqlSession getSession(){
        if(sqlSessionFactory == null){
            // 此时仍未有工厂对象，新建实例
            try {
                Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
                MybatisUtils.sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
                System.out.println(MybatisUtils.sqlSessionFactory == null);
                SqlSession session = MybatisUtils.sqlSessionFactory.openSession();
                return session;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        else
            return MybatisUtils.sqlSessionFactory.openSession();
    }
}