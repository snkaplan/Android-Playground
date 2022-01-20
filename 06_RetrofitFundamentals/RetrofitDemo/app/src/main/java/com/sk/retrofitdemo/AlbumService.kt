package com.sk.retrofitdemo

import com.sk.retrofitdemo.data.Albums
import com.sk.retrofitdemo.data.AlbumsItem
import retrofit2.Response
import retrofit2.http.*


interface AlbumService {
    @GET(value = "/albums")
    suspend fun getAlbums(): Response<Albums>

    @GET(value = "/albums")
    suspend fun getSortedAlbums(@Query("userId") userId: Int): Response<Albums>

    @GET(value = "/albums/{id}")
    suspend fun getAlbum(@Path(value = "id") albumId: Int): Response<AlbumsItem>

    @POST(value = "/albums")
    suspend fun uploadAlbum(@Body album: AlbumsItem): Response<AlbumsItem>
}