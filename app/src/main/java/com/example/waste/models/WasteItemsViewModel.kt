package com.example.waste.models
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData

class WasteItemsViewModel : ViewModel() {
    private val _items = MutableLiveData<List<PriceWasteItemModel>>()
    val items: LiveData<List<PriceWasteItemModel>> get() = _items

    init {
        // Initialize the _items LiveData with your data
        _items.value = listOf(/* Initialize with your data */)
    }

    // Function to update the quantity of an item
    fun updateQuantity(item: PriceWasteItemModel, newQuantity: Int) {
        val updatedItems = _items.value?.toMutableList() ?: mutableListOf()
        val itemIndex = updatedItems.indexOf(item)

        if (itemIndex != -1) {
            updatedItems[itemIndex].addItems = newQuantity.toString()
            _items.value = updatedItems
        }
    }
}
