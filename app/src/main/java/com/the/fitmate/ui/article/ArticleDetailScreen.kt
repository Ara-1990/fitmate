package com.the.fitmate.ui.article

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.the.fitmate.domain.SampleData


@Composable
fun ArticleDetailScreen(backStackEntry: NavBackStackEntry, onBack: () -> Unit){

    val idArg = backStackEntry.arguments?.getString("id")
    val articleId = idArg?.toIntOrNull()
    val article = SampleData.articles.firstOrNull { it.id == articleId }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {

        if (article != null) {
            Text(text = article.title, style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(12.dp))
            Text(text = article.content, style = MaterialTheme.typography.bodyMedium)
        } else {
            Text("Article not found", style = MaterialTheme.typography.bodyMedium)
        }
    }
}