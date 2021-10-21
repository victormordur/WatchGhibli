package com.victormordur.gihbli.app.db

import com.squareup.sqldelight.sqlite.driver.JdbcSqliteDriver
import com.victormordur.gihbli.app.Database
import gihbli.FilmQueries
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class GihbliDbTest {
    private val driver = JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY)

    @Before
    fun setUp() {
        Database.Schema.create(driver)
    }

    @Test
    fun testInsertAndSelectAll() {
        val queries = Database(driver).filmQueries
        queries.insertDefaultRows(2)
        val films = queries.selectAll().executeAsList()
        Assert.assertEquals(films.size, 2)
        Assert.assertEquals(films[0].director, "director1")
        Assert.assertEquals(films[1].director, "director2")
    }

    @Test
    fun testInsertAndSelectById() {
        val queries = Database(driver).filmQueries
        queries.insertDefaultRows(3)
        val film2 = queries.selectById("id2").executeAsOne()
        val film1 = queries.selectById("id1").executeAsOne()
        val film3 = queries.selectById("id3").executeAsOne()
        Assert.assertEquals(film2.title, "title2")
        Assert.assertEquals(film1.imageURL, "imageURL1")
        Assert.assertEquals(film3.description, "description3")
    }

    @Test
    fun testInsertAndDelete() {
        val queries = Database(driver).filmQueries
        queries.insertDefaultRows(3)
        val films = queries.selectAll().executeAsList()
        val film2 = queries.selectById("id2").executeAsOne()
        Assert.assertEquals(films.size, 3)
        Assert.assertEquals(film2.title, "title2")
        queries.delete("id2")
        val updatedFilms = queries.selectAll().executeAsList()
        val deletedFilm2 = queries.selectById("id2").executeAsOneOrNull()
        Assert.assertEquals(updatedFilms.size, 2)
        Assert.assertNull(deletedFilm2)
    }

    @Test
    fun testInsertAndDeleteAll() {
        val queries = Database(driver).filmQueries
        queries.insertDefaultRows(5)
        val films = queries.selectAll().executeAsList()
        Assert.assertEquals(films.size, 5)
        queries.deleteAll()
        val updatedFilms = queries.selectAll().executeAsList()
        Assert.assertTrue(updatedFilms.isEmpty())
    }

    private fun FilmQueries.insertDefaultRows(rows: Int) {
        if (rows > 1) {
            for (i in 1.until(rows + 1)) {
                insert(
                    "id$i",
                    "title$i",
                    "description$i",
                    "date$i",
                    "director$i",
                    "imageURL$i"
                )
            }
        } else {
            throw IllegalArgumentException()
        }
    }
}
