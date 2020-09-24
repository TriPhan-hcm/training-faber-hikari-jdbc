package com.faber.airmgr.controller.administrator;
import com.faber.airmgr.services.impl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller("ordersManagement")
public class OrderController {
    @Autowired
    private OrderServiceImpl orderService;

    @GetMapping("/admin/orders")
    public String index(Model model) {
        model.addAttribute("orders", this.orderService.findAll());
        return "admin/order/index";
    }
}
