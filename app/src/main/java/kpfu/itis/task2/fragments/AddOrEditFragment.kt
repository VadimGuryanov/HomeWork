package kpfu.itis.task2.fragments

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_redaction.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kpfu.itis.task2.R
import kpfu.itis.task2.activity.MainActivity
import kpfu.itis.task2.interfaces.INavigator
import kpfu.itis.task2.model.AppDatabase
import kpfu.itis.task2.utils.DataPicker
import kpfu.itis.task2.utils.HelperService

class AddOrEditFragment : Fragment(), CoroutineScope by MainScope() {

    private lateinit var db: AppDatabase
    private lateinit var navigate: INavigator

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_redaction, container, false)
        activity?.title = getString(R.string.todo)
        var toolbar: Toolbar = view.findViewById(R.id.toolbar_edit)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        db = AppDatabase(view.context)
        var id = arguments?.getInt(ARG_ID) ?: -1
        navigate = activity as MainActivity
        if (id != -1) {
            launch {
                db.todoDao().getTodoById(id)?.apply {
                    et_title.setText(title, TextView.BufferType.EDITABLE)
                    et_description.setText(description, TextView.BufferType.EDITABLE)
                    et_longitude.setText(HelperService.roundUp(longitude.toString()) ?: "", TextView.BufferType.EDITABLE)
                    et_latitude.setText(HelperService.roundUp(latitude.toString()) ?: "", TextView.BufferType.EDITABLE)
                    btn_date.text = HelperService.parse(date)
                }
            }
            activity?.title = "Edit"
            btn_save.visibility = View.INVISIBLE
            btn_edit.visibility = View.VISIBLE
        } else {
            activity?.title = "Save"
            btn_save.visibility = View.VISIBLE
            btn_edit.visibility = View.INVISIBLE
            gps(view)
            btn_date.text = HelperService.parse(date = null)
        }
        initListener(id)
    }

    private fun gps(view: View) {
        var l = getLocation((activity as MainActivity).applicationContext)
        if (l !== null) {
            l.apply {
                view.apply {
                    et_longitude.setText(
                        HelperService.roundUp(longitude.toString()),
                        TextView.BufferType.EDITABLE
                    )
                    et_latitude.setText(
                        HelperService.roundUp(latitude.toString()),
                        TextView.BufferType.EDITABLE
                    )
                }
            }
        } else {
            Toast.makeText(view.context,"GPS unable to get value",Toast.LENGTH_SHORT).show()
        }
    }

    private fun getLocation(context: Context): Location? {
        if (ContextCompat.checkSelfPermission( context, Manifest.permission.ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED) {
            return null
        }
        val lm = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val isGPSEnabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER)
        if (isGPSEnabled) {
            val providers: List<String> = lm.getProviders(true)
            var bestLocation: Location? = null
            for (provider in providers) {
                val l: Location = lm.getLastKnownLocation(provider) ?: continue
                if (bestLocation == null || l.accuracy < bestLocation.accuracy) {
                    bestLocation = l
                }
            }
            return bestLocation
        }
        return null
    }

    private fun initListener(id: Int) {
        btn_save.setOnClickListener{
            val title = et_title.text.toString()
            val description = et_description.text.toString()
            val date = HelperService.parse(btn_date.text.toString())
            val longitude = HelperService.roundUp(et_longitude.text.toString())?.toDouble()
            val latitude = HelperService.roundUp(et_latitude.text.toString())?.toDouble()
            launch {
                db.todoDao().save(title, description, date, longitude, latitude)
            }
            activity?.onBackPressed()

        }
        btn_edit.setOnClickListener{
            val title = et_title.text.toString()
            val description = et_description.text.toString()
            val date = HelperService.parse(btn_date.text.toString())
            val longitude = HelperService.roundUp(et_longitude.text.toString())?.toDouble()
            val latitude = HelperService.roundUp(et_latitude.text.toString())?.toDouble()
            launch {
                db.todoDao().updateTodo(id, title, description, date, longitude, latitude)
            }
            activity?.onBackPressed()
        }
        btn_date.apply {
                setOnClickListener{
                DataPicker(it).apply {
                    setDate()
                }
            }
        }
    }

    companion object {

        private const val ARG_ID = "id"

        fun newInstance(id: Int): AddOrEditFragment =
            AddOrEditFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_ID, id)
                }
            }

    }

}
