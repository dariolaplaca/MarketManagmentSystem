package com.dlp.mms.Entities;

import com.dlp.mms.Auditables.AuditableEntity;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.List;
@EqualsAndHashCode(callSuper = true)
@Entity
@Table
@Data
@AllArgsConstructor
public class Shop extends AuditableEntity<User> {
    @Column
    private String name;
    @Hidden
    @OneToMany
    private List<Account> employeesList;

    public Shop(){
        employeesList = new ArrayList<>();
    }

    public void addAccount(Account account){
        this.employeesList.add(account);
    }
}
