package com.faber.airmgr.controller.administrator;

import com.faber.airmgr.models.AddFlightModel;
import com.faber.airmgr.services.impl.AirPortServiceImpl;
import com.faber.airmgr.services.impl.FlightServiceImpl;
import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.PathVariable;

@Controller("flightsManagement")
public class FlightController {

    @Autowired
    private FlightServiceImpl flightService;
    @Autowired
    private AirPortServiceImpl airPortService;

    
    @GetMapping("/admin/flights")
    public String findAll(Model model) {
        model.addAttribute("flights", flightService.findAll());
        return "admin/flight/list_flight";
    }
    
    @GetMapping("/admin/flights/add")
    public ModelAndView add() {
        Map model = new HashMap<String, Object>() {
            {
                put("flight", new AddFlightModel());
                put("ports", airPortService.findAll());
                put("error", null);
            }
        };

        return new ModelAndView("admin/flight/addFlight", model);
    }

   
    @PostMapping("/admin/flights/add")
    public ModelAndView doAdd(@ModelAttribute AddFlightModel model) {
        try {
            flightService.add(model);
            return new ModelAndView("redirect:/admin/flights");
        } catch (Exception ex) {
            Map data = new HashMap<String, Object>() {
                {
                    put("flight", model);
                    put("ports", airPortService.findAll());
                    put("error", ex.getMessage());
                }
            };
            return new ModelAndView("admin/flight/addFlight", data);
        }
    }

    @GetMapping("/admin/flight/edit/{id}")
    public ModelAndView edit(@PathVariable Long id) throws SQLException {
        Map model = new HashMap<String, Object>() {
            {
                put("flight", flightService.findById(id));
                put("ports", airPortService.findAll());
                put("error", null);
            }
        };

        return new ModelAndView("admin/flight/editFlight", model);
    }

    
    @PostMapping("/admin/flight/edit")
    public ModelAndView doEdit(@ModelAttribute AddFlightModel model) {
        try {
            flightService.update(model);
            return new ModelAndView("redirect:/admin/flights");
        } catch (Exception ex) {
            return new ModelAndView("admin/flight/editFlight");
        }
    }

    @GetMapping("/admin/flight/delete/{id}")
    public ModelAndView delete(@PathVariable Long id) throws SQLException {
        Map model = new HashMap<String, Object>() {
            {
                put("flight", flightService.findById(id));
                put("ports", airPortService.findAll());
                put("error", null);
            }
        };

        return new ModelAndView("admin/flight/deleteFlight", model);
    }

    @PostMapping("/admin/flight/delete")
    public ModelAndView doDelete(Long id) {
        try {
            flightService.delete(id);
            return new ModelAndView("redirect:/admin/flights");
        } catch (Exception ex) {
            return new ModelAndView("admin/flight/deleteFlight");
        }
    }

    @GetMapping("/admin/flights/clear")
    public String clearCache() {
       flightService.clearCache();
       return "redirect:/admin/flights";
    }
}
