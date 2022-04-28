package jjfactory.jpabasic.domain.item;

import jjfactory.jpabasic.domain.BaseTimeEntity;
import jjfactory.jpabasic.domain.category.Category;
import jjfactory.jpabasic.error.exception.NotEnoughStockException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@DiscriminatorColumn(name = "dtype")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Entity
public class Item extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

    //비즈니스 로직
    public void addStock(int quantity){
        stockQuantity += quantity;
    }

    public void removeStock(int quantity){
        int restStock = stockQuantity - quantity;
        if(restStock < 0){
            throw new NotEnoughStockException("need more stock");
        }
        stockQuantity = restStock;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void changePriceAndStockQuantity(int price,int stockQuantity){
        this.stockQuantity = stockQuantity;
        this.price = price;
    }
}
