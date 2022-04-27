package jjfactory.jpabasic.service;

import jjfactory.jpabasic.domain.OrderItem;
import jjfactory.jpabasic.domain.delivery.Delivery;
import jjfactory.jpabasic.domain.item.Item;
import jjfactory.jpabasic.domain.order.Order;
import jjfactory.jpabasic.domain.user.User;
import jjfactory.jpabasic.repository.ItemRepository;
import jjfactory.jpabasic.repository.OrderRepository;
import jjfactory.jpabasic.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class OrderService {
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;

    /*
     주문
     */
    @Transactional
    public Long order(Long userId,Long itemId, int count){
        User user = userRepository.findOne(userId);
        Item item = itemRepository.findOne(itemId);
        Delivery delivery = Delivery.createDelivery(user.getAddress());

        OrderItem orderItem = OrderItem.createOrderItem(item,item.getPrice(),count);
        Order order = Order.createOrder(user, delivery, orderItem);

        orderRepository.save(order);
        return order.getId();
    }

    // 주문 취소
    @Transactional
    public void cancelOrder(Long orderId){
        Order order = orderRepository.findOne(orderId);
        order.cancel();
    }
}
