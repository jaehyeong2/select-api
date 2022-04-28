package jjfactory.jpabasic.web.controller;

import jjfactory.jpabasic.domain.item.Book;
import jjfactory.jpabasic.dto.BookForm;
import jjfactory.jpabasic.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class ItemController {
    private final ItemService itemService;

    @GetMapping("/items")
    public String list(Model model){
        model.addAttribute("items",itemService.findItems());
        return "items/itemList";
    }

    @GetMapping("/items/new")
    public String createForm(Model model){
        model.addAttribute("form",new BookForm());
        return "items/createForm";
    }

    @PostMapping("/items/new")
    public String create(BookForm form){

        Book book = new Book();
        book.setName(form.getName());
        book.changePriceAndStockQuantity(form.getPrice(),form.getStockQuantity());
        book.changeAuthorAndIsbn(form.getAuthor(),form.getIsbn());

        itemService.save(book);
        return "redirect:items";
    }


    @GetMapping("/items/{itemId}/edit")
    public String updateItemForm(@PathVariable("itemId") Long itemId, Model model){

        Book item = (Book) itemService.findOne(itemId);

        BookForm form = new BookForm(item.getId(),item.getName(),item.getPrice()
                ,item.getStockQuantity(),item.getAuthor(),item.getIsbn());

        model.addAttribute("form",form);
        return "items/updateItemForm";

    }

    @PostMapping("/items/{itemId}/edit")
    public String updateItem(@ModelAttribute("form") BookForm form){
        Book book = new Book();
        book.setId(form.getId());
        book.setName(form.getName());
        book.changeAuthorAndIsbn(form.getAuthor(),form.getIsbn());
        book.changePriceAndStockQuantity(form.getPrice(), form.getStockQuantity());

        itemService.save(book);
        return "redirect:/items";
    }

}
