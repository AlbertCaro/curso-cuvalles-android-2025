package dev.albertocaro.cursocuvalles2025.ui.post

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import dev.albertocaro.cursocuvalles2025.databinding.PostListElementBinding
import dev.albertocaro.cursocuvalles2025.domain.models.Post
import javax.inject.Inject

class PostListAdapter @Inject constructor() :
    Adapter<PostListAdapter.ViewHolder>() {

    lateinit var list: List<Post>

    private lateinit var onItemRemove: (Post) -> Unit

    private lateinit var onItemEdit: (Post) -> Unit

    private lateinit var onItemClick: (Post) -> Unit

    class ViewHolder(private val binding: PostListElementBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(post: Post, onItemRemove: (Post) -> Unit, onItemEdit: (Post) -> Unit, onItemClick: (Post) -> Unit) {
            binding.cardTitle.text = post.title
            binding.cardContent.text = post.content

            binding.delete.setOnClickListener {
                onItemRemove(post)
            }

            binding.edit.setOnClickListener {
                onItemEdit(post)
            }

            binding.postContainer.setOnClickListener {
                onItemClick(post)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            PostListElementBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position], onItemRemove, onItemEdit, onItemClick)
    }

    override fun getItemCount(): Int = list.size

    fun submitList(newList: List<Post>) {
        val diffUtil = PostDiffUtil(list, newList)

        val result = DiffUtil.calculateDiff(diffUtil)

        list = newList
//        notifyDataSetChanged()

        result.dispatchUpdatesTo(this)
    }

    fun setOnItemRemove(onItemRemove: (Post) -> Unit) {
        this.onItemRemove = onItemRemove
    }

    fun setOnItemEdit(onItemEdit: (Post) -> Unit) {
        this.onItemEdit = onItemEdit
    }

    fun setOnItemClick(onItemClick: (Post) -> Unit) {
        this.onItemClick = onItemClick
    }

}