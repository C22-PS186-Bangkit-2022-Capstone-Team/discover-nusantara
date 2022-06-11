package com.dicoding.discovernusantara.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.discovernusantara.adapter.ListSitesAdapter
import com.dicoding.discovernusantara.databinding.FragmentHistorySitesBinding
import com.dicoding.discovernusantara.helper.TmpData

class HistorySitesFragment : Fragment() {

    private var _binding: FragmentHistorySitesBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHistorySitesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvSites.layoutManager = LinearLayoutManager(context)
        binding.rvSites.setHasFixedSize(true)
        binding.rvSites.adapter = ListSitesAdapter(TmpData.listData)
    }

    companion object {
    }
}