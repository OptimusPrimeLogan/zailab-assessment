package org.acme.test01.satheesh.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "account")
public class Account {
    @Id
    private Long id;
    @Column(name="customer_number")
    private Long customerNumber;
    private Long balance;
    private Long overDraftBalance;
    @Enumerated(EnumType.STRING) @Column(name = "type")
    private AccountEnum type;
}
