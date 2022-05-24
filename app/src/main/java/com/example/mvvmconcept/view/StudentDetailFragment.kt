package com.example.mvvmconcept.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmconcept.R
import com.example.mvvmconcept.databinding.FragmentStudentDetailBinding
import com.example.mvvmconcept.model.Student
import com.example.mvvmconcept.util.loadImage
import com.example.mvvmconcept.viewmodel.DetailViewModel
import kotlinx.android.synthetic.main.fragment_student_detail.*
import kotlinx.android.synthetic.main.student_list_item.*

class StudentDetailFragment : Fragment() {
    private lateinit var viewModel: DetailViewModel

    private lateinit var detailBinding: FragmentStudentDetailBinding
    private val binding get() = detailBinding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        // return inflater.inflate(R.layout.fragment_student_detail, container, false)
        detailBinding = FragmentStudentDetailBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var studentId : String? = "detail"
        arguments?.let {
            studentId = StudentDetailFragmentArgs.fromBundle(requireArguments()).studentID
        }

        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        viewModel.fetch(studentId)

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.studentsLD.observe(viewLifecycleOwner) {
            detailBinding.studentdetail = viewModel.studentsLD.value
            /* val student = viewModel.studentsLD.value
            student?.let {
                imgStudentDetail.loadImage(it.photoURL, progressLoadingPhotoStudentDetail)
                editID.setText(it.id)
                editName.setText(it.name)
                editDOB.setText(it.dob)
                editPhone.setText(it.phone)
            } */
        }
    }

}