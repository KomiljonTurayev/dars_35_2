package com.example.dars_35

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dars_35.api.App
import com.example.dars_35. databinding.ActivityGalleryBinding
import com.example.dars_35.model.PhotoModel
import com.example.dars_35.view.ImageAdapter
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

@AndroidEntryPoint
class GalleryActivity : AppCompatActivity() {
    lateinit var binding: ActivityGalleryBinding

    @Inject
    lateinit var api: App

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGalleryBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        MyApp.app.appComponent.inject(this)
        binding.recycler.layoutManager = GridLayoutManager(this, 2)

//        val retrofit = Retrofit.Builder()
//            .baseUrl("https://jsonplaceholder.typicode.com/")
//            .client(OkHttpClient.Builder().build())
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//
//        val api = retrofit.create(App::class.java)

        api.getPhotos().enqueue(object : Callback<List<PhotoModel>> {
            override fun onResponse(
                call: Call<List<PhotoModel>>,
                response: Response<List<PhotoModel>>
            ) {
                Toast.makeText(this@GalleryActivity, response.body().toString(), Toast.LENGTH_SHORT)
                    .show()
                binding.recycler.adapter = ImageAdapter(response.body() ?: emptyList())
            }

            override fun onFailure(call: Call<List<PhotoModel>>, t: Throwable) {
                Toast.makeText(this@GalleryActivity, t.localizedMessage, Toast.LENGTH_SHORT).show()
            }

        })

    }
}