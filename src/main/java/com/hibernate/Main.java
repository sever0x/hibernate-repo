package com.hibernate;

import com.hibernate.entity.Detail;
import com.hibernate.entity.Account;
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
                .buildSessionFactory();

        Session session = null;

        try {
            session = factory.getCurrentSession();
            Account account = new Account("marksev", "kekw");
            Detail detail = new Detail("Mark", "Kyiv", 18);
            Service service = new Service("Steam", "USA");

            account.setUserDetail(detail);
            service.addAccountToService(account);

            session.beginTransaction();

            session.persist(service);

            session.getTransaction().commit();
        } finally {
            if (session != null) {
                session.close();
            }
            factory.close();
        }
    }
}
