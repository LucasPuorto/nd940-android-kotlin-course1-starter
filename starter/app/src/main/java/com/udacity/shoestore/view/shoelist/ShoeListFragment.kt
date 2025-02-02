package com.udacity.shoestore.view.shoelist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.databinding.ItemShoeBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.viewmodel.MainViewModel

class ShoeListFragment : Fragment() {

    private lateinit var binding: FragmentShoeListBinding
    private val viewModel by activityViewModels<MainViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_list, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_frag_shoe_list, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, findNavController()) || super.onOptionsItemSelected(item)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observables()
        binding.fabAddShoe.setOnClickListener { directionToFragmentShoeDetails() }
    }

    private fun directionToFragmentShoeDetails() {
        findNavController().navigate(ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailsFragment())
    }

    private fun observables() {
        viewModel.shoeListLiveData.observe(viewLifecycleOwner, {
            handlerShoeList(it)
        })
    }

    private fun handlerShoeList(shoeList: MutableList<Shoe>) {
        shoeList.forEach { shoe ->
            val inflatedViewDataBinding = DataBindingUtil.inflate<ItemShoeBinding>(layoutInflater, R.layout.item_shoe, binding.llShoesList, false)
            inflatedViewDataBinding.apply {
                tvShoeName.text = getString(R.string.shoe_list_shoe_name, shoe.name)
                tvShoeCompany.text = getString(R.string.shoe_list_company, shoe.company)
                tvShoeSize.text = getString(R.string.shoe_list_shoe_size, shoe.size.toString())
                tvShoeDescription.text = getString(R.string.shoe_list_description, shoe.description)
            }
            binding.llShoesList.addView(inflatedViewDataBinding.root)
        }
    }
}