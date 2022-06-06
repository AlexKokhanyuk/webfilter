package ua.kohaniuk.webfilter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Properties;

/**
 * @author Oleksandr Kokhaniuk
 * @created 5/10/2022, 9:04 PM
 */
@Controller
public class WebFilterController {

    @GetMapping("/")
    public String getAccess() {

        return "index";
    }

}
