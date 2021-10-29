package com.edu.cart.controller;

import com.edu.cart.DTOobjects.user.UserDTO;
import com.edu.cart.Services.OrderService;
import com.edu.cart.dao.CartDao;
import com.edu.cart.exceptions.EntityAlreadyExists;
import com.edu.cart.exceptions.ProductNotFoundException;
import com.edu.cart.exceptions.SessionExpired;
import com.edu.cart.model.Cart;
import com.edu.cart.model.Order;
import com.fasterxml.jackson.databind.node.LongNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderService orderService;

    @RequestMapping(value = "/createOrder", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<Void> placeOrder(@RequestBody Map<String, Long> cartId, HttpServletRequest request) throws URISyntaxException, SessionExpired, EntityAlreadyExists {
        String authToken = request.getHeader("Authorization");
        Long id = orderService.create(cartId.get("cartId"), new UserDTO(authToken));
        HttpHeaders header = new HttpHeaders();
        header.setLocation(new URI(request.getRequestURL() + "/" + id));
        return new ResponseEntity<Void>(header, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/getOrder/{orderId}", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<Order> getOrder(@PathVariable Long orderId) throws ProductNotFoundException {
        Order order = orderService.get(orderId);
        return new ResponseEntity<Order>(order, HttpStatus.CREATED);
    }

}
