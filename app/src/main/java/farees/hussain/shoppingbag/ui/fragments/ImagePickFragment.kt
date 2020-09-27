package farees.hussain.shoppingbag.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import farees.hussain.shoppingbag.adapter.ImageAdapter
import farees.hussain.shoppingbag.databinding.FragmentAddShoppingItemBinding
import farees.hussain.shoppingbag.databinding.FragmentImagePickBinding
import farees.hussain.shoppingbag.databinding.FragmentShoppingBinding
import farees.hussain.shoppingbag.other.Constants.GRID_SPAN_COUNT
import farees.hussain.shoppingbag.ui.ShoppingViewModel
import kotlinx.android.synthetic.main.fragment_image_pick.*
import javax.inject.Inject

class ImagePickFragment @Inject constructor(
    val imageAdapter: ImageAdapter
) : Fragment() {
    private lateinit var binding: FragmentImagePickBinding
    lateinit var viewModel : ShoppingViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentImagePickBinding.inflate(layoutInflater,container,false)
        viewModel = ViewModelProvider(requireActivity()).get(ShoppingViewModel::class.java)
        setUpRecyclerView()

        imageAdapter.setOnItemClickListner {
            findNavController().popBackStack()
            viewModel.setCurImageUrl(it)
        }

        return binding.root
    }

    private fun setUpRecyclerView(){
        binding.rvImages.apply {
            adapter = imageAdapter
            layoutManager = GridLayoutManager(requireContext(),GRID_SPAN_COUNT)
        }
    }
}