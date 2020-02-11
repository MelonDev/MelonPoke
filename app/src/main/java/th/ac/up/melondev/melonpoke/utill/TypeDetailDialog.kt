package th.ac.up.melondev.melonpoke.utill

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import androidx.core.content.res.ResourcesCompat
import kotlinx.android.synthetic.main.detail_type_dialog.view.*
import th.ac.up.melondev.melonpoke.R

class TypeDetailDialog {

    private lateinit var dialog: Dialog

    fun show(context: Context): Dialog {
        return show(context, null)
    }

    fun show(context: Context, title:CharSequence?): Dialog {
        val inflator = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflator.inflate(R.layout.detail_type_dialog, null)
        if (title != null) {
            //view.cp_title.text = title
        }
        //view.cp_bg_view.setBackgroundColor(Color.parseColor("#60000000")) //Background Color
        //view.cp_cardview.setCardBackgroundColor(Color.parseColor("#70000000")) //Box Color
        //view.cp_title.setTextColor(Color.WHITE) //Text Color

        view.detail_type_dialog_back.setOnClickListener {
            dialog.hide()
        }


        dialog = Dialog(context, R.style.CustomProgressBarTheme)
        dialog.setContentView(view)
        dialog.window?.attributes?.windowAnimations = R.style.DialogAnimation

        dialog.show()

        return dialog
    }

}