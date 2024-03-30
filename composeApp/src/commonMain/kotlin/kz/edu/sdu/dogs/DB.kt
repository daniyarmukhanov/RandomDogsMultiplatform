package kz.edu.sdu.dogs

import app.cash.sqldelight.db.SqlDriver
import kz.edu.sdu.dogs.db.Database

expect class DriverFactory {
    fun createDriver(): SqlDriver
}

fun getDb(driverFactory: DriverFactory): Database {
    val driver = driverFactory.createDriver()
    return Database(driver)
}