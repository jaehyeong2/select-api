package jjfactory.jpabasic.web.controller;

import jjfactory.jpabasic.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class OrderController {
    private final OrderService orderService;
}
