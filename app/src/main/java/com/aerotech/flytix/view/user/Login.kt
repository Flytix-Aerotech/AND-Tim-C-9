package com.aerotech.flytix.view.user

import android.content.Context
import android.content.SharedPreferences
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
import com.aerotech.flytix.databinding.FragmentLoginBinding
import com.aerotech.flytix.model.user.DataUserLoginItem
import com.aerotech.flytix.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Login : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var userLoginVM: LoginViewModel
    lateinit var sharedPref: SharedPreferences
    var token: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        requireActivity().onBackPressedDispatcher.addCallback(
//            viewLifecycleOwner,
//            object : OnBackPressedCallback(true) {
//                override fun handleOnBackPressed() {
//                    activity?.finishAffinity()
//                }
//            })
        sharedPref = requireActivity().getSharedPreferences("LOGIN", Context.MODE_PRIVATE)
        userLoginVM = ViewModelProvider(this).get(LoginViewModel::class.java)

//        binding.btnLogin.setOnClickListener {
//            doLogin()
//        }
        binding.tvDftardisini.setOnClickListener {
            findNavController().navigate(R.id.action_login2_to_register)
        }
        initListener()
    }

//    fun cobaLogin() {
//        val emailInputUser = binding.etEmaillogin.text.toString()
//        val passInputUser = binding.etPasslogin.text.toString()
//
//        if (emailInputUser.isNotEmpty() && passInputUser.isNotEmpty()) {
//            userLoginVM.authLoginUser(DataUserLoginItem(emailInputUser, passInputUser))
////            userLoginVM.authLogin()
//            userLoginVM.authLiveDataUserLogin.observe(viewLifecycleOwner) {
//                if (it.message == "failed") {
//                    val emailUser = it.user.email
//                    val passUser = it.user.password
//                    if (emailUser.isNotEmpty() && passUser.isNotEmpty()) {
//                        Log.i("tokenn", "token: ${it.token}")
//                        token = it.token
//                        // input to sharedpreferences
//                        sharedPref =
//                            requireActivity().getSharedPreferences("LOGIN", Context.MODE_PRIVATE)
//                        val userData = sharedPref.edit()
//                        userData.putString("token", it.token)
//                        userData.apply()
//                        Toast.makeText(requireContext(), "Login Success", Toast.LENGTH_SHORT).show()
//                        findNavController().navigate(R.id.action_login2_to_home3)
//                    } else {
//                        Toast.makeText(requireContext(), "Gagal Login", Toast.LENGTH_SHORT).show()
//                    }
//                }
//            }
////            if (emailUser != emailInputUser && passUser != passInputUser) {
////
////            } else {
////
////            }
//        }
//        Toast.makeText(requireContext(), "Email dan Password Tidak Terdaftar", Toast.LENGTH_SHORT).show()
//    }
    fun fiksLogin() {
        val emailInputUser = binding.etEmaillogin.text.toString()
        val passInputUser = binding.etPasslogin.text.toString()

        if (emailInputUser.isNotEmpty() && passInputUser.isNotEmpty()) {
            userLoginVM.authLoginUser(DataUserLoginItem(emailInputUser, passInputUser))
//            userLoginVM.authLogin()
            userLoginVM.authLiveDataUserLogin.observe(viewLifecycleOwner) {
                if (it != null) {
                    val emailUser = it.user.email
                    val passUser = it.user.password
                    if (emailUser.isNotEmpty() && passUser.isNotEmpty()) {
                        Log.i("tokenn", "token: ${it.token}")
                        token = it.token
                        // input to sharedpreferences
                        sharedPref =
                            requireActivity().getSharedPreferences("LOGIN", Context.MODE_PRIVATE)
                        val userData = sharedPref.edit()
                        userData.putString("token", it.token)
                        userData.apply()
                        Toast.makeText(requireContext(), "Login Success", Toast.LENGTH_SHORT).show()
                        findNavController().navigate(R.id.action_login2_to_home3)
                    } else {
                        Toast.makeText(requireContext(), "Gagal Login", Toast.LENGTH_SHORT).show()
                    }
                }
            }
//            if (emailUser != emailInputUser && passUser != passInputUser) {
//
//            } else {
//
//            }
        }
        Toast.makeText(requireContext(), "Email dan Password Tidak Terdaftar", Toast.LENGTH_SHORT).show()
    }

