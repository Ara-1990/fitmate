package com.the.fitmate.ui.article

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text

import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import com.the.fitmate.domain.SampleData


@Composable
fun ArticlesScreen(navController: NavController){
    val articles = SampleData.articles

    androidx.compose.foundation.lazy.LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(12.dp)
    ) {
        item {
            Text(
                text = "Articles",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }

        items(articles.size) { index ->
            val article = articles[index]
            ArticleCard(
                title = article.title,
                summary = article.summary,
                onClick = {
                    navController.navigate("article/${article.id}")
                }
            )
        }
    }

}

@Composable
fun ArticleCard(title: String, summary: String, onClick: () -> Unit) {
    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(title, style = MaterialTheme.typography.titleMedium, maxLines = 2)
            Spacer(modifier = Modifier.height(6.dp))
            Text(summary, style = MaterialTheme.typography.bodyMedium, maxLines = 3)
        }
    }
}