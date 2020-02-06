package th.ac.up.melondev.melonpoke.data.model.api

import android.os.Parcelable
import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.net.URI

@Parcelize
@Keep
data class PokemonURIModel(
    @SerializedName("name") var name: String? = null,
    @SerializedName("url") var url: URI? = null
): Parcelable
