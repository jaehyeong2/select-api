package jjfactory.jpabasic.repository;

import jjfactory.jpabasic.domain.order.Order;
import jjfactory.jpabasic.domain.order.OrderSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

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

    public List<Order> findAllByString(OrderSearch orderSearch) {
        String jpql = "select * from Order o join o.member m";
        boolean isFirstConnection = true;

        //주문 상태 검색
        if(orderSearch.getOrderStatus() != null){
            if (isFirstConnection) {
                jpql += "where";
                isFirstConnection = false;
            } else {
                jpql += "and";
            }
            jpql += " o.status = :status";
        }

    //회원 이름 검색
        if(StringUtils.hasText(orderSearch.getMemberName())){
            if(isFirstConnection){
                jpql += "where";
                isFirstConnection = false;
            } else{
                jpql += "and";
            }
                jpql += "m.name like :name";
        }
        TypedQuery<Order> query = em.createQuery(jpql, Order.class).setMaxResults(1000);

        if (orderSearch.getOrderStatus() != null) {
            query = query.setParameter("status", orderSearch.getOrderStatus());
        }
        if (StringUtils.hasText(orderSearch.getMemberName())) {
            query = query.setParameter("name", orderSearch.getMemberName());
        }

        return query.getResultList();
    }
}
