package jjfactory.jpabasic.domain.item;

import lombok.Getter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("B")
@Getter
public class Book extends Item{
    private String author;
    private String isbn;

    public void changeAuthorAndIsbn(String author,String isbn) {
        this.author = author;
        this.isbn = isbn;
    }
}
