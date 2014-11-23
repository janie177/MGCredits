package com.minegusta.mgcredits.files;

import com.iciql.Db;
import com.iciql.Iciql;
import com.minegusta.mgcredits.Main;

import java.util.UUID;

@Iciql.IQTable(name = "credits")
public class CreditModel {

    //-- Static constructor with fancy lines and dashes --//
    public static CreditModel build(UUID uuid)
    {
        if(exists(uuid))
        {
            return getFor(uuid);
        }

        CreditModel creditModel = new CreditModel();
        creditModel.credits = 0;
        creditModel.minecraftID = uuid.toString();

        creditModel.insert();

        return creditModel;
    }

    public static boolean exists(UUID p)
    {
        return  getFor(p) != null;
    }

    public static CreditModel getFor(UUID playerId) {
        CreditModel alias = new CreditModel();
        Db db = Db.open("jdbc:" + Main.PLUGIN.getConfig().getString("database-url"));
        try {
            return db.from(alias).where(alias.minecraftID).is(playerId.toString()).selectFirst();
        } finally {
            db.close();
        }
    }

    @Iciql.IQColumn(name = "uuid", primaryKey = true, length = 200)
    public String minecraftID;

    @Iciql.IQColumn
    public int credits = 0;

    public int getCredits()
    {
        return credits;
    }

    public void setCredits(int amount)
    {
        credits = amount;
        update();
    }

    public void insert() {
        Db db = Db.open("jdbc:" + Main.PLUGIN.getConfig().getString("database-url"));
        db.insert(this);
        db.close();
    }

    public void update() {
        Db db = Db.open("jdbc:" + Main.PLUGIN.getConfig().getString("database-url"));
        db.update(this);
        db.close();
    }

    public void delete() {
        Db db = Db.open("jdbc:" + Main.PLUGIN.getConfig().getString("database-url"));
        db.delete(this);
        db.close();
    }
}
