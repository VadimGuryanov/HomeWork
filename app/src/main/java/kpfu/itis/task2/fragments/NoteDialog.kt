package kpfu.itis.task2.fragments

import android.app.Dialog
import android.os.Bundle
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import kotlinx.android.synthetic.main.dialog_note.view.*
import kpfu.itis.task2.R
import kpfu.itis.task2.essence.Note

class NoteDialog(private val listener: (Note, String) -> Unit) : DialogFragment() {

    companion object {

        fun show(
            fragmentManager: FragmentManager,
            onPositiveClick: (Note, String) -> (Unit)
        ): NoteDialog =
            NoteDialog(onPositiveClick).apply {
                show(fragmentManager, NoteDialog::class.java.name)
            }
    }

    private var dialogView: View? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_note, null, false)
        val dialog = AlertDialog.Builder(
            ContextThemeWrapper(
                checkNotNull(context),
                R.style.ThemeOverlay_MaterialComponents
            )
        )
            .setTitle("Add note")
            .setPositiveButton("Ok") { _, _ ->
                listener(
                    Note(
                        dialogView?.et_title_new_note?.text.toString(),
                        dialogView?.et_content_new_note?.text.toString()
                    ),
                    dialogView?.et_position_new_note?.text.toString()
                )
            }
            .setNegativeButton("Cancel") { _, _ ->
                dismiss()
            }
            .setView(dialogView)
            .create()
        dialog.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE)
        return dialog
    }

}
