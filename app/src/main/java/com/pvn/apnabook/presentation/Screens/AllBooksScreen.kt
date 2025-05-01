package com.pvn.apnabook.presentation.Screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.pvn.apnabook.presentation.Effects.ShimmerEffect
import com.pvn.apnabook.presentation.UIcomponent.BookCart
import com.pvn.apnabook.presentation.ViewModel

@Composable
fun AllBooksScreen(
    navHostController: NavHostController,
    viewModel: ViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.bringAllBooks()
    }

    val res = viewModel.state.value
     when{
         res.isLoading -> {
             Column(
                 modifier = Modifier.fillMaxSize()
             ) {
                 LazyColumn {
                     items(10){
                         ShimmerEffect()
                     }
                 }

             }
         }

         res.error.isNotEmpty() -> {
             Text(text = res.error, modifier = Modifier)
         }

         res.items.isNotEmpty() -> {
             Column(
                 modifier = Modifier.fillMaxSize()
             ) {
                 LazyColumn(
                     modifier = Modifier.fillMaxSize()
                 ) {
                     items(res.items){
                         BookCart(
                             imageUrl = it.bookImage,
                             title = it.bookName,
                             author = it.bookAuthor,
                             description = it.bookDescription,
                             bookUrl = it.bookUrl,
                             navHostController = navHostController
                         )
                     }
                 }
             }
         } else -> {
             Text(text = "Something went wrong", modifier = Modifier)
         }
     }
}

