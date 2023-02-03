package com.calculator.Controller;

import com.calculator.Service.computeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/calculate")
public class computeController {

    @Autowired
    private computeService computeService;

    @PostMapping("/expression")
    public String computeExpression(@RequestBody String expression){
        return computeService.computeExpression(expression);
    }
}
