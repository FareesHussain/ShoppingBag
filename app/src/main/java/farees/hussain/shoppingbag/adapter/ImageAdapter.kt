package farees.hussain.shoppingbag.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import farees.hussain.shoppingbag.R
import kotlinx.android.synthetic.main.item_image.view.*
import javax.inject.Inject

class ImageAdapter @Inject constructor(
    private val glide: RequestManager
) : RecyclerView.Adapter<ImageAdapter.ImageViewHolder>(){

    private val diffCallback = object : DiffUtil.ItemCallback<String>(){
        override fun areItemsTheSame(oldItem: String, newItem: String) = oldItem == newItem

        override fun areContentsTheSame(oldItem: String, newItem: String)= oldItem == newItem
    }

    private val differ = AsyncListDiffer(this,diffCallback)
    var images: List<String>
    get() = differ.currentList
    set(value) = differ.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ImageViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_image,
                parent,
                false
            )
        )

    private var onItemClickListner:((String)->Unit)?=null

    fun setOnItemClickListner(listner: (String) -> Unit){
        onItemClickListner = listner
    }


    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val url = images[position]
        holder.itemView.apply {
            glide.load(url).into(ivShoppingImage)
            setOnClickListener {
                onItemClickListner?.let {click->
                    click(url)
                }
            }
        }
    }

    override fun getItemCount() = images.size

    class ImageViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)
}