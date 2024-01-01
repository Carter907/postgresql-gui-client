package org.carte.dbmsapp.database

import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException


class PostgresLoginException(message: String?) : Exception(message);

class PostgresClient(private val postgresDatabaseName: String, private val credentials: ClientCredentials) {

    private lateinit var databaseConnection: Connection;

    fun login() {
        try {
            databaseConnection = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/$postgresDatabaseName",
                credentials.userName,
                credentials.password

            )
        } catch (e: SQLException) {
            throw PostgresLoginException(e.message);
        }
    }


}