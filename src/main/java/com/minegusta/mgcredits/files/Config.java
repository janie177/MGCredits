package com.minegusta.mgcredits.files;

import java.util.UUID;

public class Config {

    //Methods

    public static void setCredits(UUID uuid, int credits)
    {
        CreditModel model = CreditModel.build(uuid);
        model.setCredits(credits);
    }

    public static int getCredits(UUID uuid)
    {
        CreditModel model = CreditModel.build(uuid);
        return model.getCredits();
    }

    public static void addCredits(UUID uuid, int credits)
    {
        CreditModel model = CreditModel.build(uuid);
        model.setCredits(model.getCredits() + credits);
    }

    public static boolean removeCredits(UUID uuid, int credits)
    {
        CreditModel model = CreditModel.build(uuid);
        int old = model.getCredits();
        if(old - credits >= 0)
        {
            model.setCredits(old - credits);
            return true;
        }
        return false;
    }
}
