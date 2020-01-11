package kpfu.itis.task2.recycler

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import kotlinx.android.synthetic.main.fragment_todos.view.*
import kpfu.itis.task2.utils.TodoListDiffUtilCallback
import kpfu.itis.task2.model.entity.Todo
import kpfu.itis.task2.recycler.TodoHolder.Companion.KEY_DATE
import kpfu.itis.task2.recycler.TodoHolder.Companion.KEY_DESCRIPTION
import kpfu.itis.task2.recycler.TodoHolder.Companion.KEY_TITLE
import kpfu.itis.task2.utils.HelperService

class TodoAdapter(
    private var dataSource: List<Todo>,
    private val itemClickLambda: (Int) -> Unit,
    private val itemClickDeleteLambda: (Int) -> Unit
): ListAdapter<Todo, TodoHolder>(object : DiffUtil.ItemCallback<Todo>() {

    override fun areItemsTheSame(oldItem: Todo, newItem: Todo): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Todo, newItem: Todo): Boolean = oldItem == newItem

    override fun getChangePayload(oldItem: Todo, newItem: Todo): Any? {
        val diffBundle = Bundle()
        if (oldItem.title !== newItem.title) {
            diffBundle.putString(KEY_TITLE, newItem.title)
        }
        if (oldItem.description !== newItem.description) {
            diffBundle.putString(KEY_DESCRIPTION, newItem.description)
        }
        if (oldItem.date !== newItem.date) {
            diffBundle.putString(KEY_DATE, HelperService.parse(newItem.date))
        }
        return if (diffBundle.isEmpty) null else diffBundle
    }

}) {

    override fun onBindViewHolder(holder: TodoHolder, position: Int) =
        holder.bind(dataSource[position])

    override fun getItemCount(): Int = dataSource.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoHolder =
        TodoHolder.create(
            parent,
            itemClickLambda,
            itemClickDeleteLambda
        )

    override fun onBindViewHolder(
        holder: TodoHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isEmpty())
            super.onBindViewHolder(holder, position, payloads)
        else {
            val bundle = payloads[0] as? Bundle
            holder.updateFromBundle(bundle)
        }
    }

    private fun updateList(newList: List<Todo>) {
        val result = DiffUtil.calculateDiff(
            TodoListDiffUtilCallback(
                dataSource,
                newList
            ), true)
        result.dispatchUpdatesTo(this)
        val temp = dataSource.toMutableList()
        temp.clear()
        temp.addAll(newList)
        dataSource = temp.toList()
    }

    fun delete(list: List<Todo>) {
        updateList(list)
    }

}
