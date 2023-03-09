package ssafy.teammaker.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ssafy.teammaker.service.LotteryService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/lottery")
public class LotteryController {

    private final LotteryService lotteryService;

    @GetMapping
    public String temp(Model model) {
        List<Double> rates = lotteryService.getRate(28);
        model.addAttribute("rates", rates);
        return "lottery";
    }
}
