package com.learning.java.lld.onlineshopping;

import com.learning.java.lld.onlineshopping.domain.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class OnlineShoppingService {
    Map<String, User> userMap;
    Map<String, Product> inventory;
    Map<String, Order> orderMap;

    public OnlineShoppingService() {
        this.userMap = new HashMap<>();
        this.inventory = new ConcurrentHashMap<>(18); //For concurrent update to inventory.
        this.orderMap = new HashMap<>();
    }

    public Map<String, Integer> browserItem(String itemName) {
        return inventory.entrySet()
                .stream()
                .filter(set -> set.getValue().getItem().getDescription().contains(itemName))
                .collect(Collectors.toMap(s -> s.getKey().toString(), s -> s.getValue().getCount()));
    }

    public boolean updateInventory(Item item, int updateCountBy, String opType) throws Exception {
        Optional<Map.Entry<String, Product>> mp = inventory.entrySet()
                .stream().filter(itemIntegerEntry -> item
                        .getItemId().toString().equals(itemIntegerEntry.getValue().getProductId().toString()))
                .findAny();

        if(opType.equals("ADD") && mp.isPresent()) {
            mp.get().getValue().updateCount(updateCountBy);
            return true;
        } else if(opType.equals("ADD") && !mp.isPresent()) {
            inventory.put(item.getItemId().toString(), new Product(item.getItemId(), item, updateCountBy));
            return true;
        }
        else if(opType.equals("DELETE") && !mp.isPresent()) {
            throw new Exception("Item not present in inventory");
        }
        else if(opType.equals("DELETE") && mp.get().getValue().getCount() - updateCountBy <= 0) {
            throw new Exception("Item is not sufficient");
        } else if(opType.equals("DELETE") && mp.get().getValue().getCount() - updateCountBy > 0) {
            mp.get().getValue().updateCount(-1 * updateCountBy);
            return true;
        }
        return false;
    }

    public void printItemAvailability() {
        System.out.println("--- ITEM AVAILABILITY ---");
        this.inventory.entrySet()
                .forEach(itemIntegerEntry -> System.out.println(itemIntegerEntry.getValue().getItem().getName()
                         + " Count : " + itemIntegerEntry.getValue().getCount()));
        System.out.println("--- ITEM AVAILABILITY ---");
    }

    public Cart createOrUpdateCart(UUID userId, Cart cart, Item item, int itemCount) {
        if(Objects.isNull(cart)) {
            cart = new Cart();
            cart.setUserId(userId);
            cart.setCartId(UUID.randomUUID());
        }
        CartItem cartItem = new CartItem();
        cartItem.setItem(item);
        cartItem.setCount(itemCount);
        Integer maxItemCount = maxItemAvailableToLink(item);
        if(maxItemCount < itemCount) {
            System.out.println("Maximum limit reached for this item in one cart : " + maxItemCount);
            cartItem.setCount(maxItemCount);
        }
        cart.getItemList().add(cartItem);
        return cart;
    }
    // Remove a item from cart completely.
    public Cart removeItemFromCart(Cart cart, Item item) throws Exception {

        CartItem cartItem = cart.getItemList()
                .stream().filter(ct -> ct.getItem().getItemId().equals(item.getItemId()))
                .findFirst().get();
        cart.getItemList()
                .removeIf(ct -> ct.getItem().getItemId().equals(item.getItemId()));
        return cart;
    }

    public synchronized boolean placeOrder(User user, Cart cart) {
        Order order = mapToOrderFromCart(user, cart);
        // update inventory

        for (CartItem cartItem : cart.getItemList()) {
            try {
                updateInventory(cartItem.getItem(), cartItem.getCount(), "DELETE");
            } catch (Exception e) {
                System.out.println("Item is not available : " + cartItem.getItem().getName());
               return false;
            }
        }
        // update order map, update user.
        orderMap.put(order.getOrderId().toString(), order);
        user.getOrderList().add(order);
        System.out.println("your order is placed");
        return true;
    }

    private Integer maxItemAvailableToLink(Item item) {
        return this.inventory.entrySet()
                .stream().filter(itemIntegerEntry -> item
                        .getItemId().toString().equals(itemIntegerEntry.getValue().getProductId()
                                .toString()))
                .map(itemIntegerEntry -> itemIntegerEntry.getValue().getCount())
                .findFirst().get();
    }

    private Order mapToOrderFromCart(User user, Cart cart) {
        Order order = new Order();
        order.setOrderId(UUID.randomUUID());
        order.setUserId(user.getUserId());
        order.setOrderStatus(OrderStatus.PENDING);
        List<OrderDetail> orderDetails = cart.getItemList()
                        .stream().map(cartItem -> {
                            OrderDetail orderDetail = new OrderDetail();
                            orderDetail.setCount(cartItem.getCount());
                            orderDetail.setItem(cartItem.getItem());
                            return orderDetail;
                }).collect(Collectors.toList());

        order.setOrderDetailsList(orderDetails);
        return order;
    }
}
