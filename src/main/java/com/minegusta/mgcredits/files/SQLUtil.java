package com.minegusta.mgcredits.files;

import java.sql.*;

import com.minegusta.mgcredits.Main;

public class SQLUtil {
	private static final String database = Main.PLUGIN.getConfig().getString("database-name", "minegusta");
	private static final String user = Main.PLUGIN.getConfig().getString("user", "minegusta");
	private static final String pass = Main.PLUGIN.getConfig().getString("password", "lelele");
	private static final String url = Main.PLUGIN.getConfig().getString("database-url", "jdbc:mysql://localhost:3306/");
	private static final String driver = "com.mysql.jdbc.Driver";
	private static final String table = "credits";

	public static void init()
	{
		try {
			Class.forName(driver).newInstance();

			Connection conn = DriverManager.getConnection(url,user,pass);

			String sqlCreateDB = "CREATE DATABASE IF NOT EXISTS " + database;
			String sqlCreate = "CREATE TABLE IF NOT EXISTS " + table
					+ "  (uuid           VARCHAR(40),"
					+ "   credits           INTEGER)";

			Statement stmt = conn.createStatement();
			stmt.execute(sqlCreateDB);
			stmt.execute(sqlCreate);

			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static Connection openDB()
	{
		try {
			Class.forName(driver).newInstance();
			return DriverManager.getConnection(url+database,user,pass);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
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
		Connection conn = openDB();
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
		Connection conn = openDB();
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
