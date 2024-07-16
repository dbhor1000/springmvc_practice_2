package javacode_dk.springmvc_practice.model.MVC1;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import javacode_dk.springmvc_practice.DTO.MVC1.UserViews;
import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
@Entity(name = "order_table")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "goods_ordered")
    @JsonView(UserViews.UserDtoWithOrders.class)
    private String goodsOrdered;
    @Column(name = "total_amount")
    @JsonView(UserViews.UserDtoWithOrders.class)
    private BigDecimal totalAmount;
    @Column(name = "order_status")
    @JsonView(UserViews.UserDtoWithOrders.class)
    private OrderStatus orderStatus;
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity buyer;

    public UserEntity getBuyer() {
        return buyer;
    }

    public void setBuyer(UserEntity buyer) {
        this.buyer = buyer;
    }

    public String getGoodsOrdered() {
        return goodsOrdered;
    }

    public void setGoodsOrdered(String goodsOrdered) {
        this.goodsOrdered = goodsOrdered;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }
}
