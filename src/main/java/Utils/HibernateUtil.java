package Utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil { // klasa konfiguracyjna
    private static final SessionFactory sessionFactory;
    private static final Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
    static {
        try {
            sessionFactory = configuration.buildSessionFactory();
        }
        catch (Throwable ex)
        {
            throw new ExceptionInInitializerError(ex);
        }
    }
    public static SessionFactory getSessionFactory()
    {
        return sessionFactory;
    }
}