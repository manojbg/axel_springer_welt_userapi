package com.welt.userapi.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserAddress {
    private String street;
    private String city;
    private String zipcode;
    private String suite;
    private Geo geo;

    public UserAddress(){}

    public UserAddress(String street, String city, String zipcode, String suite, Geo geo) {
        this.street = street;
        this.city = city;
        this.zipcode = zipcode;
        this.suite = suite;
        this.geo = geo;
    }

    @Getter
    @Setter
    public static class Geo{
        private String lat;
        private String lng;

        public Geo(){}

        public Geo(String lat, String lng) {
            this.lat = lat;
            this.lng = lng;
        }
    }
}
