package th.ac.up.melondev.melonpoke.utill

import android.content.Context
import android.util.DisplayMetrics

class Calculator {
    companion object {
        fun calculateNoOfColumns(
            context: Context,
            columnWidthDp: Float
        ): Int { // For example columnWidthdp=180
            val displayMetrics: DisplayMetrics = context.getResources().getDisplayMetrics()
            val screenWidthDp = displayMetrics.widthPixels / displayMetrics.density
            return (screenWidthDp / columnWidthDp + 0.5).toInt()
        }
    }
}