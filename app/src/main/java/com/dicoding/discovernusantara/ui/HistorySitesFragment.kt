package com.dicoding.discovernusantara.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.discovernusantara.data.Result
import com.dicoding.discovernusantara.databinding.FragmentHistorySitesBinding
import com.dicoding.discovernusantara.ui.adapter.SitesAdapter
import com.dicoding.discovernusantara.ui.viewmodels.SitesViewModel
import com.dicoding.discovernusantara.ui.viewmodels.ViewModelFactory

class HistorySitesFragment : Fragment() {

    private var _binding: FragmentHistorySitesBinding? = null
    private val binding get() = _binding!!

    private lateinit var factory: ViewModelFactory
    private val viewModel: SitesViewModel by viewModels { factory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHistorySitesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        factory = ViewModelFactory.getInstance(requireActivity())

        initData()
    }

    private fun initData() {
        val sitesAdapter = SitesAdapter()

        viewModel.getAllSites().observe(viewLifecycleOwner) { result ->
            if (result != null) {
                when (result) {
                    is Result.Loading -> binding.progressBarSites.visibility = View.VISIBLE
                    is Result.Success -> {
                        binding.progressBarSites.visibility = View.GONE
                        val sitesData = result.data
                        sitesAdapter.submitList(sitesData)
                    }
                    is Result.Error -> {
                        binding.progressBarSites.visibility = View.GONE
                        Toast.makeText(context, "Terjadi Kesalahan", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        binding.txtSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.getSiteByName(p0.toString()).observe(viewLifecycleOwner) {
                    sitesAdapter.submitList(it)
                }
            }

            override fun afterTextChanged(p0: Editable?) {}
        })

        binding.rvSites.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = sitesAdapter
        }
    }

    override fun onResume() {
        super.onResume()
        initData()
    }
}