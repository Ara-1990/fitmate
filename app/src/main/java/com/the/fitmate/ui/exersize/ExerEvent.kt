package com.the.fitmate.ui.exersize

sealed class ExerEvent {
    object LoginRequired : ExerEvent()
    object Saved : ExerEvent()

}