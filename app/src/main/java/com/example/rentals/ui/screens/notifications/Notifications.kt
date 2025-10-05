package com.example.rentals.ui.screens.notifications

import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

private data class NotificationGroup(
    val title: String,
    val notifications: List<Notification>
)

data class Notification(
    val id: String,
    val title: String,
    val message: String,
    val timestamp: String,
    val isRead: Boolean = false,
    val type: NotificationType = NotificationType.INFO
)

enum class NotificationType {
    INFO, WARNING, SUCCESS, ERROR
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationsScreen() {
    val notifications = remember {
        mutableStateOf(categorizeNotifications(getSampleNotifications()))
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopAppBar(
            windowInsets = WindowInsets(top = 10.dp),
            title = { Text("Notifications") },
            actions = {
                IconButton(onClick = { /* TODO */ }) {
                    Icon(
                        imageVector = Icons.Default.CheckCircle,
                        contentDescription = "Mark all as read",
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            notifications.value.forEach { group ->
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 12.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = group.title,
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Text(
                            text = "${group.notifications.size} notifications",
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
                items(group.notifications) { notification ->
                    NotificationItem(notification = notification)
                }
            }
        }
    }
}

private fun categorizeNotifications(notifications: List<Notification>): List<NotificationGroup> {
    // In a real app, you would parse the actual timestamps
    // This is a simplified version using the timestamp strings
    return listOf(
        NotificationGroup(
            "Today",
            notifications.filter { it.timestamp.contains("minutes ago") || it.timestamp.contains("hour") }
        ),
        NotificationGroup(
            "Yesterday",
            notifications.filter { it.timestamp.contains("day ago") }
        ),
        NotificationGroup(
            "Older",
            notifications.filter {
                !it.timestamp.contains("minutes ago") &&
                !it.timestamp.contains("hour") &&
                !it.timestamp.contains("day ago")
            }
        )
    ).filter { it.notifications.isNotEmpty() }
}

@Composable
private fun NotificationItem(notification: Notification) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp,
            pressedElevation = 4.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = if (!notification.isRead)
                MaterialTheme.colorScheme.surfaceVariant
            else MaterialTheme.colorScheme.surface
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = when (notification.type) {
                    NotificationType.INFO -> Icons.Default.Info
                    NotificationType.WARNING -> Icons.Default.Warning
                    NotificationType.ERROR -> Icons.Default.Error
                    NotificationType.SUCCESS -> Icons.Default.CheckCircle
                },
                contentDescription = null,
                tint = when (notification.type) {
                    NotificationType.INFO -> MaterialTheme.colorScheme.primary
                    NotificationType.WARNING -> Color(0xFFFFA000)
                    NotificationType.ERROR -> MaterialTheme.colorScheme.error
                    NotificationType.SUCCESS -> Color(0xFF4CAF50)
                },
                modifier = Modifier.size(24.dp)
            )

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = notification.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = notification.message,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = notification.timestamp,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

private fun getSampleNotifications(): List<Notification> = listOf(
    Notification(
        id = "1",
        title = "New Rental Request",
        message = "You have received a new rental request for your property",
        timestamp = "2 minutes ago",
        isRead = false,
        type = NotificationType.INFO
    ),
    Notification(
        id = "2",
        title = "Payment Successful",
        message = "Your payment has been processed successfully",
        timestamp = "1 day ago",
        isRead = true,
        type = NotificationType.SUCCESS
    ),
    Notification(
        id = "3",
        title = "Maintenance Required",
        message = "Scheduled maintenance is due for your property",
        timestamp = "3 days ago",
        isRead = true,
        type = NotificationType.WARNING
    ),
    Notification(
        id = "4",
        title = "Rent Due Reminder",
        message = "Rent payment is due in 3 days",
        timestamp = "5 hours ago",
        isRead = true,
        type = NotificationType.INFO
    ),
    Notification(
        id = "5",
        title = "Property Inspection",
        message = "Scheduled inspection completed successfully",
        timestamp = "2 days ago",
        isRead = true,
        type = NotificationType.SUCCESS
    ),
    Notification(
        id = "6",
        title = "Security Alert",
        message = "Unusual activity detected at your property",
        timestamp = "30 minutes ago",
        isRead = true,
        type = NotificationType.ERROR
    ),
    Notification(
        id = "7",
        title = "Lease Renewal",
        message = "Your tenant has requested a lease renewal",
        timestamp = "3 hours ago",
        isRead = true,
        type = NotificationType.INFO
    ),
    Notification(
        id = "8",
        title = "Maintenance Update",
        message = "Plumbing repair has been completed",
        timestamp = "4 days ago",
        isRead = true,
        type = NotificationType.SUCCESS
    ),
    Notification(
        id = "9",
        title = "Payment Overdue",
        message = "Tenant payment is overdue by 2 days",
        timestamp = "1 hour ago",
        isRead = true,
        type = NotificationType.WARNING
    )
)
