package com.sample.payment.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import org.springframework.stereotype.Service;

@Entity
@Getter
@Service
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;
    private Double amount;
    private String currency;
    private String status;
    private String gatewayTransactionId;
    private LocalDateTime createdAt = LocalDateTime.now();
    
}
