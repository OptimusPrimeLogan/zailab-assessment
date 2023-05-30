package org.acme.test01.satheesh.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "transaction")
public class Transaction {
    @Id
    private Long id;
    @ManyToOne
    private Account account;
    private Long customerNumber;
    @Enumerated(EnumType.STRING) @Column(name = "type")
    private TransactionEnum type;
}
