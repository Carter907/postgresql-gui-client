package org.carte.dbmsapp.database

import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException
import java.util.stream.Stream


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

    private fun runInsert(text: String): String {
        var resultText = "insert FAILED";

        useConnectionWithCredentials {

            it.prepareStatement(text).use { ps ->
                if (ps.execute())
                    resultText = "successful insert"
            }
        }
        return resultText;

    }

    private fun runDelete(text: String): String {

        var resultText = "delete FAILED"

        useConnectionWithCredentials {
            it.prepareStatement(text).use { ps ->

                if (ps.execute())
                    resultText = "successful delete"
            }
        }
        return resultText;
    }

    private fun runQuery(text: String): String {

        var resultText = "query FAILED"

        useConnectionWithCredentials {
            it.prepareStatement(text).use { ps ->
                if (ps.execute())
                    resultText = "successful query"
            }
        }
        return resultText
    }

    private fun runCreate(text: String): String {
        var resultText = "insert FAILED"

        useConnectionWithCredentials {
            it.prepareStatement(text).use { ps ->
                if (ps.execute())
                    resultText = "successful insert"
            }
        }
       return resultText
    }

    fun runSQL(text: String): String {
        var resultText = ""
        try {
            text.split(";").stream()
                .map { it.trim() }
                .forEach {
                    val first = it.split(" ")[0];

                    resultText = when (first) {
                        "SELECT" -> runQuery(text)
                        "CREATE" -> runCreate(text)
                        "DELETE" -> runDelete(text)
                        "INSERT" -> runInsert(text)
                        else -> ""
                    }
                }
        } catch (e: SQLException) {
            resultText = e.message ?: ""
        }
        return resultText;
    }

    private fun useConnectionWithCredentials(command: (it: Connection) -> Unit) {
        DriverManager.getConnection(
            databaseURL,
            credentials.username,
            credentials.password

        ).use {
            command(it);
        }
    }
}
