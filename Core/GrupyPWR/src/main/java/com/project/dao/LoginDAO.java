package com.project.dao;

import java.util.List;
import com.project.data.Prowadzacy;


public interface LoginDAO {

    public void addProwadzacy(Prowadzacy  prowadzacy);
    public List<Prowadzacy> listProwadzacy();
    public  void aktywacja(String Login, boolean Aktywowany);
    public List<Prowadzacy> validateLogin(String login, String haslo);
    public List<Prowadzacy> validateRegister(String imiona, String nazwisko, String email, String login);
}