//    fun doLogin() {
//        var emailInputUser = binding.etEmaillogin.text.toString()
//        var passInputUser = binding.etPasslogin.text.toString()
//        userLoginVM.authLiveDataUserLogin.observe(viewLifecycleOwner){datauser->
//            binding.apply {
//                emailUser = datauser.user.email
//                passUser = datauser.user.password
//                emailUser = emailInputUser
//                passUser = passInputUser
//            }
//                if (emailUser != emailInputUser || passUser != passInputUser){
//                    Toast.makeText(requireContext(), datauser.message, Toast.LENGTH_SHORT).show()
//                }else{
//                    userLoginVM.authLoginUser(DataUserLoginItem(emailInputUser,passInputUser))
//                    userLoginVM.authLiveDataUserLogin.observe(viewLifecycleOwner){
//                        if (it!= null){
//                            Log.i("tokenn", "token: ${datauser.token}")
//                            token = datauser.token
//                            // input to sharedpreferences
//                            sharedPref =
//                                requireActivity().getSharedPreferences("LOGIN", Context.MODE_PRIVATE)
//                            val userData = sharedPref.edit()
//                            userData.putString("token", datauser.token)
//                            userData.apply()
//                            Toast.makeText(requireContext(), datauser.message, Toast.LENGTH_SHORT).show()
//                            findNavController().navigate(R.id.action_login2_to_home2)
//                        }
//                    }
//                }
//            }
//        }
//
//    fun gasLogin(){
//        var emailInputUser = binding.etEmaillogin.text.toString()
//        var passInputUser = binding.etPasslogin.text.toString()
//        userLoginVM.authLoginUser(DataUserLoginItem(emailInputUser,passInputUser))
//        userLoginVM.authLiveDataUserLogin.observe(viewLifecycleOwner){datauser->
//            if (datauser != null){
//                Log.i("tokenn", "token: ${datauser.token}")
//                token = datauser.token
//                // input to sharedpreferences
//                sharedPref =
//                    requireActivity().getSharedPreferences("LOGIN", Context.MODE_PRIVATE)
//                val userData = sharedPref.edit()
//                userData.putString("token", datauser.token)
//                userData.apply()
//                Toast.makeText(requireContext(), datauser.message, Toast.LENGTH_SHORT).show()
//                findNavController().navigate(R.id.action_login2_to_home2)
//            }
//            else {
//                Toast.makeText(requireContext(), datauser?.message, Toast.LENGTH_SHORT).show()
//            }
//        }
//    }

    fun initListener() {
        binding.btnLogin.setOnClickListener {
            when {
                binding.etEmaillogin.text.isNullOrEmpty() -> binding.etEmaillogin.error =
                    " Email is required"

                binding.etPasslogin.text.isNullOrEmpty() -> binding.etPasslogin.error =
                    "Password is required"

                else -> {
                    fiksLogin()
                }
            }
        }
    }

//        if (emailUser != emailInputUser || passUser != passInputUser) {
//            Toast.makeText(requireContext(), "Gagal Login", Toast.LENGTH_SHORT).show()
//        } else {
//            userLoginVM.authLoginUser(DataUserLoginItem(emailInputUser, passInputUser))
//            userLoginVM.authLiveDataUserLogin.observe(viewLifecycleOwner) {
//                if (it != null) {
//                    Log.i("tokenn", "token: ${it.token}")
//                    token = it.token
//                    // input to sharedpreferences
//                    sharedPref =
//                        requireActivity().getSharedPreferences("LOGIN", Context.MODE_PRIVATE)
//                    val userData = sharedPref.edit()
//                    userData.putString("token", it.token)
//                    userData.apply()
//                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
//                    findNavController().navigate(R.id.action_login2_to_home2)
//                }
//            }
//        }
}


//    override fun onStart() {
//        super.onStart()
//        val emailInputUser = binding.etEmaillogin.text.toString()
//        val passInputUser = binding.etPasslogin.text.toString()
//        userLoginVM.authLogin()
//        userLoginVM.livedatauserLogin.observe(viewLifecycleOwner) {
//            emailUser = it.email
//            passUser = it.password
//            emailUser = emailInputUser
//            passUser = passInputUser
//        }
//    }


