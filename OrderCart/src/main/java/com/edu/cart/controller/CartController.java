package com.edu.cart.controller;

import com.edu.cart.DTOobjects.user.UserClient;
import com.edu.cart.DTOobjects.user.UserDTO;
import com.edu.cart.Services.CartService;
import com.edu.cart.exceptions.ProductNotFoundException;
import com.edu.cart.DTOobjects.products.ProductDTO;
import com.edu.cart.exceptions.SessionExpired;
import com.edu.cart.model.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    CartService cartService;
    @Autowired
    UserClient userClient;

    @RequestMapping(value = "/createCarts", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<Void> create(@RequestBody Cart cart, HttpServletRequest request) throws URISyntaxException, SessionExpired {
        String authToken = request.getHeader("Authorization");
        UserDTO userDTO = new UserDTO();
        userDTO.setToken(authToken);
        cart.setCustomer(userClient.user(userDTO).getUsername());
        Long id = cartService.save(cart);
        HttpHeaders header = new HttpHeaders();
        header.setLocation(new URI(request.getRequestURL() + "/" + id.toString()));
        return new ResponseEntity<Void>(header, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/getCart/{idCart}", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<Cart> viewCart(@PathVariable("idCart") Long idCart) throws URISyntaxException, ProductNotFoundException {
        Cart cart = cartService.cartItem(idCart);
        return new ResponseEntity<Cart>(cart, HttpStatus.OK);
    }

    @RequestMapping(value = "/updateCart/{idCart}", method = RequestMethod.PUT)
    public @ResponseBody
    ResponseEntity<Void> addProduct(@PathVariable("idCart") Long idCart, @RequestBody List<ProductDTO> Products,
                                    HttpServletRequest request) throws URISyntaxException, ProductNotFoundException {
        cartService.add(idCart, Products);
        HttpHeaders header = new HttpHeaders();
        header.setLocation(new URI(String.valueOf(request.getRequestURL())));
        return new ResponseEntity<Void>(header, HttpStatus.CREATED);
    }


}
