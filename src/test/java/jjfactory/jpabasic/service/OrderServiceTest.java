package jjfactory.jpabasic.service;

import jjfactory.jpabasic.domain.item.Book;
import jjfactory.jpabasic.domain.item.Item;
import jjfactory.jpabasic.domain.order.Order;
import jjfactory.jpabasic.domain.order.OrderStatus;
import jjfactory.jpabasic.domain.user.Address;
import jjfactory.jpabasic.domain.user.User;
import jjfactory.jpabasic.error.exception.NotEnoughStockException;
import jjfactory.jpabasic.repository.OrderRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
//@Rollback(value = true)
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
        Item item = createBook("harry",10000,10);
        int orderCount = 2;

        //when
        Long orderId = orderService.order(user.getId(),item.getId(),orderCount);

        //then
        Order findOrder = orderRepository.findOne(orderId);

        assertThat(findOrder.getStatus()).isEqualTo(OrderStatus.ORDER);
        assertThat(findOrder.getOrderItems().size()).isEqualTo(1);
        assertThat(findOrder.getTotalPrice()).isEqualTo(10000*2);
        assertThat(item.getStockQuantity()).isEqualTo(8);

    }

    @Test
    @DisplayName("상품줌누_재고수량 초과")
    void stockOver(){
        //given
        User user = createUser();
        Item item = createBook("harry",10000,10);

        int orderCount = 11;

        //when

        //then
        assertThatThrownBy( () -> orderService.order(user.getId(), item.getId(), orderCount))
                .isInstanceOf(NotEnoughStockException.class);
    }

    @DisplayName("주문 취소")
    @Test
    void cancelOrder(){
        //given
        User user = createUser();
        Item item = createBook("harry",10000,10);
        int orderCount = 2;

        Long orderId = orderService.order(user.getId(), item.getId(), orderCount);

        //when
        orderService.cancelOrder(orderId);

        //then
        Order one = orderRepository.findOne(orderId);
        assertThat(one.getStatus()).isEqualTo(OrderStatus.CANCEL);
        assertThat(item.getStockQuantity()).isEqualTo(10);

    }


    User createUser(){
        User user = new User("user1",new Address("seoul","gwangjin","12345"));
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
