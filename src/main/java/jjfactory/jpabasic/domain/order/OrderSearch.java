package jjfactory.jpabasic.domain.order;

import lombok.Getter;

@Getter
public class OrderSearch {
    private String memberName;
    private OrderStatus orderStatus;

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}
