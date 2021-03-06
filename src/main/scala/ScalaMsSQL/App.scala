package ScalaMsSQL

import java.sql.{Connection, DriverManager, ResultSet};
//import java.sql.DriverManager
//import java.sql.Connection
import com.microsoft.sqlserver.jdbc._

/*
	-- Table Schema

	CREATE TABLE [dbo].[TblAccount](
		[account_idx] [bigint] NOT NULL,
		[name] [nvarchar](100) NULL,
		[gold] [int] NULL,
	 CONSTRAINT [PK_TblAccount] PRIMARY KEY NONCLUSTERED 
	(
		[account_idx] ASC
	)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
	) ON [PRIMARY]
	
	GO
 
 */

object App {
	def main(args: Array[String]): Unit = {

		val driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver"
		val url = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=Practice"				
		val username = "sa"
		val password = "asdf1234$"

		var connection: Connection = null

		try {
			// make the connection
			Class.forName(driver)
			connection = DriverManager.getConnection(url, username, password)

			// create the statement, and run the select query
			val statement = connection.createStatement()
			val resultSet = statement.executeQuery("SELECT account_idx, name, gold FROM TblAccount")
			while (resultSet.next()) {
				val accIdx = resultSet.getLong("account_idx")
				val name = resultSet.getString("name")
				val gold = resultSet.getInt("gold")
				println("accIdx, Name, Gold = " + accIdx + ", " + name, ", " + gold)
			}
		} catch {
			case e => e.printStackTrace
		}

		connection.close()
	}
}