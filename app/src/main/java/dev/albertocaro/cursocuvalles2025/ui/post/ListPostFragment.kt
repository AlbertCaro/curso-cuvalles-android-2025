package dev.albertocaro.cursocuvalles2025.ui.post

import android.content.Intent
import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import dagger.hilt.android.AndroidEntryPoint
import dev.albertocaro.cursocuvalles2025.R
import dev.albertocaro.cursocuvalles2025.databinding.FragmentListPostBinding
import dev.albertocaro.cursocuvalles2025.domain.models.Post
import javax.inject.Inject

@AndroidEntryPoint
class ListPostFragment : Fragment() {

    private val viewModel: PostViewModel by viewModels()

    lateinit var binding: FragmentListPostBinding

    @Inject
    lateinit var adapter: PostListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        if (viewModel.list.value!!.isEmpty()) {
//            viewModel.list.value!!.add(Post(1, "Post 1", "Bla bla bla bla"))
//            viewModel.list.value!!.add(Post(2, "Post 2", "Bla bla bla bla"))
//            viewModel.list.value!!.add(Post(3, "Post 3", "Bla bla bla bla"))
//            viewModel.list.value!!.add(Post(4, "Post 4", "Bla bla bla bla"))
//        }

        binding = FragmentListPostBinding.inflate(inflater, container, false)

        adapter.setOnItemRemove { post ->
            val builder = AlertDialog.Builder(requireContext())

            builder.setTitle("¿Estás seguro de eliminar?")
                .setMessage("Eliminará el post ${post.title}, esta acción no se puede deshacer.")
                .setNegativeButton("Cancelar") { dialog, which ->
                    dialog.cancel()
                }
                .setPositiveButton("Eliminar") { dialog, which ->
                    viewModel.deletePost(post)
                    viewModel.list.value = viewModel.list.value!!.minus(post)
                    adapter.submitList(viewModel.list.value!!)
                    dialog.dismiss()
                }

            val dialog = builder.create()
            dialog.show()
        }

        adapter.setOnItemClick { post ->
//            Toast.makeText(requireContext(), "Click on: ${post.title}", Toast.LENGTH_SHORT).show()
            val intent = Intent(requireContext(), PostViewActivity::class.java)

            intent.putExtra("postId", post.id)

            startActivity(intent)
        }

        adapter.setOnItemEdit { post ->
            val intent = Intent(requireContext(), PostEditActivity::class.java)

            intent.putExtra("postId", post.id)

            startActivity(intent)
        }

        adapter.list = viewModel.list.value!!
        binding.postList.adapter = adapter

        viewModel.list.observe(viewLifecycleOwner) { newList ->
            adapter.submitList(newList)
            updateListVisibility()
        }

        viewModel.fetchPosts()

        return binding.root
    }

    private fun updateListVisibility() {
        if (viewModel.list.value!!.isEmpty()) {
            binding.postList.visibility = View.GONE
            binding.emptyListMessage.visibility = View.VISIBLE
            return
        }

        binding.postList.visibility = View.VISIBLE
        binding.emptyListMessage.visibility = View.GONE
    }
}