package farees.hussain.shoppingbag.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.RequestManager
import com.google.android.material.snackbar.Snackbar
import farees.hussain.shoppingbag.databinding.FragmentAddShoppingItemBinding
import farees.hussain.shoppingbag.databinding.FragmentShoppingBinding
import farees.hussain.shoppingbag.other.Status
import farees.hussain.shoppingbag.ui.ShoppingViewModel
import kotlinx.android.synthetic.main.fragment_add_shopping_item.*
import javax.inject.Inject

class AddShoppingItemFragment @Inject constructor(
    val glide : RequestManager
) : Fragment() {
    private lateinit var binding: FragmentAddShoppingItemBinding
    lateinit var viewModel : ShoppingViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddShoppingItemBinding.inflate(layoutInflater,container,false)
        viewModel = ViewModelProvider(requireActivity()).get(ShoppingViewModel::class.java)
        subscribeToObservers()

        binding.btnAddShoppingItem.setOnClickListener {
            viewModel.insertShoppingItem(
                binding.etShoppingItemName.text.toString(),
                binding.etShoppingItemAmount.text.toString(),
                binding.etShoppingItemPrice.text.toString()
            )
        }
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

    private fun subscribeToObservers(){
        viewModel.curImgUrl.observe(viewLifecycleOwner, Observer {
            glide.load(it).into(binding.ivShoppingImage)
        })
        viewModel.insertShoppingItemStatus.observe(viewLifecycleOwner, Observer {
            it.getContentIfNotHandled()?.let {result->
                when(result.status){
                    Status.SUCCESS -> {
                        Snackbar.make(
                            binding.root,
                            "Shopping Item Added Successfully",
                            Snackbar.LENGTH_SHORT
                        ).show()
                        findNavController().popBackStack()
                    }
                    Status.ERROR -> {
                        Snackbar.make(
                            binding.root,
                            result.message?:"An Unknown Error Occured",
                            Snackbar.LENGTH_SHORT
                        ).show()
                    }
                    Status.LOADING -> {
                        // no operation
                    }
                }

            }
        })
    }


}