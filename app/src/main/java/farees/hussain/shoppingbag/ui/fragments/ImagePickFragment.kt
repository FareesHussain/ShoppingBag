package farees.hussain.shoppingbag.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import farees.hussain.shoppingbag.databinding.FragmentAddShoppingItemBinding
import farees.hussain.shoppingbag.databinding.FragmentImagePickBinding
import farees.hussain.shoppingbag.databinding.FragmentShoppingBinding
import farees.hussain.shoppingbag.ui.ShoppingViewModel

class ImagePickFragment : Fragment() {
    private lateinit var binding: FragmentImagePickBinding
    private lateinit var viewModel : ShoppingViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentImagePickBinding.inflate(layoutInflater,container,false)
        viewModel = ViewModelProvider(requireActivity()).get(ShoppingViewModel::class.java)


        return binding.root
    }
}