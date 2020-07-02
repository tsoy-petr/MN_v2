package com.android.hootr.hogwartslibrary.ui.scenes.spells

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.hootr.hogwartslibrary.R
import com.android.hootr.hogwartslibrary.ui.scenes.spells.adapters.SpellAdapter
import kotlinx.android.synthetic.main.fragment_spells.*

class SpellsFragment : Fragment() {

    private lateinit var spellsViewModel: SpellsViewModel
    private val mAdapter = SpellAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        spellsViewModel =
            ViewModelProvider(this)[SpellsViewModel::class.java]

        return inflater.inflate(R.layout.fragment_spells, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configureRcycler()
        configureDataDisplay()

        btnSpellsCharm.setOnClickListener {
            it.isSelected = !it.isSelected
            spellsViewModel.pressFilter("Charm", it.isSelected )
        }
        btnSpellsSpell.setOnClickListener {
            it.isSelected = !it.isSelected
            spellsViewModel.pressFilter("Spell", it.isSelected )
        }
        btnSpellsJinx.setOnClickListener {
            it.isSelected = !it.isSelected
            spellsViewModel.pressFilter("Jinx", it.isSelected )
        }
        btnSpellsCurse.setOnClickListener {
            it.isSelected = !it.isSelected
            spellsViewModel.pressFilter("Curse", it.isSelected )
        }
    }

    private fun configureDataDisplay() {

        spellsViewModel.spellsDisplay.observe(viewLifecycleOwner, Observer {data ->
            mAdapter.setData(newData = data)
        })

    }

    private fun configureRcycler() {

        recyclerSpells.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerSpells.adapter = mAdapter
    }
}
