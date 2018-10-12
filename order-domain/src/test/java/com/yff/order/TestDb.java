package com.yff.order;


import com.yff.order.domain.entity.Order;
import com.yff.order.domain.entity.OrderDetail;
import com.yff.order.domain.repository.OrderRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import java.util.List;


@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {JpaConfiguration.class})
public class TestDb {
    private static Logger logger = LoggerFactory.getLogger(TestDb.class);

    @Autowired
    private OrderRepository orderRepository;


    @Test
    public void insertData() {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setGoodsName("测试商品1");
        orderDetail.setGoodsId(1L);
        orderDetail.setPrice(12.20F);
        orderDetail.setCount(1);
        orderDetail.setMoney(12.20D);
        orderDetail.setPhoto("../images/order/orderPic.jpg");

        OrderDetail orderDetail2 = new OrderDetail();
        orderDetail2.setGoodsName("测试商品2");
        orderDetail2.setGoodsId(2L);
        orderDetail2.setPrice(20.00F);
        orderDetail2.setCount(2);
        orderDetail2.setMoney(40.00D);
        orderDetail2.setPhoto("../images/order/orderPic.jpg");

        Order order = new Order();
        order.setOrderNo("20170930000003");
        order.setUserId(11111235L);
        order.setAmount(52.20D);
        order.setStatus(2);
        order.addOrderDetails(orderDetail);
        order.addOrderDetails(orderDetail2);
        orderRepository.save(order);
        Assert.notNull(order.getId(), "not insert");
    }

    @Test
    public void getData() {
        List<Order> orders = orderRepository.findAll();
        Assert.notEmpty(orders, "list empty");
        for (Order order : orders) {
            logger.info("========order id={}, order no={}，goods name={}",
                    order.getId(), order.getOrderNo(), order.getOrderDetails().iterator().next().getGoodsName());
        }
    }

    @Test
    public void delData() {
        orderRepository.deleteById(1L);
    }

    @Test
    public void findByOrderDetailId() {
        Order order = orderRepository.findByOrderDetailId(1L);
        logger.info("========order id={}, order no={}，goods name={}",
                order.getId(), order.getOrderNo(), order.getOrderDetails().iterator().next().getGoodsName());
    }
}
