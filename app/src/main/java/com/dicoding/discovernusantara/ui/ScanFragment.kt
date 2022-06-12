package com.dicoding.discovernusantara.ui

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import com.dicoding.discovernusantara.databinding.FragmentScanBinding
import com.dicoding.discovernusantara.helper.reduceFileImage
import com.dicoding.discovernusantara.helper.rotateBitmap
import com.dicoding.discovernusantara.helper.uriToFile
import com.dicoding.discovernusantara.ui.viewmodels.SitesViewModel
import com.dicoding.discovernusantara.ui.viewmodels.ViewModelFactory
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class ScanFragment : Fragment() {
    private var _binding: FragmentScanBinding? = null
    private val binding get() = _binding!!

    private lateinit var factory: ViewModelFactory
    private val viewModel: SitesViewModel by viewModels { factory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentScanBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        factory = ViewModelFactory.getInstance(requireActivity())

        binding.btnTakePhoto.setOnClickListener { startCameraX() }
        binding.btnGallery.setOnClickListener { openGallery() }
        binding.btnSearch.setOnClickListener { uploadPhoto() }
    }

    private fun startCameraX() {
        val intent = Intent(activity, CameraActivity::class.java)
        launcherIntentCameraX.launch(intent)
    }

    private fun openGallery() {
        val intent = Intent()
        intent.action = Intent.ACTION_GET_CONTENT
        intent.type = "image/*"
        val chooser = Intent.createChooser(intent, "Choose a Picture")
        launcherIntentGallery.launch(chooser)
    }

    private fun uploadPhoto() {
        when (getFile) {
            null -> Toast.makeText(context, "Please input the picture", Toast.LENGTH_SHORT).show()
            else -> {
                val file = reduceFileImage(getFile as File)
                val requestImageFile = file.asRequestBody("image/jpeg".toMediaTypeOrNull())
                val imageMultipart: MultipartBody.Part = MultipartBody.Part.createFormData("photo", file.name, requestImageFile)
                viewModel.getPrediction(imageMultipart)
                viewModel.isLoading.observe(viewLifecycleOwner) {
                    if (it) binding.progressBarScan.visibility = View.VISIBLE
                    else binding.progressBarScan.visibility = View.GONE
                }
                viewModel.prediction.observe(viewLifecycleOwner) {
                    if (it != null) {
                        if (it.prediction != "") {
                            val intent = Intent(activity, ResultActivity::class.java)
                            intent.putExtra(ResultActivity.EXTRA_SITES, it.prediction)
                            activity?.startActivity(intent)
                        } else {
                            Toast.makeText(context, "Ada yang salah, silahkan kirim ulang", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }

    private var getFile: File? = null
    private val launcherIntentCameraX = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == CAMERA_X_RESULT) {
            val myFile = it.data?.getSerializableExtra("image") as File
            val isBackCamera = it.data?.getBooleanExtra("isBackCamera", true) as Boolean

            getFile = myFile
            val result = rotateBitmap(BitmapFactory.decodeFile(myFile.path), isBackCamera)
            binding.imgResult.setImageBitmap(result)
        }
    }

    private val launcherIntentGallery = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == RESULT_OK) {
            val selectedImg: Uri = it.data?.data as Uri
            val myFile = context?.let { context -> uriToFile(selectedImg, context) }
            getFile = myFile
            binding.imgResult.setImageURI(selectedImg)
        }
    }

    companion object {
        const val CAMERA_X_RESULT = 200
    }
}