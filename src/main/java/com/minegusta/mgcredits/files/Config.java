package com.minegusta.mgcredits.files;

import java.util.UUID;

public class Config {

    //Methods

    public void setCredits(UUID uuid, int credits)
    {
        CreditModel model = CreditModel.build(uuid);
        model.setCredits(credits);
    }

    public int getCredits(UUID uuid)
    {
        CreditModel model = CreditModel.build(uuid);
        return model.getCredits();
    }

    public void addCredits(UUID uuid, int credits)
    {
        CreditModel model = CreditModel.build(uuid);
        model.setCredits(model.getCredits() + credits);
    }

    public boolean removeCredits(UUID uuid, int credits)
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
