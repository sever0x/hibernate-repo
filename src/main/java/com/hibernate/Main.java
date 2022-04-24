package com.hibernate;

import com.hibernate.entity.Detail;
import com.hibernate.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure()
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Detail.class)
                .buildSessionFactory();

        Session session = null;

        try {
            session = factory.getCurrentSession();
            User user = new User("marksev", "kekw");
            Detail detail = new Detail("Mark", "Kyiv", 18);
            user.setUserDetail(detail);

            session.beginTransaction();

            session.save(user);

            session.getTransaction().commit();
        } finally {
            if (session != null) {
                session.close();
            }
            factory.close();
        }
    }
}
