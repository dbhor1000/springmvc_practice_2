package javacode_dk.springmvc_practice.DTO.MVC1;

public class UserViews {

    public static class UserDtoNameAndAddress {}
    public static class UserDtoWithoutOrders extends UserDtoNameAndAddress {}
    public static class UserDtoWithOrders extends UserDtoWithoutOrders {}

}
