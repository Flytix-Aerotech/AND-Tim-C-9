package com.aerotech.flytix.view.home.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.aerotech.flytix.databinding.FragmentDataPenumpangBinding
import com.aerotech.flytix.view.adapter.DataPenumpangAdapter
import com.aerotech.flytix.viewmodel.PenumpangViewModel
import com.aerotech.flytix.viewmodel.SearchViewModel
import com.dwiki.tiketku.model.penumpang.Penumpang
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DataPenumpang : Fragment() {

    private lateinit var binding: FragmentDataPenumpangBinding
    private val searchViewModel: SearchViewModel by viewModels()
    private val penumpangViewModel: PenumpangViewModel by activityViewModels()
    private lateinit var penumpangAdapter: DataPenumpangAdapter
//    private val biodataViewModel:BiodataViewModel by viewModels()
//    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDataPenumpangBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initDewasaAdapter()
        val departureDate = arguments?.getString("TanggalKeberangkatan")
        val departureCity = arguments?.getString("KotaKeberangkatan")
        val destinationCity = arguments?.getString("KotaDestinasi")
        val returnDate = arguments?.getString("TanggalKembali")
        val id_ticket_back = arguments?.getInt("id_ticket_go")

//        binding.btnBooking.setOnClickListener {
//            val idTicket = searchViewModel.getIdTicket()
//            val dewasa = searchViewModel.getPenumpangDewasa()
//            val anak = searchViewModel.getPenumpangAnak()
//            val bayi = searchViewModel.getPenumpangBayi()
//            val total = dewasa + anak + bayi
//
//            val dataList = penumpangViewModel.getDataList()
//            Log.d("Hasil Pencarian", "$dataList")
//
//            val penumpangData = PenumpangRequest(idTicket!!, dataList, total)
//
//            val token = loginViewModel.getTokenPreferences()
//            biodataViewModel.biodataPenumpang(penumpangData, token!!)
//            biodataViewModel.getBiodataPenumpangResponse.observe(viewLifecycleOwner) {
//                if (it.status == "Success") {
//                    Toast.makeText(
//                        requireContext(),
//                        "Berhasil Menambahkan data penumpang",
//                        Toast.LENGTH_SHORT
//                    ).show()
//                }
//            }
//        }

//        binding.optionClan.setOnCheckedChangeListener { p0, isChecked ->
//
//            if (isChecked) {
//                binding.etNameClan.visibility = View.VISIBLE
//                binding.layoutNameClan.visibility = View.VISIBLE
//
//            } else {
//                binding.etNameClan.visibility = View.GONE
//                binding.layoutNameClan.visibility = View.GONE
//
//            }
//        }
    }
    
    private fun initDewasaAdapter() {
        val listPenumpang: ArrayList<Penumpang> = ArrayList()
        searchViewModel.getJumlahPenumpangDewasa().observe(viewLifecycleOwner) {
            if (it != null) {
                for (i in 1..it.toInt()) {
                    listPenumpang.add(Penumpang("Dewasa $i", "Dewasa"))
                }
            }
        }

        searchViewModel.getJumlahPenumpangAnak().observe(viewLifecycleOwner) {
            if (it != null) {
                //get Penumpang Anak
                for (i in 1..it.toInt()) {
                    listPenumpang.add(Penumpang("Anak $i", "Anak"))
                }
            }
        }
        searchViewModel.getJumlahPenumpangBayi().observe(viewLifecycleOwner) {
            if (it != null) {
                for (i in 1..it.toInt()) {
                    listPenumpang.add(Penumpang("Bayi $i", "Bayi"))
                }
            }
        }
        binding.rvBiodataPenumpang.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            penumpangAdapter = DataPenumpangAdapter(listPenumpang)
            adapter = penumpangAdapter
            penumpangAdapter.setOnItemClickListener(object : DataPenumpangAdapter.OnItemClickListener {
                override fun onItemClick(position: Int) {
                    Toast.makeText(
                        requireContext(),
                        "posisi card ini $position",
                        Toast.LENGTH_SHORT
                    ).show()
                    val bundle = Bundle()
                    val name = listPenumpang[position].penumpang
                    val role = listPenumpang[position].role
                    bundle.putInt("index", position)
                    bundle.putString("penumpang", name)
                    bundle.putString("role", role)
                    val penumpangDetail = DatapenumpangDetail()
                    penumpangDetail.arguments = bundle
                    penumpangDetail.show(parentFragmentManager, penumpangDetail.tag)
                }
            })
            isNestedScrollingEnabled = false
            setHasFixedSize(true)
        }

    }
}

