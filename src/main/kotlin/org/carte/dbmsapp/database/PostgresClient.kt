package org.carte.dbmsapp.database

import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException


class PostgresLoginException(message: String?) : Exception(message);

class PostgresClient(private val postgresDatabaseName: String, private val credentials: ClientCredentials) {

    private val databaseURL = "jdbc:postgresql://localhost:5432/$postgresDatabaseName"


    fun login() {
        try {
            DriverManager.getConnection(
                databaseURL,
                credentials.userName,
                credentials.password
            ).close();
        } catch (e: SQLException) {
            throw PostgresLoginException(e.message);
        }
    }

    fun runQuery(text: String?) {

        DriverManager.getConnection(
            databaseURL,
            credentials.userName,
            credentials.password

        ).use {

            println(it.getInfo())

            it.prepareStatement("""
                SELECT * FROM fruit;
            """.trimIndent()).use { ps ->
                ps.executeQuery().use { rs ->
                    while (rs.next()) {
                        println("""
                            ${rs.getInt(1)}
                            ${rs.getString(2)}
                            ${rs.getString(3)}
                            ${rs.getFloat(4)}
                        """.trimIndent())
                    }
                }
            }


        }

    }

    private fun Connection.getInfo() =
        """
            client-info: { $clientInfo } 
            schema: { $schema }  
        """.trimIndent()


}