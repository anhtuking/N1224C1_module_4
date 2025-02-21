package com.techzen.academy_n1224c1;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class Dictionary {
    private final Map<String, String> dictionary = Map.ofEntries(
            Map.entry("hello", "chao xìn"),
            Map.entry("apple", "quả chuối"),
            Map.entry("black", "màu đen"),
            Map.entry("dog", "con chó")
    );

    @GetMapping("/dictionary")
    public ResponseEntity<String> Dictionary(@RequestParam(defaultValue = "") String word) {
        String translatedWord = dictionary.get(word.trim().toLowerCase());

        if (translatedWord == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(translatedWord);
    }
}
