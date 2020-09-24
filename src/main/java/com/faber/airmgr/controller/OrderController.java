package com.faber.airmgr.controller;

import com.faber.airmgr.models.FlightOrderModel;
import com.faber.airmgr.services.impl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    @Autowired
    private OrderServiceImpl orderService;

    @PostMapping("/order")
    public ResponseEntity<String> doOrderNow(@RequestBody FlightOrderModel model) {
        try {
            orderService.doOrder(model);
            return ResponseEntity.ok("OK");
        } catch (RuntimeException ex) {
            return ResponseEntity.ok(ex.getMessage());
        }
    }
}
