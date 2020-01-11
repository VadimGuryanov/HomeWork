package kpfu.itis.task2.fragments

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.fragment_todos.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kpfu.itis.task2.R
import kpfu.itis.task2.activity.MainActivity
import kpfu.itis.task2.interfaces.INavigator
import kpfu.itis.task2.model.AppDatabase
import kpfu.itis.task2.model.entity.Todo
import kpfu.itis.task2.recycler.TodoAdapter


class TodosFragment : Fragment(), CoroutineScope by MainScope(){

    private lateinit var navigator: INavigator
    private lateinit var adapterTodo: TodoAdapter
    private lateinit var db: AppDatabase
    private lateinit var todos: List<Todo>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_todos, container, false)
        activity?.title = getString(R.string.todo)
        val toolbar: Toolbar = view.findViewById(R.id.toolbar_todos)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        navigator = activity as MainActivity
        db = AppDatabase(view.context)
        todos = ArrayList()
        launch {
            todos = db.todoDao().getTodos()
            if (todos.isNotEmpty()) {
                isEmptyList(false)
                adapterTodo = TodoAdapter(todos, {
                    navigateTo(AddOrEditFragment.newInstance(it))
                }) {
                    deleteTodo(it)
                }
                rv_todos.apply {
                    layoutManager = GridLayoutManager(view.context, 2)
                    adapter = adapterTodo
                }
            } else {
                isEmptyList(true)
            }
        }
        fab_todo.setOnClickListener {
            navigateTo(AddOrEditFragment.newInstance(-1))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_toolbar_todos, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_delete_all ->  {
                launch {
                    db.todoDao().deleteAllTodos()
                    isEmptyList(true)
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun deleteTodo(id: Int) {
        launch {
            db.todoDao().deleteTodo(id)
            todos = db.todoDao().getTodos()
        }
        if (todos.isEmpty()) isEmptyList(true)
        adapterTodo.delete(todos)
    }

    private fun navigateTo(fragment: Fragment) {
        navigator.navigateTo(fragment)
    }

    private fun isEmptyList(isEmpty: Boolean) {
        if (isEmpty) {
            rv_todos.visibility = View.INVISIBLE
            tv_todo_nothing.visibility = View.VISIBLE
        } else {
            rv_todos.visibility = View.VISIBLE
            tv_todo_nothing.visibility = View.INVISIBLE
        }
    }

    companion object {
        fun newInstance(): TodosFragment =
            TodosFragment()
    }

}
