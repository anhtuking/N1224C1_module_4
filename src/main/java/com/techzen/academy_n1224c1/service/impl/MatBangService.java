package com.techzen.academy_n1224c1.service.impl;

import com.techzen.academy_n1224c1.model.MatBang;
import com.techzen.academy_n1224c1.repository.IMatBangRepository;
import com.techzen.academy_n1224c1.service.IMatBangService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MatBangService implements IMatBangService {
    IMatBangRepository matBangRepository;

    public List<?> getAll() {
        return matBangRepository.getAll();
    }

    public MatBang save(MatBang matBang) {
        return matBangRepository.save(matBang);
    }

    public MatBang findById(int id) {
        return matBangRepository.findById(id);
    }

    public MatBang delete(int id) {
        return matBangRepository.delete(id);
    }
}