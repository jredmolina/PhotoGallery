package com.bignerdranch.android.photogallery

import android.app.Application
import androidx.constraintlayout.widget.ConstraintSet
import androidx.lifecycle.*

class PhotoGalleryViewModel(private val app: Application): AndroidViewModel(app) {
    val galleryItemLiveData: LiveData<List<GalleryItem>>

    private val flickrFetchr = FlickrFetchr()
    private val mutableSearchTerm = MutableLiveData<String>()


    init {
        mutableSearchTerm.value = QueryPreferences.getStoredQuery(app)

        galleryItemLiveData = Transformations.switchMap(mutableSearchTerm){
            searchTerm ->
            if(searchTerm.isBlank()){
                flickrFetchr.fetchPhotos()
            } else{
                flickrFetchr.searchPhotos(searchTerm)
            }

        }
    }

    fun fetchPhotos(query: String =""){
        QueryPreferences.setStoredQuery(app, query)
        mutableSearchTerm.value = query
    }
}