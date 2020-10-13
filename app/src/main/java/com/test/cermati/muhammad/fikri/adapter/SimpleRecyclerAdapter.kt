package m.test.cermati.muhammad.fikri.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.test.cermati.muhammad.fikri.R
import com.test.cermati.muhammad.fikri.model.User
import com.test.cermati.muhammad.fikri.utils.fromUrl
import com.test.cermati.muhammad.fikri.utils.isNotNull

class SimpleRecyclerAdapter<O>(
    private val context: Context,
    private val tag: SimpleRecyclerTag,
    private var itemList: ArrayList<O>? = null,
    private val onItemClick: (O) -> Unit
) : RecyclerView.Adapter<SimpleRecyclerAdapter<O>.SimpleRecyclerViewHolder>() {

    companion object {
        enum class SimpleRecyclerTag {
            GITHUB_USER_LIST
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleRecyclerViewHolder {
        when (tag) {
            SimpleRecyclerTag.GITHUB_USER_LIST -> {
                val itemView = LayoutInflater.from(context).inflate(R.layout.item_users_github, parent, false)
                if (!itemList.isNullOrEmpty()){
                    if (itemList!!.size > 1){
                        val params : ViewGroup.LayoutParams = itemView.layoutParams
                        params.height = (parent.height * 0.15).toInt()
                        itemView.layoutParams = params
                    }
                }
                return SimpleRecyclerViewHolder(itemView)
            }
        }
    }

    override fun getItemCount(): Int {
        if (!itemList.isNullOrEmpty()) {
            return itemList!!.size
        }
        return 0
    }

    override fun onBindViewHolder(holder: SimpleRecyclerViewHolder, position: Int) {
        if (itemList.isNotNull()) {
            holder.bind(itemList!![position])
        }
    }

    inner class SimpleRecyclerViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        //GITHUB_USER_LIST
        private var userName: TextView? = null
        private var userLogo: ImageView? = null

        init {
            when (tag) {
                SimpleRecyclerTag.GITHUB_USER_LIST -> {
                    userName = view.findViewById(R.id.user_name_igu)
                    userLogo = view.findViewById(R.id.user_logo_igu)
                }
            }
        }

        fun bind(item: O) {
            when (tag) {
                SimpleRecyclerTag.GITHUB_USER_LIST -> {
                    if (item is User) {
                        userName?.text = item.username
                        userLogo?.fromUrl(item.avatarUrl)
                        itemView.setOnClickListener {
                            onItemClick(item)
                        }
                    }
                }
            }
        }
    }
}

