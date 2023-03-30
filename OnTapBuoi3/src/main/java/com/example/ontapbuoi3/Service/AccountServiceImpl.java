package com.example.ontapbuoi3.Service;

import com.example.ontapbuoi3.Model.AccountLogin;
import com.example.ontapbuoi3.Model.Store;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService{
    public static List<AccountLogin> listAcc = Store.getListAcc();

    @Override
    public List<AccountLogin> getListAcc() {
        return listAcc;
    }

    @Override
    public boolean addAccount(AccountLogin acc) {
        for(AccountLogin accountLogin : listAcc) {
            if(accountLogin.getUsername().compareTo(acc.getUsername()) == 0 || accountLogin.getEmail().compareTo(acc.getEmail()) == 0) {
                return false;
            }
        }
        acc.setId(listAcc.size() + 1);
        listAcc.add(acc);
        return true;
    }
}
