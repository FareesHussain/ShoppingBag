package farees.hussain.shoppingbag.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import farees.hussain.shoppingbag.databinding.FragmentAddShoppingItemBinding
import farees.hussain.shoppingbag.databinding.FragmentShoppingBinding

class AddShoppingItemFragment : Fragment() {
    private lateinit var binding: FragmentAddShoppingItemBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddShoppingItemBinding.inflate(layoutInflater,container,false)


        return binding.root
    }
}