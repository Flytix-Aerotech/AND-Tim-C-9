package com.aerotech.flytix.view.home.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.aerotech.flytix.R
import com.aerotech.flytix.databinding.FragmentDataPenumpangBinding
import com.aerotech.flytix.viewmodel.BooksViewModel
import com.aerotech.flytix.viewmodel.FlightViewModel
import com.aerotech.flytix.viewmodel.SearchViewModel
import com.google.android.material.datepicker.MaterialDatePicker
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DataPenumpang : Fragment() {
//    private lateinit var analytics: FirebaseAnalytics
    private var _binding: FragmentDataPenumpangBinding? = null
    private val binding get() = _binding!!
    private lateinit var flightViewModel: FlightViewModel
    private lateinit var transactionViewModel : BooksViewModel
    private lateinit var searchViewModel: SearchViewModel
//    private lateinit var notifViewModel: NotifViewModel

//    val CHANNEL_ID = "GFG"
//    val CHANNEL_NAME = "GFG ContentWriting"
//
//    val CHANNEL_DESCRIPTION = "GFG NOTIFICATION"
//
//    val imgBitmap = BitmapFactory.decodeResource(resources, R.drawable.logo)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        analytics = Firebase.analytics
        searchViewModel = ViewModelProvider(this)[SearchViewModel::class.java]
        //notifViewModel = ViewModelProvider(this)[NotifViewModel::class.java]
        flightViewModel = ViewModelProvider(this)[FlightViewModel::class.java]
        transactionViewModel = ViewModelProvider(this)[BooksViewModel::class.java]

        _binding = FragmentDataPenumpangBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Booking()
//        getTipeTraveller()
//        getTipeId()
        getBirthday()
        getTipe()
//        binding.btnBooking.setOnClickListener{
//            findNavController().navigate(R.id.action_dataPenumpangFragment_to_rincianPembayaranFragment)
//        }
        super.onViewCreated(view, savedInstanceState)
    }

    private fun getTipe() {
        searchViewModel.getValueTripOneway().observe(viewLifecycleOwner){
            if (it == true){
                getTicketOneway()
            }
            else {
                getTicketRoundTrip()
            }
        }
    }

    private fun getBirthday() {
        val materialDateBuilder: MaterialDatePicker.Builder<*> =
            MaterialDatePicker.Builder.datePicker()

        materialDateBuilder.setTitleText("SELECT A DATE")
        val materialDatePicker = materialDateBuilder.build()

        binding.birth.setOnClickListener(
            View.OnClickListener {
                materialDatePicker.show(parentFragmentManager, "MATERIAL_DATE_PICKER")
            })
        materialDatePicker.addOnPositiveButtonClickListener {
            binding.birth.text = materialDatePicker.headerText
        }
    }

//    private fun getTipeId() {
//        val tipeId = resources.getStringArray(R.array.select_id)
//        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_item, tipeId)
//        binding.typeId.setAdapter(arrayAdapter)
//    }

    private fun getTicketRoundTrip(){
        getTicketOneway()
        val id_back = arguments?.getInt("id_ticket_back")
        Log.d("idround", id_back.toString())

    }

    private fun getTicketOneway() {
        val id = arguments?.getInt("id_oneway")
        Log.d("idone", id.toString())
        if (id != null){
            flightViewModel.getFlightDetail(id)
            flightViewModel.flightDetail.observe(viewLifecycleOwner) {
                binding.apply {
                    if (it != null) {
//                        var simpleDateFormat = SimpleDateFormat("LLL dd")
//                        var departure : Date? = it.data?.departureDate
//                        var departure_date = simpleDateFormat.format(departure?.time).toString()
//
//                        var arrival : Date? = it.data?.arrivalDate
//                        var arrivalDate = simpleDateFormat.format(arrival?.time).toString()
//                        binding.departureDate.text = departure_date
//                        binding.departureTime.text = it.data?.departureTime.toString()
//                        binding.codeIataFrom.text = it.data?.origin?.cityCode.toString()
//                        binding.city1.text = it.data?.origin?.city.toString()
//                        binding.kelas.text = it.data?.classX.toString()
//                        binding.arrivalDate.text = arrivalDate
//                        binding.arrivalTime.text = it.data?.arrivalTime.toString()
//                        binding.codeIataTo.text = it.data?.destination?.cityCode.toString()
//                        binding.city2.text = it.data?.destination?.city.toString()
//                        binding.company.text = it.data?.airplane?.company?.companyName
//                        binding.btnKelas.text = it.data?.classX
//                        binding.price.text = it.data?.price.toString()
                    }
                }
            }
        }
    }

