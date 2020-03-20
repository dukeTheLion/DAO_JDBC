package model.dao;

import model.entities.Control;

import java.util.List;

public interface ControlDAO {
    void cardUpdate(Control obj);
    List<Control> findById(Long id);
    List<Control> findByName(String name, String lastName);
    List<Control> findByNameYMD(String date, String num, String name, String lastName);
}
