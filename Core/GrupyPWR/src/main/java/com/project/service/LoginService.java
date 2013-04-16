package com.project.service;

import com.project.data.Prowadzacy;
import java.util.List;

public interface LoginService {
    public void addProwadzacy(Prowadzacy prowadzacy);
    public List<Prowadzacy> listProwadzacy();
    public List<Prowadzacy> validateLogin(String login, String haslo);
    public List<Prowadzacy> validateRegister(String imiona, String nazwisko, String email, String login);
    public  void aktywacja(String login, boolean aktywowany);


}