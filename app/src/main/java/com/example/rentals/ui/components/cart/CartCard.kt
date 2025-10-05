package com.example.rentals.ui.components.cart

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.rentals.R
import com.example.rentals.data.model.RentalItem
import java.text.NumberFormat
import java.util.Locale

// Object to hold default values and constants for the CartCard
private object CartCardDefaults {
    const val MAX_QUANTITY = 10
    val cardShape = RoundedCornerShape(12.dp)
    val imageShape = RoundedCornerShape(8.dp)
    val cardElevation = 4.dp
    val imageSize = 80.dp
    val iconButtonSize = 40.dp
}

@Composable
fun CartCard(
    item: RentalItem,
    quantity: Int,
    durationDays: Int,
    onIncreaseQuantity: () -> Unit,
    onDecreaseQuantity: () -> Unit,
    onRemoveItem: () -> Unit,
    modifier: Modifier = Modifier
) {
    // Remember the formatter to avoid recreating it on every recomposition
    val currencyFormatter = remember {
        NumberFormat.getCurrencyInstance(Locale("en", "IN"))
    }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = CartCardDefaults.cardShape,
        elevation = CardDefaults.cardElevation(defaultElevation = CartCardDefaults.cardElevation),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Item Image
            Surface(
                modifier = Modifier
                    .size(CartCardDefaults.imageSize)
                    .clip(CartCardDefaults.imageShape),
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_foreground),
                    contentDescription = "Image of ${item.name}", // Hardcoded content description
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Item Details
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = item.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = currencyFormatter.format(item.price) + " / day",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = currencyFormatter.format(item.price * quantity * durationDays),
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            // Quantity Controls and Remove Button
            Column(horizontalAlignment = Alignment.End) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    // Decrease quantity button
                    IconButton(
                        onClick = onDecreaseQuantity,
                        enabled = quantity > 1,
                        modifier = Modifier.size(CartCardDefaults.iconButtonSize)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Remove,
                            contentDescription = "Decrease quantity", // Hardcoded content description
                            tint = if (quantity > 1) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f)
                        )
                    }

                    Text(
                        text = quantity.toString(),
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.width(28.dp),
                        textAlign = TextAlign.Center
                    )

                    // Increase quantity button
                    IconButton(
                        onClick = onIncreaseQuantity,
                        enabled = quantity < CartCardDefaults.MAX_QUANTITY,
                        modifier = Modifier.size(CartCardDefaults.iconButtonSize)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Increase quantity", // Hardcoded content description
                            tint = if (quantity < CartCardDefaults.MAX_QUANTITY) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Remove item button
                IconButton(
                    onClick = onRemoveItem,
                    modifier = Modifier.size(CartCardDefaults.iconButtonSize)
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Remove item", // Hardcoded content description
                        tint = MaterialTheme.colorScheme.error
                    )
                }
            }
        }
    }
}