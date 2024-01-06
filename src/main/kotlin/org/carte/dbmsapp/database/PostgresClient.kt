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
                credentials.username,
                credentials.password
            ).close();
        } catch (e: SQLException) {
            throw PostgresLoginException(e.message);
        }
    }

    fun runInsert(text: String?) {


        DriverManager.getConnection(
            databaseURL,
            credentials.username,
            credentials.password

        ).use {

            println(it.getInfo())

            it.prepareStatement(text).use { ps ->
                ps.execute()
            }
        }


    }

}

fun runDelete(text: String?) {

}

fun runQuery(text: String?) {
}

private fun Connection.getInfo() =
    """
            client-info: { $clientInfo } 
            schema: { $schema }  
        """.trimIndent()


