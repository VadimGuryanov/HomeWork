package kpfu.itis.task2.utils

import androidx.recyclerview.widget.DiffUtil
import kpfu.itis.task2.model.entity.Todo

class TodoListDiffUtilCallback(
    private var oldItems: List<Todo>,
    private var newItems: List<Todo>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldItems.size

    override fun getNewListSize(): Int = newItems.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldItems[oldItemPosition].title == newItems[newItemPosition].title

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldItems[oldItemPosition] == newItems[newItemPosition]

}
