package com.example.ontapbuoi3.Model;

import java.util.ArrayList;
import java.util.List;

public class Store {
    public static List<AccountLogin> listAcc = new ArrayList<>();

    static {
        listAcc.add(new AccountLogin("kienpro123", "kien1234", "kienjqk@gmail.com"));
        listAcc.add(new AccountLogin("laivandu", "ac668866", "lockdoor.out@gmail.com"));
        listAcc.add(new AccountLogin("duathommm", "12345566", "duangot123@gmail.com"));
        listAcc.add(new AccountLogin("dituvenha", "phapdanhthinhtu", "chuamotcot.sap@gmail.com"));
    }

    public static List<AccountLogin> getListAcc() {
        return listAcc;
    }

    public static void setListAcc(List<AccountLogin> listAcc) {
        Store.listAcc = listAcc;
    }
}
