package com.ivan.Order_Management_System.auth.util;

import com.ivan.Order_Management_System.auth.model.Role;
import com.ivan.Order_Management_System.auth.repository.RoleRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataInitializer {

    @Autowired
    private RoleRepository roleRepository;

    @PostConstruct
    public void initRoles() {
        if (roleRepository.count() == 0) {
            Role admin = new Role();
            admin.setName("ADMIN");

            Role customer = new Role();
            customer.setName("CUSTOMER");

            Role manager = new Role();
            manager.setName("MANAGER");

            roleRepository.saveAll(List.of(admin, customer, manager));

        }
    }
}