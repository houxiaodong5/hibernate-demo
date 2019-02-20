package com.hxd.dao.impl;

import org.hibernate.Query;
import org.hibernate.type.Type;
import org.jboss.logging.annotations.Param;

import com.hxd.dao.PersonDAO;
import com.hxd.entity.Person;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class PersonDAOImpl implements PersonDAO {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession(){

        //hibernate3可使用：getCurrentSession()创建session,hibernate4不可以
        return this.sessionFactory.openSession();
    }

    /**
     *  load与get均可以查询数据库数据
     *  区别：
     *      load：加载对象时，会使用延迟加载机制  得到一个只有id的代理对象，若使用对象的其他属性，则发送查询SQL，查询对象所有属性
     *      get：直接发送sql,查询用户的所有属性
     *
     * */
    public Person load(Long id) {
        return (Person)getCurrentSession().load(Person.class,id);
    }

    public Person get(Long id) {
        return (Person)getCurrentSession().get(Person.class,id);
    }

    public List<Person> findAll() {
        //使用qQLQuery对象的list方法获取数据集合,集合里面不是对象，而是数组
        SQLQuery query = getCurrentSession().createSQLQuery("select * from person");
        query.addEntity(Person.class);
        List<Person> list = query.list();

        return list;
    }

    /**
     * persist与save  均可以进行插入数据
     * 区别：save  会立即进行插入操作，需要返回标识符
     *      persist 也会进行插入操作，但可能会被推迟
     * */

    public void persist(Person entity) {
        getCurrentSession().persist(entity);
    }

    public Long save(Person entity) {
        return (Long) getCurrentSession().save(entity);
    }

    /**
     * @Param entity : entity有主键则进行更新，无主键则进行插入
     * */
    public void saveOrUpdate(Person entity) {

        String sql="update person set address=:where id=:";
        Query query = getCurrentSession().createSQLQuery(sql).setString("address", "Beijing").setLong("id", 1L);
        int i = query.executeUpdate();
        //getCurrentSession().update(entity);
        //getCurrentSession().saveOrUpdate(entity);
    }

    public void delete(Long id) {
        Person person = load(id);
        getCurrentSession().delete(person);
    }

    //使数据库中的对象和session缓存中的对象的状态保持一致。为了保持一致，则可能发送对应的sql语句(若缓存中的对象和数据库中的对象一样则不发送sql语句)
    public void flush() {
        getCurrentSession().flush();
    }
}
