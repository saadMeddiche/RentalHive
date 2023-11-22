module stockManagement {
    exports com.rentalhive.stockManagement.helpers;
    exports com.rentalhive.stockManagement.repositories;
    exports com.rentalhive.stockManagement.exceptions.handlers;
    exports com.rentalhive.stockManagement.embeddables;
    exports com.rentalhive.stockManagement.services.helpers;
    exports com.rentalhive.stockManagement.entities;
    exports com.rentalhive.stockManagement;
    exports com.rentalhive.stockManagement.services;
    exports com.rentalhive.stockManagement.exceptions.interfaces;
    exports com.rentalhive.stockManagement.exceptions.factories;
    exports com.rentalhive.stockManagement.services.impls;
    exports com.rentalhive.stockManagement.controllers;
    exports com.rentalhive.stockManagement.exceptions.costums;

    requires java.persistence;
    requires java.validation;
    requires lombok;
    requires org.apache.tomcat.embed.core;
    requires org.slf4j;
    requires spring.beans;
    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.context;
    requires spring.core;
    requires spring.data.commons;
    requires spring.data.jpa;
    requires spring.security.crypto;
    requires spring.web;
    requires modelmapper;
}
