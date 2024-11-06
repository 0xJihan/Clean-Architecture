package com.jihan.cleanarchitecture.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.ImageLoader
import coil3.compose.SubcomposeAsyncImage
import coil3.request.crossfade
import com.jihan.cleanarchitecture.common.SearchView
import com.jihan.cleanarchitecture.domain.model.ImageModel


@Composable
fun SearchImageScreen(
    viewmodel: ImageViewmodel = hiltViewModel(),
    onItemClick: (ImageModel) -> Unit,
) {

    val result = viewmodel.imageState.value

    if (result.isLoading) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    }

    if (result.error.isNullOrBlank().not()) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = result.error.toString())
        }
    }

    Column {

        Spacer(Modifier.height(10.dp))
        SearchView(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 10.dp),
            corner = 10,
            placeholder = "Search Images...."
        ) {
            viewmodel.updateQuery(it)
        }

        result.images.let {

            LazyColumn(Modifier.fillMaxWidth()) {
                items(it) {

                    Card(
                        onClick = {
                            onItemClick(it)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(280.dp)
                            .padding(horizontal = 10.dp, vertical = 5.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White,
                            contentColor = Color.Black,
                        ),
                        elevation = CardDefaults.cardElevation(8.dp)
                    ) {
                        SubcomposeAsyncImage(model = it.imageUrl,
                            contentDescription = it.description,
                            modifier = Modifier.fillMaxSize(),
                            imageLoader = ImageLoader(LocalContext.current).newBuilder()
                                .crossfade(true).build(),
                            loading = {
                                Box(Modifier.fillMaxHeight(), contentAlignment = Alignment.Center) {
                                    CircularProgressIndicator()
                                }
                            })
                    }
                }
            }
        }
    }

}