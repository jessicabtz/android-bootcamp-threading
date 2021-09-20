package com.everis.bootcamp.threading

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_load_data.setOnClickListener {
            launchAstrosTask()
        }
    }

    fun showData(list: List<AstroPeople>){
        tv_data.text = ""
        list?.forEach{people ->
            tv_data.append("${people.name} - ${people.craft} \n\n")
        }
    }

    fun showProgressBar(){
        pb_load_indicator.visibility = View.VISIBLE

    }

    fun hideProgressBar(){
        pb_load_indicator.visibility = View.GONE

    }

    fun launchAstrosTask(){
        val task = TaskAtros()
        task.execute()
    }

    inner class TaskAtros(): AsyncTask<Void, Int, List<AstroPeople>>(){
        val repository =  AstrosRepository()

        override fun onPreExecute() {
            super.onPreExecute()
            showProgressBar()
        }

        override fun doInBackground(vararg p0: Void?): List<AstroPeople> {
            return repository.loadData()
        }

        override fun onPostExecute(result: List<AstroPeople>?) {
            super.onPostExecute(result)
            hideProgressBar()
            if (result != null) {
                showData(result)
            }
        }
    }

}
