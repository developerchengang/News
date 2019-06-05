package com.doumiao.presentation.entities


data class Data<RequestData>(val responseType: Status,
                             val data: RequestData? = null,
                             val error: Error? = null)

enum class Status {
    SUCCESSFUL, ERROR, LOADING
}