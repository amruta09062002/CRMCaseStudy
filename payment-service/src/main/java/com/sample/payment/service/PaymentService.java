package com.sample.payment.service;

import com.razorpay.*;
import com.sample.payment.entity.PaymentRequest;
import com.sample.payment.entity.PaymentResponse;
import com.sample.payment.entity.Transaction;
import com.sample.payment.repository.TransactionRepository;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private final TransactionRepository transactionRepository;

    @Value("${razorpay.key_id}")
    private String keyId;

    @Value("${razorpay.key_secret}")
    private String keySecret;

    public PaymentService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public PaymentResponse processPayment(PaymentRequest request) {
        Transaction txn = new Transaction();
        txn.setUserId(request.getUserId());
        txn.setAmount(request.getAmount());
        txn.setCurrency(request.getCurrency());
        txn.setStatus("PENDING");

        transactionRepository.save(txn);

        try {
            RazorpayClient client = new RazorpayClient(keyId, keySecret);
            JSONObject orderRequest = new JSONObject();
            orderRequest.put("amount", request.getAmount() * 100);
            orderRequest.put("currency", request.getCurrency());
            orderRequest.put("receipt", txn.getId().toString());
            orderRequest.put("payment_capture", 1);

            Order order = client.orders.create(orderRequest);
            txn.setGatewayTransactionId(order.get("id"));
            txn.setStatus("SUCCESS");
        } catch (RazorpayException e) {
            txn.setStatus("FAILED");
        }

        transactionRepository.save(txn);
        return new PaymentResponse(txn.getId(), txn.getStatus(), txn.getGatewayTransactionId());
    }
}

