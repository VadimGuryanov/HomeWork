package kpfu.itis.task2.model.dao

import androidx.room.*
import kpfu.itis.task2.model.entity.Todo
import java.util.*

@Dao
interface TodoDao {

    @Query("INSERT INTO todos (title, description, date, longitude, latitude) VALUES(:title, :description, :date, :longitude, :latitude)")
    suspend fun save(title: String, description: String, date: Date?, longitude: Double?, latitude: Double?)

    @Query("SELECT * FROM todos")
    suspend fun getTodos(): List<Todo>

    @Query("SELECT * FROM todos WHERE id = :id")
    suspend fun getTodoById(id: Int): Todo?

    @Query("DELETE FROM todos WHERE id = :id")
    suspend fun deleteTodo(id: Int)

    @Query("UPDATE todos SET title=:title, description=:description, date=:date, longitude=:longitude, latitude=:latitude WHERE id=:id")
    suspend fun updateTodo(id: Int, title: String, description: String, date: Date?, longitude: Double?, latitude: Double?)

    @Query("DELETE FROM todos")
    suspend fun deleteAllTodos()

}
