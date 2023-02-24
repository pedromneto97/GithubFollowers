package br.eng.pedro_mendes.github_followers.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import br.eng.pedro_mendes.github_followers.data.models.User
import br.eng.pedro_mendes.github_followers.databinding.FollowerBinding
import br.eng.pedro_mendes.github_followers.ui.core.GlideApp

class MainAdapter(private val followers: ArrayList<User>) : Adapter<MainAdapter.MainViewHolder>() {
    private lateinit var binding: FollowerBinding

    init {
        setHasStableIds(true)
    }

    inner class MainViewHolder(itemView: View) : ViewHolder(itemView) {
        fun bind(user: User) {
            binding.apply {
                textViewUserName.text = user.login
                textViewUrl.text = user.url
                GlideApp.with(imageViewAvatar.context)
                    .load(user.avatarUrl)
                    .into(imageViewAvatar)
            }
        }
    }

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getItemViewType(position: Int): Int = position

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        binding = FollowerBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return MainViewHolder(binding.root)
    }

    override fun getItemCount(): Int = followers.size

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(followers[position])
    }

    fun addData(users: List<User>) {
        followers.addAll(users)
    }
}