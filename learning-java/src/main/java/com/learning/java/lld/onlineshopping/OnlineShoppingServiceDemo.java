package com.learning.java.lld.onlineshopping;

import com.learning.java.lld.onlineshopping.domain.Cart;
import com.learning.java.lld.onlineshopping.domain.Item;
import com.learning.java.lld.onlineshopping.domain.User;
import lombok.SneakyThrows;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class UserThread implements Runnable {
    private OnlineShoppingService onlineShoppingService;
    private Cart cart;
    private User user;
    UserThread(OnlineShoppingService shoppingService, User user, Cart cart) {
        this.onlineShoppingService = shoppingService;
        this.user = user;
        this.cart = cart;
    }
    @SneakyThrows
    @Override
    public void run() {
        while (true) {
            if(onlineShoppingService.placeOrder(user, cart)) {
               // onlineShoppingService.printItemAvailability();
            } else {
                System.out.println("item not present in inventory");
                //onlineShoppingService.printItemAvailability();
                break;
            }
            Thread.sleep(10);
        }
    }
};

public class OnlineShoppingServiceDemo {

    public static void main(String[] args) throws Exception {
        OnlineShoppingService onlineShoppingService = new OnlineShoppingService();
        // Now let's add item to cart
        User firstUser = new User(UUID.randomUUID(),"pankaj", "pksemail", "password");
        User secondUser = new User(UUID.randomUUID(),"ranjeet", "ranja", "password");

        Item itemMango = new Item(UUID.randomUUID(), "mango", "langada mango found in bengal", 100.0);
        Item itemOrange = new Item(UUID.randomUUID(), "orange", "langada orange found in bengal", 100.0);

        // Updated inventory with 100, 10
        onlineShoppingService.updateInventory(itemMango, 100, "ADD");
        onlineShoppingService.updateInventory(itemOrange, 25, "ADD");

        Cart cartFirst = onlineShoppingService.createOrUpdateCart(firstUser.getUserId(), null, itemMango,1);
        Cart cartSecond = onlineShoppingService.createOrUpdateCart(secondUser.getUserId(), null, itemMango, 3);
        onlineShoppingService.createOrUpdateCart(secondUser.getUserId(), cartSecond, itemOrange, 2);


        onlineShoppingService.printItemAvailability();

        ExecutorService itemInventoryUpdate = Executors.newFixedThreadPool(1);
        ExecutorService userExecutor  = Executors.newFixedThreadPool(2);
        userExecutor.submit(new UserThread(onlineShoppingService, firstUser, cartFirst));
        userExecutor.submit(new UserThread(onlineShoppingService, secondUser, cartSecond));
        //userExecutor.shutdown(); // Shutdown dont accept new, but let old continue to run

        // Both should be called  for thredas to be completed.
        userExecutor.shutdown();
        // With this, your main thread wait for 30 sec to join, if thread finishes within 30 sec good
        // otherwise it proceed without killing thread.
        userExecutor.awaitTermination(30, TimeUnit.SECONDS);

        System.out.println("First user");
        printOrderDetails(firstUser);

        System.out.println("Second user");
        printOrderDetails(secondUser);
        onlineShoppingService.printItemAvailability();
    }

    private static void printOrderDetails(User user) {
        Map<String, Integer> firstUserOrderItemCount = new HashMap<>();
        user.getOrderList()
                .stream().flatMap(order -> order.getOrderDetailList().stream())
                .forEach(orderDetail -> {
                    int count = orderDetail.getCount();
                    String name = orderDetail.getItem().getName();
                    if(firstUserOrderItemCount.getOrDefault(name, null) != null) {
                        firstUserOrderItemCount.put(name,firstUserOrderItemCount.get(name) + count);
                    } else {
                        firstUserOrderItemCount.put(name,count);
                    }
                });

        firstUserOrderItemCount.entrySet().forEach(entry -> {
            System.out.println(entry.getKey() + "  : " + entry.getValue());
        });
    }
}
