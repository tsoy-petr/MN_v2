package com.android.hootr.hogwartslibrary.ui.scenes.spells

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.hootr.hogwartslibrary.ui.scenes.spells.adapters.SpellCellModel

class SpellsViewModel : ViewModel() {

    private val _spells = MutableLiveData<MutableList<SpellCellModel>>().apply {
        value = mutableListOf(
            SpellCellModel("Diffindo", "Charm"),
            SpellCellModel("Vingardio Leviosa", "Spell"),
            SpellCellModel("Avada Kedavra", "Curse"),
            SpellCellModel("Oblivios", "Jinx")
        )
    }

    private val filters = MutableLiveData<MutableList<String>>().apply {
        value = mutableListOf()
    }

    private val _spellsDisplay = MutableLiveData<MutableList<SpellCellModel>>().apply {
        value = ArrayList()
    }

    val spellsDisplay: LiveData<MutableList<SpellCellModel>> = _spellsDisplay

    init {
        _spellsDisplay.postValue(
            _spells.value ?: ArrayList()
        )
    }

    fun pressFilter(type: String, isSelected: Boolean) {

        if (isSelected) {
            filters.value?.add(type)
        } else {
            filters.value?.remove(type)
        }

        if (filters.value?.isEmpty() == true){
            _spellsDisplay.postValue(
                _spells.value ?: ArrayList()
            )
            return
        }
        _spellsDisplay.postValue(_spells.value?.filter {
            filters.value?.contains(it.type) ?: false
        }?.toMutableList())
    }


}
