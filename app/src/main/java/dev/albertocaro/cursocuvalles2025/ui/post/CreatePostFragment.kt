package dev.albertocaro.cursocuvalles2025.ui.post

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import dev.albertocaro.cursocuvalles2025.databinding.FragmentCreatePostBinding
import javax.inject.Inject

@AndroidEntryPoint
class CreatePostFragment : Fragment() {

    private val viewModel: PostViewModel by viewModels()

    private lateinit var binding: FragmentCreatePostBinding

    @Inject
    lateinit var validateForm: PostFormValidateHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCreatePostBinding.inflate(inflater, container, false)

        validateForm(binding.form) { title, content ->
            viewModel.createPost(title, content)

            Snackbar.make(binding.root, "Post guardado con éxito", 2000).show()
        }

        return binding.root
    }
}