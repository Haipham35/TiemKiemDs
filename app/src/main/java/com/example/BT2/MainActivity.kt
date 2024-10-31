package com.example.bai3

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView



class MainActivity : AppCompatActivity() {
    private lateinit var studentAdapter: StudentAdapter
    private lateinit var studentsList: List<Student>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        studentsList = listOf(
            Student("Phạm Thanh Hải", "21101015"),
            Student("Trần Văn Hùng", "20211234"),
            Student("Phùng Tùng Anh", "20203455"),
            Student("NGuyễn Văn A", "20197890"),
            Student("Bùi Văn C", "20234321")
        )
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewStudents)
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        studentAdapter = StudentAdapter(studentsList)
        recyclerView.adapter = studentAdapter

        val editTextSearch = findViewById<android.widget.EditText>(R.id.editTextSearch)
        editTextSearch.addTextChangedListener(object : android.text.TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
            // Tìm kiếm
            override fun afterTextChanged(s: android.text.Editable?) {
                val keyword = s.toString()
                if (keyword.length > 2) {
                    val filteredList = studentsList.filter { student ->
                        student.name.contains(keyword, ignoreCase = true) ||
                                student.mssv.contains(keyword)
                    }
                    studentAdapter.updateList(filteredList)
                } else {
                    studentAdapter.updateList(studentsList)
                }
            }
        })
    }
}