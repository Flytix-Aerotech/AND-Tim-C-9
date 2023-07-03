//package com.aerotech.flytix.view.home.search
//
//import android.os.Bundle
//import android.util.Log
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.core.content.ContextCompat
//import androidx.fragment.app.Fragment
//import androidx.lifecycle.ViewModelProvider
//import androidx.navigation.fragment.findNavController
//import androidx.recyclerview.widget.LinearLayoutManager
//import com.aerotech.flytix.R
//import com.aerotech.flytix.databinding.FragmentResultSearchBinding
//import com.aerotech.flytix.model.ticket.DataGetTicketItem
//import com.aerotech.flytix.view.adapter.ResultSearchAdapter
//import com.aerotech.flytix.viewmodel.SearchViewModel
//import com.aerotech.flytix.viewmodel.TicketViewModel
//import com.google.android.material.snackbar.Snackbar
//import dagger.hilt.android.AndroidEntryPoint
//
//@AndroidEntryPoint
//class ResultSearch : Fragment(), ResultSearchAdapter.ListSearchGoInterface {
//    lateinit var binding: FragmentResultSearchBinding
//    lateinit var ticketViewModel: TicketViewModel
//    private lateinit var searchViewModel: SearchViewModel
//    private lateinit var TanggalKeberangkatan: String
//    var TanggalKembali: String? = null
//    private lateinit var KotaKeberangkatan: String
//    private lateinit var KotaDestinasi: String
//    private lateinit var KelasKursi: String
//
//
//    //inisialisasi data from bundle (home fragment)
//    private var numPassenger : String = ""
//    private var seatClass : String = ""
//    private var listNumSeatPassenger : IntArray = IntArray(3)
//
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        // Inflate the layout for this fragment
//        binding = FragmentResultSearchBinding.inflate(layoutInflater, container, false)
//        return binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        searchViewModel = ViewModelProvider(this)[SearchViewModel::class.java]
//
//        getData()
//
//
////        getDataDataTicket()
//        super.onViewCreated(view, savedInstanceState)
//    }
//
//    fun getData() {
//        TanggalKeberangkatan = arguments?.getString("TanggalKeberangkatan").toString()
//        KotaKeberangkatan = arguments?.getString("KotaKeberangkatan").toString()
//        KotaDestinasi = arguments?.getString("KotaDestinasi").toString()
//        TanggalKembali = arguments?.getString("TanggalKembali").toString()
//        KelasKursi = arguments?.getString("KelasKursi").toString()
//        searchViewModel.getValueTripOneway().observe(viewLifecycleOwner) {
//            if (it == true) {
//                Log.d("tipe", it.toString())
//                getOneway()
//            } else {
//                Log.d("tipe", it.toString())
//                getRoundtrip()
//            }
//        }
//    }
//
//    fun getOneway() {
//        binding.btnRoundtrip.visibility = View.INVISIBLE
//        searchViewModel.getTanggalKeberangkatan().observe(viewLifecycleOwner) {
//            if (it != null) {
//                binding.date.text = it
//            }
//        }
//
//        searchViewModel.getKotaKeberangkatan().observe(viewLifecycleOwner) {
//            binding.tvKotakeberangkatan.text = it
//        }
//
//        searchViewModel.getKotaDestinasi().observe(viewLifecycleOwner) {
//            binding.tvKotadestinasi.text = it
//        }
//
//        val adapter = ResultSearchAdapter(this)
//        binding.apply {
//            searchViewModel.getDataSearch(
//                KotaKeberangkatan,
//                KotaDestinasi,
//                TanggalKeberangkatan,
//                KelasKursi,
//            )
//
//            searchViewModel.getLiveDataSearch().observe(viewLifecycleOwner) {
//                if (it != null) {
//                    adapter.setData(it)
//                } else {
//                    Snackbar.make(binding.root, "Data Gagal Dimuat", Snackbar.LENGTH_SHORT)
//                        .setBackgroundTint(
//                            ContextCompat.getColor(
//                                requireContext(),
//                                R.color.merah
//                            )
//                        )
//                        .setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
//                        .show()
//                }
//            }
//            rvPost.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
//            rvPost.adapter = adapter
//        }
//    }
//
//    fun getRoundtrip(){
//        binding.btnRoundtrip.visibility = View.VISIBLE
//        val id_oneway = arguments?.getInt("id_oneway")
//        Log.d("idoneway2", id_oneway.toString())
//        if (id_oneway != 0){
//            Log.d("oneway", id_oneway.toString())
//            searchViewModel.getTanggalKembali().observe(viewLifecycleOwner){
//                binding.date.text = it
//            }
//            getTicketBack()
//        } else {
//            searchViewModel.getTanggalKeberangkatan().observe(viewLifecycleOwner) {
//                binding.date.text = it
//            }
//            val adapter = ResultSearchAdapter(this)
//            binding.apply {
//                searchViewModel.getDataSearch(
//                    KotaKeberangkatan,
//                    KotaDestinasi,
//                    TanggalKeberangkatan,
//                    KelasKursi,
//                )
//                searchViewModel.getLiveDataSearch().observe(viewLifecycleOwner) {
//                    if (it != null) {
//                        adapter.setData(it as List<DataGetTicketItem>)
//                    } else {
//                        Snackbar.make(binding.root, "Data Gagal Dimuat", Snackbar.LENGTH_SHORT)
//                            .setBackgroundTint(
//                                ContextCompat.getColor(
//                                    requireContext(),
//                                    R.color.merah
//                                )
//                            )
//                            .setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
//                            .show()
//                    }
//                }
//                rvPost.layoutManager =
//                    LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
//                rvPost.adapter = adapter
//            }
//        }
//
//    }
//
//    fun getTicketBack(){
//        val adapter = ResultSearchAdapter(this)
//        binding.apply {
//            searchViewModel.getDataSearchRoundtrip(
//                KotaKeberangkatan,
//                KotaDestinasi,
//                TanggalKembali!!,
//                KelasKursi,
//            )
//            searchViewModel.getLiveDataSearch().observe(viewLifecycleOwner) {
//                if (it != null) {
//                    adapter.setData(it as List<DataGetTicketItem>)
//                } else {
//                    Snackbar.make(binding.root, "Data Gagal Dimuat", Snackbar.LENGTH_SHORT)
//                        .setBackgroundTint(
//                            ContextCompat.getColor(
//                                requireContext(),
//                                R.color.merah
//                            )
//                        )
//                        .setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
//                        .show()
//                }
//            }
//            rvPost.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
//            rvPost.adapter = adapter
//        }
//    }
//
////    fun getDataDataTicket() {
////        ticketViewModel = ViewModelProvider(this).get(TicketViewModel::class.java)
////        ticketViewModel.getDataTicket()
////        ticketViewModel._userTicket.observe(viewLifecycleOwner) {
////            binding.rvPost.layoutManager =
////                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
////            if (it != null) {
////                binding.rvPost.adapter = TicketAdapter(it)
////            }
////        }
////    }
//
//    override fun onItemClickGo(id: Int) {
//        val bund = Bundle()
//        bund.putInt("id_ticket_go", id)
//        bund.putString("TanggalKeberangkatan", TanggalKeberangkatan)
//        bund.putString("KotaKeberangkatan", KotaKeberangkatan)
//        bund.putString("KotaDestinasi", KotaDestinasi)
//        bund.putString("TanggalKembali", TanggalKembali)
//        val numSeatPassenger = searchViewModel.dataPassenger.value
//        bund.putIntArray("DATA_LIST_NUM_SEAT", numSeatPassenger!!.toIntArray())
//        findNavController().navigate(R.id.action_resultSearch_to_detail, bund)
//        Log.d("DATA_PASSENGER", searchViewModel.dataPassenger.value.toString())
//    }
//}