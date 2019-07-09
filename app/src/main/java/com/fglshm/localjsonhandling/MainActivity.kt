package com.fglshm.localjsonhandling

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.JsonParser
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray

class MainActivity : AppCompatActivity() {

    private val logTag = MainActivity::class.java.simpleName

    private var jsonText = ""

    private val mRecyclerView by lazy { recycler }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val `is` = assets.open("sample.json")
        `is`.bufferedReader().use {
            this.jsonText = it.readText()
        }

        val jsonArray = JSONArray(jsonText)

        val adapter = CountryListAdapter(this)

        mRecyclerView.adapter = adapter
        mRecyclerView.layoutManager = LinearLayoutManager(this)

        repeat(jsonArray.length()) {
            val item = jsonArray.getJSONObject(it)
            val jsonString = item.toString()
            val parser = JsonParser()
            val mJson = parser.parse(jsonString)
            val country: Country? = Gson().fromJson(mJson, Country::class.java)
            adapter.add(country)
        }
    }

    private fun showLog(message: Any?) {
        Log.d(logTag, message?.toString() ?: "no text")
    }

}