package com.hibernate;

import com.hibernate.entity.Detail;
import com.hibernate.entity.Account;
import com.hibernate.entity.Platform;
import com.hibernate.entity.Service;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure()
                .addAnnotatedClass(Account.class)
                .addAnnotatedClass(Detail.class)
                .addAnnotatedClass(Service.class)
                .addAnnotatedClass(Platform.class)
                .buildSessionFactory();

        Session session = null;

        try {
            session = factory.getCurrentSession();
            Account account1 = new Account("marksev", "kekw");
            Account account2 = new Account("bulbasaur12", "1191kfw3@@");
            Detail detail1 = new Detail("Mark", "Kyiv", 18);
            Detail detail2 = new Detail("Jaroslava", "Kharkiv", 19);
            account1.setUserDetail(detail1);
            account2.setUserDetail(detail2);

            Service service1 = new Service("Steam", "USA");
            Service service2 = new Service("Origin", "USA");

            Platform platform1 = new Platform("PC");
            Platform platform2 = new Platform("Console");
            Platform platform3 = new Platform("Mobile");

            service1.addPlatformToService(platform1);
            service1.addPlatformToService(platform2);
            service2.addPlatformToService(platform1);
            service2.addPlatformToService(platform2);
            service2.addPlatformToService(platform3);
            service1.addAccountToService(account1);
            service2.addAccountToService(account2);

            session.beginTransaction();

            session.persist(service1);
            session.persist(service2);

            session.getTransaction().commit();
        } finally {
            if (session != null) {
                session.close();
            }
            factory.close();
        }
    }
}
