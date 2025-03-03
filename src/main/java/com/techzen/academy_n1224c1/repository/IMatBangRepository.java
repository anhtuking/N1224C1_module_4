package com.techzen.academy_n1224c1.repository;

import com.techzen.academy_n1224c1.model.MatBang;

import java.util.List;

public interface IMatBangRepository {
    public List<?> getAll();

    public MatBang save(MatBang matBang);

    public MatBang findById(int id);

    public MatBang delete(int id);
}
