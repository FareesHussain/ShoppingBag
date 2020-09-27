package farees.hussain.shoppingbag.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import farees.hussain.shoppingbag.databinding.FragmentAddShoppingItemBinding
import farees.hussain.shoppingbag.databinding.FragmentShoppingBinding
import farees.hussain.shoppingbag.ui.ShoppingViewModel
import kotlinx.android.synthetic.main.fragment_add_shopping_item.*

class AddShoppingItemFragment : Fragment() {
    private lateinit var binding: FragmentAddShoppingItemBinding
    private lateinit var viewModel : ShoppingViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddShoppingItemBinding.inflate(layoutInflater,container,false)
        viewModel = ViewModelProvider(requireActivity()).get(ShoppingViewModel::class.java)
        binding.ivShoppingImage.setOnClickListener {
            findNavController().navigate(
                AddShoppingItemFragmentDirections.actionAddShoppingItemFragmentToImagePickFragment()
            )
        }

        val callBack = object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                viewModel.setCurImageUrl("")
                findNavController().popBackStack()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callBack)

        return binding.root
    }
}