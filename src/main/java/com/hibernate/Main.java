package com.hibernate;

import com.hibernate.entity.Detail;
import com.hibernate.entity.Account;
import com.hibernate.entity.Platform;
import com.hibernate.entity.Service;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

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
            session.beginTransaction();

            Account account = session.get(Account.class, 2);
            session.delete(account);

            session.createQuery("update Platform set name='Console' where id=2").executeUpdate();

            Account account1 = new Account("shdwraze", "warlock112");
            Detail detail1 = new Detail("Oleg", "Lviv", 25);
            account1.setUserDetail(detail1);
            Account account2 = new Account("night_hawk", "madsa3511");
            Detail detail2 = new Detail("Anton", "Kharkiv", 16);
            account2.setUserDetail(detail2);
            Account account3 = new Account("SERGIO15", "sergiy_d@5g");
            Detail detail3 = new Detail("Sergey", "Kyiv", 19);
            account3.setUserDetail(detail3);

            Service service = session.get(Service.class, 2);
            service.addAccountToService(account1);
            service.addAccountToService(account2);
            service.addAccountToService(account3);

            session.persist(service);

            List<Account> accounts = service.getAccounts();
            System.out.println(accounts);

            session.getTransaction().commit();
        } finally {
            if (session != null) {
                session.close();
            }
            factory.close();
        }
    }
}
