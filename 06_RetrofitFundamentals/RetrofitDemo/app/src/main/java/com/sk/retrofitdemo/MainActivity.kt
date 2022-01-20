package com.sk.retrofitdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import com.sk.retrofitdemo.data.Albums
import com.sk.retrofitdemo.data.AlbumsItem
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var retService: AlbumService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        retService = RetrofitInstance.getRetrofitInstance().create(AlbumService::class.java)
        getRequestWithPathParameters()
        getRequestWithQueryParameters()
        uploadAlbum()
    }

    private fun getRequestWithPathParameters(){

        //path parameter example
        val pathResponse: LiveData<Response<AlbumsItem>> = liveData {
            val response = retService.getAlbum(3)
            emit(response)
        }

        pathResponse.observe(this, Observer {
            val title = it.body()?.title
            Toast.makeText(this, title, Toast.LENGTH_LONG).show()
        })

    }

    private fun getRequestWithQueryParameters(){
        // Query example
        val responseLiveData: LiveData<Response<Albums>> = liveData {
            val response = retService.getSortedAlbums(2)
            emit(response)
        }

        responseLiveData.observe(this, {
            val albumsList = it.body()?.listIterator()
            if (albumsList != null) {
                while (albumsList.hasNext()) {
                    val albumsItem = albumsList.next()
                    val result = " " + "Album id: ${albumsItem.id}" + "\n" +
                            " " + "Album Title: ${albumsItem.title}" + "\n" +
                            " " + "User id: ${albumsItem.userId}" + "\n\n\n"
                    findViewById<TextView>(R.id.text_view).append(result)
                }
            }
        })
    }

    private fun uploadAlbum(){
        val albumsItem = AlbumsItem(0, "New Album", 3)
        val postResponse: LiveData<Response<AlbumsItem>> = liveData {
            val response = retService.uploadAlbum(albumsItem)
            emit(response)
        }
        postResponse.observe(this, Observer {
            val receivedAlbumsItem = it.body()
            val result = " " + "Album id: ${receivedAlbumsItem?.id}" + "\n" +
                    " " + "Album Title: ${receivedAlbumsItem?.title}" + "\n" +
                    " " + "User id: ${receivedAlbumsItem?.userId}" + "\n\n\n"
            findViewById<TextView>(R.id.text_view).append(result)
        })
    }
}