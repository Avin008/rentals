package com.example.rentals.ui.screens.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Help
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.rounded.ChevronRight
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.rentals.R

sealed interface ProfileEvent {
    object NavigateBack : ProfileEvent
    object OpenSettings : ProfileEvent
    object OpenSubscription : ProfileEvent
    object OpenBusinessProfile : ProfileEvent
    object OpenPaymentMethods : ProfileEvent
    object OpenRentalHistory : ProfileEvent
    object OpenHelpSupport : ProfileEvent
    object SignOut : ProfileEvent
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Profile(
    onEvent: (ProfileEvent) -> Unit = {}
) {
        Column(modifier = Modifier.fillMaxSize()) {
            ProfileTopBar(onNavigateBack = { onEvent(ProfileEvent.NavigateBack) })
            
            ProfileContent(onEvent = onEvent)
        }
    }

@Composable
private fun ProfileContent(
    onEvent: (ProfileEvent) -> Unit
) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        BusinessProfile()
        SubscriptionCard(onUpgradeClick = { onEvent(ProfileEvent.OpenSubscription) })
        ProfileNavigationItems(onEvent = onEvent)
        
        Spacer(modifier = Modifier.height(8.dp))
        
        Button(
            onClick = { onEvent(ProfileEvent.SignOut) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.errorContainer,
                contentColor = MaterialTheme.colorScheme.onErrorContainer
            )
        ) {
            Icon(
                Icons.AutoMirrored.Filled.Logout,
                contentDescription = null,
                modifier = Modifier.size(18.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Sign Out",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Medium
            )
        }
        
        Spacer(modifier = Modifier.height(24.dp))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ProfileTopBar(onNavigateBack: () -> Unit) {
    TopAppBar(
        windowInsets = WindowInsets(top = 10.dp),
        title = { Text("Account", fontWeight = FontWeight.Medium) },
        navigationIcon = {
            IconButton(
                onClick = onNavigateBack,
                modifier = Modifier.semantics { contentDescription = "Navigate back" }
            ) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
            }
        }
    )
}

@Composable
private fun BusinessProfile() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Surface(
            modifier = Modifier.size(100.dp),
            shape = CircleShape,
            color = MaterialTheme.colorScheme.primaryContainer
        ) {
            Image(modifier = Modifier.size(30.dp).clip(CircleShape), painter = painterResource(id = R.drawable.profile), contentDescription = null)
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Rental Business Inc.",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "contact@rentalbusiness.com",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
        )
    }
}

@Composable
private fun SubscriptionCard(onUpgradeClick: () -> Unit) {
    ElevatedCard(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.elevatedCardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        "Premium Plan",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        "Valid until Dec 2024",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.7f)
                    )
                }
                FilledTonalButton(
                    onClick = onUpgradeClick,
                    modifier = Modifier.semantics { contentDescription = "Upgrade subscription" }
                ) {
                    Text("Upgrade")
                }
            }
        }
    }
}

@Composable
private fun ProfileNavigationItems(
    onEvent: (ProfileEvent) -> Unit
) {
    Column {
        ProfileNavigationItem(
            icon = Icons.Default.Business,
            title = "My Business Profile",
            subtitle = "Manage your business details",
            onClick = { onEvent(ProfileEvent.OpenBusinessProfile) }
        )
        
        NavigationDivider()
        
        ProfileNavigationItem(
            icon = Icons.Default.Subscriptions,
            title = "Subscription",
            subtitle = "Manage your current plan",
            onClick = { onEvent(ProfileEvent.OpenSubscription) }
        )
        
        NavigationDivider()
        
        ProfileNavigationItem(
            icon = Icons.Default.Settings,
            title = "App Settings",
            subtitle = "Manage notifications, theme, and language",
            onClick = { onEvent(ProfileEvent.OpenSettings) }
        )
        NavigationDivider()
        
        ProfileNavigationItem(
            icon = Icons.AutoMirrored.Filled.Help,
            title = "Help & Support",
            subtitle = "Get help and contact support",
            onClick = { onEvent(ProfileEvent.OpenHelpSupport) }
        )
    }
}

@Composable
private fun NavigationDivider() {
    HorizontalDivider(
        modifier = Modifier.padding(vertical = 8.dp),
        color = MaterialTheme.colorScheme.outlineVariant
    )
}

@Composable
private fun ProfileNavigationItem(
    icon: ImageVector,
    title: String,
    subtitle: String,
    onClick: () -> Unit,
    tint: Color = MaterialTheme.colorScheme.primary
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) { onClick() }
            .semantics { contentDescription = "Navigate to $title" }
    ) {
        Row(
            modifier = Modifier.padding(vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = tint,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Medium,
                    color = if (tint == MaterialTheme.colorScheme.error) tint else MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                )
            }
            if (tint != MaterialTheme.colorScheme.error) {
                Icon(
                    imageVector = Icons.Rounded.ChevronRight,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                )
            }
        }
    }
}