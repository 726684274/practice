package com.lechenggu.bkeasygo.utils;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.tool.hbm2ddl.SchemaExport;

//单例--饿汉
public class HibernateUtil {
	//执行顺序
	private static final SessionFactory sessionFactory = buildSessionFactory();
	private static Configuration cfg;
	
	//方法会返回SessionFactory对象
    private static SessionFactory buildSessionFactory() {
        try {
            cfg = new Configuration().configure();
            ServiceRegistry sr = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
            return cfg.buildSessionFactory(sr);
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    
    public static void export(){
    	System.out.println(cfg);
    	//建表的工具
    	SchemaExport se = new SchemaExport(cfg);
    	//创建表
    	se.create(true, true);
    }
}
