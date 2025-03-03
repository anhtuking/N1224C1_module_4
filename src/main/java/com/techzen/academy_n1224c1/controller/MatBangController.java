package com.techzen.academy_n1224c1.controller;

import com.techzen.academy_n1224c1.dto.JsonResponse;
import com.techzen.academy_n1224c1.model.MatBang;
import com.techzen.academy_n1224c1.service.IMatBangService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/matbangs")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MatBangController {

    IMatBangService matBangService;

    @GetMapping()
    private ResponseEntity<?> getAll() {
        return JsonResponse.ok(matBangService.getAll());
    }

    @PostMapping()
    private ResponseEntity<?> add(@RequestBody MatBang matBang) {
        return JsonResponse.created(matBangService.save(matBang));
    }
    @GetMapping("/{id}")
    private ResponseEntity<?> findById(@PathVariable("id") int id) {
        return JsonResponse.ok(matBangService.findById(id));
    }
    @DeleteMapping("/{id}")
    private ResponseEntity<?> deleteById(@PathVariable("id") int id) {
        matBangService.delete(id);
        return JsonResponse.noContent();
    }

}
