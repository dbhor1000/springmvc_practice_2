package javacode_dk.springmvc_practice.model.MVC1;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import javacode_dk.springmvc_practice.DTO.MVC1.UserViews;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@Entity(name = "user_table")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "user_name")
    @JsonView(UserViews.UserDtoNameAndAddress.class)
    private String name;
    @Column(name = "user_email")
    @JsonView(UserViews.UserDtoWithoutOrders.class)
    private String email;
    @Column(name = "user_address")
    @JsonView(UserViews.UserDtoNameAndAddress.class)
    private String address;
    @Column(name = "user_orders")
    @JsonManagedReference
    @JsonView(UserViews.UserDtoWithOrders.class)
    @OneToMany(mappedBy = "buyer",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    List<Order> orders = new ArrayList<>();

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
