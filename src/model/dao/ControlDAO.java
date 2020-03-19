package model.dao;

import model.entities.Control;

import java.util.List;

public interface ControlDAO {
    void insert(Control obj);
    void update(Control obj);
    List<Control> findById(Integer id);
    List<Control> findByName(String name, String lastName);
    List<Control> findByNameYMD(String date, String num, String name);
}
