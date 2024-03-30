package kz.edu.sdu.dogs

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import kz.edu.sdu.dogs.db.Database
import java.awt.Desktop
import java.io.File
import java.net.URI

actual class DriverFactory {
    actual fun createDriver(): SqlDriver {
        val dir = getLocalAppDataDirectory()
        val driver: SqlDriver =
            JdbcSqliteDriver("jdbc:sqlite:$dir/dogs.db")
        Database.Schema.create(driver)
        return driver
    }
}

fun getLocalAppDataDirectory(): String {
    val path: String = if (System.getProperty("os.name").uppercase().contains("WIN")) {
        System.getenv("LOCALAPPDATA")
    } else {
        System.getProperty("user.home").concatOrOther("/Library/Application Support")
    }
    val directoryName = path.concatOrOther("/Dogs")
    val directory = File(directoryName)
    if (!directory.exists()) {
        directory.mkdir().let {
            if (!it) {
                return path
            }
        }
    }
    return directoryName
}

infix fun String.concatOrOther(other: String): String {
    return if (this.isEmpty() || this == "0")
        other
    else
        this + other
}