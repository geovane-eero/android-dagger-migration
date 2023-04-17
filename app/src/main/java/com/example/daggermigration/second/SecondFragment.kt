package com.example.daggermigration.second

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.daggermigration.R
import com.example.daggermigration.databinding.FragmentSecondBinding
import com.example.daggermigration.di.SecondViewModelAssistedFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
@AndroidEntryPoint
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    @Inject
    lateinit var assistedFactory: SecondViewModelAssistedFactory

    // This is directly
    private val viewModel: SecondViewModel by lazy { assistedFactory.create("SecondFragment") }

    // Here we can take advantage of the by viewModels, but we need a ViewModelProvider.Factory
//    private val viewModel: SecondViewModel by viewModels {
//        SecondViewModel.Factory(assistedFactory, "SecondFragment")
//    }

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonSecond.setOnClickListener {
            viewModel.log()
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
