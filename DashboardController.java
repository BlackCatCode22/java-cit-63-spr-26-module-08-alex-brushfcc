package org.fresnocity.weatherdashboard;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {
    private final WeatherService weatherService;

    public DashboardController(WeatherService weatherService) { this.weatherService = weatherService; }

    @GetMapping("/")
    public String getDashboard(Model model) {
        model.addAttribute("fresno", weatherService.getForecast("36.7378", "-119.7871"));
        model.addAttribute("ny", weatherService.getForecast("40.7128", "-74.0060"));
        return "dashboard";
    }
}