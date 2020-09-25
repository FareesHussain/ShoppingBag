package farees.hussain.shoppingbag.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import farees.hussain.shoppingbag.repositories.ShoppingRepository

class ShoppingViewModel @ViewModelInject constructor(
    private val repository: ShoppingRepository,
) :ViewModel() {

}