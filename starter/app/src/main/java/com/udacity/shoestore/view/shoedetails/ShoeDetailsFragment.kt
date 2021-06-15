package com.udacity.shoestore.view.shoedetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeDetailsBinding
import com.udacity.shoestore.viewmodel.MainViewModel

class ShoeDetailsFragment : Fragment() {

    private lateinit var binding: FragmentShoeDetailsBinding
    private val viewModel by activityViewModels<MainViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_details, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observables()
        binding.run {
            mainViewModel = viewModel
            lifecycleOwner = this@ShoeDetailsFragment
        }
    }

    private fun observables() {
        viewModel.run {
            cancelLiveData.observe(viewLifecycleOwner, { isCanceled ->
                if (isCanceled) {
                    directionToFragmentShoesList()
                    onCancelEventComplete()
                }
            })

            addShoeLiveData.observe(viewLifecycleOwner, { isAdded ->
                if (isAdded) {
                    directionToFragmentShoesList()
                    onAddShoeComplete()
                }
            })
        }
    }

    private fun directionToFragmentShoesList() {
        findNavController().navigate(ShoeDetailsFragmentDirections.actionShoeDetailsFragmentToShoeListFragment())
    }
}