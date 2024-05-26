package com.alvaro.civitatis.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavArgs
import androidx.navigation.fragment.findNavController
import com.alvaro.civitatis.R
import com.alvaro.civitatis.databinding.FragmentCharactersBinding
import com.alvaro.civitatis.model.DomainCharacter
import com.alvaro.civitatis.util.Constants
import com.alvaro.civitatis.viewmodel.CharactersViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharactersFragment : Fragment() {

    private lateinit var binding: FragmentCharactersBinding
    private val viewModel by viewModels<CharactersViewModel>()
    private var adapter = CharactersAdapter(
        onClickListener = { character -> onCharacterSelected(character.id) }
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCharactersBinding.inflate(layoutInflater)
        addMenu()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.loading.observe(viewLifecycleOwner) { loading ->
            if (loading == false) {
                adapter.characters = viewModel.comics.value!!
                binding.comicsRv.adapter = adapter
                binding.comicsRv.visibility = View.VISIBLE
                binding.progressBar.visibility = View.GONE
            } else {
                binding.progressBar.visibility = View.VISIBLE
                binding.comicsRv.visibility = View.GONE
            }
        }

        viewModel.comics.observe(viewLifecycleOwner) {
            adapter.characters = it
            adapter.notifyDataSetChanged()
        }
    }



    private fun onCharacterSelected(characterId: String) {
        val action = CharactersFragmentDirections.actionCharactersFragmentToCharacterDetails(characterId)
        findNavController().navigate(action)
    }

    private fun addMenu() {
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object: MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.option_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when(menuItem.itemId) {
                    R.id.ascending_name -> {
                        viewModel.getCharactersOrdered(Constants.NAME, true)
                        true
                    }
                    R.id.descending_name -> {
                        viewModel.getCharactersOrdered(Constants.NAME, false)
                        true
                    }
                    R.id.ascending_modified -> {
                        viewModel.getCharactersOrdered(Constants.MODIFIED, true)
                        true
                    }
                    R.id.descending_modified -> {
                        viewModel.getCharactersOrdered(Constants.MODIFIED, false)
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

}