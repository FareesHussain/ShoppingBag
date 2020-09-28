package farees.hussain.shoppingbag.ui.fragments

import android.icu.lang.UCharacter.IndicPositionalCategory.LEFT
import android.icu.lang.UCharacter.IndicPositionalCategory.RIGHT
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import farees.hussain.shoppingbag.R
import farees.hussain.shoppingbag.adapter.ShoppingItemAdapter
import farees.hussain.shoppingbag.databinding.FragmentShoppingBinding
import farees.hussain.shoppingbag.ui.ShoppingViewModel
import javax.inject.Inject

class ShoppingFragment @Inject constructor(
    val shoppingItemAdapter: ShoppingItemAdapter,
    var viewModel : ShoppingViewModel ?= null
) : Fragment() {
    private lateinit var binding: FragmentShoppingBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShoppingBinding.inflate(layoutInflater,container,false)
        viewModel = viewModel ?: ViewModelProvider(requireActivity()).get(ShoppingViewModel::class.java)
        subscribeToObservers()
        setUpRecyclerView()

        binding.fabAddShoppingItem.setOnClickListener {
            findNavController().navigate(
                ShoppingFragmentDirections.actionShoppingFragmentToAddShoppingItemFragment()
            )
        }
        return binding.root
    }

    private val itemTouchCallback = object : ItemTouchHelper.SimpleCallback(
        0,LEFT or RIGHT
    ) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ) = true

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val pos = viewHolder.layoutPosition
            val item = shoppingItemAdapter.shoppingItems[pos]
            viewModel?.deleteShoppingItem(item)
            Snackbar.make(requireView(),"Successfully deleted item",Snackbar.LENGTH_LONG).apply {
                setAction("Undo"){
                    viewModel?.insertShoppingItemIntoDb(item)
                }
                show()
            }
        }
    }

    private fun subscribeToObservers(){
        viewModel?.shoppingItems?.observe(viewLifecycleOwner, Observer {
            shoppingItemAdapter.shoppingItems = it
        })
        viewModel?.totalPrice?.observe(viewLifecycleOwner, Observer {
            val price = it?: 0f
            val priceText = "Total Price : $price"
            binding.tvShoppingItemPrice.text = priceText
        })
    }

    private fun setUpRecyclerView(){
        binding.rvShoppingItems.apply {
            adapter = shoppingItemAdapter
            layoutManager = LinearLayoutManager(requireContext())
            ItemTouchHelper(itemTouchCallback).attachToRecyclerView(this)
        }
    }
}