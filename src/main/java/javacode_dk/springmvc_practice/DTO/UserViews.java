package javacode_dk.springmvc_practice.DTO;

public class UserViews {

    public static class UserDtoNameAndAddress {}
    public static class UserDtoWithoutOrders extends UserDtoNameAndAddress {}
    public static class UserDtoWithOrders extends UserDtoWithoutOrders {}

}
