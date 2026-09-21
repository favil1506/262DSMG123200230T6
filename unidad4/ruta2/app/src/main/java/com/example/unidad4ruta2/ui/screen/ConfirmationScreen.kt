package com.example.unidad4ruta2.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.unidad4ruta2.R
import com.example.unidad4ruta2.model.MenuItem
import com.example.unidad4ruta2.model.OrderUiState
import java.text.NumberFormat

@Composable
fun ConfirmationScreen(
    orderUiState: OrderUiState,
    onNextButtonClicked: () -> Unit,
    onCancelButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    val priceFormat = NumberFormat.getCurrencyInstance()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(
                text = stringResource(R.string.order_summary),
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(16.dp))

            orderUiState.entree?.let { item ->
                OrderSummaryItem(
                    name = item.name,
                    price = priceFormat.format(item.price)
                )
            }

            orderUiState.sideDish?.let { item ->
                OrderSummaryItem(
                    name = item.name,
                    price = priceFormat.format(item.price)
                )
            }

            orderUiState.accompaniment?.let { item ->
                OrderSummaryItem(
                    name = item.name,
                    price = priceFormat.format(item.price)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))
            HorizontalDivider()
            Spacer(modifier = Modifier.height(8.dp))

            OrderSummaryItem(
                name = stringResource(R.string.subtotal),
                price = priceFormat.format(orderUiState.itemTotalPrice)
            )

            OrderSummaryItem(
                name = stringResource(R.string.tax),
                price = priceFormat.format(orderUiState.orderTax)
            )

            Spacer(modifier = Modifier.height(8.dp))
            HorizontalDivider()
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = stringResource(R.string.total, priceFormat.format(orderUiState.orderTotalPrice)),
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedButton(
                onClick = onCancelButtonClicked,
                modifier = Modifier.weight(1f)
            ) {
                Text(stringResource(R.string.cancel))
            }
            Button(
                onClick = onNextButtonClicked,
                modifier = Modifier.weight(1f)
            ) {
                Text(stringResource(R.string.submit))
            }
        }
    }
}

@Composable
private fun OrderSummaryItem(
    name: String,
    price: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = name)
        Text(text = price)
    }
}