package th.ac.up.melondev.melonpoke.ui.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import th.ac.up.melondev.melonpoke.R
import java.lang.reflect.Field


class TypeLoadingDialogFragment : DialogFragment() {

    companion object {
        fun newInstance(): TypeLoadingDialogFragment = TypeLoadingDialogFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
		return inflater.inflate(R.layout.progress_bar, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}