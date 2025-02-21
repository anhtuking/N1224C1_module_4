package com.techzen.academy_n1224c1;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Calculate {
    @GetMapping("/calculator")
    public ResponseEntity<String> calculate(@RequestParam(value = "firstNum", defaultValue = "") String firstNumStr,
                                            @RequestParam(value = "secondNum", defaultValue = "") String secondNumStr,
                                            @RequestParam(value = "operator", defaultValue = "") String operator) {

        if (firstNumStr.isEmpty() || secondNumStr.isEmpty() || operator.isEmpty()) {
            return ResponseEntity.badRequest().body("Missing value!");
        } else if (!isDouble(firstNumStr)) {
            return ResponseEntity.badRequest().body("First number is not a number!");
        } else if (!isDouble(secondNumStr)) {
            return ResponseEntity.badRequest().body("Second number is not a number!");
        }

        double firstNum = Double.parseDouble(firstNumStr);
        double secondNum = Double.parseDouble(secondNumStr);
        double result;

        switch (operator) {
            case "+":
                result = firstNum + secondNum;
                break;
            case "-":
                result = firstNum - secondNum;
                break;
            case "*":
                result = firstNum * secondNum;
                break;
            case "/":
                if (secondNum == 0) {
                    return ResponseEntity.badRequest().body("Division by zero is not allowed!");
                }
                result = firstNum / secondNum;
                break;
            default:
                return ResponseEntity.badRequest().body("Invalid operator: " + operator);
        }
        return ResponseEntity.ok(String.valueOf(result));
    }

    private boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}