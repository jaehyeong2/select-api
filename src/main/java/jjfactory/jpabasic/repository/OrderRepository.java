package jjfactory.jpabasic.repository;

import jjfactory.jpabasic.domain.order.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@RequiredArgsConstructor
@Repository
public class OrderRepository {

    private final EntityManager em;

    public Long saVe(Order order){
        em.persist(order);
        return order.getId();
    }

    public Order findOne(Long id){
        return em.find(Order.class,id);
    }
}
