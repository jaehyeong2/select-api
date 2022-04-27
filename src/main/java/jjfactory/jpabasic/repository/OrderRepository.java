package jjfactory.jpabasic.repository;

import jjfactory.jpabasic.domain.order.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class OrderRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Order order){
        em.persist(order);
    }

    public Order findOne(Long id){
        return em.find(Order.class,id);
    }
}