//@AndroidEntryPoint
//class DataPenumpang : Fragment() {
//    private lateinit var binding :FragmentDataPenumpangBinding
//    private lateinit var biodataPassengerAdapter: DataPenumpangAdapter
////    private var flightTicketOneTrip = FlightTicketOneTrip()
////    private var flightTicketRoundTrip = FlightTicketRoundTrip()
//    private var arrSeatPassenger = IntArray(3)
//    lateinit var searchViewModel: SearchViewModel
//    private val listPassenger = mutableListOf<Passenger>()
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
//        searchViewModel = ViewModelProvider(this)[SearchViewModel::class.java]
//        binding = FragmentDataPenumpangBinding.inflate(layoutInflater, container, false)
//        return binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        //get bundle
//        val getListSeatPassenger = arguments?.getIntArray("ListJumlahPenumpang")
//        val getDataPemesan = arguments?.getSerializable("DATA_PEMESAN")
//
//
//        searchViewModel.getArrayJumlahpenumpang().observe(viewLifecycleOwner){
//            binding.jdlAppbar.text = it.toString()
//            val getListSeatPassenger = arguments?.getIntArray("ListJumlahPenumpang")
//            if (getListSeatPassenger != null) {
//                arrSeatPassenger = getListSeatPassenger
//                setRvBioPassenger (getListSeatPassenger)
//
//            }
//        }
//
//
//
////        searchViewModel.getJumlahPenumpang().observe(viewLifecycleOwner){
////            if(it!= null){
////                arrSeatPassenger = it.toInt()
////            }
////        }
//
//
////        if (getTypeRoundTrip != null){
////            if(getTypeRoundTrip == true){
////                val getRoundTrip = arguments?.getSerializable("DATA_FLIGHT_ROUND_TRIP")
////                flightTicketRoundTrip = getRoundTrip as FlightTicketRoundTrip
////            }else{
////                val getOneTrip = arguments?.getSerializable("DATA_FLIGHT_ONE_TRIP")
////                flightTicketOneTrip = getOneTrip as FlightTicketOneTrip
////            }
////        }
//
//
////        val bottomNav = requireActivity().findViewById<BottomNavigationView>(R.id.bottomnav)
////        bottomNav.visibility = View.GONE
//
//
//
////        binding.btnBack.setOnClickListener {
////            findNavController().navigateUp()
////        }
//
////        binding.btnLanjutPilihKursi.setOnClickListener {
//////            if(getTypeRoundTrip != null){
//////                if(getTypeRoundTrip){
//////                    val putBundleDataFlight = Bundle().apply {
//////                        putIntArray("DATA_LIST_NUM_SEAT", getListSeatPassenger)
//////                        putBoolean("TYPE_TRIP_ROUNDTRIP", true)
//////                        putSerializable("DATA_FLIGHT_ROUND_TRIP", flightTicketRoundTrip)
//////                    }
//////                    findNavController().navigate(R.id.action_biodataPenumpangFragment_to_checkoutFragment3, putBundleDataFlight)
//////                }else{
//////                    val putBundleDataFlight = Bundle().apply {
//////                        putIntArray("DATA_LIST_NUM_SEAT", getListSeatPassenger)
//////                        putBoolean("TYPE_TRIP_ROUNDTRIP", false)
//////                        putSerializable("DATA_FLIGHT_ONE_TRIP", flightTicketOneTrip)
//////                    }
//////                    findNavController().navigate(R.id.action_biodataPenumpangFragment_to_checkoutFragment3, putBundleDataFlight)
//////                }
//////            }
////            val bioIsNotEmpty = checkBioIsNotEmpty(biodataPassengerAdapter.getDataBioPassenger())
////            Log.i("DATA_PASSENGGER", biodataPassengerAdapter.getDataBioPassenger().toString())
////            if(bioIsNotEmpty){
////                searchViewModel.getValueTripOneway().observe(viewLifecycleOwner){
////                    if (it!= null && getDataPemesan != null){
////
////
////                    }
////                }
////                if(getTypeRoundTrip != null && getDataPemesan != null){
////                    if(getTypeRoundTrip){
////                        val putBundleDataFlight = Bundle().apply {
////                            putIntArray("ListJumlahPenumpang", getListSeatPassenger)
////                            putBoolean("TYPE_TRIP_ROUNDTRIP", true)
////                            //putSerializable("DATA_FLIGHT_ROUND_TRIP", flightTicketRoundTrip)
////                            putSerializable("DATA_PEMESAN", getDataPemesan)
////                            putParcelableArrayList("DATA_PASSENGER", ArrayList(biodataPassengerAdapter.getDataBioPassenger()))
////                        }
//////                        findNavController().navigate(R.id.action_biodataPenumpangFragment_to_selectSeatFragment, putBundleDataFlight)
////                    }else{
////                        val putBundleDataFlight = Bundle().apply {
////                            putIntArray("DATA_LIST_NUM_SEAT", getListSeatPassenger)
////                            putBoolean("TYPE_TRIP_ROUNDTRIP", false)
////                            //putSerializable("DATA_FLIGHT_ONE_TRIP", flightTicketOneTrip)
////                            putSerializable("DATA_PEMESAN", getDataPemesan)
////                            putParcelableArrayList("DATA_PASSENGER", ArrayList(biodataPassengerAdapter.getDataBioPassenger()))
////                        }
//////                        findNavController().navigate(R.id.action_biodataPenumpangFragment_to_selectSeatFragment, putBundleDataFlight)
////                    }
////                }
////                Log.i("DATA_PASSENGGER", biodataPassengerAdapter.getDataBioPassenger().toString())
////            }
////            else{
////                Snackbar.make(binding.root, "Data Penumpang Masih Kosong", Snackbar.LENGTH_SHORT)
////                    .setBackgroundTint(
////                        ContextCompat.getColor(
////                            requireContext(),
////                            R.color.merah
////                        )
////                    )
////                    .setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
////                    .show()
////            }
////
////        }
//    }
//
//    private fun setRvBioPassenger(item: IntArray) {
//        for(i in item.indices){
//            for(z in 1 .. item[i]){
//                when(i){
//                    0 -> listPassenger.add(Passenger("Adult"))
//                    1 -> listPassenger.add(Passenger("Child"))
//                    2 -> listPassenger.add(Passenger("Baby"))
//                }
//            }
//
//        }
//        biodataPassengerAdapter = DataPenumpangAdapter(listPassenger, requireContext(), requireActivity())
//
//        binding.rvBioPassenger.apply {
//            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
//            adapter = biodataPassengerAdapter
//        }
//    }
//
//    private fun checkBioIsNotEmpty(dataBioPassenger: List<Passenger>): Boolean {
//        val bioNotEmpty = dataBioPassenger.all { passenger ->
//            passenger.passengerRole.toString().isNotEmpty() &&
//                    passenger.birthDate.toString().isNotEmpty() &&
//                    passenger.fullName.toString().isNotEmpty() &&
//                    passenger.nationality.toString().isNotEmpty() &&
////                    passenger.seat.toString().isNotEmpty() &&
////                    passenger.issuedCountry.toString().isNotEmpty() &&
////                    passenger.title.toString().isNotEmpty() &&
//                    passenger.nikNumber.toString().isNotEmpty()
//        }
////        val bioNotEmpty = dataBioPassenger.any { passenger ->
////                    passenger.type.toString().isNotEmpty() &&
////                    passenger.birthday.toString().isNotEmpty() &&
////                    passenger.name.toString().isNotEmpty() &&
////                    passenger.nationality.toString().isNotEmpty() &&
////                    passenger.issuedCountry.toString().isNotEmpty() &&
////                    passenger.title.toString().isNotEmpty() &&
////                    passenger.nik.toString().isNotEmpty()
////        }
//        return bioNotEmpty
//    }
//
//    override fun onStart() {
//        super.onStart()
//        val getListSeatPassenger = arguments?.getIntArray("ListJumlahPenumpang")
//        if (getListSeatPassenger != null) {
//            arrSeatPassenger = getListSeatPassenger
//            Log.d("DATA_PASSENGERarr", searchViewModel.dataPassenger.value.toString())
//        }
//
//        if (getListSeatPassenger != null) {
//            setRvBioPassenger(getListSeatPassenger)
//            Log.d("DATA_PASSENGERsetrv", searchViewModel.dataPassenger.value.toString())
//        }
//    }
//}