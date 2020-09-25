package farees.hussain.shoppingbag.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import farees.hussain.shoppingbag.databinding.FragmentShoppingBinding

class ShoppingFragment : Fragment() {
    private lateinit var binding: FragmentShoppingBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShoppingBinding.inflate(layoutInflater,container,false)


        return binding.root
    }
}