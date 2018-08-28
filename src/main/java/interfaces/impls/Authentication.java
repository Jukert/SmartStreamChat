package interfaces.impls;

import entity.UsersEntity;
import interfaces.UserDoInf;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.List;

public class Authentication implements UserDoInf {
    private Session session;
    private Transaction tr;
    @Override
    public void add(UsersEntity user) {

    }

    @Override
    public List<UsersEntity> findByLogin(String login) {
        session = HibernateUtil.getSession();
        tr = session.beginTransaction();
        try {
            List<UsersEntity> list = session.createQuery("from UsersEntity where login=:login").setParameter("login",login).list();
            tr.commit();
            return list;
        }catch (RuntimeException e){
            System.err.println(e);
            tr.rollback();
        }finally {
            session.close();
        }
        return null;
    }

    public UsersEntity getUserById(int id){
        session = HibernateUtil.getSession();
        tr = session.beginTransaction();
        try{
            UsersEntity user = session.get(UsersEntity.class,id);
            tr.commit();
            return user;
        }catch (RuntimeException e){
            tr.rollback();
            return null;
        }finally {
            session.close();
        }
    }
}
