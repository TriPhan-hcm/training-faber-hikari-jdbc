package com.faber.airmgr.controller;

import com.faber.airmgr.models.FlightFilterModel;
import com.faber.airmgr.services.impl.AirPortServiceImpl;
import com.faber.airmgr.services.impl.FlightServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import java.util.HashMap;
import java.util.Map;
import org.springframework.ui.Model;

@Slf4j
@Controller
public class FlightController  {
    @Autowired
    private  AirPortServiceImpl airPortService;
    @Autowired
    private  FlightServiceImpl flightService;

    private Map baseViewModel(FlightFilterModel filter) {
        return new HashMap<String, Object>() {
            {
                put("results", null);
                put("ports", airPortService.findAll());
                put("filter", filter == null ? new FlightFilterModel() : filter);
            }
        };
    }
    
    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public ModelAndView filter() {
        return new ModelAndView("flight/search", baseViewModel(null));
    }
    
    @RequestMapping(value = {"/"}, method = RequestMethod.POST)
    public ModelAndView doFilter(@ModelAttribute FlightFilterModel model) {
        Map data = baseViewModel(model);
        data.put("results", flightService.filter(model));
        return new ModelAndView("flight/search", data);
    }
    @RequestMapping(value={"/"}, method = RequestMethod.GET)
    public String loginPage(Model model){
        return "loginPage";
    }
}
