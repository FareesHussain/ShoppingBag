package farees.hussain.shoppingbag.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import farees.hussain.shoppingbag.databinding.FragmentShoppingBinding
import farees.hussain.shoppingbag.ui.ShoppingViewModel

class ShoppingFragment : Fragment() {
    private lateinit var binding: FragmentShoppingBinding
    private lateinit var viewModel : ShoppingViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShoppingBinding.inflate(layoutInflater,container,false)
        viewModel = ViewModelProvider(requireActivity()).get(ShoppingViewModel::class.java)

        binding.fabAddShoppingItem.setOnClickListener {
            findNavController().navigate(
                ShoppingFragmentDirections.actionShoppingFragmentToAddShoppingItemFragment()
            )
        }
        return binding.root
    }
}