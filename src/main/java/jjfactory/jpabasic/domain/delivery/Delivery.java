package jjfactory.jpabasic.domain.delivery;

import jjfactory.jpabasic.domain.BaseTimeEntity;
import jjfactory.jpabasic.domain.order.Order;
import jjfactory.jpabasic.domain.user.Address;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Delivery extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "delivery",fetch = FetchType.LAZY)
    private Order order;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status; //ENUM [READY(준비), COMP(배송)]

    public void setOrder(Order order) {
        this.order = order;
    }
    private void setAddress(Address address) {
        this.address = address;
    }

    private void setStatus(DeliveryStatus ready) {
        this.status = status;
    }

    public static Delivery createDelivery(Address address){
        Delivery delivery = new Delivery();
        delivery.setAddress(address);
        delivery.setStatus(DeliveryStatus.READY);
        return delivery;
    }
}
