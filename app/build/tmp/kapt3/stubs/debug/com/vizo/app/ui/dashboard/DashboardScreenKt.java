package com.vizo.app.ui.dashboard;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000H\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a&\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00010\u0007H\u0007\u001a.\u0010\b\u001a\u00020\u00012\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\n2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00010\u0007H\u0007\u001a\u001a\u0010\u000e\u001a\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u0012H\u0007\u001a0\u0010\u0013\u001a\u00020\u00012\b\b\u0002\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00172\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00010\u0007H\u0007\u001a\u0010\u0010\u001a\u001a\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u0010H\u0007\u001a*\u0010\u001b\u001a\u00020\u00012\u0006\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u001c\u001a\u00020\u00172\u0006\u0010\u001d\u001a\u00020\u001eH\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u001f\u0010 \u001a\u001e\u0010!\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00052\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00010\u0007H\u0007\u001a\u0010\u0010#\u001a\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u0010H\u0007\u0082\u0002\u0007\n\u0005\b\u00a1\u001e0\u0001\u00a8\u0006$"}, d2 = {"BoostCard", "", "hasBoost", "", "hoursRemaining", "", "onBuyBoost", "Lkotlin/Function0;", "ContactsCard", "total", "", "founders", "regular", "onViewAll", "DashboardScreen", "navController", "Landroidx/navigation/NavController;", "viewModel", "Lcom/vizo/app/ui/dashboard/DashboardViewModel;", "QuickActionButton", "modifier", "Landroidx/compose/ui/Modifier;", "icon", "", "label", "onClick", "QuickActions", "StatChip", "value", "color", "Landroidx/compose/ui/graphics/Color;", "StatChip-mxwnekA", "(Ljava/lang/String;Ljava/lang/String;J)V", "TrialCard", "onSubscribe", "VizoBottomBar", "app_debug"})
public final class DashboardScreenKt {
    
    @androidx.compose.runtime.Composable()
    public static final void DashboardScreen(@org.jetbrains.annotations.NotNull()
    androidx.navigation.NavController navController, @org.jetbrains.annotations.NotNull()
    com.vizo.app.ui.dashboard.DashboardViewModel viewModel) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void TrialCard(long hoursRemaining, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onSubscribe) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void ContactsCard(int total, int founders, int regular, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onViewAll) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void BoostCard(boolean hasBoost, long hoursRemaining, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onBuyBoost) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void QuickActions(@org.jetbrains.annotations.NotNull()
    androidx.navigation.NavController navController) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void QuickActionButton(@org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier, @org.jetbrains.annotations.NotNull()
    java.lang.String icon, @org.jetbrains.annotations.NotNull()
    java.lang.String label, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onClick) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void VizoBottomBar(@org.jetbrains.annotations.NotNull()
    androidx.navigation.NavController navController) {
    }
}