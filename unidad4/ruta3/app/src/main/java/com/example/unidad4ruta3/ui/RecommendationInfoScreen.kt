package com.example.unidad4ruta3.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.unidad4ruta3.R
import com.example.unidad4ruta3.data.local.Datasource
import com.example.unidad4ruta3.data.model.Recommendation
import com.example.unidad4ruta3.ui.theme.Unidad4Ruta3Theme

@Composable
fun RecommendationInfoScreen(
    modifier: Modifier = Modifier,
    contentType: ContentType = ContentType.LIST,
    recommendation: Recommendation? = Datasource.recommendations.first(),
    onNavigateUp: () -> Unit = {}
) {
    if (contentType == ContentType.LIST) {
        BackHandler {
            onNavigateUp()
        }
    }
    recommendation?.let {
        Column(
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .background(MaterialTheme.colorScheme.primaryContainer),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = it.image,
                    contentDescription = stringResource(id = it.title),
                    modifier = Modifier.size(120.dp),
                    tint = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
            Column(modifier = Modifier.padding(vertical = 16.dp)) {
                Text(
                    text = stringResource(id = it.title),
                    modifier = Modifier.padding(horizontal = 16.dp),
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.headlineMedium
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = stringResource(id = it.description),
                    modifier = Modifier.padding(horizontal = 16.dp),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RecommendationInfoScreenPreview() {
    Unidad4Ruta3Theme {
        RecommendationInfoScreen()
    }
}
