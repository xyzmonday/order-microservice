package com.yff.order.domain.service;

import com.yff.order.domain.entity.Order;
import com.yff.order.object.OrderQo;
import com.yff.order.domain.repository.OrderRepository;
import com.yff.order.domain.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public Order getOne(Long id) {
        return orderRepository.getOne(id);
    }

    public void save(Order order) {
        orderRepository.save(order);
    }

    public void delete(Long id) {
        orderRepository.deleteById(id);
    }


    public Page<Order> findAll(OrderQo orderQo) {
        Sort sort = new Sort(Sort.Direction.DESC, "created");
        Pageable pageable = PageRequest.of(orderQo.getPage(), orderQo.getSize(), sort);

        return orderRepository.findAll(new Specification<Order>() {
            @Override
            public Predicate toPredicate(Root<Order> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicatesList = new ArrayList<Predicate>();

                if (CommonUtils.isNotNull(orderQo.getUserId())) {
                    predicatesList.add(criteriaBuilder.equal(root.get("userId"), orderQo.getUserId()));
                }
                if (CommonUtils.isNotNull(orderQo.getMerchantId())) {
                    predicatesList.add(criteriaBuilder.equal(root.get("merchantId"), orderQo.getMerchantId()));
                }
                if (CommonUtils.isNotNull(orderQo.getOrderNo())) {
                    predicatesList.add(criteriaBuilder.equal(root.get("orderNo"), orderQo.getOrderNo()));
                }
                if (CommonUtils.isNotNull(orderQo.getStatus())) {
                    predicatesList.add(criteriaBuilder.equal(root.get("status"), orderQo.getStatus()));
                }
                if (CommonUtils.isNotNull(orderQo.getCreated())) {
                    predicatesList.add(criteriaBuilder.greaterThan(root.get("created"), orderQo.getCreated()));
                }
                query.where(predicatesList.toArray(new Predicate[predicatesList.size()]));
                return query.getRestriction();
            }
        }, pageable);
    }
}
