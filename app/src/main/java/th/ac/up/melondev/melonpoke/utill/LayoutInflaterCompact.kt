package th.ac.up.melondev.melonpoke.utill

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

fun ViewGroup.getViewFromLayoutInflater(resource :Int) :View{

    return LayoutInflater.from(this.context).inflate(
        resource,
        this,
        false
    )
}