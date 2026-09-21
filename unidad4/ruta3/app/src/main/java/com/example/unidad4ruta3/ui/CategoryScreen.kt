package com.example.unidad4ruta3.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.unidad4ruta3.R
import com.example.unidad4ruta3.data.local.Datasource
import com.example.unidad4ruta3.data.model.Recommendation
import com.example.unidad4ruta3.ui.theme.Unidad4Ruta3Theme

@Composable
fun CategoryScreen(
    modifier: Modifier = Modifier,
    recommendations: List<Recommendation> = Datasource.recommendations,
    onItemClicked: (Recommendation) -> Unit = {}
) {
    LazyColumn(
        modifier = modifier.padding(6.dp)
    ) {
        items(recommendations) {
            RecommendationListItem(recommendation = it, onItemClicked = onItemClicked)
        }
    }
}

@Composable
fun RecommendationListItem(
    modifier: Modifier = Modifier,
    recommendation: Recommendation = Datasource.recommendations.first(),
    onItemClicked: (Recommendation) -> Unit = {}
) {
    Card(
        onClick = { onItemClicked(recommendation) },
        modifier = modifier
            .padding(2.dp)
            .fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(12.dp)
        ) {
            Icon(
                imageVector = recommendation.image,
                contentDescription = null,
                modifier = Modifier
                    .size(56.dp)
                    .padding(8.dp),
                tint = MaterialTheme.colorScheme.primary
            )
            Column(
                modifier = Modifier.padding(start = 12.dp)
            ) {
                Text(
                    text = stringResource(id = recommendation.title),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.bodyLarge
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = stringResource(id = recommendation.description),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CategoryScreenPreview() {
    Unidad4Ruta3Theme {
        CategoryScreen()
    }
}
