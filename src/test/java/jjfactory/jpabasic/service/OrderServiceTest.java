package jjfactory.jpabasic.service;

import jjfactory.jpabasic.domain.item.Book;
import jjfactory.jpabasic.domain.item.Item;
import jjfactory.jpabasic.domain.order.Order;
import jjfactory.jpabasic.domain.order.OrderStatus;
import jjfactory.jpabasic.domain.user.Address;
import jjfactory.jpabasic.domain.user.User;
import jjfactory.jpabasic.repository.OrderRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@SpringBootTest
@Transactional
public class OrderServiceTest {

    @PersistenceContext
    EntityManager em;

    @Autowired OrderService orderService;
    @Autowired OrderRepository orderRepository;

    @Test
    @DisplayName("상품 주문")
    void order(){
        //given
        User user = createUser();
        Item item = createBook("해리포터",10000,10);
        int orderCount = 2;

        //when
        Long orderId = orderService.order(user.getId(),item.getId(),orderCount);

        //then
        Order findOrder = orderRepository.findOne(orderId);

        Assertions.assertThat(findOrder.getStatus()).isEqualTo(OrderStatus.ORDER);
        Assertions.assertThat(findOrder.getOrderItems().size()).isEqualTo(1);
        Assertions.assertThat(findOrder.getTotalPrice()).isEqualTo(10000*2);
        Assertions.assertThat(item.getStockQuantity()).isEqualTo(8);

    }


    User createUser(){
        User user = new User("유저1",new Address("서울","경기","12345"));
        em.persist(user);
        return user;
    }

    Book createBook(String name, int price, int stockQuantity){
        Book book = new Book();
        book.setName(name);
        book.changePriceAndStockQuantity(price,stockQuantity);
        em.persist(book);
        return book;
    }

}