//    private fun getTipeTraveller() {
//        val tipeTraveller = resources.getStringArray(R.array.tipe_penumpang)
//        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_item, tipeTraveller)
//        binding.actvTipePenumpang.setAdapter(arrayAdapter)
//    }

    val CHANNEL_ID = "BOOKING"
    val CHANNEL_NAME = "Booking Successfull"
    val CHANNEL_DESCRIPTION = "BOOKING NOTIFICATION"

//    fun makeStatusNotification(message: String, context: Context) {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            val importance = NotificationManager.IMPORTANCE_HIGH
//            val channel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, importance)
//            channel.description = CHANNEL_DESCRIPTION
//
//            val notificationManager =
//                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager?
//
//            notificationManager?.createNotificationChannel(channel)
//        }
//
//        // Create the notification
//        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
//            .setSmallIcon(R.drawable.ic_launcher_foreground)
//            .setContentTitle(CHANNEL_NAME)
//            .setContentText(message)
//            .setSmallIcon(R.drawable.logo)
//            .setColor(resources.getColor(R.color.basic))
//            .setPriority(NotificationCompat.PRIORITY_HIGH)
//            .setVibrate(LongArray(0))
//
//        // Show the notification
//        NotificationManagerCompat.from(context).notify(1, builder.build())
//    }

    private fun Booking(){
        binding.btnBooking.setOnClickListener() {
            //makeStatusNotification("Silahkan Lakukan Pembayaran agar Transaksi Anda Dapat Segera di Proses", requireContext())

            Log.d("tes", "masukk")
            val tGo = arguments?.getInt("id_oneway")
            Log.d("tgo", tGo.toString())
            val tBack = arguments?.getInt("id_ticket_back")
            val fName = binding.etNamaLengkapPenumpang.text.toString()
            val lName = binding.etNameClan.text.toString()
            val nIK = binding.etIDorPassport.text.toString()
            val birth = binding.birth.text.toString()
            val nationality = binding.etCitizenship.text.toString()

            if(nIK.length < 16){
                binding.etIDorPassport.error = "NIK minimal 16 karakter"
            }
            if(fName.isEmpty()){
                binding.etNamaLengkapPenumpang.error = "First Name harus terisi"
            }
            if(lName.isEmpty()){
                binding.etNameClan.error = "Last Name harus terisi"
            }
            if(nIK.isEmpty()){
                binding.etIDorPassport.error = "NIK harus terisi"
            }
            if(birth.isEmpty()){
                binding.birth.error = "Birth harus terisi"
            }

            searchViewModel.getValueTripOneway().observe(viewLifecycleOwner){
                val bookingId: Int
                if (it == true){
                    bookingId = 1
                } else {
                    bookingId = 2
                }
                transactionViewModel.getDataStoreToken().observe(viewLifecycleOwner) {
                    transactionViewModel.addTransaction("dewasa", birth, bookingId, lName, fName, nationality, nIK,"Bearer $it")

                    //notifViewModel.saveNotif("booking")

                    transactionViewModel.add.observe(viewLifecycleOwner) {
                        Toast.makeText(requireContext(), "Add Success", Toast.LENGTH_SHORT).show()
                        val bundle = Bundle()
                        bundle.putInt("bookingId", bookingId)
                        findNavController().navigate(
                            R.id.action_dataPenumpang_to_beforeCheckout,
                            bundle
                        )
                    }
                }
            }
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