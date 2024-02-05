package com.budgettracker.api.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorController {

    @RequestMapping("/error")
    public String handleError() {
        // Özel hata sayfasına yönlendir
        return "error";
    }
}
