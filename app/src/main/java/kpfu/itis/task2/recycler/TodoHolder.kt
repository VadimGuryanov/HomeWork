package kpfu.itis.task2.recycler

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_todo.view.*
import kpfu.itis.task2.R
import kpfu.itis.task2.model.entity.Todo
import kpfu.itis.task2.utils.HelperService

class TodoHolder (
    override val containerView: View,
    private val click: (Int) -> Unit,
    private val clickDelete: (Int) -> Unit
) : RecyclerView.ViewHolder(containerView), LayoutContainer {


    fun bind(todo: Todo) {
        containerView.apply {
            todo.apply {
                tv_title.text = title
                tv_description.text = description.run {
                    if (length > 54) {
                        substring(0, 50) + "..."
                    } else {
                        this
                    }
                }
                tv_date.text = HelperService.parse(date)
                setOnClickListener {
                    click(id)
                }
                iv_delete.setOnClickListener {
                    clickDelete(id)
                }
            }
        }
    }

    fun updateFromBundle(bundle: Bundle?) {
        containerView.apply {
            bundle?.apply {
                tv_title.text = getString(KEY_TITLE)
                tv_description.text = getString(KEY_DESCRIPTION)
                tv_date.text = getString(KEY_DESCRIPTION)
            }
        }
    }

    companion object {

        const val KEY_TITLE = "title"
        const val KEY_DESCRIPTION = "description"
        const val KEY_DATE = "date"

        fun create(parent: ViewGroup, click: (Int) -> Unit, clickDelete: (Int) -> Unit) =
            TodoHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_todo_card_view,
                    parent,
                    false
                ),
                click,
                clickDelete
            )
    }

}
