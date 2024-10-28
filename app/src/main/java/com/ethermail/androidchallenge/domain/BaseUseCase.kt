package com.ethermail.androidchallenge.domain

abstract class BaseUseCase<in Params, out T> {
    abstract fun execute(params: Params): T
}