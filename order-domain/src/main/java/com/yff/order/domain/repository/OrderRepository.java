package com.yff.order.domain.repository;


import com.yff.order.domain.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>, JpaSpecificationExecutor<Order> {

    Page<Order> findByCreatedGreaterThan(@Param("created") Date created, Pageable pageRequest);

    @Modifying
    @Query("delete from Order t where t.userId = ?1")
    void deleteByUserId(Long userId);

    @Query("select o from Order o left join o.orderDetails d where d.id = :id")
    Order findByOrderDetailId(@Param("id") Long id);
}
