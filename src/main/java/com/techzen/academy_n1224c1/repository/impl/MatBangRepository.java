package com.techzen.academy_n1224c1.repository.impl;

import com.techzen.academy_n1224c1.dto.ApiException;
import com.techzen.academy_n1224c1.exception.ErrorCode;
import com.techzen.academy_n1224c1.model.MatBang;

import com.techzen.academy_n1224c1.repository.IMatBangRepository;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MatBangRepository implements IMatBangRepository {


    public MatBang findById(int id) {
        try {
            PreparedStatement ps = BaseRepository.getConnection().prepareStatement("SELECT * FROM MatBang WHERE maMatBang = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return MatBang.builder()
                        .maMatBang(rs.getInt("maMatBang"))
                        .tenMatBang(rs.getString("tenMatBang"))
                        .diaChi(rs.getString("diaChi"))
                        .dienTich(rs.getString("dienTich"))
                        .giaThue(rs.getDouble("giaThue"))
                        .ngayBatDauThue(rs.getDate("ngayBatDau"))
                        .loaiMatBang(rs.getInt("loaiMatBangId"))
                        .build();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        throw new ApiException(ErrorCode.MAT_BANG_NOT_EXIST);
    }

    public List<?> getAll() {
        List<MatBang> matBang = new ArrayList<>();
        try {
            PreparedStatement ps = BaseRepository.getConnection().prepareStatement("select mb.maMatBang,mb.tenMatBang,mb.diaChi,mb.dienTich,mb.loaiMatBangId,l.tenLoai,mb.giaThue,mb.ngayBatDau, l.id " +
                    "from matbang mb join loaimatbang l on l.id = mb.loaiMatBangId");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                matBang.add(MatBang.builder()
                        .maMatBang(rs.getInt("maMatBang"))
                        .tenMatBang(rs.getString("tenMatBang"))
                        .diaChi(rs.getString("diaChi"))
                        .dienTich(rs.getString("dienTich"))
                        .giaThue(rs.getDouble("giaThue"))
                        .ngayBatDauThue(rs.getDate("ngayBatDau"))
                        .loaiMatBang(rs.getInt("loaiMatBangId"))
                        .build());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return matBang;
    }


    public MatBang save(MatBang matBang) {
        try {
            PreparedStatement ps = BaseRepository.getConnection().prepareStatement("INSERT INTO MatBang (tenMatBang,diaChi,dienTich,giaThue,ngayBatDau,loaiMatBangId) values(?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, matBang.getTenMatBang());
            ps.setString(2, matBang.getDiaChi());
            ps.setString(3, matBang.getDienTich());
            ps.setDouble(4, matBang.getGiaThue());
            ps.setNull(5, java.sql.Types.DATE);
            ps.setInt(6, matBang.getLoaiMatBang());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                matBang.setMaMatBang(rs.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        throw new ApiException(ErrorCode.MAT_BANG_NOT_EXIST);
    }

    public MatBang delete(int id) {
        MatBang matBang = findById(id);
        if (matBang != null) {
            try {
                PreparedStatement ps = BaseRepository.getConnection().prepareStatement("delete from MatBang where maMatBang = ?");
                ps.setInt(1, id);
                ps.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return matBang;
        }
        throw new ApiException(ErrorCode.MAT_BANG_NOT_EXIST);
    }

}
