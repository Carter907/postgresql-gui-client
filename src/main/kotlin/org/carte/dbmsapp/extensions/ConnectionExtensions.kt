package org.carte.dbmsapp.extensions

import java.sql.Connection

fun Connection.getInfo() =
    """
            client-info: { $clientInfo } 
            schema: { $schema }  
        """.trimIndent()
