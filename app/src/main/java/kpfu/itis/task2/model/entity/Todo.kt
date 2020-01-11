package kpfu.itis.task2.model.entity

import androidx.room.*
import java.util.*

@Entity(tableName = "todos")
data class Todo(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    @ColumnInfo(name = "title")
    var title: String,
    @ColumnInfo(name = "description")
    var description: String,
    @ColumnInfo(name = "date")
    var  date: Date?,
    @ColumnInfo(name = "longitude")
    var longitude: Double?,
    @ColumnInfo(name = "latitude")
    var latitude: Double?
)
