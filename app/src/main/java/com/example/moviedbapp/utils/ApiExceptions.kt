package com.example.moviedbapp

import java.io.IOException
import java.lang.Exception

class ApiExceptions(errorMessage: String): Exception(errorMessage)

class NoInternetException(message: String): IOException(message)