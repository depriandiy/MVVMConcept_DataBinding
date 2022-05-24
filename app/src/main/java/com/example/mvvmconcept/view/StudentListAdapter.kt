package com.example.mvvmconcept.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmconcept.R
import com.example.mvvmconcept.databinding.StudentListItemBinding
import com.example.mvvmconcept.model.Student
import com.example.mvvmconcept.util.loadImage
import kotlinx.android.synthetic.main.student_list_item.view.*

class StudentListAdapter(val studentList: ArrayList<Student>) :
    RecyclerView.Adapter<StudentListAdapter.StudentViewHolder>(), ButtonDetailClickListener {
    class StudentViewHolder (var view: StudentListItemBinding) : RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val inflater = LayoutInflater.from(parent.context)
//        val view = StudentListItemBinding.inflate(inflater, parent, false)
        val view = DataBindingUtil.inflate<StudentListItemBinding>(inflater, R.layout.student_list_item, parent, false)
        return StudentViewHolder(view)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
//        val student = studentList[position]
        holder.view.student = studentList[position]
        holder.view.detailListener = this

        /*val studentId = student.id // student detail
        with(holder.view) {
            textID.text = student.id
            txtName.text = student.name
            btnDetail.setOnClickListener {
                val action = StudentListFragmentDirections.actionStudentDetail(studentId.toString()) // add param
                Navigation.findNavController(it).navigate(action)
            }
            imgStudentPhoto.loadImage(student.photoURL, progressLoadingStudentPhoto)
        }*/
    }

    override fun getItemCount() = studentList.size

    fun updateStudentList(newStudentList: ArrayList<Student>) {
        studentList.clear()
        studentList.addAll(newStudentList)
        notifyDataSetChanged()
    }

    override fun onDetailClick(view: View) {
        val action = StudentListFragmentDirections.actionStudentDetail(view.tag.toString())
        Navigation.findNavController(view).navigate(action)
    }
}