//    fun dodoLogin() {
//        val emailInputUser = binding.etEmaillogin.text.toString()
//        val passInputUser = binding.etPasslogin.text.toString()
//        userLoginVM.authLoginUser(DataUserLoginItem(emailInputUser, passInputUser))
//        userLoginVM.authLiveDataUserLogin.observe(viewLifecycleOwner) {
//            if (it != null) {
//                Log.i("tokenn", "token: ${it.token}")
//                token = it.token
//                // input to sharedpreferences
//                val userData = sharedPref.edit()
//                userData.putString("token", it.token)
//                userData.apply()
//                Navigation.findNavController(requireView()).navigate(R.id.action_login2_to_home2)
//            }
//        }
//    }
//}
//    private lateinit var binding: FragmentLoginBinding
//    private lateinit var userLoginVM: LoginViewModel
//    lateinit var sharedpref: SharedPreferences
//    lateinit var emailUser: String
//    lateinit var passUser: String
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
//        binding = FragmentLoginBinding.inflate(inflater, container, false)
//        return binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        userLoginVM = ViewModelProvider(this).get(LoginViewModel::class.java)
//        sharedpref = requireContext().getSharedPreferences("LOGGED_IN", Context.MODE_PRIVATE)
////        binding.btnLogin.setOnClickListener {
////            untuklogin()
////        }
//
//        binding.btnLogin.setOnClickListener {
//            if (binding.etEmaillogin.text.toString().isEmpty()) {
//                binding.etEmaillogin.setError("Isi Username")
//            } else if (binding.etPasslogin.text.toString().isEmpty()) {
//                binding.etPasslogin.setError("Isi Password")
//            } else {
//                loginAction()
//            }
//        }
//
//        binding.tvDftardisini.setOnClickListener {
//            findNavController().navigate(R.id.action_login2_to_register)
//        }
//    }
//
//    private fun prosesLogin() {
//        val email = binding.etEmaillogin.text.toString()
//        val password = binding.etPasslogin.text.toString()
//
//        if (email.isEmpty() || password.isEmpty()) {
//            Toast.makeText(requireContext(), "Please enter email and password", Toast.LENGTH_SHORT)
//                .show()
//        } else {
//            userLoginVM.authLoginUser(DataUserLoginItem(email, password))
//            userLoginVM.authLiveDataUserLogin.observe(viewLifecycleOwner) {
//                emailUser = it.user.email
//                passUser = it.user.password
//                emailUser= email
//                passUser = password
//                if (emailUser != email && passUser != password) {
//                    Toast.makeText(requireContext(), "Gagal Login", Toast.LENGTH_SHORT).show()
//                }else{
//                    //            if (emailUser == email && passUser == password) {
//                    userLoginVM.authLoginUser(DataUserLoginItem(email, password))
//                    userLoginVM.authLiveDataUserLogin.observe(viewLifecycleOwner) { loginresult ->
//                        Toast.makeText(requireContext(),loginresult.message, Toast.LENGTH_SHORT).show()
//                        val userData = sharedpref.edit()
//                        userData.putString("token", it.token)
//                        userData.apply()
//                        findNavController().navigate(R.id.action_login2_to_home2)
//                    }
//                }
//            }
//        }
//    }
//
//
//    private fun login() {
//        val email = binding.etEmaillogin.text.toString()
//        val password = binding.etPasslogin.text.toString()
//        userLoginVM = ViewModelProvider(this).get(LoginViewModel::class.java)
//        userLoginVM.authLoginUser(DataUserLoginItem(email, password))
//        userLoginVM.authLiveDataUserLogin.observe(viewLifecycleOwner, Observer {
////            listuserlogin = it
////            loginAuth(listuserlogin)
//            if (it.token != null) {
//                findNavController().navigate(R.id.action_login2_to_home2)
//            }
//            val userData = sharedpref.edit()
//            userData.putString("token", it.token)
//            userData.apply()
//        })
//    }
//
//    fun doLogin() {
//        val emailInputUser = binding.etEmaillogin.text.toString()
//        val passInputUser = binding.etPasslogin.text.toString()
//
//        if (emailInputUser.isNotEmpty() || passInputUser.isNotEmpty()) {
//            userLoginVM.authLiveDataUserLogin.observe(viewLifecycleOwner) {
//                emailUser = it.user.email
//                passUser = it.user.password
//                emailUser = emailInputUser
//                passUser = passInputUser
//            }
//
//            if (emailUser != emailInputUser && passUser != passInputUser) {
//                Toast.makeText(requireContext(), "Gagal Login", Toast.LENGTH_SHORT).show()
//            } else {
//                userLoginVM.authLiveDataUserLogin.observe(viewLifecycleOwner) {
//                    if (it != null) {
//                        Log.i("tokenn", "token: ${it.token}")
////                        token = it.token
//                        // input to sharedpreferences
//                        val userData = sharedpref.edit()
//                        userData.putString("token", it.token)
//                        userData.apply()
//                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
//                        findNavController().navigate(R.id.action_login2_to_home2)
//                    }
//                }
//                userLoginVM.authLoginUser(
//                    DataUserLoginItem(
//                        emailUser.toString(),
//                        passUser.toString()
//                    )
//                )
//            }
//        }
//    }
//
//    private fun forLogin() {
//        val email = binding.etEmaillogin.text.toString()
//        val password = binding.etPasslogin.text.toString()
//
//        if (email.isNotEmpty() || password.isNotEmpty()) {
//            userLoginVM.authLiveDataUserLogin.observe(viewLifecycleOwner) {
////                emailUser = it.user.email
////                passUser = it.user.password
////                emailUser = email
////                passUser = password
//                if (email == it.user.email && password == it.user.password) {
//                    userLoginVM.authLoginUser(DataUserLoginItem(email, password))
//                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT)
//                        .show()
//                    findNavController().navigate(R.id.action_login2_to_home2)
//                    userLoginVM.dataPostLoginUser.observe(viewLifecycleOwner) { loginresult ->
////                    emailUser = loginresult.user.email
////                    passUser = loginresult.user.password
////                    emailUser = email
////                    passUser = password
////                if (emailUser != email && passUser != password) {
////                    Toast.makeText(requireContext(), "Gagal Login", Toast.LENGTH_SHORT).show()
////                } else {
//                        Toast.makeText(requireContext(), loginresult.message, Toast.LENGTH_SHORT)
//                            .show()
//                    }
//
//                } else {
//                    Toast.makeText(requireContext(), "Gagal Login", Toast.LENGTH_SHORT).show()
//                    Log.e("gagalLogin: ", "onFailure : ${it.message}")
////            if (emailUser == email && passUser == password) {
//                }
//            }
//
//
////            } else {
////                Toast.makeText(requireContext(), "Gagal Login", Toast.LENGTH_SHORT).show()
////            }
//        } else {
//            Toast.makeText(requireContext(), "Please enter email and password", Toast.LENGTH_SHORT)
//                .show()
//        }
//    }
//
//    private fun untuklogin(){
//        val email = binding.etEmaillogin.text.toString()
//        val password = binding.etPasslogin.text.toString()
//        userLoginVM = ViewModelProvider(this).get(LoginViewModel::class.java)
//        userLoginVM.authLiveDataUserLogin.observe(viewLifecycleOwner, Observer {
////            listuserlogin = it
////            loginAuth(listuserlogin)
//            if(it != null){
//
//                val dataUser = sharedpref.edit()
//                dataUser.putString("token", it.token)
//                dataUser.apply()
//                Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT)
//                    .show()
//                findNavController().navigate(R.id.action_login2_to_register)
//            } else{
//                Toast.makeText(requireContext(), "Login Gagal", Toast.LENGTH_SHORT)
//                    .show()
//            }
//        })
//        userLoginVM.authLoginUser(DataUserLoginItem(email, password))
//
//
//    }
//
//    lateinit var listuserlogin: List<DataUserResponse>
//    private fun loginAction() {
//        userLoginVM = ViewModelProvider(this).get(LoginViewModel::class.java)
//        userLoginVM.livedatauserLoginDone.observe(viewLifecycleOwner, Observer {
//            listuserlogin = listOf(it)
//            loginAuth(listuserlogin)
//        })
//        userLoginVM.authLoginDone()
//    }
//
//    private fun loginAuth(userDataList: List<DataUserResponse>) {
//        //make shared preference that saving log in activity history
//        sharedpref = requireActivity().getSharedPreferences("LOGGED_IN", Context.MODE_PRIVATE)
//
//        //get all data from user input
//        val inputEmail = binding.etEmaillogin.text.toString()
//        val inputPassword = binding.etPasslogin.text.toString()
//
//        //checking email and password of user to authenticate
//        for (i in userDataList.indices) {
//            if (inputPassword == userDataList[i].user.password && inputEmail == userDataList[i].user.email) {
//                Toast.makeText(requireContext(), "Berhasil login", Toast.LENGTH_SHORT).show()
//                //bundling all information about user
//                navigationBundlingSf(userDataList[i])
//                userLoginVM.authLoginUser(DataUserLoginItem(inputEmail,inputPassword))
//                break
//            } else if (i == userDataList.lastIndex && inputPassword != userDataList[i].user.password && inputEmail != userDataList[i].user.email) {
//                binding.etPasslogin.error = "Password Tidak Sesuai"
//                binding.etEmaillogin.error = "Email Tidak Sesuai"
//            }
//        }
//    }
//    private fun navigationBundlingSf(currentUser: DataUserResponse) {
//        sharedpref = requireActivity().getSharedPreferences("LOGGED_IN", Context.MODE_PRIVATE)
//        //shared pref to save log in history
//        val sharedPref = sharedpref.edit()
//        sharedPref.putString("token", currentUser.token)
//        sharedPref.putString("fullName", currentUser.user.fullName)
//        sharedPref.putString("userName", currentUser.user.username)
//        sharedPref.putString("phoneNumber", currentUser.user.phoneNumber)
//        sharedPref.putString("emailUser", currentUser.user.email)
//        sharedPref.putInt("idUser", currentUser.user.id)
//        sharedPref.apply()
//
//        findNavController().navigate(R.id.action_login2_to_home2)
//    }
//?