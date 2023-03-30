package com.example.ontapbuoi3.Service;

import com.example.ontapbuoi3.Model.AccountLogin;

import java.util.List;

public interface AccountService {
    List<AccountLogin> getListAcc();

    boolean addAccount(AccountLogin acc);
}
