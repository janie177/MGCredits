package com.minegusta.mgcredits.files;

import java.sql.*;

import com.minegusta.mgcredits.Main;

public class CreditsUtil {
	private static final String database = Main.PLUGIN.getConfig().getString("database-name", "minegusta");
	private static final String user = Main.PLUGIN.getConfig().getString("user", "root");
	private static final String pass = Main.PLUGIN.getConfig().getString("password", "");
	private static final String url = Main.PLUGIN.getConfig().getString("database-url", "jdbc:mysql://localhost:3306/");
	private static final String table = "credits";

	public static void init()
	{
		SQLUtil.createDB(user, pass, url, database);
		String tableColumns = "  (uuid           VARCHAR(40), credits           INTEGER)";
		SQLUtil.createTable(user, pass, url, database, table, tableColumns);
	}

	//----------------------------------------------------------------------------------//

	public static boolean addCredits(String uuid, int amount)
	{
		int credits = getCredits(uuid) + amount;
		return setCredits(uuid, credits);
	}

	public static boolean removeCredits(String uuid, int amount)
	{
		int credits = getCredits(uuid) - amount;
		if(credits < 0) return false;
		return setCredits(uuid, credits);
	}


	public static int getCredits(String uuid)
	{
		int credits = 0;
		Connection conn = SQLUtil.openDB(url, database, user, pass);
		if(conn != null)
		{
			try {
				String sqlGetCredits = "SELECT * FROM " + table;
				Statement statement = conn.createStatement();
				ResultSet set = statement.executeQuery(sqlGetCredits);

				while(set.next())
				{
					String name = set.getString("uuid");
					if(name.equalsIgnoreCase(uuid)){
						credits = set.getInt("credits");
						break;
					}
				}
				conn.close();
			} catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		return credits;
	}

	public static boolean setCredits(String uuid, int amount)
	{
		Connection conn = SQLUtil.openDB(url, database, user, pass);
		if(conn != null)
		{
			try {
				String sqlSetCredits = "INSERT INTO " + table + " (" + uuid  + ", " + amount + ")";

				Statement statement = conn.createStatement();
				statement.execute(sqlSetCredits);

				conn.close();
			} catch (SQLException e)
			{
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}

}
