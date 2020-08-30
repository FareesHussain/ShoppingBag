package farees.hussain.shoppingbag.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import farees.hussain.shoppingbag.databinding.FragmentAddShoppingItemBinding
import farees.hussain.shoppingbag.databinding.FragmentImagePickBinding
import farees.hussain.shoppingbag.databinding.FragmentShoppingBinding

class ImagePickFragment : Fragment() {
    private lateinit var binding: FragmentImagePickBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentImagePickBinding.inflate(layoutInflater,container,false)


        return binding.root
    }
}