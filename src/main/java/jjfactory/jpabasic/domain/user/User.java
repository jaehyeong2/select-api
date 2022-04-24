package jjfactory.jpabasic.domain.user;

import jjfactory.jpabasic.domain.BaseTimeEntity;
import jjfactory.jpabasic.domain.order.Order;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class User extends BaseTimeEntity{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "user")
    private List<Order> orderList = new ArrayList<>();

    public User(String name, String email, Address address) {
        this.name = name;
        this.email = email;
        this.address = address;
    }
}
